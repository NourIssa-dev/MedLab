package com.controller;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import com.facade.GuarantorFacade;
import com.facade.TestFacade;
import com.model.Guarantor;
import com.model.Test;

@FacesConverter(forClass = Test.class)
public class TestConverter implements Converter {

	@EJB
    private TestFacade testFacade;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && !value.isEmpty()) {
            try {
                // Convert the String value (guarID) to Guarantor object
                return testFacade.find(Integer.parseInt(value));
            } catch (NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid Test"));
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value instanceof Test) {
            // Convert the Guarantor object to its unique identifier (guarID)
            return String.valueOf(((Test) value).getId());
        }
        return null;
    }
}