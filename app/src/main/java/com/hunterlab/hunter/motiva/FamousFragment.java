package com.hunterlab.hunter.motiva;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FamousFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FamousFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FamousFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private DatabaseReference rootRef,childRef;
    private ArrayList<Author> authorArrayList=new ArrayList<>();

//    private AuthorAdapter authorAdapter;

//    String[] strings = {"Alan Turing","Albert Einstein", "Bernard Brauch", "Confucius" ,"Nikolas Tesla","Zed Zepplin" };

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private AuthorAdapter authorAdapter;
    private OnFragmentInteractionListener mListener;

    public FamousFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FamousFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FamousFragment newInstance(String param1, String param2) {
        FamousFragment fragment = new FamousFragment();
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_famous, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView=(RecyclerView)getActivity().findViewById(R.id.recylerview);
        recyclerView.setLayoutManager(linearLayoutManager);
        authorAdapter=new AuthorAdapter(authorArrayList);
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        recyclerView.setAdapter(authorAdapter);

        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(getActivity().getApplicationContext()));
        //   recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity().getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
            Author author= authorArrayList.get(position);
                //Toast.makeText(getActivity().getApplicationContext(),author.getId(),Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getActivity().getApplicationContext(),QuoteActivity.class);
                intent.putExtra("id",author.getId());
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

rootRef.child("author").addValueEventListener(new ValueEventListener() {
    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        for(DataSnapshot authorsnapshot:dataSnapshot.getChildren())
        {
            Author author=authorsnapshot.getValue(Author.class);
            String id=authorsnapshot.getKey();
            author.setId(id);
            authorArrayList.add(author);
        }
        authorAdapter.notifyDataSetChanged();
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }
});
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
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
