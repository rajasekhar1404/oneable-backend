# oneable-backend

User Productivity Logs aggregation using mongodb and spring boot

## Mongodb
I created the below query in 5 stages to get the User complete productivity logs information segregated by years, months and days

Query:
db.productivityLog.aggregate([
  {$match: {userid: "oneable1"}},
  {$group: {
    _id: {
      day: {$dateToString: {format: "%Y-%m-%d", date:"$createdDate"}},
      totalDayHours: {$sum: {$subtract: ["$connectionEndTime", "$connectionStartTime"]}},
      month: {$dateToString: {format: "%Y-%m", date:"$createdDate"}},
      year: {$dateToString: {format: "%Y", date: "$createdDate"}}
    }
  }},
  {$group: {
    _id: {
      month: "$_id.month",
      year: "$_id.year"
    },
    month: {$push: {day: "$_id.day", totalDayHours: "$_id.totalDayHours"}},
    totalMonthHours: {$sum: "$_id.totalDayHours"}
  }},
  {$group: {
    _id:{
      year: "$_id.year"
    },
    year: {$push: {month: "$_id.month", days: "$month", totalMonthHours: "$totalMonthHours"}},
    totalYearHours: {$sum: "$totalMonthHours"}
  }},
  {$group: {
    _id: "testoneable1",
    user: {$push: {year: "$_id.year", months: "$year", totalYearHours: "$totalYearHours"}},
    totalUserHours: {$sum: "$totalYearHours"}
  }}
])

## Spring boot:
