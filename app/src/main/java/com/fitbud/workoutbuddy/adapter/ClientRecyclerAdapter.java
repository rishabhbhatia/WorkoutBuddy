package com.fitbud.workoutbuddy.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fitbud.workoutbuddy.R;
import com.fitbud.workoutbuddy.models.Client;
import com.fitbud.workoutbuddy.ui.activities.WorkoutBuddyActivity;

import java.util.ArrayList;

/**
 * Created by Rishabh Bhatia on 6/6/2016.
 */
public class ClientRecyclerAdapter extends RecyclerView.Adapter<ClientRecyclerAdapter.ViewHolder> {

    private WorkoutBuddyActivity activity;
    private ArrayList<Client> clients;

    public ClientRecyclerAdapter(WorkoutBuddyActivity workoutBuddyActivity, ArrayList<Client> clients) {
        this.activity = workoutBuddyActivity;
        this.clients = clients;
    }

    @Override
    public ClientRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_client, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ClientRecyclerAdapter.ViewHolder holder, int position) {
        holder.textView.setText(clients.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return clients.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void add(Client client) {
        insert(client, clients.size());
    }

    public void insert(Client client, int position) {
        clients.add(position, client);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        clients.remove(position);
        notifyItemRemoved(position);
    }

    public void clear() {
        int size = clients.size();
        clients.clear();
        notifyItemRangeRemoved(0, size);
    }

    public void addAll(ArrayList<Client> newClients) {
        int startIndex = clients.size();
        clients.addAll(startIndex, newClients);
        notifyItemRangeInserted(startIndex, newClients.size());
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.tv_row_client_name);
        }
    }
}
