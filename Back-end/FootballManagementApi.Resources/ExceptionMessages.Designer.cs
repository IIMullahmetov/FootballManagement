﻿//------------------------------------------------------------------------------
// <auto-generated>
//     This code was generated by a tool.
//     Runtime Version:4.0.30319.42000
//
//     Changes to this file may cause incorrect behavior and will be lost if
//     the code is regenerated.
// </auto-generated>
//------------------------------------------------------------------------------

namespace FootballManagementApi.Resources {
    using System;
    
    
    /// <summary>
    ///   A strongly-typed resource class, for looking up localized strings, etc.
    /// </summary>
    // This class was auto-generated by the StronglyTypedResourceBuilder
    // class via a tool like ResGen or Visual Studio.
    // To add or remove a member, edit your .ResX file then rerun ResGen
    // with the /str option, or rebuild your VS project.
    [global::System.CodeDom.Compiler.GeneratedCodeAttribute("System.Resources.Tools.StronglyTypedResourceBuilder", "15.0.0.0")]
    [global::System.Diagnostics.DebuggerNonUserCodeAttribute()]
    [global::System.Runtime.CompilerServices.CompilerGeneratedAttribute()]
    public class ExceptionMessages {
        
        private static global::System.Resources.ResourceManager resourceMan;
        
        private static global::System.Globalization.CultureInfo resourceCulture;
        
        [global::System.Diagnostics.CodeAnalysis.SuppressMessageAttribute("Microsoft.Performance", "CA1811:AvoidUncalledPrivateCode")]
        internal ExceptionMessages() {
        }
        
        /// <summary>
        ///   Returns the cached ResourceManager instance used by this class.
        /// </summary>
        [global::System.ComponentModel.EditorBrowsableAttribute(global::System.ComponentModel.EditorBrowsableState.Advanced)]
        public static global::System.Resources.ResourceManager ResourceManager {
            get {
                if (object.ReferenceEquals(resourceMan, null)) {
                    global::System.Resources.ResourceManager temp = new global::System.Resources.ResourceManager("FootballManagementApi.Resources.ExceptionMessages", typeof(ExceptionMessages).Assembly);
                    resourceMan = temp;
                }
                return resourceMan;
            }
        }
        
        /// <summary>
        ///   Overrides the current thread's CurrentUICulture property for all
        ///   resource lookups using this strongly typed resource class.
        /// </summary>
        [global::System.ComponentModel.EditorBrowsableAttribute(global::System.ComponentModel.EditorBrowsableState.Advanced)]
        public static global::System.Globalization.CultureInfo Culture {
            get {
                return resourceCulture;
            }
            set {
                resourceCulture = value;
            }
        }
        
        /// <summary>
        ///   Looks up a localized string similar to Comment with the specified identifier does not exist.
        /// </summary>
        public static string CommentNotFound {
            get {
                return ResourceManager.GetString("CommentNotFound", resourceCulture);
            }
        }
        
        /// <summary>
        ///   Looks up a localized string similar to Email already in use.
        /// </summary>
        public static string EmailAlreadyUse {
            get {
                return ResourceManager.GetString("EmailAlreadyUse", resourceCulture);
            }
        }
        
        /// <summary>
        ///   Looks up a localized string similar to It is forbidden to change the email.
        /// </summary>
        public static string ForbiddenToChangeEmail {
            get {
                return ResourceManager.GetString("ForbiddenToChangeEmail", resourceCulture);
            }
        }
        
        /// <summary>
        ///   Looks up a localized string similar to Invalid email format.
        /// </summary>
        public static string InvalidEmailFormat {
            get {
                return ResourceManager.GetString("InvalidEmailFormat", resourceCulture);
            }
        }
        
        /// <summary>
        ///   Looks up a localized string similar to Some message about invalid password.
        /// </summary>
        public static string InvalidPassword {
            get {
                return ResourceManager.GetString("InvalidPassword", resourceCulture);
            }
        }
        
        /// <summary>
        ///   Looks up a localized string similar to Invalid registration guid.
        /// </summary>
        public static string InvalidRegistrationGuid {
            get {
                return ResourceManager.GetString("InvalidRegistrationGuid", resourceCulture);
            }
        }
        
        /// <summary>
        ///   Looks up a localized string similar to Match with the specified identifier does not exist.
        /// </summary>
        public static string MatchNotFound {
            get {
                return ResourceManager.GetString("MatchNotFound", resourceCulture);
            }
        }
        
        /// <summary>
        ///   Looks up a localized string similar to Passwords do not match.
        /// </summary>
        public static string PasswordsNotMatch {
            get {
                return ResourceManager.GetString("PasswordsNotMatch", resourceCulture);
            }
        }
        
        /// <summary>
        ///   Looks up a localized string similar to Post with the specified identifier does not exist.
        /// </summary>
        public static string PostNotFound {
            get {
                return ResourceManager.GetString("PostNotFound", resourceCulture);
            }
        }
        
        /// <summary>
        ///   Looks up a localized string similar to This email already in use with different registration type.
        /// </summary>
        public static string WrongRegistrationType {
            get {
                return ResourceManager.GetString("WrongRegistrationType", resourceCulture);
            }
        }
    }
}
