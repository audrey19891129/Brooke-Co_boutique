package com.example.brookeandcoapp.controller;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.xmlpull.v1.XmlSerializer;

import java.io.IOException;

public class CustomSoapSerializationEnvelope extends SoapSerializationEnvelope {

//   REFERENCES : https://stackoverflow.com/questions/43338650/add-xml-prefix-to-soap-request
//                https://stackoverflow.com/questions/27710169/ksoap2-v-prefixo-to-any-other-prefix

    public CustomSoapSerializationEnvelope(int version){
        super(version);
    }

    @Override
    public void write(XmlSerializer writer) throws IOException {
        writer.setPrefix("i", xsi);
        writer.setPrefix("d", xsd);
        writer.setPrefix("c", enc);
        writer.setPrefix("v", env);
        writer.setPrefix("con","http://controller/"); // <=  Here i added a custom Prefix for the soap request / response (prefix/namespace)
        writer.startTag(env, "Envelope");
        writer.startTag(env, "Header");
        writeHeader(writer);
        writer.endTag(env, "Header");
        writer.startTag(env, "Body");
        writeBody(writer);
        writer.endTag(env, "Body");
        writer.endTag(env, "Envelope");
    }
}
