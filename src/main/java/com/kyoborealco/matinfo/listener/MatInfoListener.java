package com.kyoborealco.matinfo.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.kyoborealco.common.model.RealcoMessage;
import com.kyoborealco.mro.mq.dto.MaterialInfoDTO;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.NoTypePermission;
import com.thoughtworks.xstream.security.NullPermission;
import com.thoughtworks.xstream.security.PrimitiveTypePermission;

@Component
public class MatInfoListener {

	private static final Logger log = LoggerFactory.getLogger(MatInfoListener.class);
	
	@RabbitListener(queues="${jsa.rabbitmq.queue}", containerFactory="jsaFactory")
    public void recievedMessage(RealcoMessage rm) {
		log.info("★★★★ #1 rm.userid = {}", rm.getUserId());

		XStream xstream = new XStream();

		// Security framework of XStream not initialized, XStream is probably vulnerable.
		// 참고 : https://stackoverflow.com/questions/44698296/security-framework-of-xstream-not-initialized-xstream-is-probably-vulnerable
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
		log.info("★★★★ #2 rm.message = {}", rm.getMessage());
		MaterialInfoDTO dto = (MaterialInfoDTO) xstream.fromXML(rm.getMessage());
		
		log.info("◆◆◆◆ Recieved Message: image(base64) = {}", dto.getMatImage());
    }
}
