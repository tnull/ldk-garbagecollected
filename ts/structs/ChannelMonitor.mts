import { TxOut } from '../structs/TxOut.mjs';
import { AccessError } from '../enums/AccessError.mjs';
import { COption_NoneZ } from '../enums/COption_NoneZ.mjs';
import { ChannelMonitorUpdateErr } from '../enums/ChannelMonitorUpdateErr.mjs';
import { ConfirmationTarget } from '../enums/ConfirmationTarget.mjs';
import { CreationError } from '../enums/CreationError.mjs';
import { Currency } from '../enums/Currency.mjs';
import { Level } from '../enums/Level.mjs';
import { Network } from '../enums/Network.mjs';
import { Recipient } from '../enums/Recipient.mjs';
import { Secp256k1Error } from '../enums/Secp256k1Error.mjs';
import { SemanticError } from '../enums/SemanticError.mjs';
import { SiPrefix } from '../enums/SiPrefix.mjs';
import { Bech32Error } from '../structs/Bech32Error.mjs';
import { Result_NoneNoneZ } from '../structs/Result_NoneNoneZ.mjs';
import { CounterpartyCommitmentSecrets } from '../structs/CounterpartyCommitmentSecrets.mjs';
import { DecodeError } from '../structs/DecodeError.mjs';
import { Result_CounterpartyCommitmentSecretsDecodeErrorZ } from '../structs/Result_CounterpartyCommitmentSecretsDecodeErrorZ.mjs';
import { Result_SecretKeyErrorZ } from '../structs/Result_SecretKeyErrorZ.mjs';
import { Result_PublicKeyErrorZ } from '../structs/Result_PublicKeyErrorZ.mjs';
import { TxCreationKeys } from '../structs/TxCreationKeys.mjs';
import { Result_TxCreationKeysDecodeErrorZ } from '../structs/Result_TxCreationKeysDecodeErrorZ.mjs';
import { ChannelPublicKeys } from '../structs/ChannelPublicKeys.mjs';
import { Result_ChannelPublicKeysDecodeErrorZ } from '../structs/Result_ChannelPublicKeysDecodeErrorZ.mjs';
import { Result_TxCreationKeysErrorZ } from '../structs/Result_TxCreationKeysErrorZ.mjs';
import { Option_u32Z } from '../structs/Option_u32Z.mjs';
import { HTLCOutputInCommitment } from '../structs/HTLCOutputInCommitment.mjs';
import { Result_HTLCOutputInCommitmentDecodeErrorZ } from '../structs/Result_HTLCOutputInCommitmentDecodeErrorZ.mjs';
import { CounterpartyChannelTransactionParameters } from '../structs/CounterpartyChannelTransactionParameters.mjs';
import { Result_CounterpartyChannelTransactionParametersDecodeErrorZ } from '../structs/Result_CounterpartyChannelTransactionParametersDecodeErrorZ.mjs';
import { ChannelTransactionParameters } from '../structs/ChannelTransactionParameters.mjs';
import { Result_ChannelTransactionParametersDecodeErrorZ } from '../structs/Result_ChannelTransactionParametersDecodeErrorZ.mjs';
import { HolderCommitmentTransaction } from '../structs/HolderCommitmentTransaction.mjs';
import { Result_HolderCommitmentTransactionDecodeErrorZ } from '../structs/Result_HolderCommitmentTransactionDecodeErrorZ.mjs';
import { BuiltCommitmentTransaction } from '../structs/BuiltCommitmentTransaction.mjs';
import { Result_BuiltCommitmentTransactionDecodeErrorZ } from '../structs/Result_BuiltCommitmentTransactionDecodeErrorZ.mjs';
import { TrustedClosingTransaction } from '../structs/TrustedClosingTransaction.mjs';
import { Result_TrustedClosingTransactionNoneZ } from '../structs/Result_TrustedClosingTransactionNoneZ.mjs';
import { CommitmentTransaction } from '../structs/CommitmentTransaction.mjs';
import { Result_CommitmentTransactionDecodeErrorZ } from '../structs/Result_CommitmentTransactionDecodeErrorZ.mjs';
import { TrustedCommitmentTransaction } from '../structs/TrustedCommitmentTransaction.mjs';
import { Result_TrustedCommitmentTransactionNoneZ } from '../structs/Result_TrustedCommitmentTransactionNoneZ.mjs';
import { Result_CVec_SignatureZNoneZ } from '../structs/Result_CVec_SignatureZNoneZ.mjs';
import { ShutdownScript } from '../structs/ShutdownScript.mjs';
import { Result_ShutdownScriptDecodeErrorZ } from '../structs/Result_ShutdownScriptDecodeErrorZ.mjs';
import { InvalidShutdownScript } from '../structs/InvalidShutdownScript.mjs';
import { Result_ShutdownScriptInvalidShutdownScriptZ } from '../structs/Result_ShutdownScriptInvalidShutdownScriptZ.mjs';
import { RouteHop } from '../structs/RouteHop.mjs';
import { Result_RouteHopDecodeErrorZ } from '../structs/Result_RouteHopDecodeErrorZ.mjs';
import { Route } from '../structs/Route.mjs';
import { Result_RouteDecodeErrorZ } from '../structs/Result_RouteDecodeErrorZ.mjs';
import { RouteParameters } from '../structs/RouteParameters.mjs';
import { Result_RouteParametersDecodeErrorZ } from '../structs/Result_RouteParametersDecodeErrorZ.mjs';
import { RouteHint } from '../structs/RouteHint.mjs';
import { Option_u64Z } from '../structs/Option_u64Z.mjs';
import { PaymentParameters } from '../structs/PaymentParameters.mjs';
import { Result_PaymentParametersDecodeErrorZ } from '../structs/Result_PaymentParametersDecodeErrorZ.mjs';
import { RouteHintHop } from '../structs/RouteHintHop.mjs';
import { Result_RouteHintDecodeErrorZ } from '../structs/Result_RouteHintDecodeErrorZ.mjs';
import { Result_RouteHintHopDecodeErrorZ } from '../structs/Result_RouteHintHopDecodeErrorZ.mjs';
import { ChannelDetails } from '../structs/ChannelDetails.mjs';
import { LightningError } from '../structs/LightningError.mjs';
import { Result_RouteLightningErrorZ } from '../structs/Result_RouteLightningErrorZ.mjs';
import { Result_TxOutAccessErrorZ } from '../structs/Result_TxOutAccessErrorZ.mjs';
import { TwoTuple_usizeTransactionZ } from '../structs/TwoTuple_usizeTransactionZ.mjs';
import { Result_NoneChannelMonitorUpdateErrZ } from '../structs/Result_NoneChannelMonitorUpdateErrZ.mjs';
import { HTLCUpdate } from '../structs/HTLCUpdate.mjs';
import { OutPoint } from '../structs/OutPoint.mjs';
import { MonitorEvent } from '../structs/MonitorEvent.mjs';
import { Option_C2Tuple_usizeTransactionZZ } from '../structs/Option_C2Tuple_usizeTransactionZZ.mjs';
import { ClosureReason } from '../structs/ClosureReason.mjs';
import { Option_ClosureReasonZ } from '../structs/Option_ClosureReasonZ.mjs';
import { Result_COption_ClosureReasonZDecodeErrorZ } from '../structs/Result_COption_ClosureReasonZDecodeErrorZ.mjs';
import { ChannelUpdate } from '../structs/ChannelUpdate.mjs';
import { NetworkUpdate } from '../structs/NetworkUpdate.mjs';
import { Option_NetworkUpdateZ } from '../structs/Option_NetworkUpdateZ.mjs';
import { DelayedPaymentOutputDescriptor } from '../structs/DelayedPaymentOutputDescriptor.mjs';
import { StaticPaymentOutputDescriptor } from '../structs/StaticPaymentOutputDescriptor.mjs';
import { SpendableOutputDescriptor } from '../structs/SpendableOutputDescriptor.mjs';
import { PaymentPurpose } from '../structs/PaymentPurpose.mjs';
import { ChannelTypeFeatures } from '../structs/ChannelTypeFeatures.mjs';
import { Event } from '../structs/Event.mjs';
import { Option_EventZ } from '../structs/Option_EventZ.mjs';
import { Result_COption_EventZDecodeErrorZ } from '../structs/Result_COption_EventZDecodeErrorZ.mjs';
import { AcceptChannel } from '../structs/AcceptChannel.mjs';
import { OpenChannel } from '../structs/OpenChannel.mjs';
import { FundingCreated } from '../structs/FundingCreated.mjs';
import { FundingSigned } from '../structs/FundingSigned.mjs';
import { FundingLocked } from '../structs/FundingLocked.mjs';
import { AnnouncementSignatures } from '../structs/AnnouncementSignatures.mjs';
import { CommitmentUpdate } from '../structs/CommitmentUpdate.mjs';
import { RevokeAndACK } from '../structs/RevokeAndACK.mjs';
import { ClosingSigned } from '../structs/ClosingSigned.mjs';
import { Shutdown } from '../structs/Shutdown.mjs';
import { ChannelReestablish } from '../structs/ChannelReestablish.mjs';
import { ChannelAnnouncement } from '../structs/ChannelAnnouncement.mjs';
import { NodeAnnouncement } from '../structs/NodeAnnouncement.mjs';
import { ErrorMessage } from '../structs/ErrorMessage.mjs';
import { WarningMessage } from '../structs/WarningMessage.mjs';
import { ErrorAction } from '../structs/ErrorAction.mjs';
import { QueryChannelRange } from '../structs/QueryChannelRange.mjs';
import { QueryShortChannelIds } from '../structs/QueryShortChannelIds.mjs';
import { ReplyChannelRange } from '../structs/ReplyChannelRange.mjs';
import { GossipTimestampFilter } from '../structs/GossipTimestampFilter.mjs';
import { MessageSendEvent } from '../structs/MessageSendEvent.mjs';
import { FixedPenaltyScorer } from '../structs/FixedPenaltyScorer.mjs';
import { Result_FixedPenaltyScorerDecodeErrorZ } from '../structs/Result_FixedPenaltyScorerDecodeErrorZ.mjs';
import { ScoringParameters } from '../structs/ScoringParameters.mjs';
import { Result_ScoringParametersDecodeErrorZ } from '../structs/Result_ScoringParametersDecodeErrorZ.mjs';
import { Scorer } from '../structs/Scorer.mjs';
import { Result_ScorerDecodeErrorZ } from '../structs/Result_ScorerDecodeErrorZ.mjs';
import { NetworkGraph } from '../structs/NetworkGraph.mjs';
import { ProbabilisticScorer } from '../structs/ProbabilisticScorer.mjs';
import { Result_ProbabilisticScorerDecodeErrorZ } from '../structs/Result_ProbabilisticScorerDecodeErrorZ.mjs';
import { InitFeatures } from '../structs/InitFeatures.mjs';
import { Result_InitFeaturesDecodeErrorZ } from '../structs/Result_InitFeaturesDecodeErrorZ.mjs';
import { ChannelFeatures } from '../structs/ChannelFeatures.mjs';
import { Result_ChannelFeaturesDecodeErrorZ } from '../structs/Result_ChannelFeaturesDecodeErrorZ.mjs';
import { NodeFeatures } from '../structs/NodeFeatures.mjs';
import { Result_NodeFeaturesDecodeErrorZ } from '../structs/Result_NodeFeaturesDecodeErrorZ.mjs';
import { InvoiceFeatures } from '../structs/InvoiceFeatures.mjs';
import { Result_InvoiceFeaturesDecodeErrorZ } from '../structs/Result_InvoiceFeaturesDecodeErrorZ.mjs';
import { Result_ChannelTypeFeaturesDecodeErrorZ } from '../structs/Result_ChannelTypeFeaturesDecodeErrorZ.mjs';
import { Result_DelayedPaymentOutputDescriptorDecodeErrorZ } from '../structs/Result_DelayedPaymentOutputDescriptorDecodeErrorZ.mjs';
import { Result_StaticPaymentOutputDescriptorDecodeErrorZ } from '../structs/Result_StaticPaymentOutputDescriptorDecodeErrorZ.mjs';
import { Result_SpendableOutputDescriptorDecodeErrorZ } from '../structs/Result_SpendableOutputDescriptorDecodeErrorZ.mjs';
import { TwoTuple_SignatureCVec_SignatureZZ } from '../structs/TwoTuple_SignatureCVec_SignatureZZ.mjs';
import { Result_C2Tuple_SignatureCVec_SignatureZZNoneZ } from '../structs/Result_C2Tuple_SignatureCVec_SignatureZZNoneZ.mjs';
import { Result_SignatureNoneZ } from '../structs/Result_SignatureNoneZ.mjs';
import { TwoTuple_SignatureSignatureZ } from '../structs/TwoTuple_SignatureSignatureZ.mjs';
import { Result_C2Tuple_SignatureSignatureZNoneZ } from '../structs/Result_C2Tuple_SignatureSignatureZNoneZ.mjs';
import { Result_SecretKeyNoneZ } from '../structs/Result_SecretKeyNoneZ.mjs';
import { ClosingTransaction } from '../structs/ClosingTransaction.mjs';
import { UnsignedChannelAnnouncement } from '../structs/UnsignedChannelAnnouncement.mjs';
import { BaseSign, BaseSignInterface } from '../structs/BaseSign.mjs';
import { Sign, SignInterface } from '../structs/Sign.mjs';
import { Result_SignDecodeErrorZ } from '../structs/Result_SignDecodeErrorZ.mjs';
import { Result_RecoverableSignatureNoneZ } from '../structs/Result_RecoverableSignatureNoneZ.mjs';
import { Result_CVec_CVec_u8ZZNoneZ } from '../structs/Result_CVec_CVec_u8ZZNoneZ.mjs';
import { InMemorySigner } from '../structs/InMemorySigner.mjs';
import { Result_InMemorySignerDecodeErrorZ } from '../structs/Result_InMemorySignerDecodeErrorZ.mjs';
import { Result_TransactionNoneZ } from '../structs/Result_TransactionNoneZ.mjs';
import { Option_u16Z } from '../structs/Option_u16Z.mjs';
import { APIError } from '../structs/APIError.mjs';
import { Result_NoneAPIErrorZ } from '../structs/Result_NoneAPIErrorZ.mjs';
import { Result__u832APIErrorZ } from '../structs/Result__u832APIErrorZ.mjs';
import { PaymentSendFailure } from '../structs/PaymentSendFailure.mjs';
import { Result_PaymentIdPaymentSendFailureZ } from '../structs/Result_PaymentIdPaymentSendFailureZ.mjs';
import { Result_NonePaymentSendFailureZ } from '../structs/Result_NonePaymentSendFailureZ.mjs';
import { TwoTuple_PaymentHashPaymentIdZ } from '../structs/TwoTuple_PaymentHashPaymentIdZ.mjs';
import { Result_C2Tuple_PaymentHashPaymentIdZPaymentSendFailureZ } from '../structs/Result_C2Tuple_PaymentHashPaymentIdZPaymentSendFailureZ.mjs';
import { NetAddress } from '../structs/NetAddress.mjs';
import { TwoTuple_PaymentHashPaymentSecretZ } from '../structs/TwoTuple_PaymentHashPaymentSecretZ.mjs';
import { Result_C2Tuple_PaymentHashPaymentSecretZNoneZ } from '../structs/Result_C2Tuple_PaymentHashPaymentSecretZNoneZ.mjs';
import { Result_C2Tuple_PaymentHashPaymentSecretZAPIErrorZ } from '../structs/Result_C2Tuple_PaymentHashPaymentSecretZAPIErrorZ.mjs';
import { Result_PaymentSecretNoneZ } from '../structs/Result_PaymentSecretNoneZ.mjs';
import { Result_PaymentSecretAPIErrorZ } from '../structs/Result_PaymentSecretAPIErrorZ.mjs';
import { Result_PaymentPreimageAPIErrorZ } from '../structs/Result_PaymentPreimageAPIErrorZ.mjs';
import { CounterpartyForwardingInfo } from '../structs/CounterpartyForwardingInfo.mjs';
import { Result_CounterpartyForwardingInfoDecodeErrorZ } from '../structs/Result_CounterpartyForwardingInfoDecodeErrorZ.mjs';
import { ChannelCounterparty } from '../structs/ChannelCounterparty.mjs';
import { Result_ChannelCounterpartyDecodeErrorZ } from '../structs/Result_ChannelCounterpartyDecodeErrorZ.mjs';
import { Result_ChannelDetailsDecodeErrorZ } from '../structs/Result_ChannelDetailsDecodeErrorZ.mjs';
import { PhantomRouteHints } from '../structs/PhantomRouteHints.mjs';
import { Result_PhantomRouteHintsDecodeErrorZ } from '../structs/Result_PhantomRouteHintsDecodeErrorZ.mjs';
import { ChannelMonitorUpdate } from '../structs/ChannelMonitorUpdate.mjs';
import { Watch, WatchInterface } from '../structs/Watch.mjs';
import { BroadcasterInterface, BroadcasterInterfaceInterface } from '../structs/BroadcasterInterface.mjs';
import { KeysInterface, KeysInterfaceInterface } from '../structs/KeysInterface.mjs';
import { FeeEstimator, FeeEstimatorInterface } from '../structs/FeeEstimator.mjs';
import { Record } from '../structs/Record.mjs';
import { Logger, LoggerInterface } from '../structs/Logger.mjs';
import { ChannelManager } from '../structs/ChannelManager.mjs';
import { TwoTuple_BlockHashChannelManagerZ } from '../structs/TwoTuple_BlockHashChannelManagerZ.mjs';
import { Result_C2Tuple_BlockHashChannelManagerZDecodeErrorZ } from '../structs/Result_C2Tuple_BlockHashChannelManagerZDecodeErrorZ.mjs';
import { ChannelConfig } from '../structs/ChannelConfig.mjs';
import { Result_ChannelConfigDecodeErrorZ } from '../structs/Result_ChannelConfigDecodeErrorZ.mjs';
import { Result_OutPointDecodeErrorZ } from '../structs/Result_OutPointDecodeErrorZ.mjs';
import { Type, TypeInterface } from '../structs/Type.mjs';
import { Option_TypeZ } from '../structs/Option_TypeZ.mjs';
import { Result_COption_TypeZDecodeErrorZ } from '../structs/Result_COption_TypeZDecodeErrorZ.mjs';
import { PaymentError } from '../structs/PaymentError.mjs';
import { Result_PaymentIdPaymentErrorZ } from '../structs/Result_PaymentIdPaymentErrorZ.mjs';
import { ParseError } from '../structs/ParseError.mjs';
import { Result_SiPrefixParseErrorZ } from '../structs/Result_SiPrefixParseErrorZ.mjs';
import { Invoice } from '../structs/Invoice.mjs';
import { ParseOrSemanticError } from '../structs/ParseOrSemanticError.mjs';
import { Result_InvoiceParseOrSemanticErrorZ } from '../structs/Result_InvoiceParseOrSemanticErrorZ.mjs';
import { SignedRawInvoice } from '../structs/SignedRawInvoice.mjs';
import { Result_SignedRawInvoiceParseErrorZ } from '../structs/Result_SignedRawInvoiceParseErrorZ.mjs';
import { RawInvoice } from '../structs/RawInvoice.mjs';
import { InvoiceSignature } from '../structs/InvoiceSignature.mjs';
import { ThreeTuple_RawInvoice_u832InvoiceSignatureZ } from '../structs/ThreeTuple_RawInvoice_u832InvoiceSignatureZ.mjs';
import { PayeePubKey } from '../structs/PayeePubKey.mjs';
import { Result_PayeePubKeyErrorZ } from '../structs/Result_PayeePubKeyErrorZ.mjs';
import { PrivateRoute } from '../structs/PrivateRoute.mjs';
import { PositiveTimestamp } from '../structs/PositiveTimestamp.mjs';
import { Result_PositiveTimestampCreationErrorZ } from '../structs/Result_PositiveTimestampCreationErrorZ.mjs';
import { Result_NoneSemanticErrorZ } from '../structs/Result_NoneSemanticErrorZ.mjs';
import { Result_InvoiceSemanticErrorZ } from '../structs/Result_InvoiceSemanticErrorZ.mjs';
import { Description } from '../structs/Description.mjs';
import { Result_DescriptionCreationErrorZ } from '../structs/Result_DescriptionCreationErrorZ.mjs';
import { Result_PrivateRouteCreationErrorZ } from '../structs/Result_PrivateRouteCreationErrorZ.mjs';
import { Result_StringErrorZ } from '../structs/Result_StringErrorZ.mjs';
import { Result_ChannelMonitorUpdateDecodeErrorZ } from '../structs/Result_ChannelMonitorUpdateDecodeErrorZ.mjs';
import { Option_MonitorEventZ } from '../structs/Option_MonitorEventZ.mjs';
import { Result_COption_MonitorEventZDecodeErrorZ } from '../structs/Result_COption_MonitorEventZDecodeErrorZ.mjs';
import { Result_HTLCUpdateDecodeErrorZ } from '../structs/Result_HTLCUpdateDecodeErrorZ.mjs';
import { TwoTuple_OutPointScriptZ } from '../structs/TwoTuple_OutPointScriptZ.mjs';
import { TwoTuple_u32ScriptZ } from '../structs/TwoTuple_u32ScriptZ.mjs';
import { TwoTuple_TxidCVec_C2Tuple_u32ScriptZZZ } from '../structs/TwoTuple_TxidCVec_C2Tuple_u32ScriptZZZ.mjs';
import { TwoTuple_u32TxOutZ } from '../structs/TwoTuple_u32TxOutZ.mjs';
import { TwoTuple_TxidCVec_C2Tuple_u32TxOutZZZ } from '../structs/TwoTuple_TxidCVec_C2Tuple_u32TxOutZZZ.mjs';
import { Balance } from '../structs/Balance.mjs';
import { TwoTuple_BlockHashChannelMonitorZ } from '../structs/TwoTuple_BlockHashChannelMonitorZ.mjs';
import { Result_C2Tuple_BlockHashChannelMonitorZDecodeErrorZ } from '../structs/Result_C2Tuple_BlockHashChannelMonitorZDecodeErrorZ.mjs';
import { Result_NoneLightningErrorZ } from '../structs/Result_NoneLightningErrorZ.mjs';
import { TwoTuple_PublicKeyTypeZ } from '../structs/TwoTuple_PublicKeyTypeZ.mjs';
import { Result_boolLightningErrorZ } from '../structs/Result_boolLightningErrorZ.mjs';
import { ThreeTuple_ChannelAnnouncementChannelUpdateChannelUpdateZ } from '../structs/ThreeTuple_ChannelAnnouncementChannelUpdateChannelUpdateZ.mjs';
import { Option_NetAddressZ } from '../structs/Option_NetAddressZ.mjs';
import { PeerHandleError } from '../structs/PeerHandleError.mjs';
import { Result_CVec_u8ZPeerHandleErrorZ } from '../structs/Result_CVec_u8ZPeerHandleErrorZ.mjs';
import { Result_NonePeerHandleErrorZ } from '../structs/Result_NonePeerHandleErrorZ.mjs';
import { Result_boolPeerHandleErrorZ } from '../structs/Result_boolPeerHandleErrorZ.mjs';
import { NodeId } from '../structs/NodeId.mjs';
import { Result_NodeIdDecodeErrorZ } from '../structs/Result_NodeIdDecodeErrorZ.mjs';
import { Result_COption_NetworkUpdateZDecodeErrorZ } from '../structs/Result_COption_NetworkUpdateZDecodeErrorZ.mjs';
import { Access, AccessInterface } from '../structs/Access.mjs';
import { Option_AccessZ } from '../structs/Option_AccessZ.mjs';
import { ChannelUpdateInfo } from '../structs/ChannelUpdateInfo.mjs';
import { Result_ChannelUpdateInfoDecodeErrorZ } from '../structs/Result_ChannelUpdateInfoDecodeErrorZ.mjs';
import { ChannelInfo } from '../structs/ChannelInfo.mjs';
import { Result_ChannelInfoDecodeErrorZ } from '../structs/Result_ChannelInfoDecodeErrorZ.mjs';
import { RoutingFees } from '../structs/RoutingFees.mjs';
import { Result_RoutingFeesDecodeErrorZ } from '../structs/Result_RoutingFeesDecodeErrorZ.mjs';
import { NodeAnnouncementInfo } from '../structs/NodeAnnouncementInfo.mjs';
import { Result_NodeAnnouncementInfoDecodeErrorZ } from '../structs/Result_NodeAnnouncementInfoDecodeErrorZ.mjs';
import { NodeInfo } from '../structs/NodeInfo.mjs';
import { Result_NodeInfoDecodeErrorZ } from '../structs/Result_NodeInfoDecodeErrorZ.mjs';
import { Result_NetworkGraphDecodeErrorZ } from '../structs/Result_NetworkGraphDecodeErrorZ.mjs';
import { Option_CVec_NetAddressZZ } from '../structs/Option_CVec_NetAddressZZ.mjs';
import { Result_NetAddressDecodeErrorZ } from '../structs/Result_NetAddressDecodeErrorZ.mjs';
import { UpdateAddHTLC } from '../structs/UpdateAddHTLC.mjs';
import { UpdateFulfillHTLC } from '../structs/UpdateFulfillHTLC.mjs';
import { UpdateFailHTLC } from '../structs/UpdateFailHTLC.mjs';
import { UpdateFailMalformedHTLC } from '../structs/UpdateFailMalformedHTLC.mjs';
import { Result_AcceptChannelDecodeErrorZ } from '../structs/Result_AcceptChannelDecodeErrorZ.mjs';
import { Result_AnnouncementSignaturesDecodeErrorZ } from '../structs/Result_AnnouncementSignaturesDecodeErrorZ.mjs';
import { Result_ChannelReestablishDecodeErrorZ } from '../structs/Result_ChannelReestablishDecodeErrorZ.mjs';
import { Result_ClosingSignedDecodeErrorZ } from '../structs/Result_ClosingSignedDecodeErrorZ.mjs';
import { ClosingSignedFeeRange } from '../structs/ClosingSignedFeeRange.mjs';
import { Result_ClosingSignedFeeRangeDecodeErrorZ } from '../structs/Result_ClosingSignedFeeRangeDecodeErrorZ.mjs';
import { CommitmentSigned } from '../structs/CommitmentSigned.mjs';
import { Result_CommitmentSignedDecodeErrorZ } from '../structs/Result_CommitmentSignedDecodeErrorZ.mjs';
import { Result_FundingCreatedDecodeErrorZ } from '../structs/Result_FundingCreatedDecodeErrorZ.mjs';
import { Result_FundingSignedDecodeErrorZ } from '../structs/Result_FundingSignedDecodeErrorZ.mjs';
import { Result_FundingLockedDecodeErrorZ } from '../structs/Result_FundingLockedDecodeErrorZ.mjs';
import { Init } from '../structs/Init.mjs';
import { Result_InitDecodeErrorZ } from '../structs/Result_InitDecodeErrorZ.mjs';
import { Result_OpenChannelDecodeErrorZ } from '../structs/Result_OpenChannelDecodeErrorZ.mjs';
import { Result_RevokeAndACKDecodeErrorZ } from '../structs/Result_RevokeAndACKDecodeErrorZ.mjs';
import { Result_ShutdownDecodeErrorZ } from '../structs/Result_ShutdownDecodeErrorZ.mjs';
import { Result_UpdateFailHTLCDecodeErrorZ } from '../structs/Result_UpdateFailHTLCDecodeErrorZ.mjs';
import { Result_UpdateFailMalformedHTLCDecodeErrorZ } from '../structs/Result_UpdateFailMalformedHTLCDecodeErrorZ.mjs';
import { UpdateFee } from '../structs/UpdateFee.mjs';
import { Result_UpdateFeeDecodeErrorZ } from '../structs/Result_UpdateFeeDecodeErrorZ.mjs';
import { Result_UpdateFulfillHTLCDecodeErrorZ } from '../structs/Result_UpdateFulfillHTLCDecodeErrorZ.mjs';
import { Result_UpdateAddHTLCDecodeErrorZ } from '../structs/Result_UpdateAddHTLCDecodeErrorZ.mjs';
import { Ping } from '../structs/Ping.mjs';
import { Result_PingDecodeErrorZ } from '../structs/Result_PingDecodeErrorZ.mjs';
import { Pong } from '../structs/Pong.mjs';
import { Result_PongDecodeErrorZ } from '../structs/Result_PongDecodeErrorZ.mjs';
import { Result_UnsignedChannelAnnouncementDecodeErrorZ } from '../structs/Result_UnsignedChannelAnnouncementDecodeErrorZ.mjs';
import { Result_ChannelAnnouncementDecodeErrorZ } from '../structs/Result_ChannelAnnouncementDecodeErrorZ.mjs';
import { UnsignedChannelUpdate } from '../structs/UnsignedChannelUpdate.mjs';
import { Result_UnsignedChannelUpdateDecodeErrorZ } from '../structs/Result_UnsignedChannelUpdateDecodeErrorZ.mjs';
import { Result_ChannelUpdateDecodeErrorZ } from '../structs/Result_ChannelUpdateDecodeErrorZ.mjs';
import { Result_ErrorMessageDecodeErrorZ } from '../structs/Result_ErrorMessageDecodeErrorZ.mjs';
import { Result_WarningMessageDecodeErrorZ } from '../structs/Result_WarningMessageDecodeErrorZ.mjs';
import { UnsignedNodeAnnouncement } from '../structs/UnsignedNodeAnnouncement.mjs';
import { Result_UnsignedNodeAnnouncementDecodeErrorZ } from '../structs/Result_UnsignedNodeAnnouncementDecodeErrorZ.mjs';
import { Result_NodeAnnouncementDecodeErrorZ } from '../structs/Result_NodeAnnouncementDecodeErrorZ.mjs';
import { Result_QueryShortChannelIdsDecodeErrorZ } from '../structs/Result_QueryShortChannelIdsDecodeErrorZ.mjs';
import { ReplyShortChannelIdsEnd } from '../structs/ReplyShortChannelIdsEnd.mjs';
import { Result_ReplyShortChannelIdsEndDecodeErrorZ } from '../structs/Result_ReplyShortChannelIdsEndDecodeErrorZ.mjs';
import { Result_QueryChannelRangeDecodeErrorZ } from '../structs/Result_QueryChannelRangeDecodeErrorZ.mjs';
import { Result_ReplyChannelRangeDecodeErrorZ } from '../structs/Result_ReplyChannelRangeDecodeErrorZ.mjs';
import { Result_GossipTimestampFilterDecodeErrorZ } from '../structs/Result_GossipTimestampFilterDecodeErrorZ.mjs';
import { SignOrCreationError } from '../structs/SignOrCreationError.mjs';
import { Result_InvoiceSignOrCreationErrorZ } from '../structs/Result_InvoiceSignOrCreationErrorZ.mjs';
import { WatchedOutput } from '../structs/WatchedOutput.mjs';
import { Filter, FilterInterface } from '../structs/Filter.mjs';
import { Option_FilterZ } from '../structs/Option_FilterZ.mjs';
import { LockedChannelMonitor } from '../structs/LockedChannelMonitor.mjs';
import { Result_LockedChannelMonitorNoneZ } from '../structs/Result_LockedChannelMonitorNoneZ.mjs';
import { MessageSendEventsProvider, MessageSendEventsProviderInterface } from '../structs/MessageSendEventsProvider.mjs';
import { EventHandler, EventHandlerInterface } from '../structs/EventHandler.mjs';
import { EventsProvider, EventsProviderInterface } from '../structs/EventsProvider.mjs';
import { ChannelHandshakeConfig } from '../structs/ChannelHandshakeConfig.mjs';
import { ChannelHandshakeLimits } from '../structs/ChannelHandshakeLimits.mjs';
import { UserConfig } from '../structs/UserConfig.mjs';
import { BestBlock } from '../structs/BestBlock.mjs';
import { Listen, ListenInterface } from '../structs/Listen.mjs';
import { Confirm, ConfirmInterface } from '../structs/Confirm.mjs';
import { MonitorUpdateId } from '../structs/MonitorUpdateId.mjs';
import { Persist, PersistInterface } from '../structs/Persist.mjs';
import { ChainMonitor } from '../structs/ChainMonitor.mjs';
import { KeysManager } from '../structs/KeysManager.mjs';
import { PhantomKeysManager } from '../structs/PhantomKeysManager.mjs';
import { ChainParameters } from '../structs/ChainParameters.mjs';
import { ChannelMessageHandler, ChannelMessageHandlerInterface } from '../structs/ChannelMessageHandler.mjs';
import { ChannelManagerReadArgs } from '../structs/ChannelManagerReadArgs.mjs';
import { DataLossProtect } from '../structs/DataLossProtect.mjs';
import { RoutingMessageHandler, RoutingMessageHandlerInterface } from '../structs/RoutingMessageHandler.mjs';
import { CustomMessageReader, CustomMessageReaderInterface } from '../structs/CustomMessageReader.mjs';
import { CustomMessageHandler, CustomMessageHandlerInterface } from '../structs/CustomMessageHandler.mjs';
import { IgnoringMessageHandler } from '../structs/IgnoringMessageHandler.mjs';
import { ErroringMessageHandler } from '../structs/ErroringMessageHandler.mjs';
import { MessageHandler } from '../structs/MessageHandler.mjs';
import { SocketDescriptor, SocketDescriptorInterface } from '../structs/SocketDescriptor.mjs';
import { PeerManager } from '../structs/PeerManager.mjs';
import { DirectedChannelTransactionParameters } from '../structs/DirectedChannelTransactionParameters.mjs';
import { ReadOnlyNetworkGraph } from '../structs/ReadOnlyNetworkGraph.mjs';
import { NetGraphMsgHandler } from '../structs/NetGraphMsgHandler.mjs';
import { DirectedChannelInfo } from '../structs/DirectedChannelInfo.mjs';
import { EffectiveCapacity } from '../structs/EffectiveCapacity.mjs';
import { Score, ScoreInterface } from '../structs/Score.mjs';
import { LockableScore, LockableScoreInterface } from '../structs/LockableScore.mjs';
import { MultiThreadedLockableScore } from '../structs/MultiThreadedLockableScore.mjs';
import { ProbabilisticScoringParameters } from '../structs/ProbabilisticScoringParameters.mjs';
import { RawDataPart } from '../structs/RawDataPart.mjs';
import { Sha256 } from '../structs/Sha256.mjs';
import { ExpiryTime } from '../structs/ExpiryTime.mjs';
import { MinFinalCltvExpiry } from '../structs/MinFinalCltvExpiry.mjs';
import { Fallback } from '../structs/Fallback.mjs';
import { Payer, PayerInterface } from '../structs/Payer.mjs';
import { Router, RouterInterface } from '../structs/Router.mjs';
import { InvoicePayer } from '../structs/InvoicePayer.mjs';
import { RetryAttempts } from '../structs/RetryAttempts.mjs';
import { DefaultRouter } from '../structs/DefaultRouter.mjs';

