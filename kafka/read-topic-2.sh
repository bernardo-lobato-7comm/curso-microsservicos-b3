docker exec --interactive --tty broker \
kafka-console-consumer --bootstrap-server broker:9092 \
                       --topic helloWorld \
		       --consumer-property group.id=helloWorld-2 \
		       --from-beginning