package com.ebay.eric.xstream_test;

import java.io.ObjectInputStream;
import java.io.StringReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.stubhub.app.integration.biz.entity.HoldRequest;
import com.stubhub.app.integration.biz.entity.PartnerOrderTicket;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class XmlToObjectTransformer {

	private XStream xstream;
	private Student bean = null;

	public XmlToObjectTransformer() {
		xstream = new XStream(new DomDriver());
		bean = new Student();
		bean.setAddress("china");
		bean.setEmail("jack@email.com");
		bean.setId(1);
		bean.setName("jack");
		Birthday day = new Birthday("2010-11-22");
		bean.setBirthday(day);
	}

	public void readXML4InputStream() {
		try {
			String s = "<object-stream>"
					+ "<com.ebay.eric.xstream_test.Student><id>0</id>"
					+ "<name>jack</name>"
					+ "</com.ebay.eric.xstream_test.Student>"
					+ "<com.ebay.eric.xstream_test.Birthday>"
					+ "<birthday>2010-05-33</birthday>"
					+ "</com.ebay.eric.xstream_test.Birthday>"
					+ "<byte>22</byte>"
					+ "<boolean>true</boolean>"
					+ "<float>22.0</float>"
					+ "<string>hello</string>"
					+ "</object-stream>";
			System.out
					.println("---------ObjectInputStream## XML --> javaObject---------");
			StringReader reader = new StringReader(s);
			ObjectInputStream in = xstream.createObjectInputStream(reader);
			Student stu = (Student) in.readObject();
			Birthday b = (Birthday) in.readObject();
			byte i = in.readByte();
			boolean bo = in.readBoolean();
			float f = in.readFloat();
			String str = in.readUTF();
			System.out.println(stu.toString());
			System.out.println(b);
			System.out.println(i);
			System.out.println(bo);
			System.out.println(f);
			System.out.println(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void readXml2Object() {
		try {
			System.out.println("-----------Xml >>> Bean--------------");
			Student stu = (Student) xstream.fromXML(xstream.toXML(bean));
			System.out.println(stu.toString());

			List<Student> list = new ArrayList<Student>();
			list.add(bean);// add

			Map<String, Student> map = new HashMap<String, Student>();
			map.put("No.1", bean);// put

			bean = new Student();
			bean.setAddress("china");
			bean.setEmail("tom@125.com");
			bean.setId(2);
			bean.setName("tom");
			list.add(bean);// add
			map.put("No.2", bean);// put

			bean = new Student();
			bean.setName("jack");
			list.add(bean);// add
			map.put("No.3", bean);// put

			System.out.println("==========XML >>> List===========");
			List<Student> studetns = (List<Student>) xstream.fromXML(xstream
					.toXML(list));
			System.out.println("size:" + studetns.size());// 3
			for (Student s : studetns) {
				System.out.println(s.toString());
			}

			System.out.println("==========XML >>> Map===========");
			Map<String, Student> maps = (Map<String, Student>) xstream
					.fromXML(xstream.toXML(map));
			System.out.println("size:" + maps.size());// 3
			Set<String> key = maps.keySet();
			Iterator<String> iter = key.iterator();
			while (iter.hasNext()) {
				String k = iter.next();
				System.out.println(k + ":" + map.get(k));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void readXmlToObject() throws Exception {
		xstream.alias("EntityFields", EntityFields.class);      
		xstream.addImplicitCollection(EntityFields.class, "entityFields");
		xstream.alias("OrderId", Long.class);
		xstream.alias("BuyerFirstName", String.class);
		xstream.alias("EntityField", EntityField.class);
		String str = "<EntityFields>"
				+" <OrderId>111</OrderId> "
				+" <BuyerFirstName> Eric </BuyerFirstName>"
				+ " <EntityField> "
				+ "<FieldName>Question 1</FieldName>"
				+ " <FieldDisplayName>some question 1</FieldDisplayName>"
				+ " <FieldType>character varying</FieldType>"
				+ " <IsMultivalued>false</IsMultivalued> "
				+ "</EntityField>  <EntityField> "
				+ "<FieldName>Question 2</FieldName> "
				+ "<FieldDisplayName>some question 2</FieldDisplayName> "
				+ "<FieldType>boolean</FieldType>"
				+ " <IsMultivalued>false</IsMultivalued>"
				+ " </EntityField> "
				+ " <EntityField>"
				+ " <FieldName>Question 3</FieldName>"
				+ " <FieldDisplayName>some question 3</FieldDisplayName>"
				+ " <FieldType>character varying[]</FieldType>"
				+ " <IsMultivalued>true</IsMultivalued>"
				+ " </EntityField>  "
				+ "</EntityFields>";

		EntityFields fields = (EntityFields) xstream.fromXML(str);
//		EntityField field = (EntityField) xstream.fromXML(reader);
//		ObjectInputStream in = xstream.createObjectInputStream(reader);
//		EntityFields fields = (EntityFields) in.readObject();
//		EntityField field = (EntityField) in.readObject();
		System.out.println(fields.toString());
//		System.out.println(field.toString());
		List<EntityField> list = fields.getEntityFields();
		for(EntityField f: list){
			System.out.println(f.getFieldDisplayName());
		}
	}
	
	public void convert2HoldRequest() {
		//Hold request alias
		xstream.alias("ReserveRequest", HoldRequest.class);
		xstream.alias("Ticket", PartnerOrderTicket.class);
//		xstream.aliasField("OrderId", HoldRequest.class, "orderId");
		xstream.aliasField("FulfillmentType", HoldRequest.class, "fulfillmentType");
		xstream.aliasField("BuyerFirstName", HoldRequest.class, "buyerFirstName");
		xstream.aliasField("BuyerLastName", HoldRequest.class, "buyerLastName");
		xstream.aliasField("OrderTotal", HoldRequest.class, "orderTotal");
		xstream.aliasField("SellerTotalPayout", HoldRequest.class, "sellerTotalPayout");
		xstream.aliasField("ListingId", HoldRequest.class, "listingId");
		xstream.aliasField("ExternalListingId", HoldRequest.class, "externalListingId");
		xstream.aliasField("Tickets", HoldRequest.class, "tickets");
		
		//Ticket alias
		xstream.aliasField("Price", PartnerOrderTicket.class, "price");
		xstream.aliasField("StatusId", PartnerOrderTicket.class, "statusId");
		xstream.aliasField("StatusDesc", PartnerOrderTicket.class, "statusDesc");
		xstream.aliasField("FaceValue", PartnerOrderTicket.class, "faceValue");
		xstream.aliasField("TicketSeatId", PartnerOrderTicket.class, "ticketSeatId");
		xstream.aliasField("Section", PartnerOrderTicket.class, "section");
		xstream.aliasField("Row", PartnerOrderTicket.class, "row");
		xstream.aliasField("SeatNumber", PartnerOrderTicket.class, "seatNumber");
		
		xstream.autodetectAnnotations(true);
	
		
		String xml = "<ReserveRequest>" + "<OrderId>272161163</OrderId>"
				+ "<FulfillmentType>UPS</FulfillmentType>"
				+ "<BuyerFirstName>Api_US_sell_indy01_FIRST</BuyerFirstName>"
				+ "<BuyerLastName>Api_US_sell_indy01_LAST</BuyerLastName>"
				+ "<Tickets>"
				+ " <Ticket>"
				+ "<Price>100.00</Price>"
				+ " <StatusId>1</StatusId>"
				+ "   <StatusDesc>Available</StatusDesc>"
				+ "  <FaceValue></FaceValue>"
				+ "  <TicketSeatId>2189826696</TicketSeatId>"
				+ "   <Section>100</Section>" + "  <Row>1</Row>"
				+ "<SeatNumber>1</SeatNumber>" 
				+ "</Ticket>"
				 + " </Tickets>"
				+ " <OrderTotal>159.00</OrderTotal>"
				+ " <SellerTotalPayout>0</SellerTotalPayout>"
				+ "<ListingId>1083035772</ListingId>"
				+ "<ExternalListingId>1479726</ExternalListingId>"
				+ "</ReserveRequest>";
//		String newXml = " <ReserveRequest> <OrderId>272161163</OrderId>"
//				+ "<FulfillmentType>UPS</FulfillmentType>"
//				+ "<BuyerFirstName>Api_US_sell_indy01_FIRST</BuyerFirstName>"
//				+ "<BuyerLastName>Api_US_sell_indy01_FIRST</BuyerLastName>"
//				+ "<Tickets>" + " <Ticket>" + "  <price>100</price>"
//				+ "  <statusId>1</statusId>"
//				+ "  <ticketSeatId>2189826696</ticketSeatId>"
//				+ " <section>100</section>" + "  <row>1</row>"
//				+ "  <seatNumber>1</seatNumber>" + "  </Ticket>" + "  <Ticket>"
//				+ "  <price>100</price>" + "  <statusId>1</statusId>"
//				+ "   <ticketSeatId>2189826696</ticketSeatId>"
//				+ "   <section>100</section>" + "   <row>1</row>"
//				+ "   <seatNumber>1</seatNumber>" + "  </Ticket>"
//				+ " </Tickets>" + "</ReserveRequest>";
//		String xml = xstream.toXML(this.generateHoldRequest());
		System.out.println(xml);
		HoldRequest request = (HoldRequest) xstream.fromXML(xml);
		System.out.println(request.toString());
	}

	private HoldRequest generateHoldRequest(){
		HoldRequest request = new HoldRequest();
		request.setOrderId(272161163L);
		request.setFulfillmentType("UPS");
		request.setBuyerFirstName("Api_US_sell_indy01_FIRST");
		request.setBuyerLastName("Api_US_sell_indy01_FIRST");
		List<PartnerOrderTicket> tickets = new ArrayList<PartnerOrderTicket>();
		PartnerOrderTicket ticket = new PartnerOrderTicket();
		ticket.setPrice(new BigDecimal(100.00));
		ticket.setStatusId(1L);
		ticket.setTicketSeatId(2189826696L);
		ticket.setSection("100");
		ticket.setRow("1");
		ticket.setSeatNumber("1");
		tickets.add(ticket);
		PartnerOrderTicket ticket1 = new PartnerOrderTicket();
		ticket1.setPrice(new BigDecimal(100.00));
		ticket1.setStatusId(1L);
		ticket1.setTicketSeatId(2189826696L);
		ticket1.setSection("100");
		ticket1.setRow("1");
		ticket1.setSeatNumber("1");
		tickets.add(ticket1);
		request.setTickets(tickets);
		return request;
	}
	
	public static void main(String[] args) throws Exception {
		XmlToObjectTransformer transformer = new XmlToObjectTransformer();
//		transformer.readXML4InputStream();
//		transformer.readXml2Object();
//		transformer.readXmlToObject();
		transformer.convert2HoldRequest();
	}
}
