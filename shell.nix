{pkgs ? import <nixpkgs> {}}:

with pkgs;

mkShell {
	SHELL_NAME="Trawler2.0";
	buildInputs = [ bedtools perl perlPackages.DevelNYTProf  perlPackages.CGI
	(callPackage ~/.scripts/Nix/CustomPackages/AlgorithmCluster.nix { }) 
	  jdk zip flamegraph imagemagick cfr ];
}