import { CommonBase, UInt5, UnqualifiedError } from './CommonBase.mjs';
import * as bindings from '../bindings.mjs'


/**
 * A ChannelMonitor handles chain events (blocks connected and disconnected) and generates
 * on-chain transactions to ensure no loss of funds occurs.
 * 
 * You MUST ensure that no ChannelMonitors for a given channel anywhere contain out-of-date
 * information and are actively monitoring the chain.
 * 
 * Pending Events or updated HTLCs which have not yet been read out by
 * get_and_clear_pending_monitor_events or get_and_clear_pending_events are serialized to disk and
 * reloaded at deserialize-time. Thus, you must ensure that, when handling events, all events
 * gotten are fully handled before re-serializing the new state.
 * 
 * Note that the deserializer is only implemented for (BlockHash, ChannelMonitor), which
 * tells you the last block hash which was block_connect()ed. You MUST rescan any blocks along
 * the \"reorg path\" (ie disconnecting blocks until you find a common ancestor from both the
 * returned block hash and the the current chain and then reconnecting blocks to get to the
 * best chain) upon deserializing the object!
 */
export class ChannelMonitor extends CommonBase {
	/* @internal */
	public constructor(_dummy: object, ptr: number) {
		super(ptr, bindings.ChannelMonitor_free);
	}

