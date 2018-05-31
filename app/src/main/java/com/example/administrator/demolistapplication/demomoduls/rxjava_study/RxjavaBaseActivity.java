package com.example.administrator.demolistapplication.demomoduls.rxjava_study;

import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.administrator.demolistapplication.R;
import com.example.administrator.demolistapplication.base.BaseJiaGouinterface;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.operators.observable.ObservableAll;

public class RxjavaBaseActivity extends AppCompatActivity  implements BaseJiaGouinterface {

    private String TAG = "RxjavaBaseActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava_test);

        onInitView();
        onInitHander();
        onGetData();

    }


    @Override
    public void onInitView() {
        onRepeat();
        onDefer();
    }

    @Override
    public void onGetData() {

    }

    @Override
    public void onInitHander() {

    }

    /********************************************************************/
    /**
     * 使用create( )创建Observable最基本的创建方式。
     * 可以看到，这里传入了一个 ObservableOnSubscribe对象作为参数，
     * 它的作用相当于一个计划表，当 Observable被订阅的时候，
     * ObservableOnSubscribe的subscribe()方法会自动被调用，
     * 事件序列就会依照设定依次触发（对于上面的代码，就是观察者Observer 将会被调用一次 onNext()）。
     * 这样，由被观察者调用了观察者的回调方法，就实现了由被观察者向观察者的事件传递，即观察者模式。
     *
     */
    private void onJiBenUse(){
        //基本用法
        Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext("message");
                Log.e(TAG, "我来发送数据！");
            }
        });
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                Log.e(TAG, "我来接受消息: "+ s);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);

    }

    /***
     * 使用just( )，将为你创建一个Observable并自动为你调用onNext( )发射数据。通过just( )方式
     * 直接触发onNext()，just中传递的参数将直接在Observer的onNext()方法中接收到。
     */
    private void onJust(){
        Observable<String>  observable = Observable.just("hello");
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                Log.e(TAG, "我来接受消息: "+ s);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);

    }

    /***
     * 使用fromIterable()，遍历集合，发送每个item。相当于多次回调onNext()方法，每次传入一个item。
     *
     * 注意：Collection接口是Iterable接口的子接口，
     *      所以所有Collection接口的实现类都可以作为Iterable对象直接传入fromIterable()方法。
     */
    private void onFromIterable(){
        List<String> list = new ArrayList<String>();
        for(int i =0;i<10;i++){
            list.add("Hello"+i);
        }
        Observable<String> observable = Observable.fromIterable(list);
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                Log.e(TAG, "我来接受消息: "+ s);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);
    }

    /***
     * 当观察者订阅时，才创建Observable，并且针对
     *              每个观察者创建都是一个新的Observable。
     * 以何种方式创建这个Observable对象，当满足回调条件后，就会进行相应的回调。
     *
     */
    private void onDefer(){
        Observable<String> observable = Observable.defer(new Callable<ObservableSource<? extends String>>() {
            @Override
            public ObservableSource<? extends String> call() throws Exception {
                return Observable.just("hello");
            }
        });
        /************/
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                Log.e(TAG ,"observer: " +  s);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);//执行订阅

    }

    /***
     * 创建一个按固定时间间隔发射整数序列的Observable，可用作定时器。
     * 即按照固定2秒一次调用onNext()方法。
     *
     */
    private void  onInterval(){
        Observable<Long> observable = Observable.interval(2 , TimeUnit.SECONDS);
        Observer<Long> observer = new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Long aLong) {
                Log.e(TAG , "两秒执行一次: " + aLong);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);

    }

    /***
     * 创建一个发射特定整数序列的Observable，第一个参数为起始值，第二个为发送的个数，
     * 如果为 0 则不发送，负数则抛异常。
     * 下述表示发射1到20的数。
     * 即调用20次nNext()方法，依次传入1-20数字。
     */
    private void onRange(){
        Observable<Integer> observable = Observable.range(1 , 20) ;
        Observer<Integer> observer  = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer integer) {

                Log.e(TAG , "num: " + integer);

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);

    }

    /***
     * 创建一个Observable，它在一个给定的延迟后发射一个特殊的值，
     * 即表示延迟2秒后，调用onNext()方法。
     */
    private void onTimer(){
        Observable.timer(2 , TimeUnit.SECONDS)
                //订阅
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Long aLong) {
                        Log.e(TAG , "两秒后调用我");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    /****
     * 创建一个Observable，该Observable的事件可以重复调用。
     */
    private void onRepeat(){
        Observable<Integer> observable = Observable.just(123).repeat(2);//重复调用2次
        Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer integer) {
                Log.e(TAG , "收款： " + integer  );
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);

    }

    /***
     * 其中Consumer中的accept()方法接收一个来自Observable的单个值
     * Consumer就是一个观察者。
     */
    private void onConsumer(){
        Observable.just("hello")
                .subscribe( new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        System.out.println(s);
                    }
        });
    }







}
