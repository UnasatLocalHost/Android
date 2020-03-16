package sr.unasat.bproduct.API;

import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import sr.unasat.bproduct.R;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
   private Context mContext;
   private ArrayList<Service>mServicesList;


   public Adapter(Context context, ArrayList<Service> servicesList) {
       mContext = context;
       mServicesList =servicesList;
   }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
           Service currentItem = mServicesList.get(position);

           String imageUrl = currentItem.getImageUrl();
           String CreatorName = currentItem.getCreator();
           int likeCount = currentItem.getLikeCount();

           holder.mTextViewCreator.setText(CreatorName);
           holder.mTextViewLikes.setText("Likes: "+likeCount);
        Picasso.with(mContext).load(imageUrl).fit().centerInside().into(holder.mImageView);

    }

    @Override
    public int getItemCount() {
        return (mServicesList== null) ? 0 : mServicesList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView mTextViewCreator;
        public TextView mTextViewLikes;

        public ViewHolder(View itemView) {
            super(itemView);
            mImageView =itemView.findViewById(R.id.image_view);
            mTextViewCreator = itemView.findViewById(R.id.text_view_creator);
            mTextViewLikes = itemView.findViewById(R.id.text_view_downloads);
        }
    }
}