	public clone_ptr(): number {
		const ret: number = bindings.ChannelMonitor_clone_ptr(this.ptr);
		return ret;
	}

	/**
	 * Creates a copy of the ChannelMonitor
	 */
	public clone(): ChannelMonitor {
		const ret: number = bindings.ChannelMonitor_clone(this.ptr);
		const ret_hu_conv: ChannelMonitor = new ChannelMonitor(null, ret);
		CommonBase.add_ref_from(ret_hu_conv, this);
		return ret_hu_conv;
	}

	/**
	 * Serialize the ChannelMonitor object into a byte array which can be read by ChannelMonitor_read
	 */
	public write(): Uint8Array {
		const ret: number = bindings.ChannelMonitor_write(this.ptr);
		const ret_conv: Uint8Array = bindings.decodeUint8Array(ret);
		return ret_conv;
	}

	/**
	 * Updates a ChannelMonitor on the basis of some new information provided by the Channel
	 * itself.
	 * 
	 * panics if the given update is not the next update by update_id.
	 */
	public update_monitor(updates: ChannelMonitorUpdate, broadcaster: BroadcasterInterface, fee_estimator: FeeEstimator, logger: Logger): Result_NoneNoneZ {
		const ret: number = bindings.ChannelMonitor_update_monitor(this.ptr, updates == null ? 0 : CommonBase.get_ptr_of(updates) & ~1, broadcaster == null ? 0 : CommonBase.get_ptr_of(broadcaster), fee_estimator == null ? 0 : CommonBase.get_ptr_of(fee_estimator), logger == null ? 0 : CommonBase.get_ptr_of(logger));
		const ret_hu_conv: Result_NoneNoneZ = Result_NoneNoneZ.constr_from_ptr(ret);
		CommonBase.add_ref_from(this, updates);
		CommonBase.add_ref_from(this, broadcaster);
		CommonBase.add_ref_from(this, fee_estimator);
		CommonBase.add_ref_from(this, logger);
		return ret_hu_conv;
	}

