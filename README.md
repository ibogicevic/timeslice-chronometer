# timeslice-chronometer
A stopwatch for easily measuring fast-switching time spans

This tool has originally been developed to evaluate screen capturing videos that were recorded during controlled experiments in the area of software engineering. We measured how much time the participants used for which specific activity. With timeslice-chronometer it is possible to switch the measurement of different time slices very fast so evaluating the videos could be done in a fraction of the recorded real-time.

## Video Handling

Video handling is done through JavaFX and comes with some limitations. It is only able to play certain video files.

At the time of writing videos may not be bigger than 1920x1080, have the `-pix_fmt yuv420p` option (used in ffmpeg) and use the `h264` codec.
Videos can be converted to this format by using ffmpeg:

`ffmpeg -i input.mp4 -vcodec h264 -vf scale=1920x1080 -an -pix_fmt yuv420p output.mp4`