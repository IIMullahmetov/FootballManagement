package ru.kpfu.itis.android.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import ru.kpfu.itis.android.R;
import ru.kpfu.itis.android.activities.ChampionshipActivity;
import ru.kpfu.itis.android.adapters.ChampionshipAdapter;
import ru.kpfu.itis.android.adapters.TableAdapter;
import ru.kpfu.itis.android.models.Championship;
import ru.kpfu.itis.android.models.News;
import ru.kpfu.itis.android.models.Table;
import ru.kpfu.itis.android.models.TableChampionship;
import ru.kpfu.itis.android.models.TableItem;

public class TableChampionshipFragment extends Fragment {
    private RecyclerView rvTable;
    private TableAdapter tableAdapter;
    private Context context;
    private List<TableChampionship> tableChampionships;

    public static TableChampionshipFragment newInstance(Context context, List<TableChampionship> tableChampionships) {
        TableChampionshipFragment tableChampionshipFragment = new TableChampionshipFragment();
        tableChampionshipFragment.setTableChampionships(tableChampionships);
        tableChampionshipFragment.setContext(context);
        return tableChampionshipFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_table_championship, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bind(view);

        rvTable.setLayoutManager(new LinearLayoutManager(context));
        tableAdapter = new TableAdapter(context);
        List<TableItem> tables = new ArrayList<>();

        for (TableChampionship tableChampionship:tableChampionships){
            //Todo вместо голов очки
            tables.add(new TableItem(tableChampionship.getId(), tableChampionship.getImage(), tableChampionship.getName(), "6",
                    "6", "0", "0", String.valueOf(tableChampionship.getGoals())));
        }
//        tables.add(new TableItem("-", "ЦСКАxzczx", "6", "6", "0", "0", "18"));
//        tables.add(new TableItem("-", "ЦСКА", "6", "6", "0", "0", "18"));
//        tables.add(new TableItem("-", "ЦСКА", "6", "6", "0", "0", "18"));
//        tables.add(new TableItem("-", "ЦСКА", "6", "6", "0", "0", "18"));

        tableAdapter.setTable(tables);
        rvTable.setAdapter(tableAdapter);

    }

    private void bind(View view) {
        rvTable = view.findViewById(R.id.rv_championship_table);

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("2017/2018");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout
                .simple_spinner_dropdown_item);

        Spinner spinner = view.findViewById(R.id.spinner);
        spinner.setAdapter(arrayAdapter);
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setTableChampionships(List<TableChampionship> tableChampionships) {
        this.tableChampionships = tableChampionships;
    }
}
