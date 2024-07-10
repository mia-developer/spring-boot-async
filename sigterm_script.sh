curl localhost:8080/v1/samples/async &

sleep 1

PID=$(lsof -t -i:8080 | head -n 1)

if [ -z "$PID" ]; then
    echo "Application is not running"
else
    echo "Terminating application with PID ${PID}"
    kill -15 "$PID"
fi
