package jair.ejemplo.android.ejemplowebservices;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jair Barzola on 26-Feb-17.
 */

public class UsuarioXMLParser {


    public static List<Usuario> paser(String content) {

        boolean inDataItemTag = false;
        String currentTagName = "";
        Usuario usuario = null;
        List<Usuario> usuarioList = new ArrayList<>();

        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();
            parser.setInput(new StringReader(content));

            int eventType = parser.getEventType();


            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        currentTagName = parser.getName();
                        if (currentTagName.equals("usuario")) {
                            inDataItemTag = true;
                            usuario = new Usuario();
                            usuarioList.add(usuario);
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if (parser.getName().equals("usuario")) {
                            inDataItemTag = false;
                        }
                        currentTagName = "";
                        break;
                    case XmlPullParser.TEXT:
                        if (inDataItemTag && usuario != null) {
                            switch (currentTagName) {
                                case "nombre":
                                    usuario.setNombre(parser.getText());
                                    break;
                                case "apellido":
                                    usuario.setApellido(parser.getText());
                                    break;
                                case "edad":
                                    usuario.setEdad(Integer.parseInt(parser.getText()));
                                    break;
                                case "carrera":
                                    usuario.setCarrera(parser.getText());
                                    break;
                            }
                        }
                        break;
                }
                eventType = parser.next();
            }
            return usuarioList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
