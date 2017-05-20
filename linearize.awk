#!/usr/bin/awk -f
{ if ( $0 ~ /lcd1/) { this=$0; getline nextLine getline nextLine2; printf("%s%s%s\n", this, nextLine, nextLine2) }}