	/**
	 * Gets the update_id from the latest ChannelMonitorUpdate which was applied to this
	 * ChannelMonitor.
	 */
	public get_latest_update_id(): bigint {
		const ret: bigint = bindings.ChannelMonitor_get_latest_update_id(this.ptr);
		return ret;
	}

	/**
	 * Gets the funding transaction outpoint of the channel this ChannelMonitor is monitoring for.
	 */
	public get_funding_txo(): TwoTuple_OutPointScriptZ {
		const ret: number = bindings.ChannelMonitor_get_funding_txo(this.ptr);
		const ret_hu_conv: TwoTuple_OutPointScriptZ = new TwoTuple_OutPointScriptZ(null, ret);
		CommonBase.add_ref_from(ret_hu_conv, this);
		return ret_hu_conv;
	}

	/**
	 * Gets a list of txids, with their output scripts (in the order they appear in the
	 * transaction), which we must learn about spends of via block_connected().
	 */
	public get_outputs_to_watch(): TwoTuple_TxidCVec_C2Tuple_u32ScriptZZZ[] {
		const ret: number = bindings.ChannelMonitor_get_outputs_to_watch(this.ptr);
		const ret_conv_40_len: number = bindings.getArrayLength(ret);
		const ret_conv_40_arr: TwoTuple_TxidCVec_C2Tuple_u32ScriptZZZ[] = new Array(ret_conv_40_len).fill(null);
		for (var o = 0; o < ret_conv_40_len; o++) {
			const ret_conv_40: number = bindings.getU32ArrayElem(ret, o);
			const ret_conv_40_hu_conv: TwoTuple_TxidCVec_C2Tuple_u32ScriptZZZ = new TwoTuple_TxidCVec_C2Tuple_u32ScriptZZZ(null, ret_conv_40);
			CommonBase.add_ref_from(ret_conv_40_hu_conv, this);
			ret_conv_40_arr[o] = ret_conv_40_hu_conv;
		}
		bindings.freeWasmMemory(ret)
		return ret_conv_40_arr;
	}

