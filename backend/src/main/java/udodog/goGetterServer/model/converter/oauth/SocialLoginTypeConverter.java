package udodog.goGetterServer.model.converter.oauth;

import org.modelmapper.Converters;
import org.springframework.context.annotation.Configuration;
import udodog.goGetterServer.model.enumclass.oauth.SocialLoginType;

@Configuration
public class SocialLoginTypeConverter implements Converters.Converter<String, SocialLoginType> {
    @Override
    public SocialLoginType convert(String s) {
        return SocialLoginType.valueOf(s.toUpperCase());
    }
}
