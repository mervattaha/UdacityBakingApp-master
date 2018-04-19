package itc.mervat.com.bakingapp.Recipeadapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import itc.mervat.com.bakingapp.R;
import itc.mervat.com.bakingapp.Recipepojo.Recipe;
import itc.mervat.com.bakingapp.Recipepojo.Step;
import java.util.List;


/**
 * Created by Mervat on 2-Feb-18.
 */

public class BakingAdapter extends RecyclerView.Adapter<BakingAdapter.RecyclerViewHolder> {

    List<Step> lSteps;
    private  String recipeName;

    final private ListItemClickListener lOnClickListener;

    public interface ListItemClickListener {
        void onListItemClick(List<Step> stepsOut,int clickedItemIndex,String recipeName);
    }

    public BakingAdapter(ListItemClickListener listener) {
        lOnClickListener =listener;
    }


    public void setMasterRecipeData(List<Recipe> recipesIn, Context context) {
        //lSteps = recipesIn;
        lSteps= recipesIn.get(0).getSteps();
        recipeName=recipesIn.get(0).getName();
        notifyDataSetChanged();
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();

        int layoutIdForListItem = R.layout.baking_detail_items;

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutIdForListItem, viewGroup, false);

        RecyclerViewHolder viewHolder = new RecyclerViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
       holder.textRecyclerView.setText(lSteps.get(position).getId()+". "+ lSteps.get(position).getShortDescription());

    }

    @Override
    public int getItemCount() {

        return lSteps !=null ? lSteps.size():0 ;
    }

    class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textRecyclerView;

        public RecyclerViewHolder(View itemView) {
            super(itemView);

            textRecyclerView = itemView.findViewById(R.id.shortDescription);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            lOnClickListener.onListItemClick(lSteps,clickedPosition,recipeName);
        }

    }
}
