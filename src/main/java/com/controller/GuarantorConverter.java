package com.controller;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import com.facade.GuarantorFacade;
import com.model.Guarantor;

@FacesConverter(forClass = Guarantor.class)
public class GuarantorConverter implements Converter {

	@EJB
    private GuarantorFacade guarantorFacade;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && !value.isEmpty()) {
            try {
                // Convert the String value (guarID) to Guarantor object
                return guarantorFacade.find(Integer.parseInt(value));
            } catch (NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid Guarantor"));
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value instanceof Guarantor) {
            // Convert the Guarantor object to its unique identifier (guarID)
            return String.valueOf(((Guarantor) value).getGuarID());
        }
        return null;
    }
}