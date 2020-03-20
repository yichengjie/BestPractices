package com.yicj.study;

/**
 * 调用方:DisplayClientThread
 * DisplayClientThread是调用主动对象的displayString方法(显示字符串)的线程。
 * 与MakerClientThread的时候一样，displayString方法会在被调用后立即返回，
 * 这样的调用相当于将"显示字符串"这个异步消息传递给主动对象.这里的待显示字符串(string)
 * 是通过在线程的名字后面加0,1,2,....等编号而成的。
 * 因为displayString 方法没有返回值，所以这里没有使用Future模式
 */
public class DisplayClientThread extends Thread {

    private final ActiveObject activeObject ;

    public DisplayClientThread(String name ,ActiveObject activeObject){
        super(name);
        this.activeObject = activeObject ;
    }

    @Override
    public void run() {
        try {
            for (int i= 0 ; true ; i ++){
                //没有返回值的调用
                String str = Thread.currentThread().getName() + " " + i ;
                activeObject.displayString(str) ;
                Thread.sleep(200);
            }
        }catch (InterruptedException e){

        }
    }
}
