package com.bumslap.bum.POSproject;

import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumslap.bum.DB.CardItem;
import com.bumslap.bum.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bum on 12/18/17.
 */

public class CardPagerAdapter extends PagerAdapter implements CardAdapter {



        private List<CardView> mViews;
        private List<CardItem> mData;
        private float mBaseElevation;

        public CardPagerAdapter() {
            mData = new ArrayList<>();
            mViews = new ArrayList<>();
        }

        public void addCardItem(CardItem item) {
            mViews.add(null);
            mData.add(item);
        }

        public float getBaseElevation() {
            return mBaseElevation;
        }

        @Override
        public CardView getCardViewAt(int position) {
            return mViews.get(position);
        }

        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(final ViewGroup container, final int position) {
            View view = LayoutInflater.from(container.getContext())
                    .inflate(R.layout.activity_fragment_main, container, false);
            container.addView(view);
            bind(mData.get(position), view);
            CardView cardView = (CardView) view.findViewById(R.id.cardView);

          /*  if (mBaseElevation == 0) {
                mBaseElevation = cardView.getCardElevation();
            }*/
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    if (position == 0){
                        Intent intent = new Intent(MainActivity.context,TutorialPage.class);
                        MainActivity.context.startActivity(intent);
                        Toast.makeText(MainActivity.context,String.valueOf(position),Toast.LENGTH_SHORT).show();
                    } else if(position == 1){
                        //MainActivity.mViewPager.setCurrentItem(getItem(+1),true);

                        Toast.makeText(MainActivity.context,String.valueOf(position),Toast.LENGTH_SHORT).show();

                    }
                    else if(position == 2){

                        Toast.makeText(MainActivity.context,String.valueOf(position),Toast.LENGTH_SHORT).show();}
                    else if(position == 3){


                        Toast.makeText(MainActivity.context,String.valueOf(position),Toast.LENGTH_SHORT).show();}
                }
            });

          //  cardView.setMaxCardElevation(mBaseElevation * MAX_ELEVATION_FACTOR);
            mViews.set(position, cardView);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
            mViews.set(position, null);
        }

        private void bind(CardItem item, View view) {
            ImageView mainImageView = (ImageView) view.findViewById(R.id.imageView3);
            mainImageView.setImageResource(item.getText());


            ViewPager mViewPager = (ViewPager) view.findViewById(R.id.viewPager);
        }


}
