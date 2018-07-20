package com.kx.studyview.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.kx.studyview.R;
import com.kx.studyview.utils.LogUtils;
import com.kx.studyview.utils.RenderScriptGaussianBlur;
import com.kx.studyview.views.SuperButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * 高斯图片模糊
 */
public class RenderScriptActivity extends AppCompatActivity {

    @BindView(R.id.superButton)
    SuperButton superButton;
    @BindView(R.id.iv)
    ImageView iv;
    private Bitmap mBitmap;
    private RenderScriptGaussianBlur mRenderScriptGaussianBlur;
    private int mBlurRadius;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_render_script);
        mBlurRadius = 1;
        mRenderScriptGaussianBlur = new RenderScriptGaussianBlur(this);
        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.render_script_blur_before);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.superButton)
    public void onSuperButtonClick(){
        if (mBitmap!=null && mBlurRadius <=25){
            Disposable subscribe = Observable.create(new ObservableOnSubscribe<Bitmap>() {
                @Override
                public void subscribe(ObservableEmitter<Bitmap> emitter) throws Exception {
                    LogUtils.e("当前blurRadius = " + mBlurRadius);
                    //对图片进行高斯模糊处理
                    Bitmap bitmap = mRenderScriptGaussianBlur.blur(mBlurRadius, BitmapFactory.decodeResource(getResources(), R.mipmap.render_script_blur_before));
                    mBlurRadius++;
                    if (mBlurRadius > 15) {
                        mBlurRadius = 1;
                    }
                    emitter.onNext(bitmap);
                    emitter.onComplete();
                }
            }).subscribeOn(Schedulers.computation()) //指定运算线程
                    .observeOn(AndroidSchedulers.mainThread()) //切换回主线程
                    .subscribe(new Consumer<Bitmap>() {
                        @Override
                        public void accept(Bitmap bitmap) throws Exception {
                            iv.setImageBitmap(bitmap);
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                            LogUtils.e(throwable.toString());
                        }
                    });

        }
    }


}
