package com.example.korefugee.Guide

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.example.korefugee.R
import kotlinx.android.synthetic.main.activity_guide_explain.view.*
import kotlinx.android.synthetic.main.page.view.*

class ViewPagerAdapter2  : PagerAdapter(){
    private var mContext: Context?=null


    fun ViewPagerAdapter(context: Context){
        mContext=context;
    }

    //position에 해당하는 페이지 생성
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        // page.xml 과 연결
        val view= LayoutInflater.from(container.context).inflate(R.layout.page,container,false)


        // position 설정
        view.start_num.text= (1+position).toString()
        view.title.text= "Right"


        view.end_num.text = "5"
        when(position) {
            0 ->
            {view.explaination.text = "If the final review of the application for refugee recognition is still pending, forced repatriation is prohibited."
                view.picture.setImageResource(R.drawable.one_2)}
            1 ->
            {
                view.explaination.text = "Anyone has the right to file an appeal and can get a lawyer for free."
                view.picture.setImageResource(R.drawable.two_2)}
            2 ->
            {view.explaination.text = "A trusted person is allowed to sit in the interview."
                view.explaination2.text = "You can download the necessary documents from the procedure_more doument section."
                view.picture.setImageResource(R.drawable.three_2)}
            3 ->
            {view.explaination.text = "You can receive interpretation support from refugee interpreters of the same gender."
                view.picture.setImageResource(R.drawable.four_2)}
            4 ->
            {view.explaination.text = "You can request to view and copy the data submitted by the refugee applicant and the refugee interview report."
                view.explaination2.text = "You can download the necessary documents from the procedure_more doument section."
                view.picture.setImageResource(R.drawable.five_2)}
        }
        container.addView(view)
        return view
    }

    //position에 위치한 페이지 제거
    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View?)
    }

    //사용가능한 뷰 개수 리턴
    override fun getCount(): Int {
        return 5
    }

    //페이지뷰가 특정 키 객체(key object)와 연관 되는지 여부
    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return (view==`object`)
    }

    override fun setPrimaryItem(container: ViewGroup, position: Int, `object`: Any) {
        super.setPrimaryItem(container, position, `object`)
    }

    override fun getItemPosition(`object`: Any): Int {
        return super.getItemPosition(`object`)
    }

}