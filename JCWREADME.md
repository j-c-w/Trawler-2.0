We just use the builtin data on this one, the run_trawler.sh script is actually quite good.

1. Get the  nix-shell, that should have all the bits you need --- although it also needs the AlgorithmCluster build script which is in github.com/j-c-w/config
1. Run ./run_trawler.sh, and the command that it prints.
1. nytprofhtml nytprof.out
1. There's also a java profile, which ends up taking most of the time in the first profile.
1. To get that, run vim java_run_profile.hprof and go to the end.
1. You can use cfr to decompile the jar and have a look inside.
	It's cfr lib/pecan.jar > decompiled_output.java
