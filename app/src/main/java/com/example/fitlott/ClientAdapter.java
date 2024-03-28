package com.example.fitlott;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class ClientAdapter extends RecyclerView.Adapter<ClientAdapter.ClientViewHolder> {

    private List<ClientData> clients;

    public ClientAdapter(List<ClientData> clients){
        this.clients = clients;
    }
    @NonNull
    @Override
    public ClientAdapter.ClientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.client_item, parent, false);
        return new ClientAdapter.ClientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClientAdapter.ClientViewHolder holder, int position) {
        ClientData client = clients.get(position);
        holder.clientName.setText(client.clientName);
        holder.clientEmail.setText(client.clientEmail);
    }

    @Override
    public int getItemCount() {
        return clients.size();
    }

    public class ClientViewHolder extends RecyclerView.ViewHolder {

        TextView clientEmail;
        TextView clientName;
        ClientViewHolder(View itemView){
            super(itemView);
            clientEmail = itemView.findViewById(R.id.clientEmail);
            clientName = itemView.findViewById(R.id.clientName);
        }
    }

}
