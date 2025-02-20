# NotiSave  

NotiSave is an Android app for practicing NotificationListenerService to fetch notifications, RoomDatabase for storage, and Flow for real-time updates.  

## Features  

- Listens for system notifications using `NotificationListenerService`.  
- Stores notifications locally in a database using `RoomDatabase`.  
- Provides real-time updates using `Flow`.  
- Uses an ongoing notification with a foreground service to keep the app alive.  
- Tracks unseen notifications and displays the count for each app.  
- Marks notifications as seen when viewed.  

## Screens  

NotiSave consists of three main screens:  

1. **Groups Screen** – Displays a list of apps that have received notifications, grouped by package name.  
2. **App Notifications Screen** – Shows all notifications related to a selected app.  
3. **Notification Screen** – Displays the full details of a selected notification.  

## How It Works  

1. **Notification Fetching:** The app captures incoming notifications from other apps using `NotificationListenerService`.  
2. **Local Storage:** Notifications are stored in a `RoomDatabase` to ensure persistence.  
3. **Live Updates:** The app observes database changes using `Flow`, providing real-time updates to the UI.  
4. **Foreground Service:** The app runs a persistent service with an ongoing notification to remain active and continue listening for notifications.  

## Contribution  

Contributions are welcome! Feel free to fork the repository, open issues, and submit pull requests.  
