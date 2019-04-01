package com.ccnu.test.springboot;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * 举个报旅行团旅行的例子。
 * 出发时，导游会在机场收了护照和签证，办理集体出境手续，所以，要等大家都到齐才能出发，出发前再把护照和签证发到大家手里。
 * 对应CyclicBarrier使用。
 * 每个人到达后进入barrier状态。
 * 都到达后，唤起大家一起出发去旅行。
 * 旅行出发前，导游还会有个发护照和签证的动作。
 * <p>
 * 作者：联想桥南
 * 链接：https://www.jianshu.com/p/4ef4bbf01811
 * 来源：简书
 * 简书著作权归作者所有，任何形式的转载都请联系作者获得授权并注明出处。
 */
public class CyclicBarrierTest {


    public static void main(String[] args) throws Exception {

        CyclicBarrier cyclicBarrier = new CyclicBarrier(3, new TourGuideTask());
        Executor executor = Executors.newFixedThreadPool(3);
        //登哥最大牌，到的最晚
        executor.execute(new TravelTask(cyclicBarrier, "哈登", 5));
        executor.execute(new TravelTask(cyclicBarrier, "保罗", 3));
        executor.execute(new TravelTask(cyclicBarrier, "戈登", 1));
    }


    /**
     * 旅行线程
     * Created by jiapeng on 2018/1/7.
     */
    static class TravelTask implements Runnable {

        private CyclicBarrier cyclicBarrier;
        private String name;
        private int arriveTime;//赶到的时间

        public TravelTask(CyclicBarrier cyclicBarrier, String name, int arriveTime) {
            this.cyclicBarrier = cyclicBarrier;
            this.name = name;
            this.arriveTime = arriveTime;
        }

        @Override
        public void run() {
            try {
                //模拟达到需要花的时间
                Thread.sleep(arriveTime * 1000);
                System.out.println(name + "到达集合点");
                cyclicBarrier.await();
                System.out.println(name + "开始旅行啦～～");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 导游线程，都到达目的地时，发放护照和签证
     * Created by jiapeng on 2018/1/7.
     */
    static class TourGuideTask implements Runnable {

        @Override
        public void run() {
            System.out.println("****导游分发护照签证****");
            try {
                //模拟发护照签证需要2秒
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
