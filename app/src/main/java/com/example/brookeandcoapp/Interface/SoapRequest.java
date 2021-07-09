package com.example.brookeandcoapp.Interface;

import android.util.Base64;
import android.util.Log;

import com.example.brookeandcoapp.controller.CustomSoapSerializationEnvelope;
import com.example.brookeandcoapp.model.Address;
import com.example.brookeandcoapp.model.Client;
import com.example.brookeandcoapp.model.Entry;
import com.example.brookeandcoapp.model.MarshallDouble;
import com.example.brookeandcoapp.model.Order;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.MarshalBase64;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.kxml2.kdom.Element;
import org.kxml2.kdom.Node;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.Serializable;


public interface SoapRequest {
    String NONE = "";
    String PREFIX = "con:";
    String NAMESPACE = "http://controller/";
    String SOAP_ACTION = "";
    //String URL = "http://10.0.0.104:8080/brookeandcoapp/BrookeAndCo?wsdl";
    String URL = "http://192.168.0.12:8080/brookeandcoapp/BrookeAndCo?wsdl";

    static SoapObject request(String method) throws IOException, XmlPullParserException {
        SoapObject request = new SoapObject(NAMESPACE, method);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER10);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        androidHttpTransport.debug = true;
        androidHttpTransport.call(SOAP_ACTION, envelope);
        SoapObject resultRequestSoap = (SoapObject) envelope.bodyIn;
        return resultRequestSoap;
    }

    static SoapObject getOrdersByClientId(int clientId) throws IOException, XmlPullParserException {
        SoapObject request = new SoapObject(NONE, PREFIX+"ORDER_GETBYCLIENTID");
        request.addProperty("ClientId", clientId);
        CustomSoapSerializationEnvelope envelope = new CustomSoapSerializationEnvelope(SoapEnvelope.VER10);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        androidHttpTransport.call(SOAP_ACTION, envelope);
        SoapObject resultRequestSoap = (SoapObject) envelope.bodyIn;
        return resultRequestSoap;
    }

    static SoapObject newOrder(Order order) throws IOException, XmlPullParserException {

        SoapObject request = new SoapObject(NONE, PREFIX+"ORDER_ADD");

        PropertyInfo pi = new PropertyInfo();
        pi.setType(Order.class);
        pi.setName("Order");
        pi.setValue(order);
        request.addProperty(pi);

        CustomSoapSerializationEnvelope envelope = new CustomSoapSerializationEnvelope(SoapEnvelope.VER10);
        envelope.dotNet = true;

        //Necessary to serialize double values
        MarshallDouble md = new MarshallDouble();
        md.register(envelope);

        envelope.setOutputSoapObject(request);
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        androidHttpTransport.debug = true;
        androidHttpTransport.call(SOAP_ACTION, envelope);

        if (envelope.bodyIn instanceof SoapFault)
        {
            final SoapFault sf = (SoapFault) envelope.bodyIn;
            Log.e("ERROR LOG",sf.getMessage());
            return null;
        }
        else{
            SoapObject resultRequestSoap = (SoapObject) envelope.bodyIn;
            return resultRequestSoap;
        }
    }

    static SoapObject updateOrder(Order order, int addressId) throws IOException, XmlPullParserException {

        SoapObject request = new SoapObject(NONE, PREFIX+"ORDER_UPDATE");
        PropertyInfo pi = new PropertyInfo();

        pi.setType(Order.class);
        pi.setName("Order");
        pi.setValue(order);
        request.addProperty(pi);
        request.addProperty("AddressId", addressId);

        CustomSoapSerializationEnvelope envelope = new CustomSoapSerializationEnvelope(SoapEnvelope.VER10);

        envelope.dotNet = true;

        //Necessary to serialize double values
        MarshallDouble md = new MarshallDouble();
        md.register(envelope);

        envelope.setOutputSoapObject(request);
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        androidHttpTransport.debug = true;
        androidHttpTransport.call(SOAP_ACTION, envelope);
        //SoapObject resultRequestSoap = (SoapObject) envelope.bodyIn;
        Log.d("dump Request: " ,androidHttpTransport.requestDump);
        Log.d("dump response: " ,androidHttpTransport.responseDump);
        //Log.e("request :", request.toString());

        if (envelope.bodyIn instanceof SoapFault)
        {
            final SoapFault sf = (SoapFault) envelope.bodyIn;
            Log.e("ERROR LOG",sf.getMessage());
            return null;
        }
        else{
            SoapObject resultRequestSoap = (SoapObject) envelope.bodyIn;
            Log.e("SOAP REQUEST", resultRequestSoap.getProperty("return").toString());
            return resultRequestSoap;
        }
    }

    static SoapObject login_Request(String method, String email, String password) throws IOException, XmlPullParserException {
        SoapObject request = new SoapObject(NONE, PREFIX+method);
        request.addProperty("email", email);
        request.addProperty("password", password);
        CustomSoapSerializationEnvelope envelope = new CustomSoapSerializationEnvelope(SoapEnvelope.VER10);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        androidHttpTransport.call(SOAP_ACTION, envelope);
        SoapObject resultRequestSoap = (SoapObject) envelope.bodyIn;
        return resultRequestSoap;
    }

    static SoapObject getByCategory(String method, String category) throws IOException, XmlPullParserException {
        SoapObject request = new SoapObject(NONE, PREFIX+method);
        request.addProperty("category", category);
        CustomSoapSerializationEnvelope envelope = new CustomSoapSerializationEnvelope(SoapEnvelope.VER10);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        androidHttpTransport.call(SOAP_ACTION, envelope);
        SoapObject resultRequestSoap = (SoapObject) envelope.bodyIn;
        return resultRequestSoap;
    }

    static SoapObject getById(String method, int id) throws IOException, XmlPullParserException {
        SoapObject request = new SoapObject(NONE, PREFIX+method);
        request.addProperty("id", id);
        CustomSoapSerializationEnvelope envelope = new CustomSoapSerializationEnvelope(SoapEnvelope.VER10);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        androidHttpTransport.call(SOAP_ACTION, envelope);
        SoapObject resultRequestSoap = (SoapObject) envelope.bodyIn;
        return resultRequestSoap;
    }

    static SoapObject updateEntry(int entryId, int quantity) throws IOException, XmlPullParserException {
        SoapObject request = new SoapObject(NONE, PREFIX+"ENTRY_UPDATE");
        request.addProperty("entryId", entryId);
        request.addProperty("quantity", quantity);
        CustomSoapSerializationEnvelope envelope = new CustomSoapSerializationEnvelope(SoapEnvelope.VER10);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        androidHttpTransport.call(SOAP_ACTION, envelope);
        SoapObject resultRequestSoap = (SoapObject) envelope.bodyIn;
        return resultRequestSoap;
    }

    static SoapObject deleteEntry(int entryId) throws IOException, XmlPullParserException {
        SoapObject request = new SoapObject(NONE, PREFIX+"ENTRY_DELETE");
        request.addProperty("id", entryId);
        CustomSoapSerializationEnvelope envelope = new CustomSoapSerializationEnvelope(SoapEnvelope.VER10);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        androidHttpTransport.call(SOAP_ACTION, envelope);
        SoapObject resultRequestSoap = (SoapObject) envelope.bodyIn;
        return resultRequestSoap;
    }

    static SoapObject addEntry(Entry entry, int productId) throws IOException, XmlPullParserException {

            SoapObject request = new SoapObject(NONE, PREFIX+"ENTRY_ADD");
            PropertyInfo pi = new PropertyInfo();

            pi.setType(Entry.class);
            pi.setName("Entry");
            pi.setValue(entry);
            request.addProperty(pi);
            request.addProperty("productId", productId);

            CustomSoapSerializationEnvelope envelope = new CustomSoapSerializationEnvelope(SoapEnvelope.VER10);

            envelope.dotNet = true;

            //Necessary to serialize double values
            MarshallDouble md = new MarshallDouble();
            md.register(envelope);

            envelope.setOutputSoapObject(request);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
            androidHttpTransport.debug = true;
            androidHttpTransport.call(SOAP_ACTION, envelope);
            //SoapObject resultRequestSoap = (SoapObject) envelope.bodyIn;
            Log.d("dump Request: " ,androidHttpTransport.requestDump);
            Log.d("dump response: " ,androidHttpTransport.responseDump);
            //Log.e("request :", request.toString());

        if (envelope.bodyIn instanceof SoapFault)
        {
            final SoapFault sf = (SoapFault) envelope.bodyIn;
                Log.e("ERROR LOG",sf.getMessage());
                return null;
        }
        else{
            SoapObject resultRequestSoap = (SoapObject) envelope.bodyIn;
            return resultRequestSoap;
        }
    }

    static void register(Client client, Address address) throws IOException, XmlPullParserException {

        // CLIENT
        SoapObject request = new SoapObject(NONE, PREFIX+"CLIENT_Add");
        PropertyInfo pi = new PropertyInfo();

        pi.setType(Client.class);
        pi.setName("Client");
        pi.setValue(client);
        request.addProperty(pi);

        Log.e("Get Property Info :", request.toString());
        CustomSoapSerializationEnvelope envelope = new CustomSoapSerializationEnvelope(SoapEnvelope.VER10);

        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);

        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        androidHttpTransport.debug = true;
        androidHttpTransport.call(SOAP_ACTION, envelope);

        SoapObject resultRequestSoap = (SoapObject) envelope.bodyIn;

        // ADDRESS
        SoapObject request1 = new SoapObject(NONE, PREFIX+"ADDRESS_ADD");
        PropertyInfo p2 = new PropertyInfo();

        address.setClientId(Integer.parseInt(resultRequestSoap.getProperty("return").toString()));
        p2.setType(Address.class);
        p2.setName("Address");
        p2.setValue(address);
        request1.addProperty(p2);

        CustomSoapSerializationEnvelope envelope1 = new CustomSoapSerializationEnvelope(SoapEnvelope.VER10);
        envelope1.dotNet = true;
        envelope1.setOutputSoapObject(request1);

        HttpTransportSE androidHttpTransport1 = new HttpTransportSE(URL);
        androidHttpTransport1.debug = true;
        androidHttpTransport1.call(SOAP_ACTION, envelope1);
    }

}
