package com.kyoborealco.matinfo.config;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.MarshallingMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.xstream.XStreamMarshaller;

import com.kyoborealco.common.model.RealcoMessage;
import com.kyoborealco.mro.mq.dto.MaterialInfoDTO;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.NoTypePermission;
import com.thoughtworks.xstream.security.NullPermission;
import com.thoughtworks.xstream.security.PrimitiveTypePermission;

@Configuration
public class RabbitMqConfig {

	private static final Logger log = LoggerFactory.getLogger(RabbitMqConfig.class);

	@Bean
	public MessageConverter jsonMessageConverter() {
		
		log.info("★★★★ xstreamMessageConverter() start!!");
		XStreamMarshaller xstreamMarshaller = new XStreamMarshaller();

		Map<String, Object> aliases = new HashMap<String, Object>();
		aliases.put("realcoMessage", RealcoMessage.class);
		xstreamMarshaller.setAliases(aliases);
		
		xstreamMarshaller.setMode(XStream.NO_REFERENCES);
		
		XStream xstream = xstreamMarshaller.getXStream();
		Class<?>[] classes = new Class[] { MaterialInfoDTO.class };

		// clear out existing permissions and set own ones
		xstream.addPermission(NoTypePermission.NONE);
		// allow some basics
		xstream.addPermission(NullPermission.NULL);
		xstream.addPermission(PrimitiveTypePermission.PRIMITIVES);
		xstream.allowTypes(classes);
		// allow any type from the same package
		xstream.allowTypesByWildcard(new String[] {
		    "com.kyoborealco.**"
		});

		// 
		final MarshallingMessageConverter xmlConverter = new MarshallingMessageConverter();
		xmlConverter.setMarshaller(xstreamMarshaller);
		xmlConverter.setUnmarshaller(xstreamMarshaller);
		
		return xmlConverter;
	}	

	@Bean
    public SimpleRabbitListenerContainerFactory jsaFactory(ConnectionFactory connectionFactory,
            SimpleRabbitListenerContainerFactoryConfigurer configurer) {
		log.info("★★★★ jsaFactory start()");
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        configurer.configure(factory, connectionFactory);
        factory.setMessageConverter(jsonMessageConverter());
        return factory;
    }	

}