	/**
	 * Loads the funding txo and outputs to watch into the given `chain::Filter` by repeatedly
	 * calling `chain::Filter::register_output` and `chain::Filter::register_tx` until all outputs
	 * have been registered.
	 */
	public load_outputs_to_watch(filter: Filter): void {
		bindings.ChannelMonitor_load_outputs_to_watch(this.ptr, filter == null ? 0 : CommonBase.get_ptr_of(filter));
		CommonBase.add_ref_from(this, filter);
	}

	/**
	 * Get the list of HTLCs who's status has been updated on chain. This should be called by
	 * ChannelManager via [`chain::Watch::release_pending_monitor_events`].
	 */
	public get_and_clear_pending_monitor_events(): MonitorEvent[] {
		const ret: number = bindings.ChannelMonitor_get_and_clear_pending_monitor_events(this.ptr);
		const ret_conv_14_len: number = bindings.getArrayLength(ret);
		const ret_conv_14_arr: MonitorEvent[] = new Array(ret_conv_14_len).fill(null);
		for (var o = 0; o < ret_conv_14_len; o++) {
			const ret_conv_14: number = bindings.getU32ArrayElem(ret, o);
			const ret_conv_14_hu_conv: MonitorEvent = MonitorEvent.constr_from_ptr(ret_conv_14);
			CommonBase.add_ref_from(ret_conv_14_hu_conv, this);
			ret_conv_14_arr[o] = ret_conv_14_hu_conv;
		}
		bindings.freeWasmMemory(ret)
		return ret_conv_14_arr;
	}

