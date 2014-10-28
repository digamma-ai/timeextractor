var rule_group_name = function(group_name) {
	switch (group_name) {
	case "com.codeminders.labs.timeextractor.rules.timeofday":
		return {
			"type" : "Time of day",
			"example" : "morning, evening, between noon and 3 pm, etc."
		};
	case "com.codeminders.labs.timeextractor.rules.duration":
		return {
			"type" : "Duration",
			"example" : "lasts n days, more than 3 hours, etc"
		};
	case "com.codeminders.labs.timeextractor.rules.date":
		return {
			"type" : "Date",
			"example" : "the second of December, Christmas, New Year, Thanksgiving Day, July the 14th 2014, Fri, 6/27"
		};
	case "com.codeminders.labs.timeextractor.rules.duration.interval":
		return {
			"type" : "Duration Interval",
			"example" : "10-15 month, 11-20 years"
		};
	case "com.codeminders.labs.timeextractor.rules.date.relative":
		return {
			"type" : "Relative dates",
			"example" : "10 month ago, 11 years ago, today, yestrday"
		};
	case "com.codeminders.labs.timeextractor.rules.time":
		return {
			"type" : "Time",
			"example" : "at 5:30 CET, at 5.00, at 11-30"
		};
	case "com.codeminders.labs.timeextractor.rules.dateinterval":
		return {
			"type" : "Date interval",
			"example" : "from 22 to 26 January 2014, from 22 January to 26 March 2014,  mid-late June, from winter to summer"
		};
	case "com.codeminders.labs.timeextractor.rules.repeated":
		return {
			"type" : "Repeated",
			"example" : "every day, every second friday of the month, each month, whole year, all day, full month"
		};
	case "com.codeminders.labs.timeextractor.rules.date.dayofweek":
		return {
			"type" : "Day of week",
			"example" : "Sunday, Monday, Tue, second friday of the month, 2nd monday, etc."
		};
	case "com.codeminders.labs.timeextractor.rules.season":
		return {
			"type" : "Season",
			"example" : "winter, summer, fall, etc."
		};
	case "com.codeminders.labs.timeextractor.rules.timezone":
		return {
			"type" : "Timezone",
			"example" : "UTC, EEST (used only together with time)"
		};
	case "com.codeminders.labs.timeextractor.rules.timeinterval":
		return {
			"type" : "Time intervals",
			"example" : "from 10.00 to 11.00, 20:22 to 23:30, after 19pm, until 5.33 am"
		};
	case "com.codeminders.labs.timeextractor.rules.weekend":
		return {
			"type" : "Weekends",
			"example" : "weekend, weekends, weekday"
		};

	case "past dates":
		return {
			"type" : "Ignore all past dates",
			"example" : "If today is 28/10/2014, then all dates that are less than current will be excluded(ex. 27/10/2014 will be excluded)"
		};
	default:
		return {
			"type" : "group_name",
			"example" : ""
		};
	}
}
