package com.example.vig.navigationtest;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import java.util.Date;
import static android.R.attr.name;

public class WebService {
    private static String NAMESPACE = "http://tempuri.org/";
    private static String URL = "https://www.astreaclaim.eu/TestWS/WebService.asmx";
    public  final String SOAP_ADDRESS = "http://www.astreaclaim.eu/TestWS/WebService.asmx";
    private static String SOAP_ACTION = "http://tempuri.org/";
    private static final String OPERATION_NAME = "ContaPerizie";

    public static String invokeHelloWorldWS() {

        String resTxt = null;

        // Create request
        SoapObject request = new SoapObject(NAMESPACE, OPERATION_NAME);
        // Create envelope
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        //Set envelope as dotNet
        envelope.dotNet = true;
        // Set output SOAP object
        envelope.setOutputSoapObject(request);

        // Create HTTP call object
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        androidHttpTransport.debug = true;

        try {
            // Invoke web service
            androidHttpTransport.call(SOAP_ACTION + OPERATION_NAME, envelope);
            // Get the response
            SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
            // Assign it to resTxt variable static variable
            resTxt = response.toString();
        } catch (Exception e) {
            //Print error
            String a  =androidHttpTransport.responseDump; // <-- a string containing server responsev
            e.printStackTrace();
            //Assign error message to resTxt
            resTxt = "Error occured";
        }
        //Return resTxt to calling object
        return resTxt;
    }

    public static String invokeDoppioniSanGiorgioStr() {
        String resTxt = null;

        SoapObject request = new SoapObject(NAMESPACE, OPERATION_NAME);
        // Create envelope
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        //Set envelope as dotNet
        envelope.dotNet = true;
        // Set output SOAP object
        envelope.setOutputSoapObject(request);
        // Create HTTP call object
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        try {
            // Invoke web service
            androidHttpTransport.call(SOAP_ACTION + "ContaPerizie", envelope);
            // Get the response
            SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
            // Assign it to resTxt variable static variable
            resTxt = response.toString();

        } catch (Exception e) {
            //Print error
            String a  =androidHttpTransport.responseDump; // <-- a string containing server responsev
            e.printStackTrace();
            //Assign error message to resTxt
            resTxt = "Error";
        }
        finally {
            return resTxt;
        }
        //Return resTxt to calling object

    }

}


