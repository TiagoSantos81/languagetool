#!/bin/bash

DATADIR=/home/libregrammar/libregrammar.org/libregrammar-website-2018/public/regression-tests
sleep $[ ( $RANDOM % 5 )  + 1 ]s

# Note: we first access the server and ignore it, as HTTPS has quite some (non-deterministic?) overhead.
# If we don't, we get peak times that are caused by HTTPS, not by the API itself...
RUNTIME=`curl -s -w "\nTOTAL_TIME:%{time_total}\n\n" "https://libregrammar.org" "https://libregrammar.org/api/v2/check?language=en-US&text=my+texd" | grep "TOTAL_TIME" | sed 's/TOTAL_TIME://' | tail -n 1`
DATE=`date +"%Y-%m-%d %H:%M:%S"`
echo "$DATE;$RUNTIME" >>$DATADIR/performance-api.log
# 288 per day * 30 days:
tail -n 8640 $DATADIR/performance-api.log >$DATADIR/performance-api-recent.log
/home/libregrammar/api/perf-plot-api.pg >$DATADIR/performance-api.png

# RUNTIME=`curl -s -w "\nTOTAL_TIME:%{time_total}\n\n" "https://libregrammarplus.com/api/v2/languages" "https://libregrammarplus.com/api/v2/check?language=en-US&text=my+texd" | grep "TOTAL_TIME" | sed 's/TOTAL_TIME://' | tail -n 1`
# DATE=`date +"%Y-%m-%d %H:%M:%S"`
# echo "$DATE;$RUNTIME" >>$DATADIR/performance-ltp.log
# tail -n 8640 $DATADIR/performance-ltp.log >$DATADIR/performance-ltp-recent.log

RUNTIME=`curl -s -w "\nTOTAL_TIME:%{time_total}\n\n" "https://api.libregrammar.org/v2/languages" "https://api.libregrammar.org/v2/check?language=en-US&text=my+texd" | grep "TOTAL_TIME" | sed 's/TOTAL_TIME://' | tail -n 1`
DATE=`date +"%Y-%m-%d %H:%M:%S"`
echo "$DATE;$RUNTIME" >>$DATADIR/performance-api-cloud.log
# 8640 = 288 checks per day * 30 days:
tail -n 8640 $DATADIR/performance-api-cloud.log >$DATADIR/performance-api-recent2.log

# RUNTIME=`curl -s -w "\nTOTAL_TIME:%{time_total}\n\n" "https://api.libregrammarplus.com/v2/languages" "https://api.libregrammarplus.com/v2/check?language=en-US&text=my+texd" | grep "TOTAL_TIME" | sed 's/TOTAL_TIME://' | tail -n 1`
# DATE=`date +"%Y-%m-%d %H:%M:%S"`
# echo "$DATE;$RUNTIME" >>$DATADIR/performance-ltp-api-cloud.log
# 8640 = 288 checks per day * 30 days:
# tail -n 8640 $DATADIR/performance-ltp-api-cloud.log >$DATADIR/performance-ltp-api-cloud-recent.log
