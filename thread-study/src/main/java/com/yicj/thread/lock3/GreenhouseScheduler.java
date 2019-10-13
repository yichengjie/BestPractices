package com.yicj.thread.lock3;

import java.util.*;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class GreenhouseScheduler {
    private volatile boolean light = false;
    private volatile boolean water = false;
    private String thermostat = "Day";
    private ScheduledThreadPoolExecutor scheduler =
            new ScheduledThreadPoolExecutor(10);
    ///////////////////////////////////////////////
    private Calendar lastTime = Calendar.getInstance() ;
    {//Adjust data to the half hour
        lastTime.set(Calendar.MINUTE,30);
        lastTime.set(Calendar.SECOND,00);
    }
    private float lastTemp = 65.0f ;
    private int tempDirection = +1 ;
    private float lastHumidity = 50.0f ;
    private int humidityDirection = +1 ;
    private Random rand = new Random(47) ;
    List<DataPoint> data =
            Collections.synchronizedList(new ArrayList<>()) ;

    public static void main(String[] args) {
        GreenhouseScheduler gh = new GreenhouseScheduler() ;
        gh.schedule(gh.new Terminate(),5000) ;
        gh.repeat(gh.new Bell(),0,1000);
        gh.repeat(gh.new ThermostatNight(),0,2000);
        gh.repeat(gh.new LightOn(),0, 200);
        gh.repeat(gh.new LightOff(),0,400);
        gh.repeat(gh.new WaterOn(),0,600);
        gh.repeat(gh.new WaterOff(),0,800);
        gh.repeat(gh.new ThermostatDay(),0,1400);
        gh.repeat(gh.new CollectData(),500,500);
    }



    public void schedule(Runnable event, long delay) {
        scheduler.schedule(event, delay, TimeUnit.MILLISECONDS) ;
    }


    public void repeat(Runnable event, long delay, long period) {
        scheduler.scheduleAtFixedRate(event, delay, period, TimeUnit.MILLISECONDS);
    }


    public synchronized String getThermostat() {
        return thermostat;
    }

    public synchronized void setThermostat(String value) {
        this.thermostat = value;
    }


    class LightOn implements Runnable {
        @Override
        public void run() {
            //Put hardware control code here to
            // physically turn on the light
            System.out.println("Turning on lights");
            light = true;
        }
    }

    class LightOff implements Runnable {
        @Override
        public void run() {
            System.out.println("Turning off lights");
            light = false;
        }
    }

    class WaterOn implements Runnable {
        @Override
        public void run() {
            // Put hardware control code here
            System.out.println("Turning greenhouse water on");
            water = true;
        }
    }

    class WaterOff implements Runnable {
        @Override
        public void run() {
            System.out.println("Turning greenhouse water off");
            water = false;
        }
    }

    class ThermostatNight implements Runnable {
        @Override
        public void run() {
            System.out.println("Thermostat to night setting");
            setThermostat("Night");
        }
    }

    class ThermostatDay implements Runnable {
        @Override
        public void run() {
            System.out.println("Thermostat to day setting");
            setThermostat("Day");
        }
    }

    class Bell implements Runnable {
        @Override
        public void run() {
            System.out.println("Bing !");
        }
    }

    class Terminate implements Runnable {

        @Override
        public void run() {
            System.out.println("Terminating");
            scheduler.shutdownNow();
            // Must start a separate task to do this job
            // since the scheduler has bean shut down
            new Thread() {
                @Override
                public void run() {
                    for (DataPoint d : data) {
                        System.out.println(d);
                    }
                }
            }.start();
        }
    }
    //New feature: data collection
    static class DataPoint {
        final Calendar time ;
        final float temperature ;
        final float humidity ;
        public DataPoint(Calendar d , float temp , float hum){
            this.time = d ;
            this.temperature = temp ;
            this.humidity = hum ;
        }

        @Override
        public String toString() {
            return time.getTime() +" temperature :"
                    +temperature +" humidity : " + humidity;
        }
    }

    class CollectData implements Runnable{
        @Override
        public void run() {
            System.out.println("Collection data");
            synchronized (GreenhouseScheduler.this){
                lastTime.set(Calendar.MINUTE,
                    lastTime.get(Calendar.MINUTE) + 30);
                if(rand.nextInt(5) == 4){
                    tempDirection = -tempDirection ;
                }
                lastTemp = lastTemp +
                    tempDirection * (1.0f + rand.nextFloat())  ;
                if(rand.nextInt(5) == 4){
                    humidityDirection = - humidityDirection ;
                }
                lastHumidity = lastHumidity +
                        humidityDirection * rand.nextFloat() ;
                data.add(new DataPoint((Calendar)lastTime.clone(),
                        lastTemp,lastHumidity)) ;
            }
        }
    }

}
