FROM ubuntu:latest
LABEL authors="memo_"

ENTRYPOINT ["top", "-b"]