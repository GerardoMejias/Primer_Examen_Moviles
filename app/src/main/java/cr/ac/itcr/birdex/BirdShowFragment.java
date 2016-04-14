package cr.ac.itcr.birdex;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import cr.ac.itcr.birdex.access_data.Connexion;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BirdShowFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BirdShowFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BirdShowFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public BirdShowFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BirdShowFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BirdShowFragment newInstance(String param1, String param2) {
        BirdShowFragment fragment = new BirdShowFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bird_show, container, false);
        Button addButton = (Button) view.findViewById(R.id.btnEditBird);
        final TextView txtBirds = (TextView) view.findViewById(R.id.txtBirds);
        addButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                /*
                 * Al hacer click, se realiza la conexión con la base de datos
                 * luego se seleccionan todos los elementos de la tabla birds en un cursor
                 * y luego se muestran en pantalla*/

                Connexion connexion = new Connexion(getActivity().getApplicationContext());

                try {
                    SQLiteDatabase db = connexion.getWritableDatabase();
                    if (db != null) {

                        Cursor c = db.rawQuery("SELECT gender, species, name" +
                                " FROM birds", new String[]{"Género", "Especie", "Nombre"});

                        txtBirds.setText(c.toString());
                    }
                } catch(Exception e){
                    Log.d("error", e.getMessage());
                }
            }
        });
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
