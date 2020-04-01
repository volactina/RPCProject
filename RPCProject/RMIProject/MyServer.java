
import java.net.*;
import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.UnicastRemoteObject;
import java.awt.*;
import java.awt.event.*;

import javax.naming.NamingException;


public class MyServer
{
    public static void main(String[] args) throws RemoteException, NamingException, MalformedURLException, AlreadyBoundException
    {
    	System.setProperty("java.rmi.server.hostname", "阿里云外网地址");
    	MyServerImpl centralServer=new MyServerImpl();
    	Server centralServerI=(Server)UnicastRemoteObject.exportObject(centralServer,1099);
    	// Bind the remote object's stub in the registry
    	Registry registry = LocateRegistry.createRegistry(2002);
    	registry.rebind("centralServerI", centralServerI);
//    	
//    	//启动服务器服务
//        System.out.println("Constructing server implementation");
//        MyServerImpl centralServer = new MyServerImpl();
//        System.out.println("Binding server implementation to registry");
//        LocateRegistry.createRegistry(1099);
//        //System.setProperty("java.rmi.server.hostname","222.66.117.30");
//        System.setProperty("java.rmi.server.hostname","172.31.234.4");
//        //Naming.bind("rmi://localhost:1099/central_warehoues",centralServer);
//        //Naming.bind("rmi://222.66.117.30:1099/central_warehoues",centralServer);
//        Naming.bind("rmi://172.31.234.4:1099/central_warehoues",centralServer);
//        System.out.println("Waiting for invocations from clients ...");
        
        centralServer.f.setLayout(new GridLayout(centralServer.labsnum,1));
        centralServer.f.setSize(500,500);
        centralServer.f.setBackground(Color.gray);
        for(int i=0;i<centralServer.labsnum;i++) {
        	centralServer.f.add(centralServer.labs.get(i));
        }
        centralServer.f.setAlwaysOnTop(!centralServer.f.isAlwaysOnTop());
        centralServer.f.setVisible(true);
        centralServer.f.addWindowListener (new WindowAdapter(){
		    public void windowClosing(WindowEvent e) {
		        System.exit(0);
		    }
		});
    }
}