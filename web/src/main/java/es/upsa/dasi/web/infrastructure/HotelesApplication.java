package es.upsa.dasi.web.infrastructure;

import jakarta.mvc.form.FormMethodOverwriter;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

import java.util.HashMap;
import java.util.Map;

@ApplicationPath("/web")
public class HotelesApplication extends Application
{
    @Override
    public Map<String, Object> getProperties()
    {
        Map<String, Object> properties = new HashMap<>();
        properties.put(FormMethodOverwriter.FORM_METHOD_OVERWRITE, FormMethodOverwriter.Options.ENABLED);

        return properties;
    }
}
