#!/usr/bin/awk -f
{
	if ( $0 ~ /lcd1/) {
		this=$0; getline nextLine getline nextLine2; line=sprintf("%s%s%s\n", this, nextLine, nextLine2);
		split(line, a, "\"")
		print a[2] ";" a[4] ";" a[6] ";" a[8] ";" a[10] ";" a[12] ";" a[14] ";" a[16] ";" a[18] ";" a[20] ";" a[22] ";" a[24]
	}
}
