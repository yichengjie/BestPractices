package com.yicj.study;

/**调用方： MakerClientThread类
 * 1. makeString方法会在被调用后立即返回。这里的调用相当于将"生成字符串"这个异步消息传递给主动对象
 * 2. 虽然makeString的返回值是Result类型，但是实际上这里使用的是Future模式。使用getResultValue
 * 方法可以获取实际的返回值
 * 3. 这里使用Thread.sleep方法让处理睡眠了大约10毫秒，但是实际上这里本来可以执行其他处理
 */
public class MakerClientThread extends Thread {

    private final ActiveObject activeObject ;
    private final char fillchar ;

    public MakerClientThread(String name, ActiveObject activeObject){
        super(name) ;
        this.activeObject = activeObject ;
        this.fillchar = name.charAt(0) ;
    }


    @Override
    public void run() {
        try {
            for (int i = 0 ; true ; i++){
                //有返回值的调用
                Result<String> result = activeObject.makeString(i, fillchar) ;
                Thread.sleep(10);
                String value = result.getResultValue() ;
                System.out.println(Thread.currentThread().getName() +": value = " + value);
            }
        }catch (InterruptedException e){

        }
    }
}
