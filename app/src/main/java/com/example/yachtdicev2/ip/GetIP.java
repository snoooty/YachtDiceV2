package com.example.yachtdicev2.ip;


import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

public class GetIP {

    // 집 와이파이 사용할때..

    public String homeWifi = "172.30.1.17";

    // 예빈이네 와이파이 사용할때..

    public String yeppiWifi = "192.168.35.179";

    // 학원 와이파이 사용할때..

    public String academyWifi = "172.30.1.43";

    // 기타 와이파이 사용할때..

    public String etcWifi = "192.168.50.118";

    public GetIP(){

    }

    private InetAddress getLocalHost() throws SocketException, UnknownHostException {
        try{
            NetworkInterface networkInterface = NetworkInterface.getByName("en0");
            for (Enumeration<InetAddress> addresses = networkInterface.getInetAddresses(); addresses.hasMoreElements(); ) {
                InetAddress address = addresses.nextElement();
                if (address instanceof Inet4Address && !address.isLoopbackAddress()) {
                    return address;
                }
            }
        } catch(NullPointerException e){
            return InetAddress.getLocalHost();
        }
        return InetAddress.getLocalHost();
    }

    public InetAddress getInetAddress() throws SocketException {
        Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
        while(networkInterfaces.hasMoreElements()) {
            NetworkInterface networkInterface = networkInterfaces.nextElement();
            String name = networkInterface.getName();
            String displayName = networkInterface.getDisplayName();
            boolean isLoopback = networkInterface.isLoopback();
            boolean isVirtual = networkInterface.isVirtual();
            if(!isLoopback) {
                Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
                while(inetAddresses.hasMoreElements()) {
                    InetAddress inetAddress = inetAddresses.nextElement();
                    if(!inetAddress.isLoopbackAddress() && !inetAddress.isLinkLocalAddress() && !inetAddress.isSiteLocalAddress()) {
//                        if(log.isInfoEnabled()) {
//                            log.info("==============NetworkInterface Information===============");
//                            log.info("Name: " + name);
//                            log.info("Display Name: " + displayName);
//                            log.info("Loopback: " + isLoopback);
//                            log.info("Virtual: " + isVirtual);
//                            log.info("=============================================");
//                        }
                        return inetAddress;
                    }
                }
            }
        }
        return null;
    }

    public String getHostAddress() throws SocketException {
        InetAddress inetAddress = getInetAddress();
        String hostName = inetAddress.getHostName();
        String hostAddress = inetAddress.getHostAddress();
//        if(log.isInfoEnabled()) {
//            log.info("==============Host Address Information===============");
//            log.info("Host Name: " + hostName);
//            log.info("Host Address: " + hostAddress);
//            log.info("=============================================");
//        }
        return hostAddress;
    }

    public String getIp(){

        try {
            InetAddress local = getLocalHost();
            return local.getHostAddress();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
    }

    public String getIp2(){
        try
        {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();)
            {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();)
                {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress() && !inetAddress.isLinkLocalAddress() && inetAddress.isSiteLocalAddress())
                    {
                        return inetAddress.getHostAddress().toString();
                    }
                }
            }
        }
        catch (SocketException ex) {}
        return null;
    }
}
