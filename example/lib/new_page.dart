import 'package:flutter/material.dart';
import 'package:lavpn/lavpn.dart';

import 'main.dart';

class NewPage extends StatelessWidget {
  static String subPath = '/page';

  final String page;
  final bool navigate;
  NewPage(this.page, this.navigate);
  @override
  Widget build(BuildContext context) {
    if (page == '0')
      Lavpn.init(
        localizedDescription: "ExampleVPN",
        providerBundleIdentifier:
        "com.topfreelancerdeveloper.flutterOpenvpnExample.RunnerExtension",
      ).then((value) {
        print(value);
      });
    if (page == '2') MyApp.initPlatformState();
    if (navigate) {
      Future.delayed(Duration(seconds: 3), () {
        Navigator.of(context)
            .popAndPushNamed(subPath + (int.parse(page) + 1).toString());
      });
    }
    return Scaffold(
      body: Center(
        child: Center(
          child: Text(
            page.toString(),
          ),
        ),
      ),
    );
  }
}
