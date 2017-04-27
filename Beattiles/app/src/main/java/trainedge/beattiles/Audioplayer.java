package trainedge.beattiles;

import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.util.Log;

import be.tarsos.dsp.AudioEvent;
import be.tarsos.dsp.AudioProcessor;
import be.tarsos.dsp.io.TarsosDSPAudioFormat;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.util.Log;

import be.tarsos.dsp.AudioEvent;
import be.tarsos.dsp.AudioProcessor;
import be.tarsos.dsp.io.TarsosDSPAudioFloatConverter;
import be.tarsos.dsp.io.TarsosDSPAudioFormat;

/**
 * Created by ankit on 26-Apr-17.
 */

public class Audioplayer {





    public class AndroidAudioPlayer implements AudioProcessor {

        public static final int DEFAULT_STREAM_TYPE = AudioManager.STREAM_MUSIC;
        private static final String TAG = "AndroidAudioPlayer";

        private final AudioTrack audioTrack;



        public AndroidAudioPlayer(TarsosDSPAudioFormat audioFormat, int bufferSizeInSamples, int streamType) {
            if (audioFormat.getChannels() != 1) {
                throw new IllegalArgumentException("TarsosDSP only supports mono audio channel count: " + audioFormat.getChannels());
            }

            // The requested sample rate
            int sampleRate = (int) audioFormat.getSampleRate();

            //The buffer size in bytes is twice the buffer size expressed in samples if 16bit samples are used:
            int bufferSizeInBytes = bufferSizeInSamples * audioFormat.getSampleSizeInBits()/8;


            int minBufferSizeInBytes = AudioTrack.getMinBufferSize(sampleRate, AudioFormat.CHANNEL_OUT_MONO,  AudioFormat.ENCODING_PCM_16BIT);
            if(minBufferSizeInBytes > bufferSizeInBytes){
                throw new IllegalArgumentException("The buffer size should be at least " + (minBufferSizeInBytes/(audioFormat.getSampleSizeInBits()/8)) + " (samples) according to  AudioTrack.getMinBufferSize().");
            }

            //http://developer.android.com/reference/android/media/AudioTrack.html#AudioTrack(int, int, int, int, int, int)
            audioTrack = new AudioTrack(streamType, sampleRate, AudioFormat.CHANNEL_OUT_MONO, AudioFormat.ENCODING_PCM_16BIT, bufferSizeInBytes,AudioTrack.MODE_STREAM);

            audioTrack.play();
        }

        /**
         * Constructs a new AndroidAudioPlayer from an audio format.
         *
         * @param audioFormat The audio format that this AndroidAudioPlayer will process.
         * @see AndroidAudioPlayer#AndroidAudioPlayer(TarsosDSPAudioFormat, int, int)
         */
        public AndroidAudioPlayer(TarsosDSPAudioFormat audioFormat) {
            this(audioFormat, 4096, DEFAULT_STREAM_TYPE);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean process(AudioEvent audioEvent) {
            int overlapInSamples = audioEvent.getOverlap();
            int stepSizeInSamples = audioEvent.getBufferSize() - overlapInSamples;
            byte[] byteBuffer = audioEvent.getByteBuffer();

            //int ret = audioTrack.write(audioEvent.getFloatBuffer(),overlapInSamples,stepSizeInSamples,AudioTrack.WRITE_BLOCKING);
            int ret = audioTrack.write(byteBuffer,overlapInSamples*2,stepSizeInSamples*2);
            if (ret < 0) {
                Log.e(TAG, "AudioTrack.write returned error code " + ret);
            }
            return true;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void processingFinished() {
            audioTrack.flush();
            audioTrack.stop();
            audioTrack.release();
        }
    }
}
