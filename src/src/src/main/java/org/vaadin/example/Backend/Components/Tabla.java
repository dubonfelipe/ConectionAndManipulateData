package org.vaadin.example.Backend.Components;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Section;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.ListDataProvider;

import java.util.ArrayList;
import java.util.List;

@Tag("tabla")
public class Tabla<E> extends FlexLayout {
    private Grid<E> grid;
    private String id;
    private HorizontalLayout infoComponente;
    private List<E> lista;
    private List<GridListener> gridListeners;

    public Tabla(String id){
        this.id = id;
        setSizeFull();
        add(contenido());
    }

    private Component contenido(){
        gridListeners = new ArrayList<>();
        setMinHeight("0px");
         grid = new Grid<>();
         grid.setMinHeight("0px");
         grid.setSelectionMode(Grid.SelectionMode.SINGLE);
         grid.setId(id);
         infoComponente = new HorizontalLayout(new Label("No se encontraron datos"));
         infoComponente.setWidthFull();
         infoComponente.setAlignItems(Alignment.CENTER);

         FlexLayout layout = new FlexLayout(grid,infoComponente);
         layout.setFlexDirection(FlexDirection.COLUMN);
         layout.setSizeFull();

         grid.setWidthFull();
         grid.setVisible(false);
         infoComponente.setVisible(false);
         return new Section(layout);
    }

    public void llenar(List<E> list){
        this.lista = list;

        /*grid.setDataProvider(DataProvider.fromCallbacks(
                query -> {
                    int offset = query.getOffset();
                    int limit = query.getLimit();
                    if(offset + limit<= list.size()){
                        return  list.subList(offset, offset+limit).stream();
                    }
                    return list.subList(offset,list.size()).stream();
                },query -> list.size()
        ));*/
        ListDataProvider<E> dataProvider = new ListDataProvider<>(list);
        grid.setDataProvider(dataProvider);

        grid.setHeightByRows(true);
        grid.setVisible(!list.isEmpty());
        infoComponente.setVisible(list.isEmpty());
    }

    public void render(){
        grid.removeAllColumns();
        if(gridListeners != null){
            for(GridListener listener : gridListeners){
                listener.listener(grid);
            }
        }
    }

    public void addGridListener(GridListener<E> gridListener){
        this.gridListeners.add(gridListener);
    }
    public interface GridListener<E>{
        void listener(Grid<E> grid);
    }
}
