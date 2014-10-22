var rule_group_name = function(group_name) {
	switch (group_name) {
	case "com.codeminders.labs.timeextractor.rules.timeofday":
		return "Time of day";
	case "com.codeminders.labs.timeextractor.rules.duration":
		return "Duration";
	case "com.codeminders.labs.timeextractor.rules.frequency":
		return "Frequency";
	case "com.codeminders.labs.timeextractor.rules.date":
		return "Date";
	case "com.codeminders.labs.timeextractor.rules.duration.interval":
		return "Duration Interval";
	case "com.codeminders.labs.timeextractor.rules.duration.interval":
		return "Duration Interval";
	case "com.codeminders.labs.timeextractor.rules.date.relative":
		return "Relative dates";
	case "com.codeminders.labs.timeextractor.rules.time":
		return "Time";
	case "com.codeminders.labs.timeextractor.rules.dateinterval":
		return "Date interval";
	case "com.codeminders.labs.timeextractor.rules.repeated":
		return "Repeated";
	case "com.codeminders.labs.timeextractor.rules.date.dayofweek":
		return "Day of week";
	case "com.codeminders.labs.timeextractor.rules.season":
		return "Season";
	case "com.codeminders.labs.timeextractor.rules.timezone":
		return "Timezone";
	case "com.codeminders.labs.timeextractor.rules.timeinterval":
		return "Time intervals";
	case "com.codeminders.labs.timeextractor.rules.weekend":
		return "Weekends";

	default:
		return group_name;
	}
}