	/**
	 * Gets the list of pending events which were generated by previous actions, clearing the list
	 * in the process.
	 * 
	 * This is called by ChainMonitor::get_and_clear_pending_events() and is equivalent to
	 * EventsProvider::get_and_clear_pending_events() except that it requires &mut self as we do
	 * no internal locking in ChannelMonitors.
	 */
	public get_and_clear_pending_events(): Event[] {
		const ret: number = bindings.ChannelMonitor_get_and_clear_pending_events(this.ptr);
		const ret_conv_7_len: number = bindings.getArrayLength(ret);
		const ret_conv_7_arr: Event[] = new Array(ret_conv_7_len).fill(null);
		for (var h = 0; h < ret_conv_7_len; h++) {
			const ret_conv_7: number = bindings.getU32ArrayElem(ret, h);
			const ret_conv_7_hu_conv: Event = Event.constr_from_ptr(ret_conv_7);
			CommonBase.add_ref_from(ret_conv_7_hu_conv, this);
			ret_conv_7_arr[h] = ret_conv_7_hu_conv;
		}
		bindings.freeWasmMemory(ret)
		return ret_conv_7_arr;
	}

	/**
	 * Used by ChannelManager deserialization to broadcast the latest holder state if its copy of
	 * the Channel was out-of-date. You may use it to get a broadcastable holder toxic tx in case of
	 * fallen-behind, i.e when receiving a channel_reestablish with a proof that our counterparty side knows
	 * a higher revocation secret than the holder commitment number we are aware of. Broadcasting these
	 * transactions are UNSAFE, as they allow counterparty side to punish you. Nevertheless you may want to
	 * broadcast them if counterparty don't close channel with his higher commitment transaction after a
	 * substantial amount of time (a month or even a year) to get back funds. Best may be to contact
	 * out-of-band the other node operator to coordinate with him if option is available to you.
	 * In any-case, choice is up to the user.
	 */
	public get_latest_holder_commitment_txn(logger: Logger): Uint8Array[] {
		const ret: number = bindings.ChannelMonitor_get_latest_holder_commitment_txn(this.ptr, logger == null ? 0 : CommonBase.get_ptr_of(logger));
		const ret_conv_12_len: number = bindings.getArrayLength(ret);
		const ret_conv_12_arr: Uint8Array[] = new Array(ret_conv_12_len).fill(null);
		for (var m = 0; m < ret_conv_12_len; m++) {
			const ret_conv_12: number = bindings.getU32ArrayElem(ret, m);
			const ret_conv_12_conv: Uint8Array = bindings.decodeUint8Array(ret_conv_12);
			ret_conv_12_arr[m] = ret_conv_12_conv;
		}
		bindings.freeWasmMemory(ret)
		CommonBase.add_ref_from(this, logger);
		return ret_conv_12_arr;
	}

