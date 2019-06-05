/**<ul>
 * <li>ForecastRestYahooSax</li>
 * <li>com.android2ee.formation.restservice.sax.forecastyahoo</li>
 * <li>28 mai 2014</li>
 *
 * <li>======================================================</li>
 *
 * <li>Projet : Mathias Seguy Project</li>
 * <li>Produit par MSE.</li>
 *
 /**
 * <ul>
 * Android Tutorial, An <strong>Android2EE</strong>'s project.</br> 
 * Produced by <strong>Dr. Mathias SEGUY</strong>.</br>
 * Delivered by <strong>http://android2ee.com/</strong></br>
 *  Belongs to <strong>Mathias Seguy</strong></br>
 ****************************************************************************************************************</br>
 * This code is free for any usage but can't be distribute.</br>
 * The distribution is reserved to the site <strong>http://android2ee.com</strong>.</br>
 * The intelectual property belongs to <strong>Mathias Seguy</strong>.</br>
 * <em>http://mathias-seguy.developpez.com/</em></br> </br>
 *
 * *****************************************************************************************************************</br>
 *  Ce code est libre de toute utilisation mais n'est pas distribuable.</br>
 *  Sa distribution est reservée au site <strong>http://android2ee.com</strong>.</br> 
 *  Sa propriété intellectuelle appartient à <strong>Mathias Seguy</strong>.</br>
 *  <em>http://mathias-seguy.developpez.com/</em></br> </br>
 * *****************************************************************************************************************</br>
 */

package com.android2ee.formation.init.firsttoulouseproject.arrayadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android2ee.formation.init.firsttoulouseproject.R;
import com.android2ee.formation.init.firsttoulouseproject.model.Human;

import java.util.ArrayList;

/**
 * Created by Mathias Seguy - Android2EE on 30/03/2015.
 */
public class HumanAdapter extends ArrayAdapter<Human> {
    /**
     * The inflter to inflate the xml and create a view
     */
    LayoutInflater inflater = null;

    /**
     * Constructor
     *
     * @param context  The current context.
     * @param resource The resource ID for a layout file containing a TextView to use when
     */
    public HumanAdapter(Context context, ArrayList<Human> list) {
        super(context, R.layout.human_even, list);
        inflater = LayoutInflater.from(context);
    }

    /*******************************************************************/
    /*******************************************************************/
    /***********Managing The View                          *****************/
    /*****************************************************************/
    /**
     * The displayed human
     */
    private Human humanTemp;
    /**
     * The View returned to the listView
     */
    private View rowView;
    /**
     * The ViewHolder linked with the view
     */
    private ViewHolder vh;

    /**
     * {@inheritDoc}
     *
     * @param position
     * @param convertView
     * @param parent
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        humanTemp=getItem(position);
        rowView=convertView;
        if(rowView==null){
            if(getItemViewType(position)==0){
                rowView=inflater.inflate(R.layout.human_even, parent, false);
            }else{
                rowView=inflater.inflate(R.layout.human_odd, parent, false);
            }
            //create the viewHolder
            vh=new ViewHolder(rowView);
            rowView.setTag(vh);
        }
        vh=(ViewHolder)rowView.getTag();
        vh.getImvPicture().setBackgroundResource(humanTemp.getPictureId());
        vh.getTxvName().setText(humanTemp.getName());
        vh.getTxvMessage().setText(humanTemp.getMessage());

        return rowView;
    }

    @Override
    public int getItemViewType(int position) {
        //my business rule
        return position%2;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    /*******************************************************************/
    /*******************************************************************/
    /***********ViewHolder Pattern                    *****************/
    /*****************************************************************/
    private class ViewHolder{
        private View theView;
        private TextView txvName=null;
        private TextView txvMessage=null;
        private ImageView imvPicture=null;

        private ViewHolder(View theView) {
            this.theView = theView;
        }

        public TextView getTxvName() {
            if(txvName==null){
                txvName= (TextView) theView.findViewById(R.id.txvName);
            }
            return txvName;
        }

        public TextView getTxvMessage() {
            if(txvMessage==null){
                txvMessage= (TextView) theView.findViewById(R.id.txvMessage);
            }
            return txvMessage;
        }

        public ImageView getImvPicture() {
            if(imvPicture==null){
                imvPicture= (ImageView) theView.findViewById(R.id.imvHumanPicture);
            }
            return imvPicture;
        }
    }
}
