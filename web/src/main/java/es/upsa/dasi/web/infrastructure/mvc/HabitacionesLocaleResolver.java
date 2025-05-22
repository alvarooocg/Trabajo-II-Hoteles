package es.upsa.dasi.web.infrastructure.mvc;

import jakarta.mvc.locale.LocaleResolver;
import jakarta.mvc.locale.LocaleResolverContext;
import jakarta.ws.rs.core.UriInfo;

import java.util.Locale;

public class HabitacionesLocaleResolver implements LocaleResolver
{
    @Override
    public Locale resolveLocale(LocaleResolverContext localeResolverContext) {
        UriInfo uriInfo = localeResolverContext.getUriInfo();
        String localeSegment = uriInfo.getPathSegments().getFirst().getPath();
        return Locale.forLanguageTag(localeSegment);
    }
}