	/**
	 * Processes transactions in a newly connected block, which may result in any of the following:
	 * - update the monitor's state against resolved HTLCs
	 * - punish the counterparty in the case of seeing a revoked commitment transaction
	 * - force close the channel and claim/timeout incoming/outgoing HTLCs if near expiration
	 * - detect settled outputs for later spending
	 * - schedule and bump any in-flight claims
	 * 
	 * Returns any new outputs to watch from `txdata`; after called, these are also included in
	 * [`get_outputs_to_watch`].
	 * 
	 * [`get_outputs_to_watch`]: #method.get_outputs_to_watch
	 */
	public block_connected(header: Uint8Array, txdata: TwoTuple_usizeTransactionZ[], height: number, broadcaster: BroadcasterInterface, fee_estimator: FeeEstimator, logger: Logger): TwoTuple_TxidCVec_C2Tuple_u32TxOutZZZ[] {
		const ret: number = bindings.ChannelMonitor_block_connected(this.ptr, bindings.encodeUint8Array(bindings.check_arr_len(header, 80)), bindings.encodeUint32Array(txdata != null ? txdata.map(txdata_conv_28 => txdata_conv_28 != null ? CommonBase.get_ptr_of(txdata_conv_28) : 0) : null), height, broadcaster == null ? 0 : CommonBase.get_ptr_of(broadcaster), fee_estimator == null ? 0 : CommonBase.get_ptr_of(fee_estimator), logger == null ? 0 : CommonBase.get_ptr_of(logger));
		const ret_conv_39_len: number = bindings.getArrayLength(ret);
		const ret_conv_39_arr: TwoTuple_TxidCVec_C2Tuple_u32TxOutZZZ[] = new Array(ret_conv_39_len).fill(null);
		for (var n = 0; n < ret_conv_39_len; n++) {
			const ret_conv_39: number = bindings.getU32ArrayElem(ret, n);
			const ret_conv_39_hu_conv: TwoTuple_TxidCVec_C2Tuple_u32TxOutZZZ = new TwoTuple_TxidCVec_C2Tuple_u32TxOutZZZ(null, ret_conv_39);
			CommonBase.add_ref_from(ret_conv_39_hu_conv, this);
			ret_conv_39_arr[n] = ret_conv_39_hu_conv;
		}
		bindings.freeWasmMemory(ret)
		CommonBase.add_ref_from(this, broadcaster);
		CommonBase.add_ref_from(this, fee_estimator);
		CommonBase.add_ref_from(this, logger);
		return ret_conv_39_arr;
	}

	/**
	 * Determines if the disconnected block contained any transactions of interest and updates
	 * appropriately.
	 */
	public block_disconnected(header: Uint8Array, height: number, broadcaster: BroadcasterInterface, fee_estimator: FeeEstimator, logger: Logger): void {
		bindings.ChannelMonitor_block_disconnected(this.ptr, bindings.encodeUint8Array(bindings.check_arr_len(header, 80)), height, broadcaster == null ? 0 : CommonBase.get_ptr_of(broadcaster), fee_estimator == null ? 0 : CommonBase.get_ptr_of(fee_estimator), logger == null ? 0 : CommonBase.get_ptr_of(logger));
		CommonBase.add_ref_from(this, broadcaster);
		CommonBase.add_ref_from(this, fee_estimator);
		CommonBase.add_ref_from(this, logger);
	}

