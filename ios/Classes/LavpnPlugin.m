#import "LavpnPlugin.h"
#if __has_include(<lavpn/lavpn-Swift.h>)
#import <lavpn/lavpn-Swift.h>
#else
// Support project import fallback if the generated compatibility header
// is not copied when this plugin is created as a library.
// https://forums.swift.org/t/swift-static-libraries-dont-copy-generated-objective-c-header/19816
#import "lavpn-Swift.h"
#endif

@implementation LavpnPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftLavpnPlugin registerWithRegistrar:registrar];
}
@end
