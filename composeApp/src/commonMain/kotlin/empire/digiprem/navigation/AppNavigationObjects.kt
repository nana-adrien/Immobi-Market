              package empire.digiprem.navigation
              /*@Auto import file*/
              import kotlinx.serialization.Serializable
              
                                                                                                                                                                                                                                                                                                                                              /*@Auto Generate Composable*/
                @Serializable
data class ViewCompleteAccount(val scrollPosition: Int = 0,val email: String="")
                @Serializable
data class ViewMessenger(val scrollPosition: Int = 0)
                @Serializable
data class ViewMobileDashBoard(val scrollPosition: Int = 0,val isConnected: Boolean=false)
                @Serializable
data class ViewSplash(val scrollPosition: Int = 0)
                @Serializable
data class ViewPropertyALLProperty(val scrollPosition: Int = 0)
                @Serializable
data class ViewPropertyAddProperty(val scrollPosition: Int = 0)
                @Serializable
data class ViewProfil(val scrollPosition: Int = 0)
                @Serializable
data class ViewProperty(val scrollPosition: Int = 0)
                @Serializable
data class ViewConversations(val scrollPosition: Int = 0)
                @Serializable
data class ViewNegotiations(val scrollPosition: Int = 0)
                @Serializable
data class ViewDetailRealEstateItem( val realEstateId: String = "" ,val scrollPosition: Int = 0)
                @Serializable
data class ViewVerifyIdentity(val scrollPosition: Int = 0,val email:String="")
                @Serializable
data class ViewResetPassword(val scrollPosition: Int = 0)
                @Serializable
data class ViewForgotPassword(val scrollPosition: Int = 0)
                @Serializable
data class ViewRegister(val scrollPosition: Int = 0)
                @Serializable
data class ViewStatistics(val scrollPosition: Int = 0)
                @Serializable
data class ViewLogin(val scrollPosition: Int = 0)
                @Serializable
data class ViewChat(val message: String="", val content: String="",val scrollPosition: Int = 0)
                @Serializable
data class ViewSettings(val scrollPosition: Int = 0)
                @Serializable
data class ViewNotifications(val scrollPosition: Int = 0)
              @Serializable
data class ViewHome(val isConnected: Boolean = false, val realEstateId: String = "", val scrollPosition: Int = 0)