	/**
	 * Processes transactions confirmed in a block with the given header and height, returning new
	 * outputs to watch. See [`block_connected`] for details.
	 * 
	 * Used instead of [`block_connected`] by clients that are notified of transactions rather than
	 * blocks. See [`chain::Confirm`] for calling expectations.
	 * 
	 * [`block_connected`]: Self::block_connected
	 */
	public transactions_confirmed(header: Uint8Array, txdata: TwoTuple_usizeTransactionZ[], height: number, broadcaster: BroadcasterInterface, fee_estimator: FeeEstimator, logger: Logger): TwoTuple_TxidCVec_C2Tuple_u32TxOutZZZ[] {
		const ret: number = bindings.ChannelMonitor_transactions_confirmed(this.ptr, bindings.encodeUint8Array(bindings.check_arr_len(header, 80)), bindings.encodeUint32Array(txdata != null ? txdata.map(txdata_conv_28 => txdata_conv_28 != null ? CommonBase.get_ptr_of(txdata_conv_28) : 0) : null), height, broadcaster == null ? 0 : CommonBase.get_ptr_of(broadcaster), fee_estimator == null ? 0 : CommonBase.get_ptr_of(fee_estimator), logger == null ? 0 : CommonBase.get_ptr_of(logger));
		const ret_conv_39_len: number = bindings.getArrayLength(ret);
		const ret_conv_39_arr: TwoTuple_TxidCVec_C2Tuple_u32TxOutZZZ[] = new Array(ret_conv_39_len).fill(null);
		for (var n = 0; n < ret_conv_39_len; n++) {
			const ret_conv_39: number = bindings.getU32ArrayElem(ret, n);
			const ret_conv_39_hu_conv: TwoTuple_TxidCVec_C2Tuple_u32TxOutZZZ = new TwoTuple_TxidCVec_C2Tuple_u32TxOutZZZ(null, ret_conv_39);
			CommonBase.add_ref_from(ret_conv_39_hu_conv, this);
			ret_conv_39_arr[n] = ret_conv_39_hu_conv;
		}
		bindings.freeWasmMemory(ret)
		CommonBase.add_ref_from(this, broadcaster);
		CommonBase.add_ref_from(this, fee_estimator);
		CommonBase.add_ref_from(this, logger);
		return ret_conv_39_arr;
	}

	/**
	 * Processes a transaction that was reorganized out of the chain.
	 * 
	 * Used instead of [`block_disconnected`] by clients that are notified of transactions rather
	 * than blocks. See [`chain::Confirm`] for calling expectations.
	 * 
	 * [`block_disconnected`]: Self::block_disconnected
	 */
	public transaction_unconfirmed(txid: Uint8Array, broadcaster: BroadcasterInterface, fee_estimator: FeeEstimator, logger: Logger): void {
		bindings.ChannelMonitor_transaction_unconfirmed(this.ptr, bindings.encodeUint8Array(bindings.check_arr_len(txid, 32)), broadcaster == null ? 0 : CommonBase.get_ptr_of(broadcaster), fee_estimator == null ? 0 : CommonBase.get_ptr_of(fee_estimator), logger == null ? 0 : CommonBase.get_ptr_of(logger));
		CommonBase.add_ref_from(this, broadcaster);
		CommonBase.add_ref_from(this, fee_estimator);
		CommonBase.add_ref_from(this, logger);
	}

	/**
	 * Updates the monitor with the current best chain tip, returning new outputs to watch. See
	 * [`block_connected`] for details.
	 * 
	 * Used instead of [`block_connected`] by clients that are notified of transactions rather than
	 * blocks. See [`chain::Confirm`] for calling expectations.
	 * 
	 * [`block_connected`]: Self::block_connected
	 */
	public best_block_updated(header: Uint8Array, height: number, broadcaster: BroadcasterInterface, fee_estimator: FeeEstimator, logger: Logger): TwoTuple_TxidCVec_C2Tuple_u32TxOutZZZ[] {
		const ret: number = bindings.ChannelMonitor_best_block_updated(this.ptr, bindings.encodeUint8Array(bindings.check_arr_len(header, 80)), height, broadcaster == null ? 0 : CommonBase.get_ptr_of(broadcaster), fee_estimator == null ? 0 : CommonBase.get_ptr_of(fee_estimator), logger == null ? 0 : CommonBase.get_ptr_of(logger));
		const ret_conv_39_len: number = bindings.getArrayLength(ret);
		const ret_conv_39_arr: TwoTuple_TxidCVec_C2Tuple_u32TxOutZZZ[] = new Array(ret_conv_39_len).fill(null);
		for (var n = 0; n < ret_conv_39_len; n++) {
			const ret_conv_39: number = bindings.getU32ArrayElem(ret, n);
			const ret_conv_39_hu_conv: TwoTuple_TxidCVec_C2Tuple_u32TxOutZZZ = new TwoTuple_TxidCVec_C2Tuple_u32TxOutZZZ(null, ret_conv_39);
			CommonBase.add_ref_from(ret_conv_39_hu_conv, this);
			ret_conv_39_arr[n] = ret_conv_39_hu_conv;
		}
		bindings.freeWasmMemory(ret)
		CommonBase.add_ref_from(this, broadcaster);
		CommonBase.add_ref_from(this, fee_estimator);
		CommonBase.add_ref_from(this, logger);
		return ret_conv_39_arr;
	}

	/**
	 * Returns the set of txids that should be monitored for re-organization out of the chain.
	 */
	public get_relevant_txids(): Uint8Array[] {
		const ret: number = bindings.ChannelMonitor_get_relevant_txids(this.ptr);
		const ret_conv_12_len: number = bindings.getArrayLength(ret);
		const ret_conv_12_arr: Uint8Array[] = new Array(ret_conv_12_len).fill(null);
		for (var m = 0; m < ret_conv_12_len; m++) {
			const ret_conv_12: number = bindings.getU32ArrayElem(ret, m);
			const ret_conv_12_conv: Uint8Array = bindings.decodeUint8Array(ret_conv_12);
			ret_conv_12_arr[m] = ret_conv_12_conv;
		}
		bindings.freeWasmMemory(ret)
		return ret_conv_12_arr;
	}

	/**
	 * Gets the latest best block which was connected either via the [`chain::Listen`] or
	 * [`chain::Confirm`] interfaces.
	 */
	public current_best_block(): BestBlock {
		const ret: number = bindings.ChannelMonitor_current_best_block(this.ptr);
		const ret_hu_conv: BestBlock = new BestBlock(null, ret);
		CommonBase.add_ref_from(ret_hu_conv, this);
		return ret_hu_conv;
	}

	/**
	 * Gets the balances in this channel which are either claimable by us if we were to
	 * force-close the channel now or which are claimable on-chain (possibly awaiting
	 * confirmation).
	 * 
	 * Any balances in the channel which are available on-chain (excluding on-chain fees) are
	 * included here until an [`Event::SpendableOutputs`] event has been generated for the
	 * balance, or until our counterparty has claimed the balance and accrued several
	 * confirmations on the claim transaction.
	 * 
	 * Note that the balances available when you or your counterparty have broadcasted revoked
	 * state(s) may not be fully captured here.
	 * 
	 * See [`Balance`] for additional details on the types of claimable balances which
	 * may be returned here and their meanings.
	 */
	public get_claimable_balances(): Balance[] {
		const ret: number = bindings.ChannelMonitor_get_claimable_balances(this.ptr);
		const ret_conv_9_len: number = bindings.getArrayLength(ret);
		const ret_conv_9_arr: Balance[] = new Array(ret_conv_9_len).fill(null);
		for (var j = 0; j < ret_conv_9_len; j++) {
			const ret_conv_9: number = bindings.getU32ArrayElem(ret, j);
			const ret_conv_9_hu_conv: Balance = Balance.constr_from_ptr(ret_conv_9);
			CommonBase.add_ref_from(ret_conv_9_hu_conv, this);
			ret_conv_9_arr[j] = ret_conv_9_hu_conv;
		}
		bindings.freeWasmMemory(ret)
		return ret_conv_9_arr;
	}

}
