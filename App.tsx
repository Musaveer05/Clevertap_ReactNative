/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 */

// export default App;
import React, { useEffect } from 'react';
import { View, Button, StyleSheet, Alert } from 'react-native';
// import { requestAndroidNotificationPermission } from './src/push_permission'; // import your file
import CleverTap from 'clevertap-react-native';

const App = () => {
  CleverTap.setDebugLevel(2);
  useEffect(() => {
    CleverTap.createNotificationChannel(
      'CtRNS',
      'CleverTap React Native Testing',
      'CT React Native Testing',
      5,
      true,
    );

    CleverTap.addListener(
      CleverTap.CleverTapPushNotificationClicked,
      (event: Record<string, any>) => {
        console.log('Push notification clicked:', event);
        Alert.alert('Notification Clicked', JSON.stringify(event));
      },
    );
  }, []);

  const handlePushPress = () => {
    Alert.alert('Button Pressed', 'You pressed the Push button!');
  };

  return (
    <View style={styles.container}>
      <Button title="Push" onPress={handlePushPress} />
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: 'black',
  },
});

export default App;
