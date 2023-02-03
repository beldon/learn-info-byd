package me.beldon.learninfo;

import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import androidx.navigation.fragment.NavHostFragment;

import me.beldon.learninfo.databinding.FragmentFirstBinding;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    private boolean light = false;

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaPlayer mediaPlayer = MediaPlayer.create(FirstFragment.this.getContext(), R.raw.abb);
                mediaPlayer.start();
            }
        });

        CameraManager cameraManager = (CameraManager) this.getContext().getSystemService(Context.CAMERA_SERVICE);

        binding.light.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (!light) {
                        cameraManager.setTorchMode("0", true);
                        binding.light.setText("关灯");
                        light = true;
                    } else {
                        cameraManager.setTorchMode("0", false);
                        binding.light.setText("开灯");
                        light = false;
                    }
                } catch (CameraAccessException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}