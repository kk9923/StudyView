# StudyView
#### 自定义密码输入框PayInPutView的构造方法不能以这种方式去书写，否则会引起EditText软键盘呼不出来的问题，
  >  this(context, attrs,0);
#### 查看AppCompatEditText 发现在其两个参数的构造方法中调用的系统自己的样式,而不是传0

```
    public AppCompatEditText(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.editTextStyle);
    }
```