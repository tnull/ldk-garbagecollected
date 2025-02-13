package org.ldk.impl;
import org.ldk.enums.*;
import org.ldk.impl.version;
import java.io.File;
import java.io.InputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class bindings {
	public static class VecOrSliceDef {
		public long dataptr;
		public long datalen;
		public long stride;
		public VecOrSliceDef(long dataptr, long datalen, long stride) {
			this.dataptr = dataptr; this.datalen = datalen; this.stride = stride;
		}
	}
	static {
		try {
			// Try to load natively first, this works on Android and in testing.
			System.loadLibrary("lightningjni");
		} catch (UnsatisfiedLinkError system_load_err) {
			// Otherwise try to load from the library jar.
			File tmpdir = new File(System.getProperty("java.io.tmpdir"), "ldk-java-nativelib");
			tmpdir.mkdir(); // If it fails to create, assume it was there already
			tmpdir.deleteOnExit();
			String libname = "liblightningjni_" + System.getProperty("os.name").replaceAll(" ", "") +
				"-" + System.getProperty("os.arch").replaceAll(" ", "") + ".nativelib";
			try (InputStream is = bindings.class.getResourceAsStream("/" + libname)) {
				Path libpath = new File(tmpdir.toPath().toString(), "liblightningjni.so").toPath();
				Files.copy(is, libpath, StandardCopyOption.REPLACE_EXISTING);
				Runtime.getRuntime().load(libpath.toString());
			} catch (Exception e) {
				System.err.println("Failed to load LDK native library.");
				System.err.println("System LDK native library load failed with: " + system_load_err);
				System.err.println("Resource-based LDK native library load failed with: " + e);
				throw new IllegalArgumentException(e);
			}
		}
		init(java.lang.Enum.class, VecOrSliceDef.class);
		init_class_cache();
		if (!get_lib_version_string().equals(version.get_ldk_java_bindings_version()))
			throw new IllegalArgumentException("Compiled LDK library and LDK class failes do not match");
		// Fetching the LDK versions from C also checks that the header and binaries match
		get_ldk_c_bindings_version();
		get_ldk_version();
	}
	static native void init(java.lang.Class c, java.lang.Class slicedef);
	static native void init_class_cache();
	static native String get_lib_version_string();

	public static native String get_ldk_c_bindings_version();
	public static native String get_ldk_version();

	public static native boolean deref_bool(long ptr);
	public static native long deref_long(long ptr);
	public static native void free_heap_ptr(long ptr);
	public static native byte[] read_bytes(long ptr, long len);
	public static native byte[] get_u8_slice_bytes(long slice_ptr);
	public static native long bytes_to_u8_vec(byte[] bytes);
	public static native long new_txpointer_copy_data(byte[] txdata);
	public static native void txpointer_free(long ptr);
	public static native byte[] txpointer_get_buffer(long ptr);
	public static native long vec_slice_len(long vec);
	public static native long new_empty_slice_vec();

	static { AccessError.values(); /* Force enum statics to run */ }
	static { COption_NoneZ.values(); /* Force enum statics to run */ }
	static { ChannelMonitorUpdateErr.values(); /* Force enum statics to run */ }
	static { ConfirmationTarget.values(); /* Force enum statics to run */ }
	static { CreationError.values(); /* Force enum statics to run */ }
	static { Currency.values(); /* Force enum statics to run */ }
	static { IOError.values(); /* Force enum statics to run */ }
	static { Level.values(); /* Force enum statics to run */ }
	static { Network.values(); /* Force enum statics to run */ }
	static { Recipient.values(); /* Force enum statics to run */ }
	static { Secp256k1Error.values(); /* Force enum statics to run */ }
	static { SemanticError.values(); /* Force enum statics to run */ }
	static { SiPrefix.values(); /* Force enum statics to run */ }
	public static class LDKBech32Error {
		private LDKBech32Error() {}
		public final static class MissingSeparator extends LDKBech32Error {
			MissingSeparator() { }
		}
		public final static class InvalidChecksum extends LDKBech32Error {
			InvalidChecksum() { }
		}
		public final static class InvalidLength extends LDKBech32Error {
			InvalidLength() { }
		}
		public final static class InvalidChar extends LDKBech32Error {
			public int invalid_char;
			InvalidChar(int invalid_char) { this.invalid_char = invalid_char; }
		}
		public final static class InvalidData extends LDKBech32Error {
			public byte invalid_data;
			InvalidData(byte invalid_data) { this.invalid_data = invalid_data; }
		}
		public final static class InvalidPadding extends LDKBech32Error {
			InvalidPadding() { }
		}
		public final static class MixedCase extends LDKBech32Error {
			MixedCase() { }
		}
		static native void init();
	}
	static { LDKBech32Error.init(); }
	public static native LDKBech32Error LDKBech32Error_ref_from_ptr(long ptr);
	// struct LDKCVec_u8Z TxOut_get_script_pubkey (struct LDKTxOut* thing)
	public static native byte[] TxOut_get_script_pubkey(long thing);
	// uint64_t TxOut_get_value (struct LDKTxOut* thing)
	public static native long TxOut_get_value(long thing);
	// void CResult_NoneNoneZ_get_ok(LDKCResult_NoneNoneZ *NONNULL_PTR owner);
	public static native void CResult_NoneNoneZ_get_ok(long owner);
	// void CResult_NoneNoneZ_get_err(LDKCResult_NoneNoneZ *NONNULL_PTR owner);
	public static native void CResult_NoneNoneZ_get_err(long owner);
	// struct LDKCounterpartyCommitmentSecrets CResult_CounterpartyCommitmentSecretsDecodeErrorZ_get_ok(LDKCResult_CounterpartyCommitmentSecretsDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_CounterpartyCommitmentSecretsDecodeErrorZ_get_ok(long owner);
	// struct LDKDecodeError CResult_CounterpartyCommitmentSecretsDecodeErrorZ_get_err(LDKCResult_CounterpartyCommitmentSecretsDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_CounterpartyCommitmentSecretsDecodeErrorZ_get_err(long owner);
	// struct LDKSecretKey CResult_SecretKeyErrorZ_get_ok(LDKCResult_SecretKeyErrorZ *NONNULL_PTR owner);
	public static native byte[] CResult_SecretKeyErrorZ_get_ok(long owner);
	// enum LDKSecp256k1Error CResult_SecretKeyErrorZ_get_err(LDKCResult_SecretKeyErrorZ *NONNULL_PTR owner);
	public static native Secp256k1Error CResult_SecretKeyErrorZ_get_err(long owner);
	// struct LDKPublicKey CResult_PublicKeyErrorZ_get_ok(LDKCResult_PublicKeyErrorZ *NONNULL_PTR owner);
	public static native byte[] CResult_PublicKeyErrorZ_get_ok(long owner);
	// enum LDKSecp256k1Error CResult_PublicKeyErrorZ_get_err(LDKCResult_PublicKeyErrorZ *NONNULL_PTR owner);
	public static native Secp256k1Error CResult_PublicKeyErrorZ_get_err(long owner);
	// struct LDKTxCreationKeys CResult_TxCreationKeysDecodeErrorZ_get_ok(LDKCResult_TxCreationKeysDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_TxCreationKeysDecodeErrorZ_get_ok(long owner);
	// struct LDKDecodeError CResult_TxCreationKeysDecodeErrorZ_get_err(LDKCResult_TxCreationKeysDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_TxCreationKeysDecodeErrorZ_get_err(long owner);
	// struct LDKChannelPublicKeys CResult_ChannelPublicKeysDecodeErrorZ_get_ok(LDKCResult_ChannelPublicKeysDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_ChannelPublicKeysDecodeErrorZ_get_ok(long owner);
	// struct LDKDecodeError CResult_ChannelPublicKeysDecodeErrorZ_get_err(LDKCResult_ChannelPublicKeysDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_ChannelPublicKeysDecodeErrorZ_get_err(long owner);
	// struct LDKTxCreationKeys CResult_TxCreationKeysErrorZ_get_ok(LDKCResult_TxCreationKeysErrorZ *NONNULL_PTR owner);
	public static native long CResult_TxCreationKeysErrorZ_get_ok(long owner);
	// enum LDKSecp256k1Error CResult_TxCreationKeysErrorZ_get_err(LDKCResult_TxCreationKeysErrorZ *NONNULL_PTR owner);
	public static native Secp256k1Error CResult_TxCreationKeysErrorZ_get_err(long owner);
	public static class LDKCOption_u32Z {
		private LDKCOption_u32Z() {}
		public final static class Some extends LDKCOption_u32Z {
			public int some;
			Some(int some) { this.some = some; }
		}
		public final static class None extends LDKCOption_u32Z {
			None() { }
		}
		static native void init();
	}
	static { LDKCOption_u32Z.init(); }
	public static native LDKCOption_u32Z LDKCOption_u32Z_ref_from_ptr(long ptr);
	// struct LDKHTLCOutputInCommitment CResult_HTLCOutputInCommitmentDecodeErrorZ_get_ok(LDKCResult_HTLCOutputInCommitmentDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_HTLCOutputInCommitmentDecodeErrorZ_get_ok(long owner);
	// struct LDKDecodeError CResult_HTLCOutputInCommitmentDecodeErrorZ_get_err(LDKCResult_HTLCOutputInCommitmentDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_HTLCOutputInCommitmentDecodeErrorZ_get_err(long owner);
	// struct LDKCounterpartyChannelTransactionParameters CResult_CounterpartyChannelTransactionParametersDecodeErrorZ_get_ok(LDKCResult_CounterpartyChannelTransactionParametersDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_CounterpartyChannelTransactionParametersDecodeErrorZ_get_ok(long owner);
	// struct LDKDecodeError CResult_CounterpartyChannelTransactionParametersDecodeErrorZ_get_err(LDKCResult_CounterpartyChannelTransactionParametersDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_CounterpartyChannelTransactionParametersDecodeErrorZ_get_err(long owner);
	// struct LDKChannelTransactionParameters CResult_ChannelTransactionParametersDecodeErrorZ_get_ok(LDKCResult_ChannelTransactionParametersDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_ChannelTransactionParametersDecodeErrorZ_get_ok(long owner);
	// struct LDKDecodeError CResult_ChannelTransactionParametersDecodeErrorZ_get_err(LDKCResult_ChannelTransactionParametersDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_ChannelTransactionParametersDecodeErrorZ_get_err(long owner);
	// struct LDKHolderCommitmentTransaction CResult_HolderCommitmentTransactionDecodeErrorZ_get_ok(LDKCResult_HolderCommitmentTransactionDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_HolderCommitmentTransactionDecodeErrorZ_get_ok(long owner);
	// struct LDKDecodeError CResult_HolderCommitmentTransactionDecodeErrorZ_get_err(LDKCResult_HolderCommitmentTransactionDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_HolderCommitmentTransactionDecodeErrorZ_get_err(long owner);
	// struct LDKBuiltCommitmentTransaction CResult_BuiltCommitmentTransactionDecodeErrorZ_get_ok(LDKCResult_BuiltCommitmentTransactionDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_BuiltCommitmentTransactionDecodeErrorZ_get_ok(long owner);
	// struct LDKDecodeError CResult_BuiltCommitmentTransactionDecodeErrorZ_get_err(LDKCResult_BuiltCommitmentTransactionDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_BuiltCommitmentTransactionDecodeErrorZ_get_err(long owner);
	// struct LDKTrustedClosingTransaction *CResult_TrustedClosingTransactionNoneZ_get_ok(LDKCResult_TrustedClosingTransactionNoneZ *NONNULL_PTR owner);
	public static native long CResult_TrustedClosingTransactionNoneZ_get_ok(long owner);
	// void CResult_TrustedClosingTransactionNoneZ_get_err(LDKCResult_TrustedClosingTransactionNoneZ *NONNULL_PTR owner);
	public static native void CResult_TrustedClosingTransactionNoneZ_get_err(long owner);
	// struct LDKCommitmentTransaction CResult_CommitmentTransactionDecodeErrorZ_get_ok(LDKCResult_CommitmentTransactionDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_CommitmentTransactionDecodeErrorZ_get_ok(long owner);
	// struct LDKDecodeError CResult_CommitmentTransactionDecodeErrorZ_get_err(LDKCResult_CommitmentTransactionDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_CommitmentTransactionDecodeErrorZ_get_err(long owner);
	// struct LDKTrustedCommitmentTransaction *CResult_TrustedCommitmentTransactionNoneZ_get_ok(LDKCResult_TrustedCommitmentTransactionNoneZ *NONNULL_PTR owner);
	public static native long CResult_TrustedCommitmentTransactionNoneZ_get_ok(long owner);
	// void CResult_TrustedCommitmentTransactionNoneZ_get_err(LDKCResult_TrustedCommitmentTransactionNoneZ *NONNULL_PTR owner);
	public static native void CResult_TrustedCommitmentTransactionNoneZ_get_err(long owner);
	// struct LDKCVec_SignatureZ CResult_CVec_SignatureZNoneZ_get_ok(LDKCResult_CVec_SignatureZNoneZ *NONNULL_PTR owner);
	public static native byte[][] CResult_CVec_SignatureZNoneZ_get_ok(long owner);
	// void CResult_CVec_SignatureZNoneZ_get_err(LDKCResult_CVec_SignatureZNoneZ *NONNULL_PTR owner);
	public static native void CResult_CVec_SignatureZNoneZ_get_err(long owner);
	// struct LDKShutdownScript CResult_ShutdownScriptDecodeErrorZ_get_ok(LDKCResult_ShutdownScriptDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_ShutdownScriptDecodeErrorZ_get_ok(long owner);
	// struct LDKDecodeError CResult_ShutdownScriptDecodeErrorZ_get_err(LDKCResult_ShutdownScriptDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_ShutdownScriptDecodeErrorZ_get_err(long owner);
	// struct LDKShutdownScript CResult_ShutdownScriptInvalidShutdownScriptZ_get_ok(LDKCResult_ShutdownScriptInvalidShutdownScriptZ *NONNULL_PTR owner);
	public static native long CResult_ShutdownScriptInvalidShutdownScriptZ_get_ok(long owner);
	// struct LDKInvalidShutdownScript CResult_ShutdownScriptInvalidShutdownScriptZ_get_err(LDKCResult_ShutdownScriptInvalidShutdownScriptZ *NONNULL_PTR owner);
	public static native long CResult_ShutdownScriptInvalidShutdownScriptZ_get_err(long owner);
	// void CResult_NoneErrorZ_get_ok(LDKCResult_NoneErrorZ *NONNULL_PTR owner);
	public static native void CResult_NoneErrorZ_get_ok(long owner);
	// enum LDKIOError CResult_NoneErrorZ_get_err(LDKCResult_NoneErrorZ *NONNULL_PTR owner);
	public static native IOError CResult_NoneErrorZ_get_err(long owner);
	// struct LDKRouteHop CResult_RouteHopDecodeErrorZ_get_ok(LDKCResult_RouteHopDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_RouteHopDecodeErrorZ_get_ok(long owner);
	// struct LDKDecodeError CResult_RouteHopDecodeErrorZ_get_err(LDKCResult_RouteHopDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_RouteHopDecodeErrorZ_get_err(long owner);
	// struct LDKRoute CResult_RouteDecodeErrorZ_get_ok(LDKCResult_RouteDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_RouteDecodeErrorZ_get_ok(long owner);
	// struct LDKDecodeError CResult_RouteDecodeErrorZ_get_err(LDKCResult_RouteDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_RouteDecodeErrorZ_get_err(long owner);
	// struct LDKRouteParameters CResult_RouteParametersDecodeErrorZ_get_ok(LDKCResult_RouteParametersDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_RouteParametersDecodeErrorZ_get_ok(long owner);
	// struct LDKDecodeError CResult_RouteParametersDecodeErrorZ_get_err(LDKCResult_RouteParametersDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_RouteParametersDecodeErrorZ_get_err(long owner);
	public static class LDKCOption_u64Z {
		private LDKCOption_u64Z() {}
		public final static class Some extends LDKCOption_u64Z {
			public long some;
			Some(long some) { this.some = some; }
		}
		public final static class None extends LDKCOption_u64Z {
			None() { }
		}
		static native void init();
	}
	static { LDKCOption_u64Z.init(); }
	public static native LDKCOption_u64Z LDKCOption_u64Z_ref_from_ptr(long ptr);
	// struct LDKPaymentParameters CResult_PaymentParametersDecodeErrorZ_get_ok(LDKCResult_PaymentParametersDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_PaymentParametersDecodeErrorZ_get_ok(long owner);
	// struct LDKDecodeError CResult_PaymentParametersDecodeErrorZ_get_err(LDKCResult_PaymentParametersDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_PaymentParametersDecodeErrorZ_get_err(long owner);
	// struct LDKRouteHint CResult_RouteHintDecodeErrorZ_get_ok(LDKCResult_RouteHintDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_RouteHintDecodeErrorZ_get_ok(long owner);
	// struct LDKDecodeError CResult_RouteHintDecodeErrorZ_get_err(LDKCResult_RouteHintDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_RouteHintDecodeErrorZ_get_err(long owner);
	// struct LDKRouteHintHop CResult_RouteHintHopDecodeErrorZ_get_ok(LDKCResult_RouteHintHopDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_RouteHintHopDecodeErrorZ_get_ok(long owner);
	// struct LDKDecodeError CResult_RouteHintHopDecodeErrorZ_get_err(LDKCResult_RouteHintHopDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_RouteHintHopDecodeErrorZ_get_err(long owner);
	// struct LDKRoute CResult_RouteLightningErrorZ_get_ok(LDKCResult_RouteLightningErrorZ *NONNULL_PTR owner);
	public static native long CResult_RouteLightningErrorZ_get_ok(long owner);
	// struct LDKLightningError CResult_RouteLightningErrorZ_get_err(LDKCResult_RouteLightningErrorZ *NONNULL_PTR owner);
	public static native long CResult_RouteLightningErrorZ_get_err(long owner);
	// struct LDKTxOut CResult_TxOutAccessErrorZ_get_ok(LDKCResult_TxOutAccessErrorZ *NONNULL_PTR owner);
	public static native long CResult_TxOutAccessErrorZ_get_ok(long owner);
	// enum LDKAccessError CResult_TxOutAccessErrorZ_get_err(LDKCResult_TxOutAccessErrorZ *NONNULL_PTR owner);
	public static native AccessError CResult_TxOutAccessErrorZ_get_err(long owner);
	// uintptr_t C2Tuple_usizeTransactionZ_get_a(LDKC2Tuple_usizeTransactionZ *NONNULL_PTR owner);
	public static native long C2Tuple_usizeTransactionZ_get_a(long owner);
	// struct LDKTransaction C2Tuple_usizeTransactionZ_get_b(LDKC2Tuple_usizeTransactionZ *NONNULL_PTR owner);
	public static native byte[] C2Tuple_usizeTransactionZ_get_b(long owner);
	// void CResult_NoneChannelMonitorUpdateErrZ_get_ok(LDKCResult_NoneChannelMonitorUpdateErrZ *NONNULL_PTR owner);
	public static native void CResult_NoneChannelMonitorUpdateErrZ_get_ok(long owner);
	// enum LDKChannelMonitorUpdateErr CResult_NoneChannelMonitorUpdateErrZ_get_err(LDKCResult_NoneChannelMonitorUpdateErrZ *NONNULL_PTR owner);
	public static native ChannelMonitorUpdateErr CResult_NoneChannelMonitorUpdateErrZ_get_err(long owner);
	public static class LDKMonitorEvent {
		private LDKMonitorEvent() {}
		public final static class HTLCEvent extends LDKMonitorEvent {
			public long htlc_event;
			HTLCEvent(long htlc_event) { this.htlc_event = htlc_event; }
		}
		public final static class CommitmentTxConfirmed extends LDKMonitorEvent {
			public long commitment_tx_confirmed;
			CommitmentTxConfirmed(long commitment_tx_confirmed) { this.commitment_tx_confirmed = commitment_tx_confirmed; }
		}
		public final static class UpdateCompleted extends LDKMonitorEvent {
			public long funding_txo;
			public long monitor_update_id;
			UpdateCompleted(long funding_txo, long monitor_update_id) { this.funding_txo = funding_txo; this.monitor_update_id = monitor_update_id; }
		}
		public final static class UpdateFailed extends LDKMonitorEvent {
			public long update_failed;
			UpdateFailed(long update_failed) { this.update_failed = update_failed; }
		}
		static native void init();
	}
	static { LDKMonitorEvent.init(); }
	public static native LDKMonitorEvent LDKMonitorEvent_ref_from_ptr(long ptr);
	public static class LDKCOption_C2Tuple_usizeTransactionZZ {
		private LDKCOption_C2Tuple_usizeTransactionZZ() {}
		public final static class Some extends LDKCOption_C2Tuple_usizeTransactionZZ {
			public long some;
			Some(long some) { this.some = some; }
		}
		public final static class None extends LDKCOption_C2Tuple_usizeTransactionZZ {
			None() { }
		}
		static native void init();
	}
	static { LDKCOption_C2Tuple_usizeTransactionZZ.init(); }
	public static native LDKCOption_C2Tuple_usizeTransactionZZ LDKCOption_C2Tuple_usizeTransactionZZ_ref_from_ptr(long ptr);
	public static class LDKClosureReason {
		private LDKClosureReason() {}
		public final static class CounterpartyForceClosed extends LDKClosureReason {
			public java.lang.String peer_msg;
			CounterpartyForceClosed(java.lang.String peer_msg) { this.peer_msg = peer_msg; }
		}
		public final static class HolderForceClosed extends LDKClosureReason {
			HolderForceClosed() { }
		}
		public final static class CooperativeClosure extends LDKClosureReason {
			CooperativeClosure() { }
		}
		public final static class CommitmentTxConfirmed extends LDKClosureReason {
			CommitmentTxConfirmed() { }
		}
		public final static class FundingTimedOut extends LDKClosureReason {
			FundingTimedOut() { }
		}
		public final static class ProcessingError extends LDKClosureReason {
			public java.lang.String err;
			ProcessingError(java.lang.String err) { this.err = err; }
		}
		public final static class DisconnectedPeer extends LDKClosureReason {
			DisconnectedPeer() { }
		}
		public final static class OutdatedChannelManager extends LDKClosureReason {
			OutdatedChannelManager() { }
		}
		static native void init();
	}
	static { LDKClosureReason.init(); }
	public static native LDKClosureReason LDKClosureReason_ref_from_ptr(long ptr);
	public static class LDKCOption_ClosureReasonZ {
		private LDKCOption_ClosureReasonZ() {}
		public final static class Some extends LDKCOption_ClosureReasonZ {
			public long some;
			Some(long some) { this.some = some; }
		}
		public final static class None extends LDKCOption_ClosureReasonZ {
			None() { }
		}
		static native void init();
	}
	static { LDKCOption_ClosureReasonZ.init(); }
	public static native LDKCOption_ClosureReasonZ LDKCOption_ClosureReasonZ_ref_from_ptr(long ptr);
	// struct LDKCOption_ClosureReasonZ CResult_COption_ClosureReasonZDecodeErrorZ_get_ok(LDKCResult_COption_ClosureReasonZDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_COption_ClosureReasonZDecodeErrorZ_get_ok(long owner);
	// struct LDKDecodeError CResult_COption_ClosureReasonZDecodeErrorZ_get_err(LDKCResult_COption_ClosureReasonZDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_COption_ClosureReasonZDecodeErrorZ_get_err(long owner);
	public static class LDKNetworkUpdate {
		private LDKNetworkUpdate() {}
		public final static class ChannelUpdateMessage extends LDKNetworkUpdate {
			public long msg;
			ChannelUpdateMessage(long msg) { this.msg = msg; }
		}
		public final static class ChannelClosed extends LDKNetworkUpdate {
			public long short_channel_id;
			public boolean is_permanent;
			ChannelClosed(long short_channel_id, boolean is_permanent) { this.short_channel_id = short_channel_id; this.is_permanent = is_permanent; }
		}
		public final static class NodeFailure extends LDKNetworkUpdate {
			public byte[] node_id;
			public boolean is_permanent;
			NodeFailure(byte[] node_id, boolean is_permanent) { this.node_id = node_id; this.is_permanent = is_permanent; }
		}
		static native void init();
	}
	static { LDKNetworkUpdate.init(); }
	public static native LDKNetworkUpdate LDKNetworkUpdate_ref_from_ptr(long ptr);
	public static class LDKCOption_NetworkUpdateZ {
		private LDKCOption_NetworkUpdateZ() {}
		public final static class Some extends LDKCOption_NetworkUpdateZ {
			public long some;
			Some(long some) { this.some = some; }
		}
		public final static class None extends LDKCOption_NetworkUpdateZ {
			None() { }
		}
		static native void init();
	}
	static { LDKCOption_NetworkUpdateZ.init(); }
	public static native LDKCOption_NetworkUpdateZ LDKCOption_NetworkUpdateZ_ref_from_ptr(long ptr);
	public static class LDKSpendableOutputDescriptor {
		private LDKSpendableOutputDescriptor() {}
		public final static class StaticOutput extends LDKSpendableOutputDescriptor {
			public long outpoint;
			public long output;
			StaticOutput(long outpoint, long output) { this.outpoint = outpoint; this.output = output; }
		}
		public final static class DelayedPaymentOutput extends LDKSpendableOutputDescriptor {
			public long delayed_payment_output;
			DelayedPaymentOutput(long delayed_payment_output) { this.delayed_payment_output = delayed_payment_output; }
		}
		public final static class StaticPaymentOutput extends LDKSpendableOutputDescriptor {
			public long static_payment_output;
			StaticPaymentOutput(long static_payment_output) { this.static_payment_output = static_payment_output; }
		}
		static native void init();
	}
	static { LDKSpendableOutputDescriptor.init(); }
	public static native LDKSpendableOutputDescriptor LDKSpendableOutputDescriptor_ref_from_ptr(long ptr);
	public static class LDKPaymentPurpose {
		private LDKPaymentPurpose() {}
		public final static class InvoicePayment extends LDKPaymentPurpose {
			public byte[] payment_preimage;
			public byte[] payment_secret;
			InvoicePayment(byte[] payment_preimage, byte[] payment_secret) { this.payment_preimage = payment_preimage; this.payment_secret = payment_secret; }
		}
		public final static class SpontaneousPayment extends LDKPaymentPurpose {
			public byte[] spontaneous_payment;
			SpontaneousPayment(byte[] spontaneous_payment) { this.spontaneous_payment = spontaneous_payment; }
		}
		static native void init();
	}
	static { LDKPaymentPurpose.init(); }
	public static native LDKPaymentPurpose LDKPaymentPurpose_ref_from_ptr(long ptr);
	public static class LDKEvent {
		private LDKEvent() {}
		public final static class FundingGenerationReady extends LDKEvent {
			public byte[] temporary_channel_id;
			public long channel_value_satoshis;
			public byte[] output_script;
			public long user_channel_id;
			FundingGenerationReady(byte[] temporary_channel_id, long channel_value_satoshis, byte[] output_script, long user_channel_id) { this.temporary_channel_id = temporary_channel_id; this.channel_value_satoshis = channel_value_satoshis; this.output_script = output_script; this.user_channel_id = user_channel_id; }
		}
		public final static class PaymentReceived extends LDKEvent {
			public byte[] payment_hash;
			public long amt;
			public long purpose;
			PaymentReceived(byte[] payment_hash, long amt, long purpose) { this.payment_hash = payment_hash; this.amt = amt; this.purpose = purpose; }
		}
		public final static class PaymentSent extends LDKEvent {
			public byte[] payment_id;
			public byte[] payment_preimage;
			public byte[] payment_hash;
			public long fee_paid_msat;
			PaymentSent(byte[] payment_id, byte[] payment_preimage, byte[] payment_hash, long fee_paid_msat) { this.payment_id = payment_id; this.payment_preimage = payment_preimage; this.payment_hash = payment_hash; this.fee_paid_msat = fee_paid_msat; }
		}
		public final static class PaymentPathFailed extends LDKEvent {
			public byte[] payment_id;
			public byte[] payment_hash;
			public boolean rejected_by_dest;
			public long network_update;
			public boolean all_paths_failed;
			public long[] path;
			public long short_channel_id;
			public long retry;
			PaymentPathFailed(byte[] payment_id, byte[] payment_hash, boolean rejected_by_dest, long network_update, boolean all_paths_failed, long[] path, long short_channel_id, long retry) { this.payment_id = payment_id; this.payment_hash = payment_hash; this.rejected_by_dest = rejected_by_dest; this.network_update = network_update; this.all_paths_failed = all_paths_failed; this.path = path; this.short_channel_id = short_channel_id; this.retry = retry; }
		}
		public final static class PaymentFailed extends LDKEvent {
			public byte[] payment_id;
			public byte[] payment_hash;
			PaymentFailed(byte[] payment_id, byte[] payment_hash) { this.payment_id = payment_id; this.payment_hash = payment_hash; }
		}
		public final static class PendingHTLCsForwardable extends LDKEvent {
			public long time_forwardable;
			PendingHTLCsForwardable(long time_forwardable) { this.time_forwardable = time_forwardable; }
		}
		public final static class SpendableOutputs extends LDKEvent {
			public long[] outputs;
			SpendableOutputs(long[] outputs) { this.outputs = outputs; }
		}
		public final static class PaymentForwarded extends LDKEvent {
			public long fee_earned_msat;
			public boolean claim_from_onchain_tx;
			PaymentForwarded(long fee_earned_msat, boolean claim_from_onchain_tx) { this.fee_earned_msat = fee_earned_msat; this.claim_from_onchain_tx = claim_from_onchain_tx; }
		}
		public final static class ChannelClosed extends LDKEvent {
			public byte[] channel_id;
			public long user_channel_id;
			public long reason;
			ChannelClosed(byte[] channel_id, long user_channel_id, long reason) { this.channel_id = channel_id; this.user_channel_id = user_channel_id; this.reason = reason; }
		}
		public final static class DiscardFunding extends LDKEvent {
			public byte[] channel_id;
			public byte[] transaction;
			DiscardFunding(byte[] channel_id, byte[] transaction) { this.channel_id = channel_id; this.transaction = transaction; }
		}
		public final static class PaymentPathSuccessful extends LDKEvent {
			public byte[] payment_id;
			public byte[] payment_hash;
			public long[] path;
			PaymentPathSuccessful(byte[] payment_id, byte[] payment_hash, long[] path) { this.payment_id = payment_id; this.payment_hash = payment_hash; this.path = path; }
		}
		public final static class OpenChannelRequest extends LDKEvent {
			public byte[] temporary_channel_id;
			public byte[] counterparty_node_id;
			public long funding_satoshis;
			public long push_msat;
			public long channel_type;
			OpenChannelRequest(byte[] temporary_channel_id, byte[] counterparty_node_id, long funding_satoshis, long push_msat, long channel_type) { this.temporary_channel_id = temporary_channel_id; this.counterparty_node_id = counterparty_node_id; this.funding_satoshis = funding_satoshis; this.push_msat = push_msat; this.channel_type = channel_type; }
		}
		static native void init();
	}
	static { LDKEvent.init(); }
	public static native LDKEvent LDKEvent_ref_from_ptr(long ptr);
	public static class LDKCOption_EventZ {
		private LDKCOption_EventZ() {}
		public final static class Some extends LDKCOption_EventZ {
			public long some;
			Some(long some) { this.some = some; }
		}
		public final static class None extends LDKCOption_EventZ {
			None() { }
		}
		static native void init();
	}
	static { LDKCOption_EventZ.init(); }
	public static native LDKCOption_EventZ LDKCOption_EventZ_ref_from_ptr(long ptr);
	// struct LDKCOption_EventZ CResult_COption_EventZDecodeErrorZ_get_ok(LDKCResult_COption_EventZDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_COption_EventZDecodeErrorZ_get_ok(long owner);
	// struct LDKDecodeError CResult_COption_EventZDecodeErrorZ_get_err(LDKCResult_COption_EventZDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_COption_EventZDecodeErrorZ_get_err(long owner);
	public static class LDKErrorAction {
		private LDKErrorAction() {}
		public final static class DisconnectPeer extends LDKErrorAction {
			public long msg;
			DisconnectPeer(long msg) { this.msg = msg; }
		}
		public final static class IgnoreError extends LDKErrorAction {
			IgnoreError() { }
		}
		public final static class IgnoreAndLog extends LDKErrorAction {
			public org.ldk.enums.Level ignore_and_log;
			IgnoreAndLog(org.ldk.enums.Level ignore_and_log) { this.ignore_and_log = ignore_and_log; }
		}
		public final static class IgnoreDuplicateGossip extends LDKErrorAction {
			IgnoreDuplicateGossip() { }
		}
		public final static class SendErrorMessage extends LDKErrorAction {
			public long msg;
			SendErrorMessage(long msg) { this.msg = msg; }
		}
		public final static class SendWarningMessage extends LDKErrorAction {
			public long msg;
			public org.ldk.enums.Level log_level;
			SendWarningMessage(long msg, org.ldk.enums.Level log_level) { this.msg = msg; this.log_level = log_level; }
		}
		static native void init();
	}
	static { LDKErrorAction.init(); }
	public static native LDKErrorAction LDKErrorAction_ref_from_ptr(long ptr);
	public static class LDKMessageSendEvent {
		private LDKMessageSendEvent() {}
		public final static class SendAcceptChannel extends LDKMessageSendEvent {
			public byte[] node_id;
			public long msg;
			SendAcceptChannel(byte[] node_id, long msg) { this.node_id = node_id; this.msg = msg; }
		}
		public final static class SendOpenChannel extends LDKMessageSendEvent {
			public byte[] node_id;
			public long msg;
			SendOpenChannel(byte[] node_id, long msg) { this.node_id = node_id; this.msg = msg; }
		}
		public final static class SendFundingCreated extends LDKMessageSendEvent {
			public byte[] node_id;
			public long msg;
			SendFundingCreated(byte[] node_id, long msg) { this.node_id = node_id; this.msg = msg; }
		}
		public final static class SendFundingSigned extends LDKMessageSendEvent {
			public byte[] node_id;
			public long msg;
			SendFundingSigned(byte[] node_id, long msg) { this.node_id = node_id; this.msg = msg; }
		}
		public final static class SendFundingLocked extends LDKMessageSendEvent {
			public byte[] node_id;
			public long msg;
			SendFundingLocked(byte[] node_id, long msg) { this.node_id = node_id; this.msg = msg; }
		}
		public final static class SendAnnouncementSignatures extends LDKMessageSendEvent {
			public byte[] node_id;
			public long msg;
			SendAnnouncementSignatures(byte[] node_id, long msg) { this.node_id = node_id; this.msg = msg; }
		}
		public final static class UpdateHTLCs extends LDKMessageSendEvent {
			public byte[] node_id;
			public long updates;
			UpdateHTLCs(byte[] node_id, long updates) { this.node_id = node_id; this.updates = updates; }
		}
		public final static class SendRevokeAndACK extends LDKMessageSendEvent {
			public byte[] node_id;
			public long msg;
			SendRevokeAndACK(byte[] node_id, long msg) { this.node_id = node_id; this.msg = msg; }
		}
		public final static class SendClosingSigned extends LDKMessageSendEvent {
			public byte[] node_id;
			public long msg;
			SendClosingSigned(byte[] node_id, long msg) { this.node_id = node_id; this.msg = msg; }
		}
		public final static class SendShutdown extends LDKMessageSendEvent {
			public byte[] node_id;
			public long msg;
			SendShutdown(byte[] node_id, long msg) { this.node_id = node_id; this.msg = msg; }
		}
		public final static class SendChannelReestablish extends LDKMessageSendEvent {
			public byte[] node_id;
			public long msg;
			SendChannelReestablish(byte[] node_id, long msg) { this.node_id = node_id; this.msg = msg; }
		}
		public final static class BroadcastChannelAnnouncement extends LDKMessageSendEvent {
			public long msg;
			public long update_msg;
			BroadcastChannelAnnouncement(long msg, long update_msg) { this.msg = msg; this.update_msg = update_msg; }
		}
		public final static class BroadcastNodeAnnouncement extends LDKMessageSendEvent {
			public long msg;
			BroadcastNodeAnnouncement(long msg) { this.msg = msg; }
		}
		public final static class BroadcastChannelUpdate extends LDKMessageSendEvent {
			public long msg;
			BroadcastChannelUpdate(long msg) { this.msg = msg; }
		}
		public final static class SendChannelUpdate extends LDKMessageSendEvent {
			public byte[] node_id;
			public long msg;
			SendChannelUpdate(byte[] node_id, long msg) { this.node_id = node_id; this.msg = msg; }
		}
		public final static class HandleError extends LDKMessageSendEvent {
			public byte[] node_id;
			public long action;
			HandleError(byte[] node_id, long action) { this.node_id = node_id; this.action = action; }
		}
		public final static class SendChannelRangeQuery extends LDKMessageSendEvent {
			public byte[] node_id;
			public long msg;
			SendChannelRangeQuery(byte[] node_id, long msg) { this.node_id = node_id; this.msg = msg; }
		}
		public final static class SendShortIdsQuery extends LDKMessageSendEvent {
			public byte[] node_id;
			public long msg;
			SendShortIdsQuery(byte[] node_id, long msg) { this.node_id = node_id; this.msg = msg; }
		}
		public final static class SendReplyChannelRange extends LDKMessageSendEvent {
			public byte[] node_id;
			public long msg;
			SendReplyChannelRange(byte[] node_id, long msg) { this.node_id = node_id; this.msg = msg; }
		}
		public final static class SendGossipTimestampFilter extends LDKMessageSendEvent {
			public byte[] node_id;
			public long msg;
			SendGossipTimestampFilter(byte[] node_id, long msg) { this.node_id = node_id; this.msg = msg; }
		}
		static native void init();
	}
	static { LDKMessageSendEvent.init(); }
	public static native LDKMessageSendEvent LDKMessageSendEvent_ref_from_ptr(long ptr);
	// struct LDKFixedPenaltyScorer CResult_FixedPenaltyScorerDecodeErrorZ_get_ok(LDKCResult_FixedPenaltyScorerDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_FixedPenaltyScorerDecodeErrorZ_get_ok(long owner);
	// struct LDKDecodeError CResult_FixedPenaltyScorerDecodeErrorZ_get_err(LDKCResult_FixedPenaltyScorerDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_FixedPenaltyScorerDecodeErrorZ_get_err(long owner);
	// struct LDKScoringParameters CResult_ScoringParametersDecodeErrorZ_get_ok(LDKCResult_ScoringParametersDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_ScoringParametersDecodeErrorZ_get_ok(long owner);
	// struct LDKDecodeError CResult_ScoringParametersDecodeErrorZ_get_err(LDKCResult_ScoringParametersDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_ScoringParametersDecodeErrorZ_get_err(long owner);
	// struct LDKScorer *CResult_ScorerDecodeErrorZ_get_ok(LDKCResult_ScorerDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_ScorerDecodeErrorZ_get_ok(long owner);
	// struct LDKDecodeError CResult_ScorerDecodeErrorZ_get_err(LDKCResult_ScorerDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_ScorerDecodeErrorZ_get_err(long owner);
	// struct LDKProbabilisticScorer *CResult_ProbabilisticScorerDecodeErrorZ_get_ok(LDKCResult_ProbabilisticScorerDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_ProbabilisticScorerDecodeErrorZ_get_ok(long owner);
	// struct LDKDecodeError CResult_ProbabilisticScorerDecodeErrorZ_get_err(LDKCResult_ProbabilisticScorerDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_ProbabilisticScorerDecodeErrorZ_get_err(long owner);
	// struct LDKInitFeatures CResult_InitFeaturesDecodeErrorZ_get_ok(LDKCResult_InitFeaturesDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_InitFeaturesDecodeErrorZ_get_ok(long owner);
	// struct LDKDecodeError CResult_InitFeaturesDecodeErrorZ_get_err(LDKCResult_InitFeaturesDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_InitFeaturesDecodeErrorZ_get_err(long owner);
	// struct LDKChannelFeatures CResult_ChannelFeaturesDecodeErrorZ_get_ok(LDKCResult_ChannelFeaturesDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_ChannelFeaturesDecodeErrorZ_get_ok(long owner);
	// struct LDKDecodeError CResult_ChannelFeaturesDecodeErrorZ_get_err(LDKCResult_ChannelFeaturesDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_ChannelFeaturesDecodeErrorZ_get_err(long owner);
	// struct LDKNodeFeatures CResult_NodeFeaturesDecodeErrorZ_get_ok(LDKCResult_NodeFeaturesDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_NodeFeaturesDecodeErrorZ_get_ok(long owner);
	// struct LDKDecodeError CResult_NodeFeaturesDecodeErrorZ_get_err(LDKCResult_NodeFeaturesDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_NodeFeaturesDecodeErrorZ_get_err(long owner);
	// struct LDKInvoiceFeatures CResult_InvoiceFeaturesDecodeErrorZ_get_ok(LDKCResult_InvoiceFeaturesDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_InvoiceFeaturesDecodeErrorZ_get_ok(long owner);
	// struct LDKDecodeError CResult_InvoiceFeaturesDecodeErrorZ_get_err(LDKCResult_InvoiceFeaturesDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_InvoiceFeaturesDecodeErrorZ_get_err(long owner);
	// struct LDKChannelTypeFeatures CResult_ChannelTypeFeaturesDecodeErrorZ_get_ok(LDKCResult_ChannelTypeFeaturesDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_ChannelTypeFeaturesDecodeErrorZ_get_ok(long owner);
	// struct LDKDecodeError CResult_ChannelTypeFeaturesDecodeErrorZ_get_err(LDKCResult_ChannelTypeFeaturesDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_ChannelTypeFeaturesDecodeErrorZ_get_err(long owner);
	// struct LDKDelayedPaymentOutputDescriptor CResult_DelayedPaymentOutputDescriptorDecodeErrorZ_get_ok(LDKCResult_DelayedPaymentOutputDescriptorDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_DelayedPaymentOutputDescriptorDecodeErrorZ_get_ok(long owner);
	// struct LDKDecodeError CResult_DelayedPaymentOutputDescriptorDecodeErrorZ_get_err(LDKCResult_DelayedPaymentOutputDescriptorDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_DelayedPaymentOutputDescriptorDecodeErrorZ_get_err(long owner);
	// struct LDKStaticPaymentOutputDescriptor CResult_StaticPaymentOutputDescriptorDecodeErrorZ_get_ok(LDKCResult_StaticPaymentOutputDescriptorDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_StaticPaymentOutputDescriptorDecodeErrorZ_get_ok(long owner);
	// struct LDKDecodeError CResult_StaticPaymentOutputDescriptorDecodeErrorZ_get_err(LDKCResult_StaticPaymentOutputDescriptorDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_StaticPaymentOutputDescriptorDecodeErrorZ_get_err(long owner);
	// struct LDKSpendableOutputDescriptor CResult_SpendableOutputDescriptorDecodeErrorZ_get_ok(LDKCResult_SpendableOutputDescriptorDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_SpendableOutputDescriptorDecodeErrorZ_get_ok(long owner);
	// struct LDKDecodeError CResult_SpendableOutputDescriptorDecodeErrorZ_get_err(LDKCResult_SpendableOutputDescriptorDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_SpendableOutputDescriptorDecodeErrorZ_get_err(long owner);
	// struct LDKSignature C2Tuple_SignatureCVec_SignatureZZ_get_a(LDKC2Tuple_SignatureCVec_SignatureZZ *NONNULL_PTR owner);
	public static native byte[] C2Tuple_SignatureCVec_SignatureZZ_get_a(long owner);
	// struct LDKCVec_SignatureZ C2Tuple_SignatureCVec_SignatureZZ_get_b(LDKC2Tuple_SignatureCVec_SignatureZZ *NONNULL_PTR owner);
	public static native byte[][] C2Tuple_SignatureCVec_SignatureZZ_get_b(long owner);
	// struct LDKC2Tuple_SignatureCVec_SignatureZZ CResult_C2Tuple_SignatureCVec_SignatureZZNoneZ_get_ok(LDKCResult_C2Tuple_SignatureCVec_SignatureZZNoneZ *NONNULL_PTR owner);
	public static native long CResult_C2Tuple_SignatureCVec_SignatureZZNoneZ_get_ok(long owner);
	// void CResult_C2Tuple_SignatureCVec_SignatureZZNoneZ_get_err(LDKCResult_C2Tuple_SignatureCVec_SignatureZZNoneZ *NONNULL_PTR owner);
	public static native void CResult_C2Tuple_SignatureCVec_SignatureZZNoneZ_get_err(long owner);
	// struct LDKSignature CResult_SignatureNoneZ_get_ok(LDKCResult_SignatureNoneZ *NONNULL_PTR owner);
	public static native byte[] CResult_SignatureNoneZ_get_ok(long owner);
	// void CResult_SignatureNoneZ_get_err(LDKCResult_SignatureNoneZ *NONNULL_PTR owner);
	public static native void CResult_SignatureNoneZ_get_err(long owner);
	// struct LDKSignature C2Tuple_SignatureSignatureZ_get_a(LDKC2Tuple_SignatureSignatureZ *NONNULL_PTR owner);
	public static native byte[] C2Tuple_SignatureSignatureZ_get_a(long owner);
	// struct LDKSignature C2Tuple_SignatureSignatureZ_get_b(LDKC2Tuple_SignatureSignatureZ *NONNULL_PTR owner);
	public static native byte[] C2Tuple_SignatureSignatureZ_get_b(long owner);
	// struct LDKC2Tuple_SignatureSignatureZ CResult_C2Tuple_SignatureSignatureZNoneZ_get_ok(LDKCResult_C2Tuple_SignatureSignatureZNoneZ *NONNULL_PTR owner);
	public static native long CResult_C2Tuple_SignatureSignatureZNoneZ_get_ok(long owner);
	// void CResult_C2Tuple_SignatureSignatureZNoneZ_get_err(LDKCResult_C2Tuple_SignatureSignatureZNoneZ *NONNULL_PTR owner);
	public static native void CResult_C2Tuple_SignatureSignatureZNoneZ_get_err(long owner);
	// struct LDKSecretKey CResult_SecretKeyNoneZ_get_ok(LDKCResult_SecretKeyNoneZ *NONNULL_PTR owner);
	public static native byte[] CResult_SecretKeyNoneZ_get_ok(long owner);
	// void CResult_SecretKeyNoneZ_get_err(LDKCResult_SecretKeyNoneZ *NONNULL_PTR owner);
	public static native void CResult_SecretKeyNoneZ_get_err(long owner);
	public interface LDKBaseSign {
		 byte[] get_per_commitment_point(long idx);
		 byte[] release_commitment_secret(long idx);
		 long validate_holder_commitment(long holder_tx, byte[][] preimages);
		 byte[] channel_keys_id();
		 long sign_counterparty_commitment(long commitment_tx, byte[][] preimages);
		 long validate_counterparty_revocation(long idx, byte[] secret);
		 long sign_holder_commitment_and_htlcs(long commitment_tx);
		 long sign_justice_revoked_output(byte[] justice_tx, long input, long amount, byte[] per_commitment_key);
		 long sign_justice_revoked_htlc(byte[] justice_tx, long input, long amount, byte[] per_commitment_key, long htlc);
		 long sign_counterparty_htlc_transaction(byte[] htlc_tx, long input, long amount, byte[] per_commitment_point, long htlc);
		 long sign_closing_transaction(long closing_tx);
		 long sign_channel_announcement(long msg);
		 void ready_channel(long channel_parameters);
	}
	public static native long LDKBaseSign_new(LDKBaseSign impl, long pubkeys);
	// LDKPublicKey BaseSign_get_per_commitment_point LDKBaseSign *NONNULL_PTR this_arg, uint64_t idx
	public static native byte[] BaseSign_get_per_commitment_point(long this_arg, long idx);
	// LDKThirtyTwoBytes BaseSign_release_commitment_secret LDKBaseSign *NONNULL_PTR this_arg, uint64_t idx
	public static native byte[] BaseSign_release_commitment_secret(long this_arg, long idx);
	// LDKCResult_NoneNoneZ BaseSign_validate_holder_commitment LDKBaseSign *NONNULL_PTR this_arg, const struct LDKHolderCommitmentTransaction *NONNULL_PTR holder_tx, struct LDKCVec_PaymentPreimageZ preimages
	public static native long BaseSign_validate_holder_commitment(long this_arg, long holder_tx, byte[][] preimages);
	// LDKThirtyTwoBytes BaseSign_channel_keys_id LDKBaseSign *NONNULL_PTR this_arg
	public static native byte[] BaseSign_channel_keys_id(long this_arg);
	// LDKCResult_C2Tuple_SignatureCVec_SignatureZZNoneZ BaseSign_sign_counterparty_commitment LDKBaseSign *NONNULL_PTR this_arg, const struct LDKCommitmentTransaction *NONNULL_PTR commitment_tx, struct LDKCVec_PaymentPreimageZ preimages
	public static native long BaseSign_sign_counterparty_commitment(long this_arg, long commitment_tx, byte[][] preimages);
	// LDKCResult_NoneNoneZ BaseSign_validate_counterparty_revocation LDKBaseSign *NONNULL_PTR this_arg, uint64_t idx, const uint8_t (*secret)[32]
	public static native long BaseSign_validate_counterparty_revocation(long this_arg, long idx, byte[] secret);
	// LDKCResult_C2Tuple_SignatureCVec_SignatureZZNoneZ BaseSign_sign_holder_commitment_and_htlcs LDKBaseSign *NONNULL_PTR this_arg, const struct LDKHolderCommitmentTransaction *NONNULL_PTR commitment_tx
	public static native long BaseSign_sign_holder_commitment_and_htlcs(long this_arg, long commitment_tx);
	// LDKCResult_SignatureNoneZ BaseSign_sign_justice_revoked_output LDKBaseSign *NONNULL_PTR this_arg, struct LDKTransaction justice_tx, uintptr_t input, uint64_t amount, const uint8_t (*per_commitment_key)[32]
	public static native long BaseSign_sign_justice_revoked_output(long this_arg, byte[] justice_tx, long input, long amount, byte[] per_commitment_key);
	// LDKCResult_SignatureNoneZ BaseSign_sign_justice_revoked_htlc LDKBaseSign *NONNULL_PTR this_arg, struct LDKTransaction justice_tx, uintptr_t input, uint64_t amount, const uint8_t (*per_commitment_key)[32], const struct LDKHTLCOutputInCommitment *NONNULL_PTR htlc
	public static native long BaseSign_sign_justice_revoked_htlc(long this_arg, byte[] justice_tx, long input, long amount, byte[] per_commitment_key, long htlc);
	// LDKCResult_SignatureNoneZ BaseSign_sign_counterparty_htlc_transaction LDKBaseSign *NONNULL_PTR this_arg, struct LDKTransaction htlc_tx, uintptr_t input, uint64_t amount, struct LDKPublicKey per_commitment_point, const struct LDKHTLCOutputInCommitment *NONNULL_PTR htlc
	public static native long BaseSign_sign_counterparty_htlc_transaction(long this_arg, byte[] htlc_tx, long input, long amount, byte[] per_commitment_point, long htlc);
	// LDKCResult_SignatureNoneZ BaseSign_sign_closing_transaction LDKBaseSign *NONNULL_PTR this_arg, const struct LDKClosingTransaction *NONNULL_PTR closing_tx
	public static native long BaseSign_sign_closing_transaction(long this_arg, long closing_tx);
	// LDKCResult_C2Tuple_SignatureSignatureZNoneZ BaseSign_sign_channel_announcement LDKBaseSign *NONNULL_PTR this_arg, const struct LDKUnsignedChannelAnnouncement *NONNULL_PTR msg
	public static native long BaseSign_sign_channel_announcement(long this_arg, long msg);
	// void BaseSign_ready_channel LDKBaseSign *NONNULL_PTR this_arg, const struct LDKChannelTransactionParameters *NONNULL_PTR channel_parameters
	public static native void BaseSign_ready_channel(long this_arg, long channel_parameters);
	// LDKChannelPublicKeys BaseSign_get_pubkeys LDKBaseSign *NONNULL_PTR this_arg
	public static native long BaseSign_get_pubkeys(long this_arg);
	public interface LDKSign {
		 byte[] write();
	}
	public static native long LDKSign_new(LDKSign impl, LDKBaseSign BaseSign, long pubkeys);
	public static native long LDKSign_get_BaseSign(long arg);
	// LDKCVec_u8Z Sign_write LDKSign *NONNULL_PTR this_arg
	public static native byte[] Sign_write(long this_arg);
	// struct LDKSign CResult_SignDecodeErrorZ_get_ok(LDKCResult_SignDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_SignDecodeErrorZ_get_ok(long owner);
	// struct LDKDecodeError CResult_SignDecodeErrorZ_get_err(LDKCResult_SignDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_SignDecodeErrorZ_get_err(long owner);
	// struct LDKRecoverableSignature CResult_RecoverableSignatureNoneZ_get_ok(LDKCResult_RecoverableSignatureNoneZ *NONNULL_PTR owner);
	public static native byte[] CResult_RecoverableSignatureNoneZ_get_ok(long owner);
	// void CResult_RecoverableSignatureNoneZ_get_err(LDKCResult_RecoverableSignatureNoneZ *NONNULL_PTR owner);
	public static native void CResult_RecoverableSignatureNoneZ_get_err(long owner);
	// struct LDKCVec_CVec_u8ZZ CResult_CVec_CVec_u8ZZNoneZ_get_ok(LDKCResult_CVec_CVec_u8ZZNoneZ *NONNULL_PTR owner);
	public static native byte[][] CResult_CVec_CVec_u8ZZNoneZ_get_ok(long owner);
	// void CResult_CVec_CVec_u8ZZNoneZ_get_err(LDKCResult_CVec_CVec_u8ZZNoneZ *NONNULL_PTR owner);
	public static native void CResult_CVec_CVec_u8ZZNoneZ_get_err(long owner);
	// struct LDKInMemorySigner CResult_InMemorySignerDecodeErrorZ_get_ok(LDKCResult_InMemorySignerDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_InMemorySignerDecodeErrorZ_get_ok(long owner);
	// struct LDKDecodeError CResult_InMemorySignerDecodeErrorZ_get_err(LDKCResult_InMemorySignerDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_InMemorySignerDecodeErrorZ_get_err(long owner);
	// struct LDKTransaction CResult_TransactionNoneZ_get_ok(LDKCResult_TransactionNoneZ *NONNULL_PTR owner);
	public static native byte[] CResult_TransactionNoneZ_get_ok(long owner);
	// void CResult_TransactionNoneZ_get_err(LDKCResult_TransactionNoneZ *NONNULL_PTR owner);
	public static native void CResult_TransactionNoneZ_get_err(long owner);
	// struct LDKThirtyTwoBytes C2Tuple_BlockHashChannelMonitorZ_get_a(LDKC2Tuple_BlockHashChannelMonitorZ *NONNULL_PTR owner);
	public static native byte[] C2Tuple_BlockHashChannelMonitorZ_get_a(long owner);
	// struct LDKChannelMonitor C2Tuple_BlockHashChannelMonitorZ_get_b(LDKC2Tuple_BlockHashChannelMonitorZ *NONNULL_PTR owner);
	public static native long C2Tuple_BlockHashChannelMonitorZ_get_b(long owner);
	// struct LDKCVec_C2Tuple_BlockHashChannelMonitorZZ CResult_CVec_C2Tuple_BlockHashChannelMonitorZZErrorZ_get_ok(LDKCResult_CVec_C2Tuple_BlockHashChannelMonitorZZErrorZ *NONNULL_PTR owner);
	public static native long[] CResult_CVec_C2Tuple_BlockHashChannelMonitorZZErrorZ_get_ok(long owner);
	// enum LDKIOError CResult_CVec_C2Tuple_BlockHashChannelMonitorZZErrorZ_get_err(LDKCResult_CVec_C2Tuple_BlockHashChannelMonitorZZErrorZ *NONNULL_PTR owner);
	public static native IOError CResult_CVec_C2Tuple_BlockHashChannelMonitorZZErrorZ_get_err(long owner);
	public static class LDKCOption_u16Z {
		private LDKCOption_u16Z() {}
		public final static class Some extends LDKCOption_u16Z {
			public short some;
			Some(short some) { this.some = some; }
		}
		public final static class None extends LDKCOption_u16Z {
			None() { }
		}
		static native void init();
	}
	static { LDKCOption_u16Z.init(); }
	public static native LDKCOption_u16Z LDKCOption_u16Z_ref_from_ptr(long ptr);
	public static class LDKAPIError {
		private LDKAPIError() {}
		public final static class APIMisuseError extends LDKAPIError {
			public java.lang.String err;
			APIMisuseError(java.lang.String err) { this.err = err; }
		}
		public final static class FeeRateTooHigh extends LDKAPIError {
			public java.lang.String err;
			public int feerate;
			FeeRateTooHigh(java.lang.String err, int feerate) { this.err = err; this.feerate = feerate; }
		}
		public final static class RouteError extends LDKAPIError {
			public java.lang.String err;
			RouteError(java.lang.String err) { this.err = err; }
		}
		public final static class ChannelUnavailable extends LDKAPIError {
			public java.lang.String err;
			ChannelUnavailable(java.lang.String err) { this.err = err; }
		}
		public final static class MonitorUpdateFailed extends LDKAPIError {
			MonitorUpdateFailed() { }
		}
		public final static class IncompatibleShutdownScript extends LDKAPIError {
			public long script;
			IncompatibleShutdownScript(long script) { this.script = script; }
		}
		static native void init();
	}
	static { LDKAPIError.init(); }
	public static native LDKAPIError LDKAPIError_ref_from_ptr(long ptr);
	// void CResult_NoneAPIErrorZ_get_ok(LDKCResult_NoneAPIErrorZ *NONNULL_PTR owner);
	public static native void CResult_NoneAPIErrorZ_get_ok(long owner);
	// struct LDKAPIError CResult_NoneAPIErrorZ_get_err(LDKCResult_NoneAPIErrorZ *NONNULL_PTR owner);
	public static native long CResult_NoneAPIErrorZ_get_err(long owner);
	// struct LDKThirtyTwoBytes CResult__u832APIErrorZ_get_ok(LDKCResult__u832APIErrorZ *NONNULL_PTR owner);
	public static native byte[] CResult__u832APIErrorZ_get_ok(long owner);
	// struct LDKAPIError CResult__u832APIErrorZ_get_err(LDKCResult__u832APIErrorZ *NONNULL_PTR owner);
	public static native long CResult__u832APIErrorZ_get_err(long owner);
	public static class LDKPaymentSendFailure {
		private LDKPaymentSendFailure() {}
		public final static class ParameterError extends LDKPaymentSendFailure {
			public long parameter_error;
			ParameterError(long parameter_error) { this.parameter_error = parameter_error; }
		}
		public final static class PathParameterError extends LDKPaymentSendFailure {
			public long[] path_parameter_error;
			PathParameterError(long[] path_parameter_error) { this.path_parameter_error = path_parameter_error; }
		}
		public final static class AllFailedRetrySafe extends LDKPaymentSendFailure {
			public long[] all_failed_retry_safe;
			AllFailedRetrySafe(long[] all_failed_retry_safe) { this.all_failed_retry_safe = all_failed_retry_safe; }
		}
		public final static class PartialFailure extends LDKPaymentSendFailure {
			public long[] results;
			public long failed_paths_retry;
			public byte[] payment_id;
			PartialFailure(long[] results, long failed_paths_retry, byte[] payment_id) { this.results = results; this.failed_paths_retry = failed_paths_retry; this.payment_id = payment_id; }
		}
		static native void init();
	}
	static { LDKPaymentSendFailure.init(); }
	public static native LDKPaymentSendFailure LDKPaymentSendFailure_ref_from_ptr(long ptr);
	// struct LDKThirtyTwoBytes CResult_PaymentIdPaymentSendFailureZ_get_ok(LDKCResult_PaymentIdPaymentSendFailureZ *NONNULL_PTR owner);
	public static native byte[] CResult_PaymentIdPaymentSendFailureZ_get_ok(long owner);
	// struct LDKPaymentSendFailure CResult_PaymentIdPaymentSendFailureZ_get_err(LDKCResult_PaymentIdPaymentSendFailureZ *NONNULL_PTR owner);
	public static native long CResult_PaymentIdPaymentSendFailureZ_get_err(long owner);
	// void CResult_NonePaymentSendFailureZ_get_ok(LDKCResult_NonePaymentSendFailureZ *NONNULL_PTR owner);
	public static native void CResult_NonePaymentSendFailureZ_get_ok(long owner);
	// struct LDKPaymentSendFailure CResult_NonePaymentSendFailureZ_get_err(LDKCResult_NonePaymentSendFailureZ *NONNULL_PTR owner);
	public static native long CResult_NonePaymentSendFailureZ_get_err(long owner);
	// struct LDKThirtyTwoBytes C2Tuple_PaymentHashPaymentIdZ_get_a(LDKC2Tuple_PaymentHashPaymentIdZ *NONNULL_PTR owner);
	public static native byte[] C2Tuple_PaymentHashPaymentIdZ_get_a(long owner);
	// struct LDKThirtyTwoBytes C2Tuple_PaymentHashPaymentIdZ_get_b(LDKC2Tuple_PaymentHashPaymentIdZ *NONNULL_PTR owner);
	public static native byte[] C2Tuple_PaymentHashPaymentIdZ_get_b(long owner);
	// struct LDKC2Tuple_PaymentHashPaymentIdZ CResult_C2Tuple_PaymentHashPaymentIdZPaymentSendFailureZ_get_ok(LDKCResult_C2Tuple_PaymentHashPaymentIdZPaymentSendFailureZ *NONNULL_PTR owner);
	public static native long CResult_C2Tuple_PaymentHashPaymentIdZPaymentSendFailureZ_get_ok(long owner);
	// struct LDKPaymentSendFailure CResult_C2Tuple_PaymentHashPaymentIdZPaymentSendFailureZ_get_err(LDKCResult_C2Tuple_PaymentHashPaymentIdZPaymentSendFailureZ *NONNULL_PTR owner);
	public static native long CResult_C2Tuple_PaymentHashPaymentIdZPaymentSendFailureZ_get_err(long owner);
	public static class LDKNetAddress {
		private LDKNetAddress() {}
		public final static class IPv4 extends LDKNetAddress {
			public byte[] addr;
			public short port;
			IPv4(byte[] addr, short port) { this.addr = addr; this.port = port; }
		}
		public final static class IPv6 extends LDKNetAddress {
			public byte[] addr;
			public short port;
			IPv6(byte[] addr, short port) { this.addr = addr; this.port = port; }
		}
		public final static class OnionV2 extends LDKNetAddress {
			public byte[] onion_v2;
			OnionV2(byte[] onion_v2) { this.onion_v2 = onion_v2; }
		}
		public final static class OnionV3 extends LDKNetAddress {
			public byte[] ed25519_pubkey;
			public short checksum;
			public byte version;
			public short port;
			OnionV3(byte[] ed25519_pubkey, short checksum, byte version, short port) { this.ed25519_pubkey = ed25519_pubkey; this.checksum = checksum; this.version = version; this.port = port; }
		}
		static native void init();
	}
	static { LDKNetAddress.init(); }
	public static native LDKNetAddress LDKNetAddress_ref_from_ptr(long ptr);
	// struct LDKThirtyTwoBytes C2Tuple_PaymentHashPaymentSecretZ_get_a(LDKC2Tuple_PaymentHashPaymentSecretZ *NONNULL_PTR owner);
	public static native byte[] C2Tuple_PaymentHashPaymentSecretZ_get_a(long owner);
	// struct LDKThirtyTwoBytes C2Tuple_PaymentHashPaymentSecretZ_get_b(LDKC2Tuple_PaymentHashPaymentSecretZ *NONNULL_PTR owner);
	public static native byte[] C2Tuple_PaymentHashPaymentSecretZ_get_b(long owner);
	// struct LDKC2Tuple_PaymentHashPaymentSecretZ CResult_C2Tuple_PaymentHashPaymentSecretZNoneZ_get_ok(LDKCResult_C2Tuple_PaymentHashPaymentSecretZNoneZ *NONNULL_PTR owner);
	public static native long CResult_C2Tuple_PaymentHashPaymentSecretZNoneZ_get_ok(long owner);
	// void CResult_C2Tuple_PaymentHashPaymentSecretZNoneZ_get_err(LDKCResult_C2Tuple_PaymentHashPaymentSecretZNoneZ *NONNULL_PTR owner);
	public static native void CResult_C2Tuple_PaymentHashPaymentSecretZNoneZ_get_err(long owner);
	// struct LDKC2Tuple_PaymentHashPaymentSecretZ CResult_C2Tuple_PaymentHashPaymentSecretZAPIErrorZ_get_ok(LDKCResult_C2Tuple_PaymentHashPaymentSecretZAPIErrorZ *NONNULL_PTR owner);
	public static native long CResult_C2Tuple_PaymentHashPaymentSecretZAPIErrorZ_get_ok(long owner);
	// struct LDKAPIError CResult_C2Tuple_PaymentHashPaymentSecretZAPIErrorZ_get_err(LDKCResult_C2Tuple_PaymentHashPaymentSecretZAPIErrorZ *NONNULL_PTR owner);
	public static native long CResult_C2Tuple_PaymentHashPaymentSecretZAPIErrorZ_get_err(long owner);
	// struct LDKThirtyTwoBytes CResult_PaymentSecretNoneZ_get_ok(LDKCResult_PaymentSecretNoneZ *NONNULL_PTR owner);
	public static native byte[] CResult_PaymentSecretNoneZ_get_ok(long owner);
	// void CResult_PaymentSecretNoneZ_get_err(LDKCResult_PaymentSecretNoneZ *NONNULL_PTR owner);
	public static native void CResult_PaymentSecretNoneZ_get_err(long owner);
	// struct LDKThirtyTwoBytes CResult_PaymentSecretAPIErrorZ_get_ok(LDKCResult_PaymentSecretAPIErrorZ *NONNULL_PTR owner);
	public static native byte[] CResult_PaymentSecretAPIErrorZ_get_ok(long owner);
	// struct LDKAPIError CResult_PaymentSecretAPIErrorZ_get_err(LDKCResult_PaymentSecretAPIErrorZ *NONNULL_PTR owner);
	public static native long CResult_PaymentSecretAPIErrorZ_get_err(long owner);
	// struct LDKThirtyTwoBytes CResult_PaymentPreimageAPIErrorZ_get_ok(LDKCResult_PaymentPreimageAPIErrorZ *NONNULL_PTR owner);
	public static native byte[] CResult_PaymentPreimageAPIErrorZ_get_ok(long owner);
	// struct LDKAPIError CResult_PaymentPreimageAPIErrorZ_get_err(LDKCResult_PaymentPreimageAPIErrorZ *NONNULL_PTR owner);
	public static native long CResult_PaymentPreimageAPIErrorZ_get_err(long owner);
	// struct LDKCounterpartyForwardingInfo CResult_CounterpartyForwardingInfoDecodeErrorZ_get_ok(LDKCResult_CounterpartyForwardingInfoDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_CounterpartyForwardingInfoDecodeErrorZ_get_ok(long owner);
	// struct LDKDecodeError CResult_CounterpartyForwardingInfoDecodeErrorZ_get_err(LDKCResult_CounterpartyForwardingInfoDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_CounterpartyForwardingInfoDecodeErrorZ_get_err(long owner);
	// struct LDKChannelCounterparty CResult_ChannelCounterpartyDecodeErrorZ_get_ok(LDKCResult_ChannelCounterpartyDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_ChannelCounterpartyDecodeErrorZ_get_ok(long owner);
	// struct LDKDecodeError CResult_ChannelCounterpartyDecodeErrorZ_get_err(LDKCResult_ChannelCounterpartyDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_ChannelCounterpartyDecodeErrorZ_get_err(long owner);
	// struct LDKChannelDetails CResult_ChannelDetailsDecodeErrorZ_get_ok(LDKCResult_ChannelDetailsDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_ChannelDetailsDecodeErrorZ_get_ok(long owner);
	// struct LDKDecodeError CResult_ChannelDetailsDecodeErrorZ_get_err(LDKCResult_ChannelDetailsDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_ChannelDetailsDecodeErrorZ_get_err(long owner);
	// struct LDKPhantomRouteHints CResult_PhantomRouteHintsDecodeErrorZ_get_ok(LDKCResult_PhantomRouteHintsDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_PhantomRouteHintsDecodeErrorZ_get_ok(long owner);
	// struct LDKDecodeError CResult_PhantomRouteHintsDecodeErrorZ_get_err(LDKCResult_PhantomRouteHintsDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_PhantomRouteHintsDecodeErrorZ_get_err(long owner);
	public interface LDKWatch {
		 long watch_channel(long funding_txo, long monitor);
		 long update_channel(long funding_txo, long update);
		 long[] release_pending_monitor_events();
	}
	public static native long LDKWatch_new(LDKWatch impl);
	// LDKCResult_NoneChannelMonitorUpdateErrZ Watch_watch_channel LDKWatch *NONNULL_PTR this_arg, struct LDKOutPoint funding_txo, struct LDKChannelMonitor monitor
	public static native long Watch_watch_channel(long this_arg, long funding_txo, long monitor);
	// LDKCResult_NoneChannelMonitorUpdateErrZ Watch_update_channel LDKWatch *NONNULL_PTR this_arg, struct LDKOutPoint funding_txo, struct LDKChannelMonitorUpdate update
	public static native long Watch_update_channel(long this_arg, long funding_txo, long update);
	// LDKCVec_MonitorEventZ Watch_release_pending_monitor_events LDKWatch *NONNULL_PTR this_arg
	public static native long[] Watch_release_pending_monitor_events(long this_arg);
	public interface LDKBroadcasterInterface {
		 void broadcast_transaction(byte[] tx);
	}
	public static native long LDKBroadcasterInterface_new(LDKBroadcasterInterface impl);
	// void BroadcasterInterface_broadcast_transaction LDKBroadcasterInterface *NONNULL_PTR this_arg, struct LDKTransaction tx
	public static native void BroadcasterInterface_broadcast_transaction(long this_arg, byte[] tx);
	public interface LDKKeysInterface {
		 long get_node_secret(Recipient recipient);
		 byte[] get_destination_script();
		 long get_shutdown_scriptpubkey();
		 long get_channel_signer(boolean inbound, long channel_value_satoshis);
		 byte[] get_secure_random_bytes();
		 long read_chan_signer(byte[] reader);
		 long sign_invoice(byte[] hrp_bytes, byte[] invoice_data, Recipient receipient);
		 byte[] get_inbound_payment_key_material();
	}
	public static native long LDKKeysInterface_new(LDKKeysInterface impl);
	// LDKCResult_SecretKeyNoneZ KeysInterface_get_node_secret LDKKeysInterface *NONNULL_PTR this_arg, enum LDKRecipient recipient
	public static native long KeysInterface_get_node_secret(long this_arg, Recipient recipient);
	// LDKCVec_u8Z KeysInterface_get_destination_script LDKKeysInterface *NONNULL_PTR this_arg
	public static native byte[] KeysInterface_get_destination_script(long this_arg);
	// LDKShutdownScript KeysInterface_get_shutdown_scriptpubkey LDKKeysInterface *NONNULL_PTR this_arg
	public static native long KeysInterface_get_shutdown_scriptpubkey(long this_arg);
	// LDKSign KeysInterface_get_channel_signer LDKKeysInterface *NONNULL_PTR this_arg, bool inbound, uint64_t channel_value_satoshis
	public static native long KeysInterface_get_channel_signer(long this_arg, boolean inbound, long channel_value_satoshis);
	// LDKThirtyTwoBytes KeysInterface_get_secure_random_bytes LDKKeysInterface *NONNULL_PTR this_arg
	public static native byte[] KeysInterface_get_secure_random_bytes(long this_arg);
	// LDKCResult_SignDecodeErrorZ KeysInterface_read_chan_signer LDKKeysInterface *NONNULL_PTR this_arg, struct LDKu8slice reader
	public static native long KeysInterface_read_chan_signer(long this_arg, byte[] reader);
	// LDKCResult_RecoverableSignatureNoneZ KeysInterface_sign_invoice LDKKeysInterface *NONNULL_PTR this_arg, struct LDKu8slice hrp_bytes, struct LDKCVec_u5Z invoice_data, enum LDKRecipient receipient
	public static native long KeysInterface_sign_invoice(long this_arg, byte[] hrp_bytes, byte[] invoice_data, Recipient receipient);
	// LDKThirtyTwoBytes KeysInterface_get_inbound_payment_key_material LDKKeysInterface *NONNULL_PTR this_arg
	public static native byte[] KeysInterface_get_inbound_payment_key_material(long this_arg);
	public interface LDKFeeEstimator {
		 int get_est_sat_per_1000_weight(ConfirmationTarget confirmation_target);
	}
	public static native long LDKFeeEstimator_new(LDKFeeEstimator impl);
	// uint32_t FeeEstimator_get_est_sat_per_1000_weight LDKFeeEstimator *NONNULL_PTR this_arg, enum LDKConfirmationTarget confirmation_target
	public static native int FeeEstimator_get_est_sat_per_1000_weight(long this_arg, ConfirmationTarget confirmation_target);
	public interface LDKLogger {
		 void log(long record);
	}
	public static native long LDKLogger_new(LDKLogger impl);
	// struct LDKThirtyTwoBytes C2Tuple_BlockHashChannelManagerZ_get_a(LDKC2Tuple_BlockHashChannelManagerZ *NONNULL_PTR owner);
	public static native byte[] C2Tuple_BlockHashChannelManagerZ_get_a(long owner);
	// struct LDKChannelManager *C2Tuple_BlockHashChannelManagerZ_get_b(LDKC2Tuple_BlockHashChannelManagerZ *NONNULL_PTR owner);
	public static native long C2Tuple_BlockHashChannelManagerZ_get_b(long owner);
	// struct LDKC2Tuple_BlockHashChannelManagerZ *CResult_C2Tuple_BlockHashChannelManagerZDecodeErrorZ_get_ok(LDKCResult_C2Tuple_BlockHashChannelManagerZDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_C2Tuple_BlockHashChannelManagerZDecodeErrorZ_get_ok(long owner);
	// struct LDKDecodeError CResult_C2Tuple_BlockHashChannelManagerZDecodeErrorZ_get_err(LDKCResult_C2Tuple_BlockHashChannelManagerZDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_C2Tuple_BlockHashChannelManagerZDecodeErrorZ_get_err(long owner);
	// struct LDKChannelConfig CResult_ChannelConfigDecodeErrorZ_get_ok(LDKCResult_ChannelConfigDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_ChannelConfigDecodeErrorZ_get_ok(long owner);
	// struct LDKDecodeError CResult_ChannelConfigDecodeErrorZ_get_err(LDKCResult_ChannelConfigDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_ChannelConfigDecodeErrorZ_get_err(long owner);
	// struct LDKOutPoint CResult_OutPointDecodeErrorZ_get_ok(LDKCResult_OutPointDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_OutPointDecodeErrorZ_get_ok(long owner);
	// struct LDKDecodeError CResult_OutPointDecodeErrorZ_get_err(LDKCResult_OutPointDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_OutPointDecodeErrorZ_get_err(long owner);
	public interface LDKType {
		 short type_id();
		 String debug_str();
		 byte[] write();
	}
	public static native long LDKType_new(LDKType impl);
	// uint16_t Type_type_id LDKType *NONNULL_PTR this_arg
	public static native short Type_type_id(long this_arg);
	// LDKStr Type_debug_str LDKType *NONNULL_PTR this_arg
	public static native String Type_debug_str(long this_arg);
	// LDKCVec_u8Z Type_write LDKType *NONNULL_PTR this_arg
	public static native byte[] Type_write(long this_arg);
	public static class LDKCOption_TypeZ {
		private LDKCOption_TypeZ() {}
		public final static class Some extends LDKCOption_TypeZ {
			public long some;
			Some(long some) { this.some = some; }
		}
		public final static class None extends LDKCOption_TypeZ {
			None() { }
		}
		static native void init();
	}
	static { LDKCOption_TypeZ.init(); }
	public static native LDKCOption_TypeZ LDKCOption_TypeZ_ref_from_ptr(long ptr);
	// struct LDKCOption_TypeZ CResult_COption_TypeZDecodeErrorZ_get_ok(LDKCResult_COption_TypeZDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_COption_TypeZDecodeErrorZ_get_ok(long owner);
	// struct LDKDecodeError CResult_COption_TypeZDecodeErrorZ_get_err(LDKCResult_COption_TypeZDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_COption_TypeZDecodeErrorZ_get_err(long owner);
	public static class LDKPaymentError {
		private LDKPaymentError() {}
		public final static class Invoice extends LDKPaymentError {
			public java.lang.String invoice;
			Invoice(java.lang.String invoice) { this.invoice = invoice; }
		}
		public final static class Routing extends LDKPaymentError {
			public long routing;
			Routing(long routing) { this.routing = routing; }
		}
		public final static class Sending extends LDKPaymentError {
			public long sending;
			Sending(long sending) { this.sending = sending; }
		}
		static native void init();
	}
	static { LDKPaymentError.init(); }
	public static native LDKPaymentError LDKPaymentError_ref_from_ptr(long ptr);
	// struct LDKThirtyTwoBytes CResult_PaymentIdPaymentErrorZ_get_ok(LDKCResult_PaymentIdPaymentErrorZ *NONNULL_PTR owner);
	public static native byte[] CResult_PaymentIdPaymentErrorZ_get_ok(long owner);
	// struct LDKPaymentError CResult_PaymentIdPaymentErrorZ_get_err(LDKCResult_PaymentIdPaymentErrorZ *NONNULL_PTR owner);
	public static native long CResult_PaymentIdPaymentErrorZ_get_err(long owner);
	public static class LDKParseError {
		private LDKParseError() {}
		public final static class Bech32Error extends LDKParseError {
			public long bech32_error;
			Bech32Error(long bech32_error) { this.bech32_error = bech32_error; }
		}
		public final static class ParseAmountError extends LDKParseError {
			public int parse_amount_error;
			ParseAmountError(int parse_amount_error) { this.parse_amount_error = parse_amount_error; }
		}
		public final static class MalformedSignature extends LDKParseError {
			public org.ldk.enums.Secp256k1Error malformed_signature;
			MalformedSignature(org.ldk.enums.Secp256k1Error malformed_signature) { this.malformed_signature = malformed_signature; }
		}
		public final static class BadPrefix extends LDKParseError {
			BadPrefix() { }
		}
		public final static class UnknownCurrency extends LDKParseError {
			UnknownCurrency() { }
		}
		public final static class UnknownSiPrefix extends LDKParseError {
			UnknownSiPrefix() { }
		}
		public final static class MalformedHRP extends LDKParseError {
			MalformedHRP() { }
		}
		public final static class TooShortDataPart extends LDKParseError {
			TooShortDataPart() { }
		}
		public final static class UnexpectedEndOfTaggedFields extends LDKParseError {
			UnexpectedEndOfTaggedFields() { }
		}
		public final static class DescriptionDecodeError extends LDKParseError {
			public int description_decode_error;
			DescriptionDecodeError(int description_decode_error) { this.description_decode_error = description_decode_error; }
		}
		public final static class PaddingError extends LDKParseError {
			PaddingError() { }
		}
		public final static class IntegerOverflowError extends LDKParseError {
			IntegerOverflowError() { }
		}
		public final static class InvalidSegWitProgramLength extends LDKParseError {
			InvalidSegWitProgramLength() { }
		}
		public final static class InvalidPubKeyHashLength extends LDKParseError {
			InvalidPubKeyHashLength() { }
		}
		public final static class InvalidScriptHashLength extends LDKParseError {
			InvalidScriptHashLength() { }
		}
		public final static class InvalidRecoveryId extends LDKParseError {
			InvalidRecoveryId() { }
		}
		public final static class InvalidSliceLength extends LDKParseError {
			public java.lang.String invalid_slice_length;
			InvalidSliceLength(java.lang.String invalid_slice_length) { this.invalid_slice_length = invalid_slice_length; }
		}
		public final static class Skip extends LDKParseError {
			Skip() { }
		}
		static native void init();
	}
	static { LDKParseError.init(); }
	public static native LDKParseError LDKParseError_ref_from_ptr(long ptr);
	// enum LDKSiPrefix CResult_SiPrefixParseErrorZ_get_ok(LDKCResult_SiPrefixParseErrorZ *NONNULL_PTR owner);
	public static native SiPrefix CResult_SiPrefixParseErrorZ_get_ok(long owner);
	// struct LDKParseError CResult_SiPrefixParseErrorZ_get_err(LDKCResult_SiPrefixParseErrorZ *NONNULL_PTR owner);
	public static native long CResult_SiPrefixParseErrorZ_get_err(long owner);
	public static class LDKParseOrSemanticError {
		private LDKParseOrSemanticError() {}
		public final static class ParseError extends LDKParseOrSemanticError {
			public long parse_error;
			ParseError(long parse_error) { this.parse_error = parse_error; }
		}
		public final static class SemanticError extends LDKParseOrSemanticError {
			public org.ldk.enums.SemanticError semantic_error;
			SemanticError(org.ldk.enums.SemanticError semantic_error) { this.semantic_error = semantic_error; }
		}
		static native void init();
	}
	static { LDKParseOrSemanticError.init(); }
	public static native LDKParseOrSemanticError LDKParseOrSemanticError_ref_from_ptr(long ptr);
	// struct LDKInvoice CResult_InvoiceParseOrSemanticErrorZ_get_ok(LDKCResult_InvoiceParseOrSemanticErrorZ *NONNULL_PTR owner);
	public static native long CResult_InvoiceParseOrSemanticErrorZ_get_ok(long owner);
	// struct LDKParseOrSemanticError CResult_InvoiceParseOrSemanticErrorZ_get_err(LDKCResult_InvoiceParseOrSemanticErrorZ *NONNULL_PTR owner);
	public static native long CResult_InvoiceParseOrSemanticErrorZ_get_err(long owner);
	// struct LDKSignedRawInvoice CResult_SignedRawInvoiceParseErrorZ_get_ok(LDKCResult_SignedRawInvoiceParseErrorZ *NONNULL_PTR owner);
	public static native long CResult_SignedRawInvoiceParseErrorZ_get_ok(long owner);
	// struct LDKParseError CResult_SignedRawInvoiceParseErrorZ_get_err(LDKCResult_SignedRawInvoiceParseErrorZ *NONNULL_PTR owner);
	public static native long CResult_SignedRawInvoiceParseErrorZ_get_err(long owner);
	// struct LDKRawInvoice C3Tuple_RawInvoice_u832InvoiceSignatureZ_get_a(LDKC3Tuple_RawInvoice_u832InvoiceSignatureZ *NONNULL_PTR owner);
	public static native long C3Tuple_RawInvoice_u832InvoiceSignatureZ_get_a(long owner);
	// struct LDKThirtyTwoBytes C3Tuple_RawInvoice_u832InvoiceSignatureZ_get_b(LDKC3Tuple_RawInvoice_u832InvoiceSignatureZ *NONNULL_PTR owner);
	public static native byte[] C3Tuple_RawInvoice_u832InvoiceSignatureZ_get_b(long owner);
	// struct LDKInvoiceSignature C3Tuple_RawInvoice_u832InvoiceSignatureZ_get_c(LDKC3Tuple_RawInvoice_u832InvoiceSignatureZ *NONNULL_PTR owner);
	public static native long C3Tuple_RawInvoice_u832InvoiceSignatureZ_get_c(long owner);
	// struct LDKPayeePubKey CResult_PayeePubKeyErrorZ_get_ok(LDKCResult_PayeePubKeyErrorZ *NONNULL_PTR owner);
	public static native long CResult_PayeePubKeyErrorZ_get_ok(long owner);
	// enum LDKSecp256k1Error CResult_PayeePubKeyErrorZ_get_err(LDKCResult_PayeePubKeyErrorZ *NONNULL_PTR owner);
	public static native Secp256k1Error CResult_PayeePubKeyErrorZ_get_err(long owner);
	// struct LDKPositiveTimestamp CResult_PositiveTimestampCreationErrorZ_get_ok(LDKCResult_PositiveTimestampCreationErrorZ *NONNULL_PTR owner);
	public static native long CResult_PositiveTimestampCreationErrorZ_get_ok(long owner);
	// enum LDKCreationError CResult_PositiveTimestampCreationErrorZ_get_err(LDKCResult_PositiveTimestampCreationErrorZ *NONNULL_PTR owner);
	public static native CreationError CResult_PositiveTimestampCreationErrorZ_get_err(long owner);
	// void CResult_NoneSemanticErrorZ_get_ok(LDKCResult_NoneSemanticErrorZ *NONNULL_PTR owner);
	public static native void CResult_NoneSemanticErrorZ_get_ok(long owner);
	// enum LDKSemanticError CResult_NoneSemanticErrorZ_get_err(LDKCResult_NoneSemanticErrorZ *NONNULL_PTR owner);
	public static native SemanticError CResult_NoneSemanticErrorZ_get_err(long owner);
	// struct LDKInvoice CResult_InvoiceSemanticErrorZ_get_ok(LDKCResult_InvoiceSemanticErrorZ *NONNULL_PTR owner);
	public static native long CResult_InvoiceSemanticErrorZ_get_ok(long owner);
	// enum LDKSemanticError CResult_InvoiceSemanticErrorZ_get_err(LDKCResult_InvoiceSemanticErrorZ *NONNULL_PTR owner);
	public static native SemanticError CResult_InvoiceSemanticErrorZ_get_err(long owner);
	// struct LDKDescription CResult_DescriptionCreationErrorZ_get_ok(LDKCResult_DescriptionCreationErrorZ *NONNULL_PTR owner);
	public static native long CResult_DescriptionCreationErrorZ_get_ok(long owner);
	// enum LDKCreationError CResult_DescriptionCreationErrorZ_get_err(LDKCResult_DescriptionCreationErrorZ *NONNULL_PTR owner);
	public static native CreationError CResult_DescriptionCreationErrorZ_get_err(long owner);
	// struct LDKPrivateRoute CResult_PrivateRouteCreationErrorZ_get_ok(LDKCResult_PrivateRouteCreationErrorZ *NONNULL_PTR owner);
	public static native long CResult_PrivateRouteCreationErrorZ_get_ok(long owner);
	// enum LDKCreationError CResult_PrivateRouteCreationErrorZ_get_err(LDKCResult_PrivateRouteCreationErrorZ *NONNULL_PTR owner);
	public static native CreationError CResult_PrivateRouteCreationErrorZ_get_err(long owner);
	// struct LDKStr CResult_StringErrorZ_get_ok(LDKCResult_StringErrorZ *NONNULL_PTR owner);
	public static native String CResult_StringErrorZ_get_ok(long owner);
	// enum LDKSecp256k1Error CResult_StringErrorZ_get_err(LDKCResult_StringErrorZ *NONNULL_PTR owner);
	public static native Secp256k1Error CResult_StringErrorZ_get_err(long owner);
	// struct LDKChannelMonitorUpdate CResult_ChannelMonitorUpdateDecodeErrorZ_get_ok(LDKCResult_ChannelMonitorUpdateDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_ChannelMonitorUpdateDecodeErrorZ_get_ok(long owner);
	// struct LDKDecodeError CResult_ChannelMonitorUpdateDecodeErrorZ_get_err(LDKCResult_ChannelMonitorUpdateDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_ChannelMonitorUpdateDecodeErrorZ_get_err(long owner);
	public static class LDKCOption_MonitorEventZ {
		private LDKCOption_MonitorEventZ() {}
		public final static class Some extends LDKCOption_MonitorEventZ {
			public long some;
			Some(long some) { this.some = some; }
		}
		public final static class None extends LDKCOption_MonitorEventZ {
			None() { }
		}
		static native void init();
	}
	static { LDKCOption_MonitorEventZ.init(); }
	public static native LDKCOption_MonitorEventZ LDKCOption_MonitorEventZ_ref_from_ptr(long ptr);
	// struct LDKCOption_MonitorEventZ CResult_COption_MonitorEventZDecodeErrorZ_get_ok(LDKCResult_COption_MonitorEventZDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_COption_MonitorEventZDecodeErrorZ_get_ok(long owner);
	// struct LDKDecodeError CResult_COption_MonitorEventZDecodeErrorZ_get_err(LDKCResult_COption_MonitorEventZDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_COption_MonitorEventZDecodeErrorZ_get_err(long owner);
	// struct LDKHTLCUpdate CResult_HTLCUpdateDecodeErrorZ_get_ok(LDKCResult_HTLCUpdateDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_HTLCUpdateDecodeErrorZ_get_ok(long owner);
	// struct LDKDecodeError CResult_HTLCUpdateDecodeErrorZ_get_err(LDKCResult_HTLCUpdateDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_HTLCUpdateDecodeErrorZ_get_err(long owner);
	// struct LDKOutPoint C2Tuple_OutPointScriptZ_get_a(LDKC2Tuple_OutPointScriptZ *NONNULL_PTR owner);
	public static native long C2Tuple_OutPointScriptZ_get_a(long owner);
	// struct LDKCVec_u8Z C2Tuple_OutPointScriptZ_get_b(LDKC2Tuple_OutPointScriptZ *NONNULL_PTR owner);
	public static native byte[] C2Tuple_OutPointScriptZ_get_b(long owner);
	// uint32_t C2Tuple_u32ScriptZ_get_a(LDKC2Tuple_u32ScriptZ *NONNULL_PTR owner);
	public static native int C2Tuple_u32ScriptZ_get_a(long owner);
	// struct LDKCVec_u8Z C2Tuple_u32ScriptZ_get_b(LDKC2Tuple_u32ScriptZ *NONNULL_PTR owner);
	public static native byte[] C2Tuple_u32ScriptZ_get_b(long owner);
	// struct LDKThirtyTwoBytes C2Tuple_TxidCVec_C2Tuple_u32ScriptZZZ_get_a(LDKC2Tuple_TxidCVec_C2Tuple_u32ScriptZZZ *NONNULL_PTR owner);
	public static native byte[] C2Tuple_TxidCVec_C2Tuple_u32ScriptZZZ_get_a(long owner);
	// struct LDKCVec_C2Tuple_u32ScriptZZ C2Tuple_TxidCVec_C2Tuple_u32ScriptZZZ_get_b(LDKC2Tuple_TxidCVec_C2Tuple_u32ScriptZZZ *NONNULL_PTR owner);
	public static native long[] C2Tuple_TxidCVec_C2Tuple_u32ScriptZZZ_get_b(long owner);
	// uint32_t C2Tuple_u32TxOutZ_get_a(LDKC2Tuple_u32TxOutZ *NONNULL_PTR owner);
	public static native int C2Tuple_u32TxOutZ_get_a(long owner);
	// struct LDKTxOut C2Tuple_u32TxOutZ_get_b(LDKC2Tuple_u32TxOutZ *NONNULL_PTR owner);
	public static native long C2Tuple_u32TxOutZ_get_b(long owner);
	// struct LDKThirtyTwoBytes C2Tuple_TxidCVec_C2Tuple_u32TxOutZZZ_get_a(LDKC2Tuple_TxidCVec_C2Tuple_u32TxOutZZZ *NONNULL_PTR owner);
	public static native byte[] C2Tuple_TxidCVec_C2Tuple_u32TxOutZZZ_get_a(long owner);
	// struct LDKCVec_C2Tuple_u32TxOutZZ C2Tuple_TxidCVec_C2Tuple_u32TxOutZZZ_get_b(LDKC2Tuple_TxidCVec_C2Tuple_u32TxOutZZZ *NONNULL_PTR owner);
	public static native long[] C2Tuple_TxidCVec_C2Tuple_u32TxOutZZZ_get_b(long owner);
	public static class LDKBalance {
		private LDKBalance() {}
		public final static class ClaimableOnChannelClose extends LDKBalance {
			public long claimable_amount_satoshis;
			ClaimableOnChannelClose(long claimable_amount_satoshis) { this.claimable_amount_satoshis = claimable_amount_satoshis; }
		}
		public final static class ClaimableAwaitingConfirmations extends LDKBalance {
			public long claimable_amount_satoshis;
			public int confirmation_height;
			ClaimableAwaitingConfirmations(long claimable_amount_satoshis, int confirmation_height) { this.claimable_amount_satoshis = claimable_amount_satoshis; this.confirmation_height = confirmation_height; }
		}
		public final static class ContentiousClaimable extends LDKBalance {
			public long claimable_amount_satoshis;
			public int timeout_height;
			ContentiousClaimable(long claimable_amount_satoshis, int timeout_height) { this.claimable_amount_satoshis = claimable_amount_satoshis; this.timeout_height = timeout_height; }
		}
		public final static class MaybeClaimableHTLCAwaitingTimeout extends LDKBalance {
			public long claimable_amount_satoshis;
			public int claimable_height;
			MaybeClaimableHTLCAwaitingTimeout(long claimable_amount_satoshis, int claimable_height) { this.claimable_amount_satoshis = claimable_amount_satoshis; this.claimable_height = claimable_height; }
		}
		static native void init();
	}
	static { LDKBalance.init(); }
	public static native LDKBalance LDKBalance_ref_from_ptr(long ptr);
	// struct LDKC2Tuple_BlockHashChannelMonitorZ CResult_C2Tuple_BlockHashChannelMonitorZDecodeErrorZ_get_ok(LDKCResult_C2Tuple_BlockHashChannelMonitorZDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_C2Tuple_BlockHashChannelMonitorZDecodeErrorZ_get_ok(long owner);
	// struct LDKDecodeError CResult_C2Tuple_BlockHashChannelMonitorZDecodeErrorZ_get_err(LDKCResult_C2Tuple_BlockHashChannelMonitorZDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_C2Tuple_BlockHashChannelMonitorZDecodeErrorZ_get_err(long owner);
	// void CResult_NoneLightningErrorZ_get_ok(LDKCResult_NoneLightningErrorZ *NONNULL_PTR owner);
	public static native void CResult_NoneLightningErrorZ_get_ok(long owner);
	// struct LDKLightningError CResult_NoneLightningErrorZ_get_err(LDKCResult_NoneLightningErrorZ *NONNULL_PTR owner);
	public static native long CResult_NoneLightningErrorZ_get_err(long owner);
	// struct LDKPublicKey C2Tuple_PublicKeyTypeZ_get_a(LDKC2Tuple_PublicKeyTypeZ *NONNULL_PTR owner);
	public static native byte[] C2Tuple_PublicKeyTypeZ_get_a(long owner);
	// struct LDKType C2Tuple_PublicKeyTypeZ_get_b(LDKC2Tuple_PublicKeyTypeZ *NONNULL_PTR owner);
	public static native long C2Tuple_PublicKeyTypeZ_get_b(long owner);
	// bool CResult_boolLightningErrorZ_get_ok(LDKCResult_boolLightningErrorZ *NONNULL_PTR owner);
	public static native boolean CResult_boolLightningErrorZ_get_ok(long owner);
	// struct LDKLightningError CResult_boolLightningErrorZ_get_err(LDKCResult_boolLightningErrorZ *NONNULL_PTR owner);
	public static native long CResult_boolLightningErrorZ_get_err(long owner);
	// struct LDKChannelAnnouncement C3Tuple_ChannelAnnouncementChannelUpdateChannelUpdateZ_get_a(LDKC3Tuple_ChannelAnnouncementChannelUpdateChannelUpdateZ *NONNULL_PTR owner);
	public static native long C3Tuple_ChannelAnnouncementChannelUpdateChannelUpdateZ_get_a(long owner);
	// struct LDKChannelUpdate C3Tuple_ChannelAnnouncementChannelUpdateChannelUpdateZ_get_b(LDKC3Tuple_ChannelAnnouncementChannelUpdateChannelUpdateZ *NONNULL_PTR owner);
	public static native long C3Tuple_ChannelAnnouncementChannelUpdateChannelUpdateZ_get_b(long owner);
	// struct LDKChannelUpdate C3Tuple_ChannelAnnouncementChannelUpdateChannelUpdateZ_get_c(LDKC3Tuple_ChannelAnnouncementChannelUpdateChannelUpdateZ *NONNULL_PTR owner);
	public static native long C3Tuple_ChannelAnnouncementChannelUpdateChannelUpdateZ_get_c(long owner);
	public static class LDKCOption_NetAddressZ {
		private LDKCOption_NetAddressZ() {}
		public final static class Some extends LDKCOption_NetAddressZ {
			public long some;
			Some(long some) { this.some = some; }
		}
		public final static class None extends LDKCOption_NetAddressZ {
			None() { }
		}
		static native void init();
	}
	static { LDKCOption_NetAddressZ.init(); }
	public static native LDKCOption_NetAddressZ LDKCOption_NetAddressZ_ref_from_ptr(long ptr);
	// struct LDKCVec_u8Z CResult_CVec_u8ZPeerHandleErrorZ_get_ok(LDKCResult_CVec_u8ZPeerHandleErrorZ *NONNULL_PTR owner);
	public static native byte[] CResult_CVec_u8ZPeerHandleErrorZ_get_ok(long owner);
	// struct LDKPeerHandleError CResult_CVec_u8ZPeerHandleErrorZ_get_err(LDKCResult_CVec_u8ZPeerHandleErrorZ *NONNULL_PTR owner);
	public static native long CResult_CVec_u8ZPeerHandleErrorZ_get_err(long owner);
	// void CResult_NonePeerHandleErrorZ_get_ok(LDKCResult_NonePeerHandleErrorZ *NONNULL_PTR owner);
	public static native void CResult_NonePeerHandleErrorZ_get_ok(long owner);
	// struct LDKPeerHandleError CResult_NonePeerHandleErrorZ_get_err(LDKCResult_NonePeerHandleErrorZ *NONNULL_PTR owner);
	public static native long CResult_NonePeerHandleErrorZ_get_err(long owner);
	// bool CResult_boolPeerHandleErrorZ_get_ok(LDKCResult_boolPeerHandleErrorZ *NONNULL_PTR owner);
	public static native boolean CResult_boolPeerHandleErrorZ_get_ok(long owner);
	// struct LDKPeerHandleError CResult_boolPeerHandleErrorZ_get_err(LDKCResult_boolPeerHandleErrorZ *NONNULL_PTR owner);
	public static native long CResult_boolPeerHandleErrorZ_get_err(long owner);
	// struct LDKNodeId CResult_NodeIdDecodeErrorZ_get_ok(LDKCResult_NodeIdDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_NodeIdDecodeErrorZ_get_ok(long owner);
	// struct LDKDecodeError CResult_NodeIdDecodeErrorZ_get_err(LDKCResult_NodeIdDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_NodeIdDecodeErrorZ_get_err(long owner);
	// struct LDKCOption_NetworkUpdateZ CResult_COption_NetworkUpdateZDecodeErrorZ_get_ok(LDKCResult_COption_NetworkUpdateZDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_COption_NetworkUpdateZDecodeErrorZ_get_ok(long owner);
	// struct LDKDecodeError CResult_COption_NetworkUpdateZDecodeErrorZ_get_err(LDKCResult_COption_NetworkUpdateZDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_COption_NetworkUpdateZDecodeErrorZ_get_err(long owner);
	public interface LDKAccess {
		 long get_utxo(byte[] genesis_hash, long short_channel_id);
	}
	public static native long LDKAccess_new(LDKAccess impl);
	// LDKCResult_TxOutAccessErrorZ Access_get_utxo LDKAccess *NONNULL_PTR this_arg, const uint8_t (*genesis_hash)[32], uint64_t short_channel_id
	public static native long Access_get_utxo(long this_arg, byte[] genesis_hash, long short_channel_id);
	public static class LDKCOption_AccessZ {
		private LDKCOption_AccessZ() {}
		public final static class Some extends LDKCOption_AccessZ {
			public long some;
			Some(long some) { this.some = some; }
		}
		public final static class None extends LDKCOption_AccessZ {
			None() { }
		}
		static native void init();
	}
	static { LDKCOption_AccessZ.init(); }
	public static native LDKCOption_AccessZ LDKCOption_AccessZ_ref_from_ptr(long ptr);
	// struct LDKChannelUpdateInfo CResult_ChannelUpdateInfoDecodeErrorZ_get_ok(LDKCResult_ChannelUpdateInfoDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_ChannelUpdateInfoDecodeErrorZ_get_ok(long owner);
	// struct LDKDecodeError CResult_ChannelUpdateInfoDecodeErrorZ_get_err(LDKCResult_ChannelUpdateInfoDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_ChannelUpdateInfoDecodeErrorZ_get_err(long owner);
	// struct LDKChannelInfo CResult_ChannelInfoDecodeErrorZ_get_ok(LDKCResult_ChannelInfoDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_ChannelInfoDecodeErrorZ_get_ok(long owner);
	// struct LDKDecodeError CResult_ChannelInfoDecodeErrorZ_get_err(LDKCResult_ChannelInfoDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_ChannelInfoDecodeErrorZ_get_err(long owner);
	// struct LDKRoutingFees CResult_RoutingFeesDecodeErrorZ_get_ok(LDKCResult_RoutingFeesDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_RoutingFeesDecodeErrorZ_get_ok(long owner);
	// struct LDKDecodeError CResult_RoutingFeesDecodeErrorZ_get_err(LDKCResult_RoutingFeesDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_RoutingFeesDecodeErrorZ_get_err(long owner);
	// struct LDKNodeAnnouncementInfo CResult_NodeAnnouncementInfoDecodeErrorZ_get_ok(LDKCResult_NodeAnnouncementInfoDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_NodeAnnouncementInfoDecodeErrorZ_get_ok(long owner);
	// struct LDKDecodeError CResult_NodeAnnouncementInfoDecodeErrorZ_get_err(LDKCResult_NodeAnnouncementInfoDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_NodeAnnouncementInfoDecodeErrorZ_get_err(long owner);
	// struct LDKNodeInfo CResult_NodeInfoDecodeErrorZ_get_ok(LDKCResult_NodeInfoDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_NodeInfoDecodeErrorZ_get_ok(long owner);
	// struct LDKDecodeError CResult_NodeInfoDecodeErrorZ_get_err(LDKCResult_NodeInfoDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_NodeInfoDecodeErrorZ_get_err(long owner);
	// struct LDKNetworkGraph CResult_NetworkGraphDecodeErrorZ_get_ok(LDKCResult_NetworkGraphDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_NetworkGraphDecodeErrorZ_get_ok(long owner);
	// struct LDKDecodeError CResult_NetworkGraphDecodeErrorZ_get_err(LDKCResult_NetworkGraphDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_NetworkGraphDecodeErrorZ_get_err(long owner);
	public static class LDKCOption_CVec_NetAddressZZ {
		private LDKCOption_CVec_NetAddressZZ() {}
		public final static class Some extends LDKCOption_CVec_NetAddressZZ {
			public long[] some;
			Some(long[] some) { this.some = some; }
		}
		public final static class None extends LDKCOption_CVec_NetAddressZZ {
			None() { }
		}
		static native void init();
	}
	static { LDKCOption_CVec_NetAddressZZ.init(); }
	public static native LDKCOption_CVec_NetAddressZZ LDKCOption_CVec_NetAddressZZ_ref_from_ptr(long ptr);
	// struct LDKNetAddress CResult_NetAddressDecodeErrorZ_get_ok(LDKCResult_NetAddressDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_NetAddressDecodeErrorZ_get_ok(long owner);
	// struct LDKDecodeError CResult_NetAddressDecodeErrorZ_get_err(LDKCResult_NetAddressDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_NetAddressDecodeErrorZ_get_err(long owner);
	// struct LDKAcceptChannel CResult_AcceptChannelDecodeErrorZ_get_ok(LDKCResult_AcceptChannelDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_AcceptChannelDecodeErrorZ_get_ok(long owner);
	// struct LDKDecodeError CResult_AcceptChannelDecodeErrorZ_get_err(LDKCResult_AcceptChannelDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_AcceptChannelDecodeErrorZ_get_err(long owner);
	// struct LDKAnnouncementSignatures CResult_AnnouncementSignaturesDecodeErrorZ_get_ok(LDKCResult_AnnouncementSignaturesDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_AnnouncementSignaturesDecodeErrorZ_get_ok(long owner);
	// struct LDKDecodeError CResult_AnnouncementSignaturesDecodeErrorZ_get_err(LDKCResult_AnnouncementSignaturesDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_AnnouncementSignaturesDecodeErrorZ_get_err(long owner);
	// struct LDKChannelReestablish CResult_ChannelReestablishDecodeErrorZ_get_ok(LDKCResult_ChannelReestablishDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_ChannelReestablishDecodeErrorZ_get_ok(long owner);
	// struct LDKDecodeError CResult_ChannelReestablishDecodeErrorZ_get_err(LDKCResult_ChannelReestablishDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_ChannelReestablishDecodeErrorZ_get_err(long owner);
	// struct LDKClosingSigned CResult_ClosingSignedDecodeErrorZ_get_ok(LDKCResult_ClosingSignedDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_ClosingSignedDecodeErrorZ_get_ok(long owner);
	// struct LDKDecodeError CResult_ClosingSignedDecodeErrorZ_get_err(LDKCResult_ClosingSignedDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_ClosingSignedDecodeErrorZ_get_err(long owner);
	// struct LDKClosingSignedFeeRange CResult_ClosingSignedFeeRangeDecodeErrorZ_get_ok(LDKCResult_ClosingSignedFeeRangeDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_ClosingSignedFeeRangeDecodeErrorZ_get_ok(long owner);
	// struct LDKDecodeError CResult_ClosingSignedFeeRangeDecodeErrorZ_get_err(LDKCResult_ClosingSignedFeeRangeDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_ClosingSignedFeeRangeDecodeErrorZ_get_err(long owner);
	// struct LDKCommitmentSigned CResult_CommitmentSignedDecodeErrorZ_get_ok(LDKCResult_CommitmentSignedDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_CommitmentSignedDecodeErrorZ_get_ok(long owner);
	// struct LDKDecodeError CResult_CommitmentSignedDecodeErrorZ_get_err(LDKCResult_CommitmentSignedDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_CommitmentSignedDecodeErrorZ_get_err(long owner);
	// struct LDKFundingCreated CResult_FundingCreatedDecodeErrorZ_get_ok(LDKCResult_FundingCreatedDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_FundingCreatedDecodeErrorZ_get_ok(long owner);
	// struct LDKDecodeError CResult_FundingCreatedDecodeErrorZ_get_err(LDKCResult_FundingCreatedDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_FundingCreatedDecodeErrorZ_get_err(long owner);
	// struct LDKFundingSigned CResult_FundingSignedDecodeErrorZ_get_ok(LDKCResult_FundingSignedDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_FundingSignedDecodeErrorZ_get_ok(long owner);
	// struct LDKDecodeError CResult_FundingSignedDecodeErrorZ_get_err(LDKCResult_FundingSignedDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_FundingSignedDecodeErrorZ_get_err(long owner);
	// struct LDKFundingLocked CResult_FundingLockedDecodeErrorZ_get_ok(LDKCResult_FundingLockedDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_FundingLockedDecodeErrorZ_get_ok(long owner);
	// struct LDKDecodeError CResult_FundingLockedDecodeErrorZ_get_err(LDKCResult_FundingLockedDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_FundingLockedDecodeErrorZ_get_err(long owner);
	// struct LDKInit CResult_InitDecodeErrorZ_get_ok(LDKCResult_InitDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_InitDecodeErrorZ_get_ok(long owner);
	// struct LDKDecodeError CResult_InitDecodeErrorZ_get_err(LDKCResult_InitDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_InitDecodeErrorZ_get_err(long owner);
	// struct LDKOpenChannel CResult_OpenChannelDecodeErrorZ_get_ok(LDKCResult_OpenChannelDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_OpenChannelDecodeErrorZ_get_ok(long owner);
	// struct LDKDecodeError CResult_OpenChannelDecodeErrorZ_get_err(LDKCResult_OpenChannelDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_OpenChannelDecodeErrorZ_get_err(long owner);
	// struct LDKRevokeAndACK CResult_RevokeAndACKDecodeErrorZ_get_ok(LDKCResult_RevokeAndACKDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_RevokeAndACKDecodeErrorZ_get_ok(long owner);
	// struct LDKDecodeError CResult_RevokeAndACKDecodeErrorZ_get_err(LDKCResult_RevokeAndACKDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_RevokeAndACKDecodeErrorZ_get_err(long owner);
	// struct LDKShutdown CResult_ShutdownDecodeErrorZ_get_ok(LDKCResult_ShutdownDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_ShutdownDecodeErrorZ_get_ok(long owner);
	// struct LDKDecodeError CResult_ShutdownDecodeErrorZ_get_err(LDKCResult_ShutdownDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_ShutdownDecodeErrorZ_get_err(long owner);
	// struct LDKUpdateFailHTLC CResult_UpdateFailHTLCDecodeErrorZ_get_ok(LDKCResult_UpdateFailHTLCDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_UpdateFailHTLCDecodeErrorZ_get_ok(long owner);
	// struct LDKDecodeError CResult_UpdateFailHTLCDecodeErrorZ_get_err(LDKCResult_UpdateFailHTLCDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_UpdateFailHTLCDecodeErrorZ_get_err(long owner);
	// struct LDKUpdateFailMalformedHTLC CResult_UpdateFailMalformedHTLCDecodeErrorZ_get_ok(LDKCResult_UpdateFailMalformedHTLCDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_UpdateFailMalformedHTLCDecodeErrorZ_get_ok(long owner);
	// struct LDKDecodeError CResult_UpdateFailMalformedHTLCDecodeErrorZ_get_err(LDKCResult_UpdateFailMalformedHTLCDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_UpdateFailMalformedHTLCDecodeErrorZ_get_err(long owner);
	// struct LDKUpdateFee CResult_UpdateFeeDecodeErrorZ_get_ok(LDKCResult_UpdateFeeDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_UpdateFeeDecodeErrorZ_get_ok(long owner);
	// struct LDKDecodeError CResult_UpdateFeeDecodeErrorZ_get_err(LDKCResult_UpdateFeeDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_UpdateFeeDecodeErrorZ_get_err(long owner);
	// struct LDKUpdateFulfillHTLC CResult_UpdateFulfillHTLCDecodeErrorZ_get_ok(LDKCResult_UpdateFulfillHTLCDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_UpdateFulfillHTLCDecodeErrorZ_get_ok(long owner);
	// struct LDKDecodeError CResult_UpdateFulfillHTLCDecodeErrorZ_get_err(LDKCResult_UpdateFulfillHTLCDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_UpdateFulfillHTLCDecodeErrorZ_get_err(long owner);
	// struct LDKUpdateAddHTLC CResult_UpdateAddHTLCDecodeErrorZ_get_ok(LDKCResult_UpdateAddHTLCDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_UpdateAddHTLCDecodeErrorZ_get_ok(long owner);
	// struct LDKDecodeError CResult_UpdateAddHTLCDecodeErrorZ_get_err(LDKCResult_UpdateAddHTLCDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_UpdateAddHTLCDecodeErrorZ_get_err(long owner);
	// struct LDKPing CResult_PingDecodeErrorZ_get_ok(LDKCResult_PingDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_PingDecodeErrorZ_get_ok(long owner);
	// struct LDKDecodeError CResult_PingDecodeErrorZ_get_err(LDKCResult_PingDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_PingDecodeErrorZ_get_err(long owner);
	// struct LDKPong CResult_PongDecodeErrorZ_get_ok(LDKCResult_PongDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_PongDecodeErrorZ_get_ok(long owner);
	// struct LDKDecodeError CResult_PongDecodeErrorZ_get_err(LDKCResult_PongDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_PongDecodeErrorZ_get_err(long owner);
	// struct LDKUnsignedChannelAnnouncement CResult_UnsignedChannelAnnouncementDecodeErrorZ_get_ok(LDKCResult_UnsignedChannelAnnouncementDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_UnsignedChannelAnnouncementDecodeErrorZ_get_ok(long owner);
	// struct LDKDecodeError CResult_UnsignedChannelAnnouncementDecodeErrorZ_get_err(LDKCResult_UnsignedChannelAnnouncementDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_UnsignedChannelAnnouncementDecodeErrorZ_get_err(long owner);
	// struct LDKChannelAnnouncement CResult_ChannelAnnouncementDecodeErrorZ_get_ok(LDKCResult_ChannelAnnouncementDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_ChannelAnnouncementDecodeErrorZ_get_ok(long owner);
	// struct LDKDecodeError CResult_ChannelAnnouncementDecodeErrorZ_get_err(LDKCResult_ChannelAnnouncementDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_ChannelAnnouncementDecodeErrorZ_get_err(long owner);
	// struct LDKUnsignedChannelUpdate CResult_UnsignedChannelUpdateDecodeErrorZ_get_ok(LDKCResult_UnsignedChannelUpdateDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_UnsignedChannelUpdateDecodeErrorZ_get_ok(long owner);
	// struct LDKDecodeError CResult_UnsignedChannelUpdateDecodeErrorZ_get_err(LDKCResult_UnsignedChannelUpdateDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_UnsignedChannelUpdateDecodeErrorZ_get_err(long owner);
	// struct LDKChannelUpdate CResult_ChannelUpdateDecodeErrorZ_get_ok(LDKCResult_ChannelUpdateDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_ChannelUpdateDecodeErrorZ_get_ok(long owner);
	// struct LDKDecodeError CResult_ChannelUpdateDecodeErrorZ_get_err(LDKCResult_ChannelUpdateDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_ChannelUpdateDecodeErrorZ_get_err(long owner);
	// struct LDKErrorMessage CResult_ErrorMessageDecodeErrorZ_get_ok(LDKCResult_ErrorMessageDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_ErrorMessageDecodeErrorZ_get_ok(long owner);
	// struct LDKDecodeError CResult_ErrorMessageDecodeErrorZ_get_err(LDKCResult_ErrorMessageDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_ErrorMessageDecodeErrorZ_get_err(long owner);
	// struct LDKWarningMessage CResult_WarningMessageDecodeErrorZ_get_ok(LDKCResult_WarningMessageDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_WarningMessageDecodeErrorZ_get_ok(long owner);
	// struct LDKDecodeError CResult_WarningMessageDecodeErrorZ_get_err(LDKCResult_WarningMessageDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_WarningMessageDecodeErrorZ_get_err(long owner);
	// struct LDKUnsignedNodeAnnouncement CResult_UnsignedNodeAnnouncementDecodeErrorZ_get_ok(LDKCResult_UnsignedNodeAnnouncementDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_UnsignedNodeAnnouncementDecodeErrorZ_get_ok(long owner);
	// struct LDKDecodeError CResult_UnsignedNodeAnnouncementDecodeErrorZ_get_err(LDKCResult_UnsignedNodeAnnouncementDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_UnsignedNodeAnnouncementDecodeErrorZ_get_err(long owner);
	// struct LDKNodeAnnouncement CResult_NodeAnnouncementDecodeErrorZ_get_ok(LDKCResult_NodeAnnouncementDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_NodeAnnouncementDecodeErrorZ_get_ok(long owner);
	// struct LDKDecodeError CResult_NodeAnnouncementDecodeErrorZ_get_err(LDKCResult_NodeAnnouncementDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_NodeAnnouncementDecodeErrorZ_get_err(long owner);
	// struct LDKQueryShortChannelIds CResult_QueryShortChannelIdsDecodeErrorZ_get_ok(LDKCResult_QueryShortChannelIdsDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_QueryShortChannelIdsDecodeErrorZ_get_ok(long owner);
	// struct LDKDecodeError CResult_QueryShortChannelIdsDecodeErrorZ_get_err(LDKCResult_QueryShortChannelIdsDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_QueryShortChannelIdsDecodeErrorZ_get_err(long owner);
	// struct LDKReplyShortChannelIdsEnd CResult_ReplyShortChannelIdsEndDecodeErrorZ_get_ok(LDKCResult_ReplyShortChannelIdsEndDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_ReplyShortChannelIdsEndDecodeErrorZ_get_ok(long owner);
	// struct LDKDecodeError CResult_ReplyShortChannelIdsEndDecodeErrorZ_get_err(LDKCResult_ReplyShortChannelIdsEndDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_ReplyShortChannelIdsEndDecodeErrorZ_get_err(long owner);
	// struct LDKQueryChannelRange CResult_QueryChannelRangeDecodeErrorZ_get_ok(LDKCResult_QueryChannelRangeDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_QueryChannelRangeDecodeErrorZ_get_ok(long owner);
	// struct LDKDecodeError CResult_QueryChannelRangeDecodeErrorZ_get_err(LDKCResult_QueryChannelRangeDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_QueryChannelRangeDecodeErrorZ_get_err(long owner);
	// struct LDKReplyChannelRange CResult_ReplyChannelRangeDecodeErrorZ_get_ok(LDKCResult_ReplyChannelRangeDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_ReplyChannelRangeDecodeErrorZ_get_ok(long owner);
	// struct LDKDecodeError CResult_ReplyChannelRangeDecodeErrorZ_get_err(LDKCResult_ReplyChannelRangeDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_ReplyChannelRangeDecodeErrorZ_get_err(long owner);
	// struct LDKGossipTimestampFilter CResult_GossipTimestampFilterDecodeErrorZ_get_ok(LDKCResult_GossipTimestampFilterDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_GossipTimestampFilterDecodeErrorZ_get_ok(long owner);
	// struct LDKDecodeError CResult_GossipTimestampFilterDecodeErrorZ_get_err(LDKCResult_GossipTimestampFilterDecodeErrorZ *NONNULL_PTR owner);
	public static native long CResult_GossipTimestampFilterDecodeErrorZ_get_err(long owner);
	public static class LDKSignOrCreationError {
		private LDKSignOrCreationError() {}
		public final static class SignError extends LDKSignOrCreationError {
			SignError() { }
		}
		public final static class CreationError extends LDKSignOrCreationError {
			public org.ldk.enums.CreationError creation_error;
			CreationError(org.ldk.enums.CreationError creation_error) { this.creation_error = creation_error; }
		}
		static native void init();
	}
	static { LDKSignOrCreationError.init(); }
	public static native LDKSignOrCreationError LDKSignOrCreationError_ref_from_ptr(long ptr);
	// struct LDKInvoice CResult_InvoiceSignOrCreationErrorZ_get_ok(LDKCResult_InvoiceSignOrCreationErrorZ *NONNULL_PTR owner);
	public static native long CResult_InvoiceSignOrCreationErrorZ_get_ok(long owner);
	// struct LDKSignOrCreationError CResult_InvoiceSignOrCreationErrorZ_get_err(LDKCResult_InvoiceSignOrCreationErrorZ *NONNULL_PTR owner);
	public static native long CResult_InvoiceSignOrCreationErrorZ_get_err(long owner);
	public interface LDKFilter {
		 void register_tx(byte[] txid, byte[] script_pubkey);
		 long register_output(long output);
	}
	public static native long LDKFilter_new(LDKFilter impl);
	// void Filter_register_tx LDKFilter *NONNULL_PTR this_arg, const uint8_t (*txid)[32], struct LDKu8slice script_pubkey
	public static native void Filter_register_tx(long this_arg, byte[] txid, byte[] script_pubkey);
	// LDKCOption_C2Tuple_usizeTransactionZZ Filter_register_output LDKFilter *NONNULL_PTR this_arg, struct LDKWatchedOutput output
	public static native long Filter_register_output(long this_arg, long output);
	public static class LDKCOption_FilterZ {
		private LDKCOption_FilterZ() {}
		public final static class Some extends LDKCOption_FilterZ {
			public long some;
			Some(long some) { this.some = some; }
		}
		public final static class None extends LDKCOption_FilterZ {
			None() { }
		}
		static native void init();
	}
	static { LDKCOption_FilterZ.init(); }
	public static native LDKCOption_FilterZ LDKCOption_FilterZ_ref_from_ptr(long ptr);
	// struct LDKLockedChannelMonitor *CResult_LockedChannelMonitorNoneZ_get_ok(LDKCResult_LockedChannelMonitorNoneZ *NONNULL_PTR owner);
	public static native long CResult_LockedChannelMonitorNoneZ_get_ok(long owner);
	// void CResult_LockedChannelMonitorNoneZ_get_err(LDKCResult_LockedChannelMonitorNoneZ *NONNULL_PTR owner);
	public static native void CResult_LockedChannelMonitorNoneZ_get_err(long owner);
	public interface LDKMessageSendEventsProvider {
		 long[] get_and_clear_pending_msg_events();
	}
	public static native long LDKMessageSendEventsProvider_new(LDKMessageSendEventsProvider impl);
	// LDKCVec_MessageSendEventZ MessageSendEventsProvider_get_and_clear_pending_msg_events LDKMessageSendEventsProvider *NONNULL_PTR this_arg
	public static native long[] MessageSendEventsProvider_get_and_clear_pending_msg_events(long this_arg);
	public interface LDKEventHandler {
		 void handle_event(long event);
	}
	public static native long LDKEventHandler_new(LDKEventHandler impl);
	// void EventHandler_handle_event LDKEventHandler *NONNULL_PTR this_arg, const struct LDKEvent *NONNULL_PTR event
	public static native void EventHandler_handle_event(long this_arg, long event);
	public interface LDKEventsProvider {
		 void process_pending_events(long handler);
	}
	public static native long LDKEventsProvider_new(LDKEventsProvider impl);
	// void EventsProvider_process_pending_events LDKEventsProvider *NONNULL_PTR this_arg, struct LDKEventHandler handler
	public static native void EventsProvider_process_pending_events(long this_arg, long handler);
	public interface LDKListen {
		 void block_connected(byte[] block, int height);
		 void block_disconnected(byte[] header, int height);
	}
	public static native long LDKListen_new(LDKListen impl);
	// void Listen_block_connected LDKListen *NONNULL_PTR this_arg, struct LDKu8slice block, uint32_t height
	public static native void Listen_block_connected(long this_arg, byte[] block, int height);
	// void Listen_block_disconnected LDKListen *NONNULL_PTR this_arg, const uint8_t (*header)[80], uint32_t height
	public static native void Listen_block_disconnected(long this_arg, byte[] header, int height);
	public interface LDKConfirm {
		 void transactions_confirmed(byte[] header, long[] txdata, int height);
		 void transaction_unconfirmed(byte[] txid);
		 void best_block_updated(byte[] header, int height);
		 byte[][] get_relevant_txids();
	}
	public static native long LDKConfirm_new(LDKConfirm impl);
	// void Confirm_transactions_confirmed LDKConfirm *NONNULL_PTR this_arg, const uint8_t (*header)[80], struct LDKCVec_C2Tuple_usizeTransactionZZ txdata, uint32_t height
	public static native void Confirm_transactions_confirmed(long this_arg, byte[] header, long[] txdata, int height);
	// void Confirm_transaction_unconfirmed LDKConfirm *NONNULL_PTR this_arg, const uint8_t (*txid)[32]
	public static native void Confirm_transaction_unconfirmed(long this_arg, byte[] txid);
	// void Confirm_best_block_updated LDKConfirm *NONNULL_PTR this_arg, const uint8_t (*header)[80], uint32_t height
	public static native void Confirm_best_block_updated(long this_arg, byte[] header, int height);
	// LDKCVec_TxidZ Confirm_get_relevant_txids LDKConfirm *NONNULL_PTR this_arg
	public static native byte[][] Confirm_get_relevant_txids(long this_arg);
	public interface LDKPersist {
		 long persist_new_channel(long channel_id, long data, long update_id);
		 long update_persisted_channel(long channel_id, long update, long data, long update_id);
	}
	public static native long LDKPersist_new(LDKPersist impl);
	// LDKCResult_NoneChannelMonitorUpdateErrZ Persist_persist_new_channel LDKPersist *NONNULL_PTR this_arg, struct LDKOutPoint channel_id, const struct LDKChannelMonitor *NONNULL_PTR data, struct LDKMonitorUpdateId update_id
	public static native long Persist_persist_new_channel(long this_arg, long channel_id, long data, long update_id);
	// LDKCResult_NoneChannelMonitorUpdateErrZ Persist_update_persisted_channel LDKPersist *NONNULL_PTR this_arg, struct LDKOutPoint channel_id, const struct LDKChannelMonitorUpdate *NONNULL_PTR update, const struct LDKChannelMonitor *NONNULL_PTR data, struct LDKMonitorUpdateId update_id
	public static native long Persist_update_persisted_channel(long this_arg, long channel_id, long update, long data, long update_id);
	public interface LDKChannelMessageHandler {
		 void handle_open_channel(byte[] their_node_id, long their_features, long msg);
		 void handle_accept_channel(byte[] their_node_id, long their_features, long msg);
		 void handle_funding_created(byte[] their_node_id, long msg);
		 void handle_funding_signed(byte[] their_node_id, long msg);
		 void handle_funding_locked(byte[] their_node_id, long msg);
		 void handle_shutdown(byte[] their_node_id, long their_features, long msg);
		 void handle_closing_signed(byte[] their_node_id, long msg);
		 void handle_update_add_htlc(byte[] their_node_id, long msg);
		 void handle_update_fulfill_htlc(byte[] their_node_id, long msg);
		 void handle_update_fail_htlc(byte[] their_node_id, long msg);
		 void handle_update_fail_malformed_htlc(byte[] their_node_id, long msg);
		 void handle_commitment_signed(byte[] their_node_id, long msg);
		 void handle_revoke_and_ack(byte[] their_node_id, long msg);
		 void handle_update_fee(byte[] their_node_id, long msg);
		 void handle_announcement_signatures(byte[] their_node_id, long msg);
		 void peer_disconnected(byte[] their_node_id, boolean no_connection_possible);
		 void peer_connected(byte[] their_node_id, long msg);
		 void handle_channel_reestablish(byte[] their_node_id, long msg);
		 void handle_channel_update(byte[] their_node_id, long msg);
		 void handle_error(byte[] their_node_id, long msg);
	}
	public static native long LDKChannelMessageHandler_new(LDKChannelMessageHandler impl, LDKMessageSendEventsProvider MessageSendEventsProvider);
	public static native long LDKChannelMessageHandler_get_MessageSendEventsProvider(long arg);
	// void ChannelMessageHandler_handle_open_channel LDKChannelMessageHandler *NONNULL_PTR this_arg, struct LDKPublicKey their_node_id, struct LDKInitFeatures their_features, const struct LDKOpenChannel *NONNULL_PTR msg
	public static native void ChannelMessageHandler_handle_open_channel(long this_arg, byte[] their_node_id, long their_features, long msg);
	// void ChannelMessageHandler_handle_accept_channel LDKChannelMessageHandler *NONNULL_PTR this_arg, struct LDKPublicKey their_node_id, struct LDKInitFeatures their_features, const struct LDKAcceptChannel *NONNULL_PTR msg
	public static native void ChannelMessageHandler_handle_accept_channel(long this_arg, byte[] their_node_id, long their_features, long msg);
	// void ChannelMessageHandler_handle_funding_created LDKChannelMessageHandler *NONNULL_PTR this_arg, struct LDKPublicKey their_node_id, const struct LDKFundingCreated *NONNULL_PTR msg
	public static native void ChannelMessageHandler_handle_funding_created(long this_arg, byte[] their_node_id, long msg);
	// void ChannelMessageHandler_handle_funding_signed LDKChannelMessageHandler *NONNULL_PTR this_arg, struct LDKPublicKey their_node_id, const struct LDKFundingSigned *NONNULL_PTR msg
	public static native void ChannelMessageHandler_handle_funding_signed(long this_arg, byte[] their_node_id, long msg);
	// void ChannelMessageHandler_handle_funding_locked LDKChannelMessageHandler *NONNULL_PTR this_arg, struct LDKPublicKey their_node_id, const struct LDKFundingLocked *NONNULL_PTR msg
	public static native void ChannelMessageHandler_handle_funding_locked(long this_arg, byte[] their_node_id, long msg);
	// void ChannelMessageHandler_handle_shutdown LDKChannelMessageHandler *NONNULL_PTR this_arg, struct LDKPublicKey their_node_id, const struct LDKInitFeatures *NONNULL_PTR their_features, const struct LDKShutdown *NONNULL_PTR msg
	public static native void ChannelMessageHandler_handle_shutdown(long this_arg, byte[] their_node_id, long their_features, long msg);
	// void ChannelMessageHandler_handle_closing_signed LDKChannelMessageHandler *NONNULL_PTR this_arg, struct LDKPublicKey their_node_id, const struct LDKClosingSigned *NONNULL_PTR msg
	public static native void ChannelMessageHandler_handle_closing_signed(long this_arg, byte[] their_node_id, long msg);
	// void ChannelMessageHandler_handle_update_add_htlc LDKChannelMessageHandler *NONNULL_PTR this_arg, struct LDKPublicKey their_node_id, const struct LDKUpdateAddHTLC *NONNULL_PTR msg
	public static native void ChannelMessageHandler_handle_update_add_htlc(long this_arg, byte[] their_node_id, long msg);
	// void ChannelMessageHandler_handle_update_fulfill_htlc LDKChannelMessageHandler *NONNULL_PTR this_arg, struct LDKPublicKey their_node_id, const struct LDKUpdateFulfillHTLC *NONNULL_PTR msg
	public static native void ChannelMessageHandler_handle_update_fulfill_htlc(long this_arg, byte[] their_node_id, long msg);
	// void ChannelMessageHandler_handle_update_fail_htlc LDKChannelMessageHandler *NONNULL_PTR this_arg, struct LDKPublicKey their_node_id, const struct LDKUpdateFailHTLC *NONNULL_PTR msg
	public static native void ChannelMessageHandler_handle_update_fail_htlc(long this_arg, byte[] their_node_id, long msg);
	// void ChannelMessageHandler_handle_update_fail_malformed_htlc LDKChannelMessageHandler *NONNULL_PTR this_arg, struct LDKPublicKey their_node_id, const struct LDKUpdateFailMalformedHTLC *NONNULL_PTR msg
	public static native void ChannelMessageHandler_handle_update_fail_malformed_htlc(long this_arg, byte[] their_node_id, long msg);
	// void ChannelMessageHandler_handle_commitment_signed LDKChannelMessageHandler *NONNULL_PTR this_arg, struct LDKPublicKey their_node_id, const struct LDKCommitmentSigned *NONNULL_PTR msg
	public static native void ChannelMessageHandler_handle_commitment_signed(long this_arg, byte[] their_node_id, long msg);
	// void ChannelMessageHandler_handle_revoke_and_ack LDKChannelMessageHandler *NONNULL_PTR this_arg, struct LDKPublicKey their_node_id, const struct LDKRevokeAndACK *NONNULL_PTR msg
	public static native void ChannelMessageHandler_handle_revoke_and_ack(long this_arg, byte[] their_node_id, long msg);
	// void ChannelMessageHandler_handle_update_fee LDKChannelMessageHandler *NONNULL_PTR this_arg, struct LDKPublicKey their_node_id, const struct LDKUpdateFee *NONNULL_PTR msg
	public static native void ChannelMessageHandler_handle_update_fee(long this_arg, byte[] their_node_id, long msg);
	// void ChannelMessageHandler_handle_announcement_signatures LDKChannelMessageHandler *NONNULL_PTR this_arg, struct LDKPublicKey their_node_id, const struct LDKAnnouncementSignatures *NONNULL_PTR msg
	public static native void ChannelMessageHandler_handle_announcement_signatures(long this_arg, byte[] their_node_id, long msg);
	// void ChannelMessageHandler_peer_disconnected LDKChannelMessageHandler *NONNULL_PTR this_arg, struct LDKPublicKey their_node_id, bool no_connection_possible
	public static native void ChannelMessageHandler_peer_disconnected(long this_arg, byte[] their_node_id, boolean no_connection_possible);
	// void ChannelMessageHandler_peer_connected LDKChannelMessageHandler *NONNULL_PTR this_arg, struct LDKPublicKey their_node_id, const struct LDKInit *NONNULL_PTR msg
	public static native void ChannelMessageHandler_peer_connected(long this_arg, byte[] their_node_id, long msg);
	// void ChannelMessageHandler_handle_channel_reestablish LDKChannelMessageHandler *NONNULL_PTR this_arg, struct LDKPublicKey their_node_id, const struct LDKChannelReestablish *NONNULL_PTR msg
	public static native void ChannelMessageHandler_handle_channel_reestablish(long this_arg, byte[] their_node_id, long msg);
	// void ChannelMessageHandler_handle_channel_update LDKChannelMessageHandler *NONNULL_PTR this_arg, struct LDKPublicKey their_node_id, const struct LDKChannelUpdate *NONNULL_PTR msg
	public static native void ChannelMessageHandler_handle_channel_update(long this_arg, byte[] their_node_id, long msg);
	// void ChannelMessageHandler_handle_error LDKChannelMessageHandler *NONNULL_PTR this_arg, struct LDKPublicKey their_node_id, const struct LDKErrorMessage *NONNULL_PTR msg
	public static native void ChannelMessageHandler_handle_error(long this_arg, byte[] their_node_id, long msg);
	public interface LDKRoutingMessageHandler {
		 long handle_node_announcement(long msg);
		 long handle_channel_announcement(long msg);
		 long handle_channel_update(long msg);
		 long[] get_next_channel_announcements(long starting_point, byte batch_amount);
		 long[] get_next_node_announcements(byte[] starting_point, byte batch_amount);
		 void peer_connected(byte[] their_node_id, long init);
		 long handle_reply_channel_range(byte[] their_node_id, long msg);
		 long handle_reply_short_channel_ids_end(byte[] their_node_id, long msg);
		 long handle_query_channel_range(byte[] their_node_id, long msg);
		 long handle_query_short_channel_ids(byte[] their_node_id, long msg);
	}
	public static native long LDKRoutingMessageHandler_new(LDKRoutingMessageHandler impl, LDKMessageSendEventsProvider MessageSendEventsProvider);
	public static native long LDKRoutingMessageHandler_get_MessageSendEventsProvider(long arg);
	// LDKCResult_boolLightningErrorZ RoutingMessageHandler_handle_node_announcement LDKRoutingMessageHandler *NONNULL_PTR this_arg, const struct LDKNodeAnnouncement *NONNULL_PTR msg
	public static native long RoutingMessageHandler_handle_node_announcement(long this_arg, long msg);
	// LDKCResult_boolLightningErrorZ RoutingMessageHandler_handle_channel_announcement LDKRoutingMessageHandler *NONNULL_PTR this_arg, const struct LDKChannelAnnouncement *NONNULL_PTR msg
	public static native long RoutingMessageHandler_handle_channel_announcement(long this_arg, long msg);
	// LDKCResult_boolLightningErrorZ RoutingMessageHandler_handle_channel_update LDKRoutingMessageHandler *NONNULL_PTR this_arg, const struct LDKChannelUpdate *NONNULL_PTR msg
	public static native long RoutingMessageHandler_handle_channel_update(long this_arg, long msg);
	// LDKCVec_C3Tuple_ChannelAnnouncementChannelUpdateChannelUpdateZZ RoutingMessageHandler_get_next_channel_announcements LDKRoutingMessageHandler *NONNULL_PTR this_arg, uint64_t starting_point, uint8_t batch_amount
	public static native long[] RoutingMessageHandler_get_next_channel_announcements(long this_arg, long starting_point, byte batch_amount);
	// LDKCVec_NodeAnnouncementZ RoutingMessageHandler_get_next_node_announcements LDKRoutingMessageHandler *NONNULL_PTR this_arg, struct LDKPublicKey starting_point, uint8_t batch_amount
	public static native long[] RoutingMessageHandler_get_next_node_announcements(long this_arg, byte[] starting_point, byte batch_amount);
	// void RoutingMessageHandler_peer_connected LDKRoutingMessageHandler *NONNULL_PTR this_arg, struct LDKPublicKey their_node_id, const struct LDKInit *NONNULL_PTR init
	public static native void RoutingMessageHandler_peer_connected(long this_arg, byte[] their_node_id, long init);
	// LDKCResult_NoneLightningErrorZ RoutingMessageHandler_handle_reply_channel_range LDKRoutingMessageHandler *NONNULL_PTR this_arg, struct LDKPublicKey their_node_id, struct LDKReplyChannelRange msg
	public static native long RoutingMessageHandler_handle_reply_channel_range(long this_arg, byte[] their_node_id, long msg);
	// LDKCResult_NoneLightningErrorZ RoutingMessageHandler_handle_reply_short_channel_ids_end LDKRoutingMessageHandler *NONNULL_PTR this_arg, struct LDKPublicKey their_node_id, struct LDKReplyShortChannelIdsEnd msg
	public static native long RoutingMessageHandler_handle_reply_short_channel_ids_end(long this_arg, byte[] their_node_id, long msg);
	// LDKCResult_NoneLightningErrorZ RoutingMessageHandler_handle_query_channel_range LDKRoutingMessageHandler *NONNULL_PTR this_arg, struct LDKPublicKey their_node_id, struct LDKQueryChannelRange msg
	public static native long RoutingMessageHandler_handle_query_channel_range(long this_arg, byte[] their_node_id, long msg);
	// LDKCResult_NoneLightningErrorZ RoutingMessageHandler_handle_query_short_channel_ids LDKRoutingMessageHandler *NONNULL_PTR this_arg, struct LDKPublicKey their_node_id, struct LDKQueryShortChannelIds msg
	public static native long RoutingMessageHandler_handle_query_short_channel_ids(long this_arg, byte[] their_node_id, long msg);
	public interface LDKCustomMessageReader {
		 long read(short message_type, byte[] buffer);
	}
	public static native long LDKCustomMessageReader_new(LDKCustomMessageReader impl);
	// LDKCResult_COption_TypeZDecodeErrorZ CustomMessageReader_read LDKCustomMessageReader *NONNULL_PTR this_arg, uint16_t message_type, struct LDKu8slice buffer
	public static native long CustomMessageReader_read(long this_arg, short message_type, byte[] buffer);
	public interface LDKCustomMessageHandler {
		 long handle_custom_message(long msg, byte[] sender_node_id);
		 long[] get_and_clear_pending_msg();
	}
	public static native long LDKCustomMessageHandler_new(LDKCustomMessageHandler impl, LDKCustomMessageReader CustomMessageReader);
	public static native long LDKCustomMessageHandler_get_CustomMessageReader(long arg);
	// LDKCResult_NoneLightningErrorZ CustomMessageHandler_handle_custom_message LDKCustomMessageHandler *NONNULL_PTR this_arg, struct LDKType msg, struct LDKPublicKey sender_node_id
	public static native long CustomMessageHandler_handle_custom_message(long this_arg, long msg, byte[] sender_node_id);
	// LDKCVec_C2Tuple_PublicKeyTypeZZ CustomMessageHandler_get_and_clear_pending_msg LDKCustomMessageHandler *NONNULL_PTR this_arg
	public static native long[] CustomMessageHandler_get_and_clear_pending_msg(long this_arg);
	public interface LDKSocketDescriptor {
		 long send_data(byte[] data, boolean resume_read);
		 void disconnect_socket();
		 boolean eq(long other_arg);
		 long hash();
	}
	public static native long LDKSocketDescriptor_new(LDKSocketDescriptor impl);
	// uintptr_t SocketDescriptor_send_data LDKSocketDescriptor *NONNULL_PTR this_arg, struct LDKu8slice data, bool resume_read
	public static native long SocketDescriptor_send_data(long this_arg, byte[] data, boolean resume_read);
	// void SocketDescriptor_disconnect_socket LDKSocketDescriptor *NONNULL_PTR this_arg
	public static native void SocketDescriptor_disconnect_socket(long this_arg);
	// uint64_t SocketDescriptor_hash LDKSocketDescriptor *NONNULL_PTR this_arg
	public static native long SocketDescriptor_hash(long this_arg);
	public static class LDKEffectiveCapacity {
		private LDKEffectiveCapacity() {}
		public final static class ExactLiquidity extends LDKEffectiveCapacity {
			public long liquidity_msat;
			ExactLiquidity(long liquidity_msat) { this.liquidity_msat = liquidity_msat; }
		}
		public final static class MaximumHTLC extends LDKEffectiveCapacity {
			public long amount_msat;
			MaximumHTLC(long amount_msat) { this.amount_msat = amount_msat; }
		}
		public final static class Total extends LDKEffectiveCapacity {
			public long capacity_msat;
			Total(long capacity_msat) { this.capacity_msat = capacity_msat; }
		}
		public final static class Infinite extends LDKEffectiveCapacity {
			Infinite() { }
		}
		public final static class Unknown extends LDKEffectiveCapacity {
			Unknown() { }
		}
		static native void init();
	}
	static { LDKEffectiveCapacity.init(); }
	public static native LDKEffectiveCapacity LDKEffectiveCapacity_ref_from_ptr(long ptr);
	public interface LDKScore {
		 long channel_penalty_msat(long short_channel_id, long send_amt_msat, long capacity_msat, long source, long target);
		 void payment_path_failed(long[] path, long short_channel_id);
		 void payment_path_successful(long[] path);
		 byte[] write();
	}
	public static native long LDKScore_new(LDKScore impl);
	// uint64_t Score_channel_penalty_msat LDKScore *NONNULL_PTR this_arg, uint64_t short_channel_id, uint64_t send_amt_msat, uint64_t capacity_msat, const struct LDKNodeId *NONNULL_PTR source, const struct LDKNodeId *NONNULL_PTR target
	public static native long Score_channel_penalty_msat(long this_arg, long short_channel_id, long send_amt_msat, long capacity_msat, long source, long target);
	// void Score_payment_path_failed LDKScore *NONNULL_PTR this_arg, struct LDKCVec_RouteHopZ path, uint64_t short_channel_id
	public static native void Score_payment_path_failed(long this_arg, long[] path, long short_channel_id);
	// void Score_payment_path_successful LDKScore *NONNULL_PTR this_arg, struct LDKCVec_RouteHopZ path
	public static native void Score_payment_path_successful(long this_arg, long[] path);
	// LDKCVec_u8Z Score_write LDKScore *NONNULL_PTR this_arg
	public static native byte[] Score_write(long this_arg);
	public interface LDKLockableScore {
		 long lock();
	}
	public static native long LDKLockableScore_new(LDKLockableScore impl);
	// LDKScore LockableScore_lock LDKLockableScore *NONNULL_PTR this_arg
	public static native long LockableScore_lock(long this_arg);
	public interface LDKPersister {
		 long persist_manager(long channel_manager);
		 long persist_graph(long network_graph);
	}
	public static native long LDKPersister_new(LDKPersister impl);
	// LDKCResult_NoneErrorZ Persister_persist_manager LDKPersister *NONNULL_PTR this_arg, const struct LDKChannelManager *NONNULL_PTR channel_manager
	public static native long Persister_persist_manager(long this_arg, long channel_manager);
	// LDKCResult_NoneErrorZ Persister_persist_graph LDKPersister *NONNULL_PTR this_arg, const struct LDKNetworkGraph *NONNULL_PTR network_graph
	public static native long Persister_persist_graph(long this_arg, long network_graph);
	public static class LDKFallback {
		private LDKFallback() {}
		public final static class SegWitProgram extends LDKFallback {
			public byte version;
			public byte[] program;
			SegWitProgram(byte version, byte[] program) { this.version = version; this.program = program; }
		}
		public final static class PubKeyHash extends LDKFallback {
			public byte[] pub_key_hash;
			PubKeyHash(byte[] pub_key_hash) { this.pub_key_hash = pub_key_hash; }
		}
		public final static class ScriptHash extends LDKFallback {
			public byte[] script_hash;
			ScriptHash(byte[] script_hash) { this.script_hash = script_hash; }
		}
		static native void init();
	}
	static { LDKFallback.init(); }
	public static native LDKFallback LDKFallback_ref_from_ptr(long ptr);
	public interface LDKPayer {
		 byte[] node_id();
		 long[] first_hops();
		 long send_payment(long route, byte[] payment_hash, byte[] payment_secret);
		 long send_spontaneous_payment(long route, byte[] payment_preimage);
		 long retry_payment(long route, byte[] payment_id);
		 void abandon_payment(byte[] payment_id);
	}
	public static native long LDKPayer_new(LDKPayer impl);
	// LDKPublicKey Payer_node_id LDKPayer *NONNULL_PTR this_arg
	public static native byte[] Payer_node_id(long this_arg);
	// LDKCVec_ChannelDetailsZ Payer_first_hops LDKPayer *NONNULL_PTR this_arg
	public static native long[] Payer_first_hops(long this_arg);
	// LDKCResult_PaymentIdPaymentSendFailureZ Payer_send_payment LDKPayer *NONNULL_PTR this_arg, const struct LDKRoute *NONNULL_PTR route, struct LDKThirtyTwoBytes payment_hash, struct LDKThirtyTwoBytes payment_secret
	public static native long Payer_send_payment(long this_arg, long route, byte[] payment_hash, byte[] payment_secret);
	// LDKCResult_PaymentIdPaymentSendFailureZ Payer_send_spontaneous_payment LDKPayer *NONNULL_PTR this_arg, const struct LDKRoute *NONNULL_PTR route, struct LDKThirtyTwoBytes payment_preimage
	public static native long Payer_send_spontaneous_payment(long this_arg, long route, byte[] payment_preimage);
	// LDKCResult_NonePaymentSendFailureZ Payer_retry_payment LDKPayer *NONNULL_PTR this_arg, const struct LDKRoute *NONNULL_PTR route, struct LDKThirtyTwoBytes payment_id
	public static native long Payer_retry_payment(long this_arg, long route, byte[] payment_id);
	// void Payer_abandon_payment LDKPayer *NONNULL_PTR this_arg, struct LDKThirtyTwoBytes payment_id
	public static native void Payer_abandon_payment(long this_arg, byte[] payment_id);
	public interface LDKRouter {
		 long find_route(byte[] payer, long route_params, byte[] payment_hash, long[] first_hops, long scorer);
	}
	public static native long LDKRouter_new(LDKRouter impl);
	// LDKCResult_RouteLightningErrorZ Router_find_route LDKRouter *NONNULL_PTR this_arg, struct LDKPublicKey payer, const struct LDKRouteParameters *NONNULL_PTR route_params, const uint8_t (*payment_hash)[32], struct LDKCVec_ChannelDetailsZ *first_hops, const struct LDKScore *NONNULL_PTR scorer
	public static native long Router_find_route(long this_arg, byte[] payer, long route_params, byte[] payment_hash, long[] first_hops, long scorer);
	// struct LDKStr _ldk_get_compiled_version(void);
	public static native String _ldk_get_compiled_version();
	// struct LDKStr _ldk_c_bindings_get_compiled_version(void);
	public static native String _ldk_c_bindings_get_compiled_version();
	// uintptr_t Bech32Error_clone_ptr(LDKBech32Error *NONNULL_PTR arg);
	public static native long Bech32Error_clone_ptr(long arg);
	// struct LDKBech32Error Bech32Error_clone(const struct LDKBech32Error *NONNULL_PTR orig);
	public static native long Bech32Error_clone(long orig);
	// void Bech32Error_free(struct LDKBech32Error o);
	public static native void Bech32Error_free(long o);
	// void Transaction_free(struct LDKTransaction _res);
	public static native void Transaction_free(byte[] _res);
	// struct LDKTxOut TxOut_new(struct LDKCVec_u8Z script_pubkey, uint64_t value);
	public static native long TxOut_new(byte[] script_pubkey, long value);
	// void TxOut_free(struct LDKTxOut _res);
	public static native void TxOut_free(long _res);
	// uintptr_t TxOut_clone_ptr(LDKTxOut *NONNULL_PTR arg);
	public static native long TxOut_clone_ptr(long arg);
	// struct LDKTxOut TxOut_clone(const struct LDKTxOut *NONNULL_PTR orig);
	public static native long TxOut_clone(long orig);
	// void Str_free(struct LDKStr _res);
	public static native void Str_free(String _res);
	// struct LDKCResult_NoneNoneZ CResult_NoneNoneZ_ok(void);
	public static native long CResult_NoneNoneZ_ok();
	// struct LDKCResult_NoneNoneZ CResult_NoneNoneZ_err(void);
	public static native long CResult_NoneNoneZ_err();
	// bool CResult_NoneNoneZ_is_ok(const struct LDKCResult_NoneNoneZ *NONNULL_PTR o);
	public static native boolean CResult_NoneNoneZ_is_ok(long o);
	// void CResult_NoneNoneZ_free(struct LDKCResult_NoneNoneZ _res);
	public static native void CResult_NoneNoneZ_free(long _res);
	// uintptr_t CResult_NoneNoneZ_clone_ptr(LDKCResult_NoneNoneZ *NONNULL_PTR arg);
	public static native long CResult_NoneNoneZ_clone_ptr(long arg);
	// struct LDKCResult_NoneNoneZ CResult_NoneNoneZ_clone(const struct LDKCResult_NoneNoneZ *NONNULL_PTR orig);
	public static native long CResult_NoneNoneZ_clone(long orig);
	// struct LDKCResult_CounterpartyCommitmentSecretsDecodeErrorZ CResult_CounterpartyCommitmentSecretsDecodeErrorZ_ok(struct LDKCounterpartyCommitmentSecrets o);
	public static native long CResult_CounterpartyCommitmentSecretsDecodeErrorZ_ok(long o);
	// struct LDKCResult_CounterpartyCommitmentSecretsDecodeErrorZ CResult_CounterpartyCommitmentSecretsDecodeErrorZ_err(struct LDKDecodeError e);
	public static native long CResult_CounterpartyCommitmentSecretsDecodeErrorZ_err(long e);
	// bool CResult_CounterpartyCommitmentSecretsDecodeErrorZ_is_ok(const struct LDKCResult_CounterpartyCommitmentSecretsDecodeErrorZ *NONNULL_PTR o);
	public static native boolean CResult_CounterpartyCommitmentSecretsDecodeErrorZ_is_ok(long o);
	// void CResult_CounterpartyCommitmentSecretsDecodeErrorZ_free(struct LDKCResult_CounterpartyCommitmentSecretsDecodeErrorZ _res);
	public static native void CResult_CounterpartyCommitmentSecretsDecodeErrorZ_free(long _res);
	// uintptr_t CResult_CounterpartyCommitmentSecretsDecodeErrorZ_clone_ptr(LDKCResult_CounterpartyCommitmentSecretsDecodeErrorZ *NONNULL_PTR arg);
	public static native long CResult_CounterpartyCommitmentSecretsDecodeErrorZ_clone_ptr(long arg);
	// struct LDKCResult_CounterpartyCommitmentSecretsDecodeErrorZ CResult_CounterpartyCommitmentSecretsDecodeErrorZ_clone(const struct LDKCResult_CounterpartyCommitmentSecretsDecodeErrorZ *NONNULL_PTR orig);
	public static native long CResult_CounterpartyCommitmentSecretsDecodeErrorZ_clone(long orig);
	// struct LDKCResult_SecretKeyErrorZ CResult_SecretKeyErrorZ_ok(struct LDKSecretKey o);
	public static native long CResult_SecretKeyErrorZ_ok(byte[] o);
	// struct LDKCResult_SecretKeyErrorZ CResult_SecretKeyErrorZ_err(enum LDKSecp256k1Error e);
	public static native long CResult_SecretKeyErrorZ_err(Secp256k1Error e);
	// bool CResult_SecretKeyErrorZ_is_ok(const struct LDKCResult_SecretKeyErrorZ *NONNULL_PTR o);
	public static native boolean CResult_SecretKeyErrorZ_is_ok(long o);
	// void CResult_SecretKeyErrorZ_free(struct LDKCResult_SecretKeyErrorZ _res);
	public static native void CResult_SecretKeyErrorZ_free(long _res);
	// uintptr_t CResult_SecretKeyErrorZ_clone_ptr(LDKCResult_SecretKeyErrorZ *NONNULL_PTR arg);
	public static native long CResult_SecretKeyErrorZ_clone_ptr(long arg);
	// struct LDKCResult_SecretKeyErrorZ CResult_SecretKeyErrorZ_clone(const struct LDKCResult_SecretKeyErrorZ *NONNULL_PTR orig);
	public static native long CResult_SecretKeyErrorZ_clone(long orig);
	// struct LDKCResult_PublicKeyErrorZ CResult_PublicKeyErrorZ_ok(struct LDKPublicKey o);
	public static native long CResult_PublicKeyErrorZ_ok(byte[] o);
	// struct LDKCResult_PublicKeyErrorZ CResult_PublicKeyErrorZ_err(enum LDKSecp256k1Error e);
	public static native long CResult_PublicKeyErrorZ_err(Secp256k1Error e);
	// bool CResult_PublicKeyErrorZ_is_ok(const struct LDKCResult_PublicKeyErrorZ *NONNULL_PTR o);
	public static native boolean CResult_PublicKeyErrorZ_is_ok(long o);
	// void CResult_PublicKeyErrorZ_free(struct LDKCResult_PublicKeyErrorZ _res);
	public static native void CResult_PublicKeyErrorZ_free(long _res);
	// uintptr_t CResult_PublicKeyErrorZ_clone_ptr(LDKCResult_PublicKeyErrorZ *NONNULL_PTR arg);
	public static native long CResult_PublicKeyErrorZ_clone_ptr(long arg);
	// struct LDKCResult_PublicKeyErrorZ CResult_PublicKeyErrorZ_clone(const struct LDKCResult_PublicKeyErrorZ *NONNULL_PTR orig);
	public static native long CResult_PublicKeyErrorZ_clone(long orig);
	// struct LDKCResult_TxCreationKeysDecodeErrorZ CResult_TxCreationKeysDecodeErrorZ_ok(struct LDKTxCreationKeys o);
	public static native long CResult_TxCreationKeysDecodeErrorZ_ok(long o);
	// struct LDKCResult_TxCreationKeysDecodeErrorZ CResult_TxCreationKeysDecodeErrorZ_err(struct LDKDecodeError e);
	public static native long CResult_TxCreationKeysDecodeErrorZ_err(long e);
	// bool CResult_TxCreationKeysDecodeErrorZ_is_ok(const struct LDKCResult_TxCreationKeysDecodeErrorZ *NONNULL_PTR o);
	public static native boolean CResult_TxCreationKeysDecodeErrorZ_is_ok(long o);
	// void CResult_TxCreationKeysDecodeErrorZ_free(struct LDKCResult_TxCreationKeysDecodeErrorZ _res);
	public static native void CResult_TxCreationKeysDecodeErrorZ_free(long _res);
	// uintptr_t CResult_TxCreationKeysDecodeErrorZ_clone_ptr(LDKCResult_TxCreationKeysDecodeErrorZ *NONNULL_PTR arg);
	public static native long CResult_TxCreationKeysDecodeErrorZ_clone_ptr(long arg);
	// struct LDKCResult_TxCreationKeysDecodeErrorZ CResult_TxCreationKeysDecodeErrorZ_clone(const struct LDKCResult_TxCreationKeysDecodeErrorZ *NONNULL_PTR orig);
	public static native long CResult_TxCreationKeysDecodeErrorZ_clone(long orig);
	// struct LDKCResult_ChannelPublicKeysDecodeErrorZ CResult_ChannelPublicKeysDecodeErrorZ_ok(struct LDKChannelPublicKeys o);
	public static native long CResult_ChannelPublicKeysDecodeErrorZ_ok(long o);
	// struct LDKCResult_ChannelPublicKeysDecodeErrorZ CResult_ChannelPublicKeysDecodeErrorZ_err(struct LDKDecodeError e);
	public static native long CResult_ChannelPublicKeysDecodeErrorZ_err(long e);
	// bool CResult_ChannelPublicKeysDecodeErrorZ_is_ok(const struct LDKCResult_ChannelPublicKeysDecodeErrorZ *NONNULL_PTR o);
	public static native boolean CResult_ChannelPublicKeysDecodeErrorZ_is_ok(long o);
	// void CResult_ChannelPublicKeysDecodeErrorZ_free(struct LDKCResult_ChannelPublicKeysDecodeErrorZ _res);
	public static native void CResult_ChannelPublicKeysDecodeErrorZ_free(long _res);
	// uintptr_t CResult_ChannelPublicKeysDecodeErrorZ_clone_ptr(LDKCResult_ChannelPublicKeysDecodeErrorZ *NONNULL_PTR arg);
	public static native long CResult_ChannelPublicKeysDecodeErrorZ_clone_ptr(long arg);
	// struct LDKCResult_ChannelPublicKeysDecodeErrorZ CResult_ChannelPublicKeysDecodeErrorZ_clone(const struct LDKCResult_ChannelPublicKeysDecodeErrorZ *NONNULL_PTR orig);
	public static native long CResult_ChannelPublicKeysDecodeErrorZ_clone(long orig);
	// struct LDKCResult_TxCreationKeysErrorZ CResult_TxCreationKeysErrorZ_ok(struct LDKTxCreationKeys o);
	public static native long CResult_TxCreationKeysErrorZ_ok(long o);
	// struct LDKCResult_TxCreationKeysErrorZ CResult_TxCreationKeysErrorZ_err(enum LDKSecp256k1Error e);
	public static native long CResult_TxCreationKeysErrorZ_err(Secp256k1Error e);
	// bool CResult_TxCreationKeysErrorZ_is_ok(const struct LDKCResult_TxCreationKeysErrorZ *NONNULL_PTR o);
	public static native boolean CResult_TxCreationKeysErrorZ_is_ok(long o);
	// void CResult_TxCreationKeysErrorZ_free(struct LDKCResult_TxCreationKeysErrorZ _res);
	public static native void CResult_TxCreationKeysErrorZ_free(long _res);
	// uintptr_t CResult_TxCreationKeysErrorZ_clone_ptr(LDKCResult_TxCreationKeysErrorZ *NONNULL_PTR arg);
	public static native long CResult_TxCreationKeysErrorZ_clone_ptr(long arg);
	// struct LDKCResult_TxCreationKeysErrorZ CResult_TxCreationKeysErrorZ_clone(const struct LDKCResult_TxCreationKeysErrorZ *NONNULL_PTR orig);
	public static native long CResult_TxCreationKeysErrorZ_clone(long orig);
	// struct LDKCOption_u32Z COption_u32Z_some(uint32_t o);
	public static native long COption_u32Z_some(int o);
	// struct LDKCOption_u32Z COption_u32Z_none(void);
	public static native long COption_u32Z_none();
	// void COption_u32Z_free(struct LDKCOption_u32Z _res);
	public static native void COption_u32Z_free(long _res);
	// uintptr_t COption_u32Z_clone_ptr(LDKCOption_u32Z *NONNULL_PTR arg);
	public static native long COption_u32Z_clone_ptr(long arg);
	// struct LDKCOption_u32Z COption_u32Z_clone(const struct LDKCOption_u32Z *NONNULL_PTR orig);
	public static native long COption_u32Z_clone(long orig);
	// struct LDKCResult_HTLCOutputInCommitmentDecodeErrorZ CResult_HTLCOutputInCommitmentDecodeErrorZ_ok(struct LDKHTLCOutputInCommitment o);
	public static native long CResult_HTLCOutputInCommitmentDecodeErrorZ_ok(long o);
	// struct LDKCResult_HTLCOutputInCommitmentDecodeErrorZ CResult_HTLCOutputInCommitmentDecodeErrorZ_err(struct LDKDecodeError e);
	public static native long CResult_HTLCOutputInCommitmentDecodeErrorZ_err(long e);
	// bool CResult_HTLCOutputInCommitmentDecodeErrorZ_is_ok(const struct LDKCResult_HTLCOutputInCommitmentDecodeErrorZ *NONNULL_PTR o);
	public static native boolean CResult_HTLCOutputInCommitmentDecodeErrorZ_is_ok(long o);
	// void CResult_HTLCOutputInCommitmentDecodeErrorZ_free(struct LDKCResult_HTLCOutputInCommitmentDecodeErrorZ _res);
	public static native void CResult_HTLCOutputInCommitmentDecodeErrorZ_free(long _res);
	// uintptr_t CResult_HTLCOutputInCommitmentDecodeErrorZ_clone_ptr(LDKCResult_HTLCOutputInCommitmentDecodeErrorZ *NONNULL_PTR arg);
	public static native long CResult_HTLCOutputInCommitmentDecodeErrorZ_clone_ptr(long arg);
	// struct LDKCResult_HTLCOutputInCommitmentDecodeErrorZ CResult_HTLCOutputInCommitmentDecodeErrorZ_clone(const struct LDKCResult_HTLCOutputInCommitmentDecodeErrorZ *NONNULL_PTR orig);
	public static native long CResult_HTLCOutputInCommitmentDecodeErrorZ_clone(long orig);
	// enum LDKCOption_NoneZ COption_NoneZ_some(void);
	public static native COption_NoneZ COption_NoneZ_some();
	// enum LDKCOption_NoneZ COption_NoneZ_none(void);
	public static native COption_NoneZ COption_NoneZ_none();
	// void COption_NoneZ_free(enum LDKCOption_NoneZ _res);
	public static native void COption_NoneZ_free(COption_NoneZ _res);
	// struct LDKCResult_CounterpartyChannelTransactionParametersDecodeErrorZ CResult_CounterpartyChannelTransactionParametersDecodeErrorZ_ok(struct LDKCounterpartyChannelTransactionParameters o);
	public static native long CResult_CounterpartyChannelTransactionParametersDecodeErrorZ_ok(long o);
	// struct LDKCResult_CounterpartyChannelTransactionParametersDecodeErrorZ CResult_CounterpartyChannelTransactionParametersDecodeErrorZ_err(struct LDKDecodeError e);
	public static native long CResult_CounterpartyChannelTransactionParametersDecodeErrorZ_err(long e);
	// bool CResult_CounterpartyChannelTransactionParametersDecodeErrorZ_is_ok(const struct LDKCResult_CounterpartyChannelTransactionParametersDecodeErrorZ *NONNULL_PTR o);
	public static native boolean CResult_CounterpartyChannelTransactionParametersDecodeErrorZ_is_ok(long o);
	// void CResult_CounterpartyChannelTransactionParametersDecodeErrorZ_free(struct LDKCResult_CounterpartyChannelTransactionParametersDecodeErrorZ _res);
	public static native void CResult_CounterpartyChannelTransactionParametersDecodeErrorZ_free(long _res);
	// uintptr_t CResult_CounterpartyChannelTransactionParametersDecodeErrorZ_clone_ptr(LDKCResult_CounterpartyChannelTransactionParametersDecodeErrorZ *NONNULL_PTR arg);
	public static native long CResult_CounterpartyChannelTransactionParametersDecodeErrorZ_clone_ptr(long arg);
	// struct LDKCResult_CounterpartyChannelTransactionParametersDecodeErrorZ CResult_CounterpartyChannelTransactionParametersDecodeErrorZ_clone(const struct LDKCResult_CounterpartyChannelTransactionParametersDecodeErrorZ *NONNULL_PTR orig);
	public static native long CResult_CounterpartyChannelTransactionParametersDecodeErrorZ_clone(long orig);
	// struct LDKCResult_ChannelTransactionParametersDecodeErrorZ CResult_ChannelTransactionParametersDecodeErrorZ_ok(struct LDKChannelTransactionParameters o);
	public static native long CResult_ChannelTransactionParametersDecodeErrorZ_ok(long o);
	// struct LDKCResult_ChannelTransactionParametersDecodeErrorZ CResult_ChannelTransactionParametersDecodeErrorZ_err(struct LDKDecodeError e);
	public static native long CResult_ChannelTransactionParametersDecodeErrorZ_err(long e);
	// bool CResult_ChannelTransactionParametersDecodeErrorZ_is_ok(const struct LDKCResult_ChannelTransactionParametersDecodeErrorZ *NONNULL_PTR o);
	public static native boolean CResult_ChannelTransactionParametersDecodeErrorZ_is_ok(long o);
	// void CResult_ChannelTransactionParametersDecodeErrorZ_free(struct LDKCResult_ChannelTransactionParametersDecodeErrorZ _res);
	public static native void CResult_ChannelTransactionParametersDecodeErrorZ_free(long _res);
	// uintptr_t CResult_ChannelTransactionParametersDecodeErrorZ_clone_ptr(LDKCResult_ChannelTransactionParametersDecodeErrorZ *NONNULL_PTR arg);
	public static native long CResult_ChannelTransactionParametersDecodeErrorZ_clone_ptr(long arg);
	// struct LDKCResult_ChannelTransactionParametersDecodeErrorZ CResult_ChannelTransactionParametersDecodeErrorZ_clone(const struct LDKCResult_ChannelTransactionParametersDecodeErrorZ *NONNULL_PTR orig);
	public static native long CResult_ChannelTransactionParametersDecodeErrorZ_clone(long orig);
	// void CVec_SignatureZ_free(struct LDKCVec_SignatureZ _res);
	public static native void CVec_SignatureZ_free(byte[][] _res);
	// struct LDKCResult_HolderCommitmentTransactionDecodeErrorZ CResult_HolderCommitmentTransactionDecodeErrorZ_ok(struct LDKHolderCommitmentTransaction o);
	public static native long CResult_HolderCommitmentTransactionDecodeErrorZ_ok(long o);
	// struct LDKCResult_HolderCommitmentTransactionDecodeErrorZ CResult_HolderCommitmentTransactionDecodeErrorZ_err(struct LDKDecodeError e);
	public static native long CResult_HolderCommitmentTransactionDecodeErrorZ_err(long e);
	// bool CResult_HolderCommitmentTransactionDecodeErrorZ_is_ok(const struct LDKCResult_HolderCommitmentTransactionDecodeErrorZ *NONNULL_PTR o);
	public static native boolean CResult_HolderCommitmentTransactionDecodeErrorZ_is_ok(long o);
	// void CResult_HolderCommitmentTransactionDecodeErrorZ_free(struct LDKCResult_HolderCommitmentTransactionDecodeErrorZ _res);
	public static native void CResult_HolderCommitmentTransactionDecodeErrorZ_free(long _res);
	// uintptr_t CResult_HolderCommitmentTransactionDecodeErrorZ_clone_ptr(LDKCResult_HolderCommitmentTransactionDecodeErrorZ *NONNULL_PTR arg);
	public static native long CResult_HolderCommitmentTransactionDecodeErrorZ_clone_ptr(long arg);
	// struct LDKCResult_HolderCommitmentTransactionDecodeErrorZ CResult_HolderCommitmentTransactionDecodeErrorZ_clone(const struct LDKCResult_HolderCommitmentTransactionDecodeErrorZ *NONNULL_PTR orig);
	public static native long CResult_HolderCommitmentTransactionDecodeErrorZ_clone(long orig);
	// struct LDKCResult_BuiltCommitmentTransactionDecodeErrorZ CResult_BuiltCommitmentTransactionDecodeErrorZ_ok(struct LDKBuiltCommitmentTransaction o);
	public static native long CResult_BuiltCommitmentTransactionDecodeErrorZ_ok(long o);
	// struct LDKCResult_BuiltCommitmentTransactionDecodeErrorZ CResult_BuiltCommitmentTransactionDecodeErrorZ_err(struct LDKDecodeError e);
	public static native long CResult_BuiltCommitmentTransactionDecodeErrorZ_err(long e);
	// bool CResult_BuiltCommitmentTransactionDecodeErrorZ_is_ok(const struct LDKCResult_BuiltCommitmentTransactionDecodeErrorZ *NONNULL_PTR o);
	public static native boolean CResult_BuiltCommitmentTransactionDecodeErrorZ_is_ok(long o);
	// void CResult_BuiltCommitmentTransactionDecodeErrorZ_free(struct LDKCResult_BuiltCommitmentTransactionDecodeErrorZ _res);
	public static native void CResult_BuiltCommitmentTransactionDecodeErrorZ_free(long _res);
	// uintptr_t CResult_BuiltCommitmentTransactionDecodeErrorZ_clone_ptr(LDKCResult_BuiltCommitmentTransactionDecodeErrorZ *NONNULL_PTR arg);
	public static native long CResult_BuiltCommitmentTransactionDecodeErrorZ_clone_ptr(long arg);
	// struct LDKCResult_BuiltCommitmentTransactionDecodeErrorZ CResult_BuiltCommitmentTransactionDecodeErrorZ_clone(const struct LDKCResult_BuiltCommitmentTransactionDecodeErrorZ *NONNULL_PTR orig);
	public static native long CResult_BuiltCommitmentTransactionDecodeErrorZ_clone(long orig);
	// struct LDKCResult_TrustedClosingTransactionNoneZ CResult_TrustedClosingTransactionNoneZ_ok(struct LDKTrustedClosingTransaction o);
	public static native long CResult_TrustedClosingTransactionNoneZ_ok(long o);
	// struct LDKCResult_TrustedClosingTransactionNoneZ CResult_TrustedClosingTransactionNoneZ_err(void);
	public static native long CResult_TrustedClosingTransactionNoneZ_err();
	// bool CResult_TrustedClosingTransactionNoneZ_is_ok(const struct LDKCResult_TrustedClosingTransactionNoneZ *NONNULL_PTR o);
	public static native boolean CResult_TrustedClosingTransactionNoneZ_is_ok(long o);
	// void CResult_TrustedClosingTransactionNoneZ_free(struct LDKCResult_TrustedClosingTransactionNoneZ _res);
	public static native void CResult_TrustedClosingTransactionNoneZ_free(long _res);
	// struct LDKCResult_CommitmentTransactionDecodeErrorZ CResult_CommitmentTransactionDecodeErrorZ_ok(struct LDKCommitmentTransaction o);
	public static native long CResult_CommitmentTransactionDecodeErrorZ_ok(long o);
	// struct LDKCResult_CommitmentTransactionDecodeErrorZ CResult_CommitmentTransactionDecodeErrorZ_err(struct LDKDecodeError e);
	public static native long CResult_CommitmentTransactionDecodeErrorZ_err(long e);
	// bool CResult_CommitmentTransactionDecodeErrorZ_is_ok(const struct LDKCResult_CommitmentTransactionDecodeErrorZ *NONNULL_PTR o);
	public static native boolean CResult_CommitmentTransactionDecodeErrorZ_is_ok(long o);
	// void CResult_CommitmentTransactionDecodeErrorZ_free(struct LDKCResult_CommitmentTransactionDecodeErrorZ _res);
	public static native void CResult_CommitmentTransactionDecodeErrorZ_free(long _res);
	// uintptr_t CResult_CommitmentTransactionDecodeErrorZ_clone_ptr(LDKCResult_CommitmentTransactionDecodeErrorZ *NONNULL_PTR arg);
	public static native long CResult_CommitmentTransactionDecodeErrorZ_clone_ptr(long arg);
	// struct LDKCResult_CommitmentTransactionDecodeErrorZ CResult_CommitmentTransactionDecodeErrorZ_clone(const struct LDKCResult_CommitmentTransactionDecodeErrorZ *NONNULL_PTR orig);
	public static native long CResult_CommitmentTransactionDecodeErrorZ_clone(long orig);
	// struct LDKCResult_TrustedCommitmentTransactionNoneZ CResult_TrustedCommitmentTransactionNoneZ_ok(struct LDKTrustedCommitmentTransaction o);
	public static native long CResult_TrustedCommitmentTransactionNoneZ_ok(long o);
	// struct LDKCResult_TrustedCommitmentTransactionNoneZ CResult_TrustedCommitmentTransactionNoneZ_err(void);
	public static native long CResult_TrustedCommitmentTransactionNoneZ_err();
	// bool CResult_TrustedCommitmentTransactionNoneZ_is_ok(const struct LDKCResult_TrustedCommitmentTransactionNoneZ *NONNULL_PTR o);
	public static native boolean CResult_TrustedCommitmentTransactionNoneZ_is_ok(long o);
	// void CResult_TrustedCommitmentTransactionNoneZ_free(struct LDKCResult_TrustedCommitmentTransactionNoneZ _res);
	public static native void CResult_TrustedCommitmentTransactionNoneZ_free(long _res);
	// struct LDKCResult_CVec_SignatureZNoneZ CResult_CVec_SignatureZNoneZ_ok(struct LDKCVec_SignatureZ o);
	public static native long CResult_CVec_SignatureZNoneZ_ok(byte[][] o);
	// struct LDKCResult_CVec_SignatureZNoneZ CResult_CVec_SignatureZNoneZ_err(void);
	public static native long CResult_CVec_SignatureZNoneZ_err();
	// bool CResult_CVec_SignatureZNoneZ_is_ok(const struct LDKCResult_CVec_SignatureZNoneZ *NONNULL_PTR o);
	public static native boolean CResult_CVec_SignatureZNoneZ_is_ok(long o);
	// void CResult_CVec_SignatureZNoneZ_free(struct LDKCResult_CVec_SignatureZNoneZ _res);
	public static native void CResult_CVec_SignatureZNoneZ_free(long _res);
	// uintptr_t CResult_CVec_SignatureZNoneZ_clone_ptr(LDKCResult_CVec_SignatureZNoneZ *NONNULL_PTR arg);
	public static native long CResult_CVec_SignatureZNoneZ_clone_ptr(long arg);
	// struct LDKCResult_CVec_SignatureZNoneZ CResult_CVec_SignatureZNoneZ_clone(const struct LDKCResult_CVec_SignatureZNoneZ *NONNULL_PTR orig);
	public static native long CResult_CVec_SignatureZNoneZ_clone(long orig);
	// struct LDKCResult_ShutdownScriptDecodeErrorZ CResult_ShutdownScriptDecodeErrorZ_ok(struct LDKShutdownScript o);
	public static native long CResult_ShutdownScriptDecodeErrorZ_ok(long o);
	// struct LDKCResult_ShutdownScriptDecodeErrorZ CResult_ShutdownScriptDecodeErrorZ_err(struct LDKDecodeError e);
	public static native long CResult_ShutdownScriptDecodeErrorZ_err(long e);
	// bool CResult_ShutdownScriptDecodeErrorZ_is_ok(const struct LDKCResult_ShutdownScriptDecodeErrorZ *NONNULL_PTR o);
	public static native boolean CResult_ShutdownScriptDecodeErrorZ_is_ok(long o);
	// void CResult_ShutdownScriptDecodeErrorZ_free(struct LDKCResult_ShutdownScriptDecodeErrorZ _res);
	public static native void CResult_ShutdownScriptDecodeErrorZ_free(long _res);
	// uintptr_t CResult_ShutdownScriptDecodeErrorZ_clone_ptr(LDKCResult_ShutdownScriptDecodeErrorZ *NONNULL_PTR arg);
	public static native long CResult_ShutdownScriptDecodeErrorZ_clone_ptr(long arg);
	// struct LDKCResult_ShutdownScriptDecodeErrorZ CResult_ShutdownScriptDecodeErrorZ_clone(const struct LDKCResult_ShutdownScriptDecodeErrorZ *NONNULL_PTR orig);
	public static native long CResult_ShutdownScriptDecodeErrorZ_clone(long orig);
	// struct LDKCResult_ShutdownScriptInvalidShutdownScriptZ CResult_ShutdownScriptInvalidShutdownScriptZ_ok(struct LDKShutdownScript o);
	public static native long CResult_ShutdownScriptInvalidShutdownScriptZ_ok(long o);
	// struct LDKCResult_ShutdownScriptInvalidShutdownScriptZ CResult_ShutdownScriptInvalidShutdownScriptZ_err(struct LDKInvalidShutdownScript e);
	public static native long CResult_ShutdownScriptInvalidShutdownScriptZ_err(long e);
	// bool CResult_ShutdownScriptInvalidShutdownScriptZ_is_ok(const struct LDKCResult_ShutdownScriptInvalidShutdownScriptZ *NONNULL_PTR o);
	public static native boolean CResult_ShutdownScriptInvalidShutdownScriptZ_is_ok(long o);
	// void CResult_ShutdownScriptInvalidShutdownScriptZ_free(struct LDKCResult_ShutdownScriptInvalidShutdownScriptZ _res);
	public static native void CResult_ShutdownScriptInvalidShutdownScriptZ_free(long _res);
	// uintptr_t CResult_ShutdownScriptInvalidShutdownScriptZ_clone_ptr(LDKCResult_ShutdownScriptInvalidShutdownScriptZ *NONNULL_PTR arg);
	public static native long CResult_ShutdownScriptInvalidShutdownScriptZ_clone_ptr(long arg);
	// struct LDKCResult_ShutdownScriptInvalidShutdownScriptZ CResult_ShutdownScriptInvalidShutdownScriptZ_clone(const struct LDKCResult_ShutdownScriptInvalidShutdownScriptZ *NONNULL_PTR orig);
	public static native long CResult_ShutdownScriptInvalidShutdownScriptZ_clone(long orig);
	// struct LDKCResult_NoneErrorZ CResult_NoneErrorZ_ok(void);
	public static native long CResult_NoneErrorZ_ok();
	// struct LDKCResult_NoneErrorZ CResult_NoneErrorZ_err(enum LDKIOError e);
	public static native long CResult_NoneErrorZ_err(IOError e);
	// bool CResult_NoneErrorZ_is_ok(const struct LDKCResult_NoneErrorZ *NONNULL_PTR o);
	public static native boolean CResult_NoneErrorZ_is_ok(long o);
	// void CResult_NoneErrorZ_free(struct LDKCResult_NoneErrorZ _res);
	public static native void CResult_NoneErrorZ_free(long _res);
	// uintptr_t CResult_NoneErrorZ_clone_ptr(LDKCResult_NoneErrorZ *NONNULL_PTR arg);
	public static native long CResult_NoneErrorZ_clone_ptr(long arg);
	// struct LDKCResult_NoneErrorZ CResult_NoneErrorZ_clone(const struct LDKCResult_NoneErrorZ *NONNULL_PTR orig);
	public static native long CResult_NoneErrorZ_clone(long orig);
	// struct LDKCResult_RouteHopDecodeErrorZ CResult_RouteHopDecodeErrorZ_ok(struct LDKRouteHop o);
	public static native long CResult_RouteHopDecodeErrorZ_ok(long o);
	// struct LDKCResult_RouteHopDecodeErrorZ CResult_RouteHopDecodeErrorZ_err(struct LDKDecodeError e);
	public static native long CResult_RouteHopDecodeErrorZ_err(long e);
	// bool CResult_RouteHopDecodeErrorZ_is_ok(const struct LDKCResult_RouteHopDecodeErrorZ *NONNULL_PTR o);
	public static native boolean CResult_RouteHopDecodeErrorZ_is_ok(long o);
	// void CResult_RouteHopDecodeErrorZ_free(struct LDKCResult_RouteHopDecodeErrorZ _res);
	public static native void CResult_RouteHopDecodeErrorZ_free(long _res);
	// uintptr_t CResult_RouteHopDecodeErrorZ_clone_ptr(LDKCResult_RouteHopDecodeErrorZ *NONNULL_PTR arg);
	public static native long CResult_RouteHopDecodeErrorZ_clone_ptr(long arg);
	// struct LDKCResult_RouteHopDecodeErrorZ CResult_RouteHopDecodeErrorZ_clone(const struct LDKCResult_RouteHopDecodeErrorZ *NONNULL_PTR orig);
	public static native long CResult_RouteHopDecodeErrorZ_clone(long orig);
	// void CVec_RouteHopZ_free(struct LDKCVec_RouteHopZ _res);
	public static native void CVec_RouteHopZ_free(long[] _res);
	// void CVec_CVec_RouteHopZZ_free(struct LDKCVec_CVec_RouteHopZZ _res);
	public static native void CVec_CVec_RouteHopZZ_free(long[][] _res);
	// struct LDKCResult_RouteDecodeErrorZ CResult_RouteDecodeErrorZ_ok(struct LDKRoute o);
	public static native long CResult_RouteDecodeErrorZ_ok(long o);
	// struct LDKCResult_RouteDecodeErrorZ CResult_RouteDecodeErrorZ_err(struct LDKDecodeError e);
	public static native long CResult_RouteDecodeErrorZ_err(long e);
	// bool CResult_RouteDecodeErrorZ_is_ok(const struct LDKCResult_RouteDecodeErrorZ *NONNULL_PTR o);
	public static native boolean CResult_RouteDecodeErrorZ_is_ok(long o);
	// void CResult_RouteDecodeErrorZ_free(struct LDKCResult_RouteDecodeErrorZ _res);
	public static native void CResult_RouteDecodeErrorZ_free(long _res);
	// uintptr_t CResult_RouteDecodeErrorZ_clone_ptr(LDKCResult_RouteDecodeErrorZ *NONNULL_PTR arg);
	public static native long CResult_RouteDecodeErrorZ_clone_ptr(long arg);
	// struct LDKCResult_RouteDecodeErrorZ CResult_RouteDecodeErrorZ_clone(const struct LDKCResult_RouteDecodeErrorZ *NONNULL_PTR orig);
	public static native long CResult_RouteDecodeErrorZ_clone(long orig);
	// struct LDKCResult_RouteParametersDecodeErrorZ CResult_RouteParametersDecodeErrorZ_ok(struct LDKRouteParameters o);
	public static native long CResult_RouteParametersDecodeErrorZ_ok(long o);
	// struct LDKCResult_RouteParametersDecodeErrorZ CResult_RouteParametersDecodeErrorZ_err(struct LDKDecodeError e);
	public static native long CResult_RouteParametersDecodeErrorZ_err(long e);
	// bool CResult_RouteParametersDecodeErrorZ_is_ok(const struct LDKCResult_RouteParametersDecodeErrorZ *NONNULL_PTR o);
	public static native boolean CResult_RouteParametersDecodeErrorZ_is_ok(long o);
	// void CResult_RouteParametersDecodeErrorZ_free(struct LDKCResult_RouteParametersDecodeErrorZ _res);
	public static native void CResult_RouteParametersDecodeErrorZ_free(long _res);
	// uintptr_t CResult_RouteParametersDecodeErrorZ_clone_ptr(LDKCResult_RouteParametersDecodeErrorZ *NONNULL_PTR arg);
	public static native long CResult_RouteParametersDecodeErrorZ_clone_ptr(long arg);
	// struct LDKCResult_RouteParametersDecodeErrorZ CResult_RouteParametersDecodeErrorZ_clone(const struct LDKCResult_RouteParametersDecodeErrorZ *NONNULL_PTR orig);
	public static native long CResult_RouteParametersDecodeErrorZ_clone(long orig);
	// void CVec_RouteHintZ_free(struct LDKCVec_RouteHintZ _res);
	public static native void CVec_RouteHintZ_free(long[] _res);
	// struct LDKCOption_u64Z COption_u64Z_some(uint64_t o);
	public static native long COption_u64Z_some(long o);
	// struct LDKCOption_u64Z COption_u64Z_none(void);
	public static native long COption_u64Z_none();
	// void COption_u64Z_free(struct LDKCOption_u64Z _res);
	public static native void COption_u64Z_free(long _res);
	// uintptr_t COption_u64Z_clone_ptr(LDKCOption_u64Z *NONNULL_PTR arg);
	public static native long COption_u64Z_clone_ptr(long arg);
	// struct LDKCOption_u64Z COption_u64Z_clone(const struct LDKCOption_u64Z *NONNULL_PTR orig);
	public static native long COption_u64Z_clone(long orig);
	// struct LDKCResult_PaymentParametersDecodeErrorZ CResult_PaymentParametersDecodeErrorZ_ok(struct LDKPaymentParameters o);
	public static native long CResult_PaymentParametersDecodeErrorZ_ok(long o);
	// struct LDKCResult_PaymentParametersDecodeErrorZ CResult_PaymentParametersDecodeErrorZ_err(struct LDKDecodeError e);
	public static native long CResult_PaymentParametersDecodeErrorZ_err(long e);
	// bool CResult_PaymentParametersDecodeErrorZ_is_ok(const struct LDKCResult_PaymentParametersDecodeErrorZ *NONNULL_PTR o);
	public static native boolean CResult_PaymentParametersDecodeErrorZ_is_ok(long o);
	// void CResult_PaymentParametersDecodeErrorZ_free(struct LDKCResult_PaymentParametersDecodeErrorZ _res);
	public static native void CResult_PaymentParametersDecodeErrorZ_free(long _res);
	// uintptr_t CResult_PaymentParametersDecodeErrorZ_clone_ptr(LDKCResult_PaymentParametersDecodeErrorZ *NONNULL_PTR arg);
	public static native long CResult_PaymentParametersDecodeErrorZ_clone_ptr(long arg);
	// struct LDKCResult_PaymentParametersDecodeErrorZ CResult_PaymentParametersDecodeErrorZ_clone(const struct LDKCResult_PaymentParametersDecodeErrorZ *NONNULL_PTR orig);
	public static native long CResult_PaymentParametersDecodeErrorZ_clone(long orig);
	// void CVec_RouteHintHopZ_free(struct LDKCVec_RouteHintHopZ _res);
	public static native void CVec_RouteHintHopZ_free(long[] _res);
	// struct LDKCResult_RouteHintDecodeErrorZ CResult_RouteHintDecodeErrorZ_ok(struct LDKRouteHint o);
	public static native long CResult_RouteHintDecodeErrorZ_ok(long o);
	// struct LDKCResult_RouteHintDecodeErrorZ CResult_RouteHintDecodeErrorZ_err(struct LDKDecodeError e);
	public static native long CResult_RouteHintDecodeErrorZ_err(long e);
	// bool CResult_RouteHintDecodeErrorZ_is_ok(const struct LDKCResult_RouteHintDecodeErrorZ *NONNULL_PTR o);
	public static native boolean CResult_RouteHintDecodeErrorZ_is_ok(long o);
	// void CResult_RouteHintDecodeErrorZ_free(struct LDKCResult_RouteHintDecodeErrorZ _res);
	public static native void CResult_RouteHintDecodeErrorZ_free(long _res);
	// uintptr_t CResult_RouteHintDecodeErrorZ_clone_ptr(LDKCResult_RouteHintDecodeErrorZ *NONNULL_PTR arg);
	public static native long CResult_RouteHintDecodeErrorZ_clone_ptr(long arg);
	// struct LDKCResult_RouteHintDecodeErrorZ CResult_RouteHintDecodeErrorZ_clone(const struct LDKCResult_RouteHintDecodeErrorZ *NONNULL_PTR orig);
	public static native long CResult_RouteHintDecodeErrorZ_clone(long orig);
	// struct LDKCResult_RouteHintHopDecodeErrorZ CResult_RouteHintHopDecodeErrorZ_ok(struct LDKRouteHintHop o);
	public static native long CResult_RouteHintHopDecodeErrorZ_ok(long o);
	// struct LDKCResult_RouteHintHopDecodeErrorZ CResult_RouteHintHopDecodeErrorZ_err(struct LDKDecodeError e);
	public static native long CResult_RouteHintHopDecodeErrorZ_err(long e);
	// bool CResult_RouteHintHopDecodeErrorZ_is_ok(const struct LDKCResult_RouteHintHopDecodeErrorZ *NONNULL_PTR o);
	public static native boolean CResult_RouteHintHopDecodeErrorZ_is_ok(long o);
	// void CResult_RouteHintHopDecodeErrorZ_free(struct LDKCResult_RouteHintHopDecodeErrorZ _res);
	public static native void CResult_RouteHintHopDecodeErrorZ_free(long _res);
	// uintptr_t CResult_RouteHintHopDecodeErrorZ_clone_ptr(LDKCResult_RouteHintHopDecodeErrorZ *NONNULL_PTR arg);
	public static native long CResult_RouteHintHopDecodeErrorZ_clone_ptr(long arg);
	// struct LDKCResult_RouteHintHopDecodeErrorZ CResult_RouteHintHopDecodeErrorZ_clone(const struct LDKCResult_RouteHintHopDecodeErrorZ *NONNULL_PTR orig);
	public static native long CResult_RouteHintHopDecodeErrorZ_clone(long orig);
	// void CVec_ChannelDetailsZ_free(struct LDKCVec_ChannelDetailsZ _res);
	public static native void CVec_ChannelDetailsZ_free(long[] _res);
	// struct LDKCResult_RouteLightningErrorZ CResult_RouteLightningErrorZ_ok(struct LDKRoute o);
	public static native long CResult_RouteLightningErrorZ_ok(long o);
	// struct LDKCResult_RouteLightningErrorZ CResult_RouteLightningErrorZ_err(struct LDKLightningError e);
	public static native long CResult_RouteLightningErrorZ_err(long e);
	// bool CResult_RouteLightningErrorZ_is_ok(const struct LDKCResult_RouteLightningErrorZ *NONNULL_PTR o);
	public static native boolean CResult_RouteLightningErrorZ_is_ok(long o);
	// void CResult_RouteLightningErrorZ_free(struct LDKCResult_RouteLightningErrorZ _res);
	public static native void CResult_RouteLightningErrorZ_free(long _res);
	// uintptr_t CResult_RouteLightningErrorZ_clone_ptr(LDKCResult_RouteLightningErrorZ *NONNULL_PTR arg);
	public static native long CResult_RouteLightningErrorZ_clone_ptr(long arg);
	// struct LDKCResult_RouteLightningErrorZ CResult_RouteLightningErrorZ_clone(const struct LDKCResult_RouteLightningErrorZ *NONNULL_PTR orig);
	public static native long CResult_RouteLightningErrorZ_clone(long orig);
	// struct LDKCResult_TxOutAccessErrorZ CResult_TxOutAccessErrorZ_ok(struct LDKTxOut o);
	public static native long CResult_TxOutAccessErrorZ_ok(long o);
	// struct LDKCResult_TxOutAccessErrorZ CResult_TxOutAccessErrorZ_err(enum LDKAccessError e);
	public static native long CResult_TxOutAccessErrorZ_err(AccessError e);
	// bool CResult_TxOutAccessErrorZ_is_ok(const struct LDKCResult_TxOutAccessErrorZ *NONNULL_PTR o);
	public static native boolean CResult_TxOutAccessErrorZ_is_ok(long o);
	// void CResult_TxOutAccessErrorZ_free(struct LDKCResult_TxOutAccessErrorZ _res);
	public static native void CResult_TxOutAccessErrorZ_free(long _res);
	// uintptr_t CResult_TxOutAccessErrorZ_clone_ptr(LDKCResult_TxOutAccessErrorZ *NONNULL_PTR arg);
	public static native long CResult_TxOutAccessErrorZ_clone_ptr(long arg);
	// struct LDKCResult_TxOutAccessErrorZ CResult_TxOutAccessErrorZ_clone(const struct LDKCResult_TxOutAccessErrorZ *NONNULL_PTR orig);
	public static native long CResult_TxOutAccessErrorZ_clone(long orig);
	// uintptr_t C2Tuple_usizeTransactionZ_clone_ptr(LDKC2Tuple_usizeTransactionZ *NONNULL_PTR arg);
	public static native long C2Tuple_usizeTransactionZ_clone_ptr(long arg);
	// struct LDKC2Tuple_usizeTransactionZ C2Tuple_usizeTransactionZ_clone(const struct LDKC2Tuple_usizeTransactionZ *NONNULL_PTR orig);
	public static native long C2Tuple_usizeTransactionZ_clone(long orig);
	// struct LDKC2Tuple_usizeTransactionZ C2Tuple_usizeTransactionZ_new(uintptr_t a, struct LDKTransaction b);
	public static native long C2Tuple_usizeTransactionZ_new(long a, byte[] b);
	// void C2Tuple_usizeTransactionZ_free(struct LDKC2Tuple_usizeTransactionZ _res);
	public static native void C2Tuple_usizeTransactionZ_free(long _res);
	// void CVec_C2Tuple_usizeTransactionZZ_free(struct LDKCVec_C2Tuple_usizeTransactionZZ _res);
	public static native void CVec_C2Tuple_usizeTransactionZZ_free(long[] _res);
	// void CVec_TxidZ_free(struct LDKCVec_TxidZ _res);
	public static native void CVec_TxidZ_free(byte[][] _res);
	// struct LDKCResult_NoneChannelMonitorUpdateErrZ CResult_NoneChannelMonitorUpdateErrZ_ok(void);
	public static native long CResult_NoneChannelMonitorUpdateErrZ_ok();
	// struct LDKCResult_NoneChannelMonitorUpdateErrZ CResult_NoneChannelMonitorUpdateErrZ_err(enum LDKChannelMonitorUpdateErr e);
	public static native long CResult_NoneChannelMonitorUpdateErrZ_err(ChannelMonitorUpdateErr e);
	// bool CResult_NoneChannelMonitorUpdateErrZ_is_ok(const struct LDKCResult_NoneChannelMonitorUpdateErrZ *NONNULL_PTR o);
	public static native boolean CResult_NoneChannelMonitorUpdateErrZ_is_ok(long o);
	// void CResult_NoneChannelMonitorUpdateErrZ_free(struct LDKCResult_NoneChannelMonitorUpdateErrZ _res);
	public static native void CResult_NoneChannelMonitorUpdateErrZ_free(long _res);
	// uintptr_t CResult_NoneChannelMonitorUpdateErrZ_clone_ptr(LDKCResult_NoneChannelMonitorUpdateErrZ *NONNULL_PTR arg);
	public static native long CResult_NoneChannelMonitorUpdateErrZ_clone_ptr(long arg);
	// struct LDKCResult_NoneChannelMonitorUpdateErrZ CResult_NoneChannelMonitorUpdateErrZ_clone(const struct LDKCResult_NoneChannelMonitorUpdateErrZ *NONNULL_PTR orig);
	public static native long CResult_NoneChannelMonitorUpdateErrZ_clone(long orig);
	// void CVec_MonitorEventZ_free(struct LDKCVec_MonitorEventZ _res);
	public static native void CVec_MonitorEventZ_free(long[] _res);
	// struct LDKCOption_C2Tuple_usizeTransactionZZ COption_C2Tuple_usizeTransactionZZ_some(struct LDKC2Tuple_usizeTransactionZ o);
	public static native long COption_C2Tuple_usizeTransactionZZ_some(long o);
	// struct LDKCOption_C2Tuple_usizeTransactionZZ COption_C2Tuple_usizeTransactionZZ_none(void);
	public static native long COption_C2Tuple_usizeTransactionZZ_none();
	// void COption_C2Tuple_usizeTransactionZZ_free(struct LDKCOption_C2Tuple_usizeTransactionZZ _res);
	public static native void COption_C2Tuple_usizeTransactionZZ_free(long _res);
	// uintptr_t COption_C2Tuple_usizeTransactionZZ_clone_ptr(LDKCOption_C2Tuple_usizeTransactionZZ *NONNULL_PTR arg);
	public static native long COption_C2Tuple_usizeTransactionZZ_clone_ptr(long arg);
	// struct LDKCOption_C2Tuple_usizeTransactionZZ COption_C2Tuple_usizeTransactionZZ_clone(const struct LDKCOption_C2Tuple_usizeTransactionZZ *NONNULL_PTR orig);
	public static native long COption_C2Tuple_usizeTransactionZZ_clone(long orig);
	// struct LDKCOption_ClosureReasonZ COption_ClosureReasonZ_some(struct LDKClosureReason o);
	public static native long COption_ClosureReasonZ_some(long o);
	// struct LDKCOption_ClosureReasonZ COption_ClosureReasonZ_none(void);
	public static native long COption_ClosureReasonZ_none();
	// void COption_ClosureReasonZ_free(struct LDKCOption_ClosureReasonZ _res);
	public static native void COption_ClosureReasonZ_free(long _res);
	// uintptr_t COption_ClosureReasonZ_clone_ptr(LDKCOption_ClosureReasonZ *NONNULL_PTR arg);
	public static native long COption_ClosureReasonZ_clone_ptr(long arg);
	// struct LDKCOption_ClosureReasonZ COption_ClosureReasonZ_clone(const struct LDKCOption_ClosureReasonZ *NONNULL_PTR orig);
	public static native long COption_ClosureReasonZ_clone(long orig);
	// struct LDKCResult_COption_ClosureReasonZDecodeErrorZ CResult_COption_ClosureReasonZDecodeErrorZ_ok(struct LDKCOption_ClosureReasonZ o);
	public static native long CResult_COption_ClosureReasonZDecodeErrorZ_ok(long o);
	// struct LDKCResult_COption_ClosureReasonZDecodeErrorZ CResult_COption_ClosureReasonZDecodeErrorZ_err(struct LDKDecodeError e);
	public static native long CResult_COption_ClosureReasonZDecodeErrorZ_err(long e);
	// bool CResult_COption_ClosureReasonZDecodeErrorZ_is_ok(const struct LDKCResult_COption_ClosureReasonZDecodeErrorZ *NONNULL_PTR o);
	public static native boolean CResult_COption_ClosureReasonZDecodeErrorZ_is_ok(long o);
	// void CResult_COption_ClosureReasonZDecodeErrorZ_free(struct LDKCResult_COption_ClosureReasonZDecodeErrorZ _res);
	public static native void CResult_COption_ClosureReasonZDecodeErrorZ_free(long _res);
	// uintptr_t CResult_COption_ClosureReasonZDecodeErrorZ_clone_ptr(LDKCResult_COption_ClosureReasonZDecodeErrorZ *NONNULL_PTR arg);
	public static native long CResult_COption_ClosureReasonZDecodeErrorZ_clone_ptr(long arg);
	// struct LDKCResult_COption_ClosureReasonZDecodeErrorZ CResult_COption_ClosureReasonZDecodeErrorZ_clone(const struct LDKCResult_COption_ClosureReasonZDecodeErrorZ *NONNULL_PTR orig);
	public static native long CResult_COption_ClosureReasonZDecodeErrorZ_clone(long orig);
	// struct LDKCOption_NetworkUpdateZ COption_NetworkUpdateZ_some(struct LDKNetworkUpdate o);
	public static native long COption_NetworkUpdateZ_some(long o);
	// struct LDKCOption_NetworkUpdateZ COption_NetworkUpdateZ_none(void);
	public static native long COption_NetworkUpdateZ_none();
	// void COption_NetworkUpdateZ_free(struct LDKCOption_NetworkUpdateZ _res);
	public static native void COption_NetworkUpdateZ_free(long _res);
	// uintptr_t COption_NetworkUpdateZ_clone_ptr(LDKCOption_NetworkUpdateZ *NONNULL_PTR arg);
	public static native long COption_NetworkUpdateZ_clone_ptr(long arg);
	// struct LDKCOption_NetworkUpdateZ COption_NetworkUpdateZ_clone(const struct LDKCOption_NetworkUpdateZ *NONNULL_PTR orig);
	public static native long COption_NetworkUpdateZ_clone(long orig);
	// void CVec_SpendableOutputDescriptorZ_free(struct LDKCVec_SpendableOutputDescriptorZ _res);
	public static native void CVec_SpendableOutputDescriptorZ_free(long[] _res);
	// struct LDKCOption_EventZ COption_EventZ_some(struct LDKEvent o);
	public static native long COption_EventZ_some(long o);
	// struct LDKCOption_EventZ COption_EventZ_none(void);
	public static native long COption_EventZ_none();
	// void COption_EventZ_free(struct LDKCOption_EventZ _res);
	public static native void COption_EventZ_free(long _res);
	// uintptr_t COption_EventZ_clone_ptr(LDKCOption_EventZ *NONNULL_PTR arg);
	public static native long COption_EventZ_clone_ptr(long arg);
	// struct LDKCOption_EventZ COption_EventZ_clone(const struct LDKCOption_EventZ *NONNULL_PTR orig);
	public static native long COption_EventZ_clone(long orig);
	// struct LDKCResult_COption_EventZDecodeErrorZ CResult_COption_EventZDecodeErrorZ_ok(struct LDKCOption_EventZ o);
	public static native long CResult_COption_EventZDecodeErrorZ_ok(long o);
	// struct LDKCResult_COption_EventZDecodeErrorZ CResult_COption_EventZDecodeErrorZ_err(struct LDKDecodeError e);
	public static native long CResult_COption_EventZDecodeErrorZ_err(long e);
	// bool CResult_COption_EventZDecodeErrorZ_is_ok(const struct LDKCResult_COption_EventZDecodeErrorZ *NONNULL_PTR o);
	public static native boolean CResult_COption_EventZDecodeErrorZ_is_ok(long o);
	// void CResult_COption_EventZDecodeErrorZ_free(struct LDKCResult_COption_EventZDecodeErrorZ _res);
	public static native void CResult_COption_EventZDecodeErrorZ_free(long _res);
	// uintptr_t CResult_COption_EventZDecodeErrorZ_clone_ptr(LDKCResult_COption_EventZDecodeErrorZ *NONNULL_PTR arg);
	public static native long CResult_COption_EventZDecodeErrorZ_clone_ptr(long arg);
	// struct LDKCResult_COption_EventZDecodeErrorZ CResult_COption_EventZDecodeErrorZ_clone(const struct LDKCResult_COption_EventZDecodeErrorZ *NONNULL_PTR orig);
	public static native long CResult_COption_EventZDecodeErrorZ_clone(long orig);
	// void CVec_MessageSendEventZ_free(struct LDKCVec_MessageSendEventZ _res);
	public static native void CVec_MessageSendEventZ_free(long[] _res);
	// struct LDKCResult_FixedPenaltyScorerDecodeErrorZ CResult_FixedPenaltyScorerDecodeErrorZ_ok(struct LDKFixedPenaltyScorer o);
	public static native long CResult_FixedPenaltyScorerDecodeErrorZ_ok(long o);
	// struct LDKCResult_FixedPenaltyScorerDecodeErrorZ CResult_FixedPenaltyScorerDecodeErrorZ_err(struct LDKDecodeError e);
	public static native long CResult_FixedPenaltyScorerDecodeErrorZ_err(long e);
	// bool CResult_FixedPenaltyScorerDecodeErrorZ_is_ok(const struct LDKCResult_FixedPenaltyScorerDecodeErrorZ *NONNULL_PTR o);
	public static native boolean CResult_FixedPenaltyScorerDecodeErrorZ_is_ok(long o);
	// void CResult_FixedPenaltyScorerDecodeErrorZ_free(struct LDKCResult_FixedPenaltyScorerDecodeErrorZ _res);
	public static native void CResult_FixedPenaltyScorerDecodeErrorZ_free(long _res);
	// uintptr_t CResult_FixedPenaltyScorerDecodeErrorZ_clone_ptr(LDKCResult_FixedPenaltyScorerDecodeErrorZ *NONNULL_PTR arg);
	public static native long CResult_FixedPenaltyScorerDecodeErrorZ_clone_ptr(long arg);
	// struct LDKCResult_FixedPenaltyScorerDecodeErrorZ CResult_FixedPenaltyScorerDecodeErrorZ_clone(const struct LDKCResult_FixedPenaltyScorerDecodeErrorZ *NONNULL_PTR orig);
	public static native long CResult_FixedPenaltyScorerDecodeErrorZ_clone(long orig);
	// struct LDKCResult_ScoringParametersDecodeErrorZ CResult_ScoringParametersDecodeErrorZ_ok(struct LDKScoringParameters o);
	public static native long CResult_ScoringParametersDecodeErrorZ_ok(long o);
	// struct LDKCResult_ScoringParametersDecodeErrorZ CResult_ScoringParametersDecodeErrorZ_err(struct LDKDecodeError e);
	public static native long CResult_ScoringParametersDecodeErrorZ_err(long e);
	// bool CResult_ScoringParametersDecodeErrorZ_is_ok(const struct LDKCResult_ScoringParametersDecodeErrorZ *NONNULL_PTR o);
	public static native boolean CResult_ScoringParametersDecodeErrorZ_is_ok(long o);
	// void CResult_ScoringParametersDecodeErrorZ_free(struct LDKCResult_ScoringParametersDecodeErrorZ _res);
	public static native void CResult_ScoringParametersDecodeErrorZ_free(long _res);
	// uintptr_t CResult_ScoringParametersDecodeErrorZ_clone_ptr(LDKCResult_ScoringParametersDecodeErrorZ *NONNULL_PTR arg);
	public static native long CResult_ScoringParametersDecodeErrorZ_clone_ptr(long arg);
	// struct LDKCResult_ScoringParametersDecodeErrorZ CResult_ScoringParametersDecodeErrorZ_clone(const struct LDKCResult_ScoringParametersDecodeErrorZ *NONNULL_PTR orig);
	public static native long CResult_ScoringParametersDecodeErrorZ_clone(long orig);
	// struct LDKCResult_ScorerDecodeErrorZ CResult_ScorerDecodeErrorZ_ok(struct LDKScorer o);
	public static native long CResult_ScorerDecodeErrorZ_ok(long o);
	// struct LDKCResult_ScorerDecodeErrorZ CResult_ScorerDecodeErrorZ_err(struct LDKDecodeError e);
	public static native long CResult_ScorerDecodeErrorZ_err(long e);
	// bool CResult_ScorerDecodeErrorZ_is_ok(const struct LDKCResult_ScorerDecodeErrorZ *NONNULL_PTR o);
	public static native boolean CResult_ScorerDecodeErrorZ_is_ok(long o);
	// void CResult_ScorerDecodeErrorZ_free(struct LDKCResult_ScorerDecodeErrorZ _res);
	public static native void CResult_ScorerDecodeErrorZ_free(long _res);
	// struct LDKCResult_ProbabilisticScorerDecodeErrorZ CResult_ProbabilisticScorerDecodeErrorZ_ok(struct LDKProbabilisticScorer o);
	public static native long CResult_ProbabilisticScorerDecodeErrorZ_ok(long o);
	// struct LDKCResult_ProbabilisticScorerDecodeErrorZ CResult_ProbabilisticScorerDecodeErrorZ_err(struct LDKDecodeError e);
	public static native long CResult_ProbabilisticScorerDecodeErrorZ_err(long e);
	// bool CResult_ProbabilisticScorerDecodeErrorZ_is_ok(const struct LDKCResult_ProbabilisticScorerDecodeErrorZ *NONNULL_PTR o);
	public static native boolean CResult_ProbabilisticScorerDecodeErrorZ_is_ok(long o);
	// void CResult_ProbabilisticScorerDecodeErrorZ_free(struct LDKCResult_ProbabilisticScorerDecodeErrorZ _res);
	public static native void CResult_ProbabilisticScorerDecodeErrorZ_free(long _res);
	// struct LDKCResult_InitFeaturesDecodeErrorZ CResult_InitFeaturesDecodeErrorZ_ok(struct LDKInitFeatures o);
	public static native long CResult_InitFeaturesDecodeErrorZ_ok(long o);
	// struct LDKCResult_InitFeaturesDecodeErrorZ CResult_InitFeaturesDecodeErrorZ_err(struct LDKDecodeError e);
	public static native long CResult_InitFeaturesDecodeErrorZ_err(long e);
	// bool CResult_InitFeaturesDecodeErrorZ_is_ok(const struct LDKCResult_InitFeaturesDecodeErrorZ *NONNULL_PTR o);
	public static native boolean CResult_InitFeaturesDecodeErrorZ_is_ok(long o);
	// void CResult_InitFeaturesDecodeErrorZ_free(struct LDKCResult_InitFeaturesDecodeErrorZ _res);
	public static native void CResult_InitFeaturesDecodeErrorZ_free(long _res);
	// struct LDKCResult_ChannelFeaturesDecodeErrorZ CResult_ChannelFeaturesDecodeErrorZ_ok(struct LDKChannelFeatures o);
	public static native long CResult_ChannelFeaturesDecodeErrorZ_ok(long o);
	// struct LDKCResult_ChannelFeaturesDecodeErrorZ CResult_ChannelFeaturesDecodeErrorZ_err(struct LDKDecodeError e);
	public static native long CResult_ChannelFeaturesDecodeErrorZ_err(long e);
	// bool CResult_ChannelFeaturesDecodeErrorZ_is_ok(const struct LDKCResult_ChannelFeaturesDecodeErrorZ *NONNULL_PTR o);
	public static native boolean CResult_ChannelFeaturesDecodeErrorZ_is_ok(long o);
	// void CResult_ChannelFeaturesDecodeErrorZ_free(struct LDKCResult_ChannelFeaturesDecodeErrorZ _res);
	public static native void CResult_ChannelFeaturesDecodeErrorZ_free(long _res);
	// struct LDKCResult_NodeFeaturesDecodeErrorZ CResult_NodeFeaturesDecodeErrorZ_ok(struct LDKNodeFeatures o);
	public static native long CResult_NodeFeaturesDecodeErrorZ_ok(long o);
	// struct LDKCResult_NodeFeaturesDecodeErrorZ CResult_NodeFeaturesDecodeErrorZ_err(struct LDKDecodeError e);
	public static native long CResult_NodeFeaturesDecodeErrorZ_err(long e);
	// bool CResult_NodeFeaturesDecodeErrorZ_is_ok(const struct LDKCResult_NodeFeaturesDecodeErrorZ *NONNULL_PTR o);
	public static native boolean CResult_NodeFeaturesDecodeErrorZ_is_ok(long o);
	// void CResult_NodeFeaturesDecodeErrorZ_free(struct LDKCResult_NodeFeaturesDecodeErrorZ _res);
	public static native void CResult_NodeFeaturesDecodeErrorZ_free(long _res);
	// struct LDKCResult_InvoiceFeaturesDecodeErrorZ CResult_InvoiceFeaturesDecodeErrorZ_ok(struct LDKInvoiceFeatures o);
	public static native long CResult_InvoiceFeaturesDecodeErrorZ_ok(long o);
	// struct LDKCResult_InvoiceFeaturesDecodeErrorZ CResult_InvoiceFeaturesDecodeErrorZ_err(struct LDKDecodeError e);
	public static native long CResult_InvoiceFeaturesDecodeErrorZ_err(long e);
	// bool CResult_InvoiceFeaturesDecodeErrorZ_is_ok(const struct LDKCResult_InvoiceFeaturesDecodeErrorZ *NONNULL_PTR o);
	public static native boolean CResult_InvoiceFeaturesDecodeErrorZ_is_ok(long o);
	// void CResult_InvoiceFeaturesDecodeErrorZ_free(struct LDKCResult_InvoiceFeaturesDecodeErrorZ _res);
	public static native void CResult_InvoiceFeaturesDecodeErrorZ_free(long _res);
	// struct LDKCResult_ChannelTypeFeaturesDecodeErrorZ CResult_ChannelTypeFeaturesDecodeErrorZ_ok(struct LDKChannelTypeFeatures o);
	public static native long CResult_ChannelTypeFeaturesDecodeErrorZ_ok(long o);
	// struct LDKCResult_ChannelTypeFeaturesDecodeErrorZ CResult_ChannelTypeFeaturesDecodeErrorZ_err(struct LDKDecodeError e);
	public static native long CResult_ChannelTypeFeaturesDecodeErrorZ_err(long e);
	// bool CResult_ChannelTypeFeaturesDecodeErrorZ_is_ok(const struct LDKCResult_ChannelTypeFeaturesDecodeErrorZ *NONNULL_PTR o);
	public static native boolean CResult_ChannelTypeFeaturesDecodeErrorZ_is_ok(long o);
	// void CResult_ChannelTypeFeaturesDecodeErrorZ_free(struct LDKCResult_ChannelTypeFeaturesDecodeErrorZ _res);
	public static native void CResult_ChannelTypeFeaturesDecodeErrorZ_free(long _res);
	// struct LDKCResult_DelayedPaymentOutputDescriptorDecodeErrorZ CResult_DelayedPaymentOutputDescriptorDecodeErrorZ_ok(struct LDKDelayedPaymentOutputDescriptor o);
	public static native long CResult_DelayedPaymentOutputDescriptorDecodeErrorZ_ok(long o);
	// struct LDKCResult_DelayedPaymentOutputDescriptorDecodeErrorZ CResult_DelayedPaymentOutputDescriptorDecodeErrorZ_err(struct LDKDecodeError e);
	public static native long CResult_DelayedPaymentOutputDescriptorDecodeErrorZ_err(long e);
	// bool CResult_DelayedPaymentOutputDescriptorDecodeErrorZ_is_ok(const struct LDKCResult_DelayedPaymentOutputDescriptorDecodeErrorZ *NONNULL_PTR o);
	public static native boolean CResult_DelayedPaymentOutputDescriptorDecodeErrorZ_is_ok(long o);
	// void CResult_DelayedPaymentOutputDescriptorDecodeErrorZ_free(struct LDKCResult_DelayedPaymentOutputDescriptorDecodeErrorZ _res);
	public static native void CResult_DelayedPaymentOutputDescriptorDecodeErrorZ_free(long _res);
	// uintptr_t CResult_DelayedPaymentOutputDescriptorDecodeErrorZ_clone_ptr(LDKCResult_DelayedPaymentOutputDescriptorDecodeErrorZ *NONNULL_PTR arg);
	public static native long CResult_DelayedPaymentOutputDescriptorDecodeErrorZ_clone_ptr(long arg);
	// struct LDKCResult_DelayedPaymentOutputDescriptorDecodeErrorZ CResult_DelayedPaymentOutputDescriptorDecodeErrorZ_clone(const struct LDKCResult_DelayedPaymentOutputDescriptorDecodeErrorZ *NONNULL_PTR orig);
	public static native long CResult_DelayedPaymentOutputDescriptorDecodeErrorZ_clone(long orig);
	// struct LDKCResult_StaticPaymentOutputDescriptorDecodeErrorZ CResult_StaticPaymentOutputDescriptorDecodeErrorZ_ok(struct LDKStaticPaymentOutputDescriptor o);
	public static native long CResult_StaticPaymentOutputDescriptorDecodeErrorZ_ok(long o);
	// struct LDKCResult_StaticPaymentOutputDescriptorDecodeErrorZ CResult_StaticPaymentOutputDescriptorDecodeErrorZ_err(struct LDKDecodeError e);
	public static native long CResult_StaticPaymentOutputDescriptorDecodeErrorZ_err(long e);
	// bool CResult_StaticPaymentOutputDescriptorDecodeErrorZ_is_ok(const struct LDKCResult_StaticPaymentOutputDescriptorDecodeErrorZ *NONNULL_PTR o);
	public static native boolean CResult_StaticPaymentOutputDescriptorDecodeErrorZ_is_ok(long o);
	// void CResult_StaticPaymentOutputDescriptorDecodeErrorZ_free(struct LDKCResult_StaticPaymentOutputDescriptorDecodeErrorZ _res);
	public static native void CResult_StaticPaymentOutputDescriptorDecodeErrorZ_free(long _res);
	// uintptr_t CResult_StaticPaymentOutputDescriptorDecodeErrorZ_clone_ptr(LDKCResult_StaticPaymentOutputDescriptorDecodeErrorZ *NONNULL_PTR arg);
	public static native long CResult_StaticPaymentOutputDescriptorDecodeErrorZ_clone_ptr(long arg);
	// struct LDKCResult_StaticPaymentOutputDescriptorDecodeErrorZ CResult_StaticPaymentOutputDescriptorDecodeErrorZ_clone(const struct LDKCResult_StaticPaymentOutputDescriptorDecodeErrorZ *NONNULL_PTR orig);
	public static native long CResult_StaticPaymentOutputDescriptorDecodeErrorZ_clone(long orig);
	// struct LDKCResult_SpendableOutputDescriptorDecodeErrorZ CResult_SpendableOutputDescriptorDecodeErrorZ_ok(struct LDKSpendableOutputDescriptor o);
	public static native long CResult_SpendableOutputDescriptorDecodeErrorZ_ok(long o);
	// struct LDKCResult_SpendableOutputDescriptorDecodeErrorZ CResult_SpendableOutputDescriptorDecodeErrorZ_err(struct LDKDecodeError e);
	public static native long CResult_SpendableOutputDescriptorDecodeErrorZ_err(long e);
	// bool CResult_SpendableOutputDescriptorDecodeErrorZ_is_ok(const struct LDKCResult_SpendableOutputDescriptorDecodeErrorZ *NONNULL_PTR o);
	public static native boolean CResult_SpendableOutputDescriptorDecodeErrorZ_is_ok(long o);
	// void CResult_SpendableOutputDescriptorDecodeErrorZ_free(struct LDKCResult_SpendableOutputDescriptorDecodeErrorZ _res);
	public static native void CResult_SpendableOutputDescriptorDecodeErrorZ_free(long _res);
	// uintptr_t CResult_SpendableOutputDescriptorDecodeErrorZ_clone_ptr(LDKCResult_SpendableOutputDescriptorDecodeErrorZ *NONNULL_PTR arg);
	public static native long CResult_SpendableOutputDescriptorDecodeErrorZ_clone_ptr(long arg);
	// struct LDKCResult_SpendableOutputDescriptorDecodeErrorZ CResult_SpendableOutputDescriptorDecodeErrorZ_clone(const struct LDKCResult_SpendableOutputDescriptorDecodeErrorZ *NONNULL_PTR orig);
	public static native long CResult_SpendableOutputDescriptorDecodeErrorZ_clone(long orig);
	// void CVec_PaymentPreimageZ_free(struct LDKCVec_PaymentPreimageZ _res);
	public static native void CVec_PaymentPreimageZ_free(byte[][] _res);
	// uintptr_t C2Tuple_SignatureCVec_SignatureZZ_clone_ptr(LDKC2Tuple_SignatureCVec_SignatureZZ *NONNULL_PTR arg);
	public static native long C2Tuple_SignatureCVec_SignatureZZ_clone_ptr(long arg);
	// struct LDKC2Tuple_SignatureCVec_SignatureZZ C2Tuple_SignatureCVec_SignatureZZ_clone(const struct LDKC2Tuple_SignatureCVec_SignatureZZ *NONNULL_PTR orig);
	public static native long C2Tuple_SignatureCVec_SignatureZZ_clone(long orig);
	// struct LDKC2Tuple_SignatureCVec_SignatureZZ C2Tuple_SignatureCVec_SignatureZZ_new(struct LDKSignature a, struct LDKCVec_SignatureZ b);
	public static native long C2Tuple_SignatureCVec_SignatureZZ_new(byte[] a, byte[][] b);
	// void C2Tuple_SignatureCVec_SignatureZZ_free(struct LDKC2Tuple_SignatureCVec_SignatureZZ _res);
	public static native void C2Tuple_SignatureCVec_SignatureZZ_free(long _res);
	// struct LDKCResult_C2Tuple_SignatureCVec_SignatureZZNoneZ CResult_C2Tuple_SignatureCVec_SignatureZZNoneZ_ok(struct LDKC2Tuple_SignatureCVec_SignatureZZ o);
	public static native long CResult_C2Tuple_SignatureCVec_SignatureZZNoneZ_ok(long o);
	// struct LDKCResult_C2Tuple_SignatureCVec_SignatureZZNoneZ CResult_C2Tuple_SignatureCVec_SignatureZZNoneZ_err(void);
	public static native long CResult_C2Tuple_SignatureCVec_SignatureZZNoneZ_err();
	// bool CResult_C2Tuple_SignatureCVec_SignatureZZNoneZ_is_ok(const struct LDKCResult_C2Tuple_SignatureCVec_SignatureZZNoneZ *NONNULL_PTR o);
	public static native boolean CResult_C2Tuple_SignatureCVec_SignatureZZNoneZ_is_ok(long o);
	// void CResult_C2Tuple_SignatureCVec_SignatureZZNoneZ_free(struct LDKCResult_C2Tuple_SignatureCVec_SignatureZZNoneZ _res);
	public static native void CResult_C2Tuple_SignatureCVec_SignatureZZNoneZ_free(long _res);
	// uintptr_t CResult_C2Tuple_SignatureCVec_SignatureZZNoneZ_clone_ptr(LDKCResult_C2Tuple_SignatureCVec_SignatureZZNoneZ *NONNULL_PTR arg);
	public static native long CResult_C2Tuple_SignatureCVec_SignatureZZNoneZ_clone_ptr(long arg);
	// struct LDKCResult_C2Tuple_SignatureCVec_SignatureZZNoneZ CResult_C2Tuple_SignatureCVec_SignatureZZNoneZ_clone(const struct LDKCResult_C2Tuple_SignatureCVec_SignatureZZNoneZ *NONNULL_PTR orig);
	public static native long CResult_C2Tuple_SignatureCVec_SignatureZZNoneZ_clone(long orig);
	// struct LDKCResult_SignatureNoneZ CResult_SignatureNoneZ_ok(struct LDKSignature o);
	public static native long CResult_SignatureNoneZ_ok(byte[] o);
	// struct LDKCResult_SignatureNoneZ CResult_SignatureNoneZ_err(void);
	public static native long CResult_SignatureNoneZ_err();
	// bool CResult_SignatureNoneZ_is_ok(const struct LDKCResult_SignatureNoneZ *NONNULL_PTR o);
	public static native boolean CResult_SignatureNoneZ_is_ok(long o);
	// void CResult_SignatureNoneZ_free(struct LDKCResult_SignatureNoneZ _res);
	public static native void CResult_SignatureNoneZ_free(long _res);
	// uintptr_t CResult_SignatureNoneZ_clone_ptr(LDKCResult_SignatureNoneZ *NONNULL_PTR arg);
	public static native long CResult_SignatureNoneZ_clone_ptr(long arg);
	// struct LDKCResult_SignatureNoneZ CResult_SignatureNoneZ_clone(const struct LDKCResult_SignatureNoneZ *NONNULL_PTR orig);
	public static native long CResult_SignatureNoneZ_clone(long orig);
	// uintptr_t C2Tuple_SignatureSignatureZ_clone_ptr(LDKC2Tuple_SignatureSignatureZ *NONNULL_PTR arg);
	public static native long C2Tuple_SignatureSignatureZ_clone_ptr(long arg);
	// struct LDKC2Tuple_SignatureSignatureZ C2Tuple_SignatureSignatureZ_clone(const struct LDKC2Tuple_SignatureSignatureZ *NONNULL_PTR orig);
	public static native long C2Tuple_SignatureSignatureZ_clone(long orig);
	// struct LDKC2Tuple_SignatureSignatureZ C2Tuple_SignatureSignatureZ_new(struct LDKSignature a, struct LDKSignature b);
	public static native long C2Tuple_SignatureSignatureZ_new(byte[] a, byte[] b);
	// void C2Tuple_SignatureSignatureZ_free(struct LDKC2Tuple_SignatureSignatureZ _res);
	public static native void C2Tuple_SignatureSignatureZ_free(long _res);
	// struct LDKCResult_C2Tuple_SignatureSignatureZNoneZ CResult_C2Tuple_SignatureSignatureZNoneZ_ok(struct LDKC2Tuple_SignatureSignatureZ o);
	public static native long CResult_C2Tuple_SignatureSignatureZNoneZ_ok(long o);
	// struct LDKCResult_C2Tuple_SignatureSignatureZNoneZ CResult_C2Tuple_SignatureSignatureZNoneZ_err(void);
	public static native long CResult_C2Tuple_SignatureSignatureZNoneZ_err();
	// bool CResult_C2Tuple_SignatureSignatureZNoneZ_is_ok(const struct LDKCResult_C2Tuple_SignatureSignatureZNoneZ *NONNULL_PTR o);
	public static native boolean CResult_C2Tuple_SignatureSignatureZNoneZ_is_ok(long o);
	// void CResult_C2Tuple_SignatureSignatureZNoneZ_free(struct LDKCResult_C2Tuple_SignatureSignatureZNoneZ _res);
	public static native void CResult_C2Tuple_SignatureSignatureZNoneZ_free(long _res);
	// uintptr_t CResult_C2Tuple_SignatureSignatureZNoneZ_clone_ptr(LDKCResult_C2Tuple_SignatureSignatureZNoneZ *NONNULL_PTR arg);
	public static native long CResult_C2Tuple_SignatureSignatureZNoneZ_clone_ptr(long arg);
	// struct LDKCResult_C2Tuple_SignatureSignatureZNoneZ CResult_C2Tuple_SignatureSignatureZNoneZ_clone(const struct LDKCResult_C2Tuple_SignatureSignatureZNoneZ *NONNULL_PTR orig);
	public static native long CResult_C2Tuple_SignatureSignatureZNoneZ_clone(long orig);
	// struct LDKCResult_SecretKeyNoneZ CResult_SecretKeyNoneZ_ok(struct LDKSecretKey o);
	public static native long CResult_SecretKeyNoneZ_ok(byte[] o);
	// struct LDKCResult_SecretKeyNoneZ CResult_SecretKeyNoneZ_err(void);
	public static native long CResult_SecretKeyNoneZ_err();
	// bool CResult_SecretKeyNoneZ_is_ok(const struct LDKCResult_SecretKeyNoneZ *NONNULL_PTR o);
	public static native boolean CResult_SecretKeyNoneZ_is_ok(long o);
	// void CResult_SecretKeyNoneZ_free(struct LDKCResult_SecretKeyNoneZ _res);
	public static native void CResult_SecretKeyNoneZ_free(long _res);
	// uintptr_t CResult_SecretKeyNoneZ_clone_ptr(LDKCResult_SecretKeyNoneZ *NONNULL_PTR arg);
	public static native long CResult_SecretKeyNoneZ_clone_ptr(long arg);
	// struct LDKCResult_SecretKeyNoneZ CResult_SecretKeyNoneZ_clone(const struct LDKCResult_SecretKeyNoneZ *NONNULL_PTR orig);
	public static native long CResult_SecretKeyNoneZ_clone(long orig);
	// struct LDKCResult_SignDecodeErrorZ CResult_SignDecodeErrorZ_ok(struct LDKSign o);
	public static native long CResult_SignDecodeErrorZ_ok(long o);
	// struct LDKCResult_SignDecodeErrorZ CResult_SignDecodeErrorZ_err(struct LDKDecodeError e);
	public static native long CResult_SignDecodeErrorZ_err(long e);
	// bool CResult_SignDecodeErrorZ_is_ok(const struct LDKCResult_SignDecodeErrorZ *NONNULL_PTR o);
	public static native boolean CResult_SignDecodeErrorZ_is_ok(long o);
	// void CResult_SignDecodeErrorZ_free(struct LDKCResult_SignDecodeErrorZ _res);
	public static native void CResult_SignDecodeErrorZ_free(long _res);
	// uintptr_t CResult_SignDecodeErrorZ_clone_ptr(LDKCResult_SignDecodeErrorZ *NONNULL_PTR arg);
	public static native long CResult_SignDecodeErrorZ_clone_ptr(long arg);
	// struct LDKCResult_SignDecodeErrorZ CResult_SignDecodeErrorZ_clone(const struct LDKCResult_SignDecodeErrorZ *NONNULL_PTR orig);
	public static native long CResult_SignDecodeErrorZ_clone(long orig);
	// void CVec_u5Z_free(struct LDKCVec_u5Z _res);
	public static native void CVec_u5Z_free(byte[] _res);
	// struct LDKCResult_RecoverableSignatureNoneZ CResult_RecoverableSignatureNoneZ_ok(struct LDKRecoverableSignature o);
	public static native long CResult_RecoverableSignatureNoneZ_ok(byte[] o);
	// struct LDKCResult_RecoverableSignatureNoneZ CResult_RecoverableSignatureNoneZ_err(void);
	public static native long CResult_RecoverableSignatureNoneZ_err();
	// bool CResult_RecoverableSignatureNoneZ_is_ok(const struct LDKCResult_RecoverableSignatureNoneZ *NONNULL_PTR o);
	public static native boolean CResult_RecoverableSignatureNoneZ_is_ok(long o);
	// void CResult_RecoverableSignatureNoneZ_free(struct LDKCResult_RecoverableSignatureNoneZ _res);
	public static native void CResult_RecoverableSignatureNoneZ_free(long _res);
	// uintptr_t CResult_RecoverableSignatureNoneZ_clone_ptr(LDKCResult_RecoverableSignatureNoneZ *NONNULL_PTR arg);
	public static native long CResult_RecoverableSignatureNoneZ_clone_ptr(long arg);
	// struct LDKCResult_RecoverableSignatureNoneZ CResult_RecoverableSignatureNoneZ_clone(const struct LDKCResult_RecoverableSignatureNoneZ *NONNULL_PTR orig);
	public static native long CResult_RecoverableSignatureNoneZ_clone(long orig);
	// void CVec_u8Z_free(struct LDKCVec_u8Z _res);
	public static native void CVec_u8Z_free(byte[] _res);
	// void CVec_CVec_u8ZZ_free(struct LDKCVec_CVec_u8ZZ _res);
	public static native void CVec_CVec_u8ZZ_free(byte[][] _res);
	// struct LDKCResult_CVec_CVec_u8ZZNoneZ CResult_CVec_CVec_u8ZZNoneZ_ok(struct LDKCVec_CVec_u8ZZ o);
	public static native long CResult_CVec_CVec_u8ZZNoneZ_ok(byte[][] o);
	// struct LDKCResult_CVec_CVec_u8ZZNoneZ CResult_CVec_CVec_u8ZZNoneZ_err(void);
	public static native long CResult_CVec_CVec_u8ZZNoneZ_err();
	// bool CResult_CVec_CVec_u8ZZNoneZ_is_ok(const struct LDKCResult_CVec_CVec_u8ZZNoneZ *NONNULL_PTR o);
	public static native boolean CResult_CVec_CVec_u8ZZNoneZ_is_ok(long o);
	// void CResult_CVec_CVec_u8ZZNoneZ_free(struct LDKCResult_CVec_CVec_u8ZZNoneZ _res);
	public static native void CResult_CVec_CVec_u8ZZNoneZ_free(long _res);
	// uintptr_t CResult_CVec_CVec_u8ZZNoneZ_clone_ptr(LDKCResult_CVec_CVec_u8ZZNoneZ *NONNULL_PTR arg);
	public static native long CResult_CVec_CVec_u8ZZNoneZ_clone_ptr(long arg);
	// struct LDKCResult_CVec_CVec_u8ZZNoneZ CResult_CVec_CVec_u8ZZNoneZ_clone(const struct LDKCResult_CVec_CVec_u8ZZNoneZ *NONNULL_PTR orig);
	public static native long CResult_CVec_CVec_u8ZZNoneZ_clone(long orig);
	// struct LDKCResult_InMemorySignerDecodeErrorZ CResult_InMemorySignerDecodeErrorZ_ok(struct LDKInMemorySigner o);
	public static native long CResult_InMemorySignerDecodeErrorZ_ok(long o);
	// struct LDKCResult_InMemorySignerDecodeErrorZ CResult_InMemorySignerDecodeErrorZ_err(struct LDKDecodeError e);
	public static native long CResult_InMemorySignerDecodeErrorZ_err(long e);
	// bool CResult_InMemorySignerDecodeErrorZ_is_ok(const struct LDKCResult_InMemorySignerDecodeErrorZ *NONNULL_PTR o);
	public static native boolean CResult_InMemorySignerDecodeErrorZ_is_ok(long o);
	// void CResult_InMemorySignerDecodeErrorZ_free(struct LDKCResult_InMemorySignerDecodeErrorZ _res);
	public static native void CResult_InMemorySignerDecodeErrorZ_free(long _res);
	// uintptr_t CResult_InMemorySignerDecodeErrorZ_clone_ptr(LDKCResult_InMemorySignerDecodeErrorZ *NONNULL_PTR arg);
	public static native long CResult_InMemorySignerDecodeErrorZ_clone_ptr(long arg);
	// struct LDKCResult_InMemorySignerDecodeErrorZ CResult_InMemorySignerDecodeErrorZ_clone(const struct LDKCResult_InMemorySignerDecodeErrorZ *NONNULL_PTR orig);
	public static native long CResult_InMemorySignerDecodeErrorZ_clone(long orig);
	// void CVec_TxOutZ_free(struct LDKCVec_TxOutZ _res);
	public static native void CVec_TxOutZ_free(long[] _res);
	// struct LDKCResult_TransactionNoneZ CResult_TransactionNoneZ_ok(struct LDKTransaction o);
	public static native long CResult_TransactionNoneZ_ok(byte[] o);
	// struct LDKCResult_TransactionNoneZ CResult_TransactionNoneZ_err(void);
	public static native long CResult_TransactionNoneZ_err();
	// bool CResult_TransactionNoneZ_is_ok(const struct LDKCResult_TransactionNoneZ *NONNULL_PTR o);
	public static native boolean CResult_TransactionNoneZ_is_ok(long o);
	// void CResult_TransactionNoneZ_free(struct LDKCResult_TransactionNoneZ _res);
	public static native void CResult_TransactionNoneZ_free(long _res);
	// uintptr_t CResult_TransactionNoneZ_clone_ptr(LDKCResult_TransactionNoneZ *NONNULL_PTR arg);
	public static native long CResult_TransactionNoneZ_clone_ptr(long arg);
	// struct LDKCResult_TransactionNoneZ CResult_TransactionNoneZ_clone(const struct LDKCResult_TransactionNoneZ *NONNULL_PTR orig);
	public static native long CResult_TransactionNoneZ_clone(long orig);
	// uintptr_t C2Tuple_BlockHashChannelMonitorZ_clone_ptr(LDKC2Tuple_BlockHashChannelMonitorZ *NONNULL_PTR arg);
	public static native long C2Tuple_BlockHashChannelMonitorZ_clone_ptr(long arg);
	// struct LDKC2Tuple_BlockHashChannelMonitorZ C2Tuple_BlockHashChannelMonitorZ_clone(const struct LDKC2Tuple_BlockHashChannelMonitorZ *NONNULL_PTR orig);
	public static native long C2Tuple_BlockHashChannelMonitorZ_clone(long orig);
	// struct LDKC2Tuple_BlockHashChannelMonitorZ C2Tuple_BlockHashChannelMonitorZ_new(struct LDKThirtyTwoBytes a, struct LDKChannelMonitor b);
	public static native long C2Tuple_BlockHashChannelMonitorZ_new(byte[] a, long b);
	// void C2Tuple_BlockHashChannelMonitorZ_free(struct LDKC2Tuple_BlockHashChannelMonitorZ _res);
	public static native void C2Tuple_BlockHashChannelMonitorZ_free(long _res);
	// void CVec_C2Tuple_BlockHashChannelMonitorZZ_free(struct LDKCVec_C2Tuple_BlockHashChannelMonitorZZ _res);
	public static native void CVec_C2Tuple_BlockHashChannelMonitorZZ_free(long[] _res);
	// struct LDKCResult_CVec_C2Tuple_BlockHashChannelMonitorZZErrorZ CResult_CVec_C2Tuple_BlockHashChannelMonitorZZErrorZ_ok(struct LDKCVec_C2Tuple_BlockHashChannelMonitorZZ o);
	public static native long CResult_CVec_C2Tuple_BlockHashChannelMonitorZZErrorZ_ok(long[] o);
	// struct LDKCResult_CVec_C2Tuple_BlockHashChannelMonitorZZErrorZ CResult_CVec_C2Tuple_BlockHashChannelMonitorZZErrorZ_err(enum LDKIOError e);
	public static native long CResult_CVec_C2Tuple_BlockHashChannelMonitorZZErrorZ_err(IOError e);
	// bool CResult_CVec_C2Tuple_BlockHashChannelMonitorZZErrorZ_is_ok(const struct LDKCResult_CVec_C2Tuple_BlockHashChannelMonitorZZErrorZ *NONNULL_PTR o);
	public static native boolean CResult_CVec_C2Tuple_BlockHashChannelMonitorZZErrorZ_is_ok(long o);
	// void CResult_CVec_C2Tuple_BlockHashChannelMonitorZZErrorZ_free(struct LDKCResult_CVec_C2Tuple_BlockHashChannelMonitorZZErrorZ _res);
	public static native void CResult_CVec_C2Tuple_BlockHashChannelMonitorZZErrorZ_free(long _res);
	// uintptr_t CResult_CVec_C2Tuple_BlockHashChannelMonitorZZErrorZ_clone_ptr(LDKCResult_CVec_C2Tuple_BlockHashChannelMonitorZZErrorZ *NONNULL_PTR arg);
	public static native long CResult_CVec_C2Tuple_BlockHashChannelMonitorZZErrorZ_clone_ptr(long arg);
	// struct LDKCResult_CVec_C2Tuple_BlockHashChannelMonitorZZErrorZ CResult_CVec_C2Tuple_BlockHashChannelMonitorZZErrorZ_clone(const struct LDKCResult_CVec_C2Tuple_BlockHashChannelMonitorZZErrorZ *NONNULL_PTR orig);
	public static native long CResult_CVec_C2Tuple_BlockHashChannelMonitorZZErrorZ_clone(long orig);
	// struct LDKCOption_u16Z COption_u16Z_some(uint16_t o);
	public static native long COption_u16Z_some(short o);
	// struct LDKCOption_u16Z COption_u16Z_none(void);
	public static native long COption_u16Z_none();
	// void COption_u16Z_free(struct LDKCOption_u16Z _res);
	public static native void COption_u16Z_free(long _res);
	// uintptr_t COption_u16Z_clone_ptr(LDKCOption_u16Z *NONNULL_PTR arg);
	public static native long COption_u16Z_clone_ptr(long arg);
	// struct LDKCOption_u16Z COption_u16Z_clone(const struct LDKCOption_u16Z *NONNULL_PTR orig);
	public static native long COption_u16Z_clone(long orig);
	// struct LDKCResult_NoneAPIErrorZ CResult_NoneAPIErrorZ_ok(void);
	public static native long CResult_NoneAPIErrorZ_ok();
	// struct LDKCResult_NoneAPIErrorZ CResult_NoneAPIErrorZ_err(struct LDKAPIError e);
	public static native long CResult_NoneAPIErrorZ_err(long e);
	// bool CResult_NoneAPIErrorZ_is_ok(const struct LDKCResult_NoneAPIErrorZ *NONNULL_PTR o);
	public static native boolean CResult_NoneAPIErrorZ_is_ok(long o);
	// void CResult_NoneAPIErrorZ_free(struct LDKCResult_NoneAPIErrorZ _res);
	public static native void CResult_NoneAPIErrorZ_free(long _res);
	// uintptr_t CResult_NoneAPIErrorZ_clone_ptr(LDKCResult_NoneAPIErrorZ *NONNULL_PTR arg);
	public static native long CResult_NoneAPIErrorZ_clone_ptr(long arg);
	// struct LDKCResult_NoneAPIErrorZ CResult_NoneAPIErrorZ_clone(const struct LDKCResult_NoneAPIErrorZ *NONNULL_PTR orig);
	public static native long CResult_NoneAPIErrorZ_clone(long orig);
	// void CVec_CResult_NoneAPIErrorZZ_free(struct LDKCVec_CResult_NoneAPIErrorZZ _res);
	public static native void CVec_CResult_NoneAPIErrorZZ_free(long[] _res);
	// void CVec_APIErrorZ_free(struct LDKCVec_APIErrorZ _res);
	public static native void CVec_APIErrorZ_free(long[] _res);
	// struct LDKCResult__u832APIErrorZ CResult__u832APIErrorZ_ok(struct LDKThirtyTwoBytes o);
	public static native long CResult__u832APIErrorZ_ok(byte[] o);
	// struct LDKCResult__u832APIErrorZ CResult__u832APIErrorZ_err(struct LDKAPIError e);
	public static native long CResult__u832APIErrorZ_err(long e);
	// bool CResult__u832APIErrorZ_is_ok(const struct LDKCResult__u832APIErrorZ *NONNULL_PTR o);
	public static native boolean CResult__u832APIErrorZ_is_ok(long o);
	// void CResult__u832APIErrorZ_free(struct LDKCResult__u832APIErrorZ _res);
	public static native void CResult__u832APIErrorZ_free(long _res);
	// uintptr_t CResult__u832APIErrorZ_clone_ptr(LDKCResult__u832APIErrorZ *NONNULL_PTR arg);
	public static native long CResult__u832APIErrorZ_clone_ptr(long arg);
	// struct LDKCResult__u832APIErrorZ CResult__u832APIErrorZ_clone(const struct LDKCResult__u832APIErrorZ *NONNULL_PTR orig);
	public static native long CResult__u832APIErrorZ_clone(long orig);
	// struct LDKCResult_PaymentIdPaymentSendFailureZ CResult_PaymentIdPaymentSendFailureZ_ok(struct LDKThirtyTwoBytes o);
	public static native long CResult_PaymentIdPaymentSendFailureZ_ok(byte[] o);
	// struct LDKCResult_PaymentIdPaymentSendFailureZ CResult_PaymentIdPaymentSendFailureZ_err(struct LDKPaymentSendFailure e);
	public static native long CResult_PaymentIdPaymentSendFailureZ_err(long e);
	// bool CResult_PaymentIdPaymentSendFailureZ_is_ok(const struct LDKCResult_PaymentIdPaymentSendFailureZ *NONNULL_PTR o);
	public static native boolean CResult_PaymentIdPaymentSendFailureZ_is_ok(long o);
	// void CResult_PaymentIdPaymentSendFailureZ_free(struct LDKCResult_PaymentIdPaymentSendFailureZ _res);
	public static native void CResult_PaymentIdPaymentSendFailureZ_free(long _res);
	// uintptr_t CResult_PaymentIdPaymentSendFailureZ_clone_ptr(LDKCResult_PaymentIdPaymentSendFailureZ *NONNULL_PTR arg);
	public static native long CResult_PaymentIdPaymentSendFailureZ_clone_ptr(long arg);
	// struct LDKCResult_PaymentIdPaymentSendFailureZ CResult_PaymentIdPaymentSendFailureZ_clone(const struct LDKCResult_PaymentIdPaymentSendFailureZ *NONNULL_PTR orig);
	public static native long CResult_PaymentIdPaymentSendFailureZ_clone(long orig);
	// struct LDKCResult_NonePaymentSendFailureZ CResult_NonePaymentSendFailureZ_ok(void);
	public static native long CResult_NonePaymentSendFailureZ_ok();
	// struct LDKCResult_NonePaymentSendFailureZ CResult_NonePaymentSendFailureZ_err(struct LDKPaymentSendFailure e);
	public static native long CResult_NonePaymentSendFailureZ_err(long e);
	// bool CResult_NonePaymentSendFailureZ_is_ok(const struct LDKCResult_NonePaymentSendFailureZ *NONNULL_PTR o);
	public static native boolean CResult_NonePaymentSendFailureZ_is_ok(long o);
	// void CResult_NonePaymentSendFailureZ_free(struct LDKCResult_NonePaymentSendFailureZ _res);
	public static native void CResult_NonePaymentSendFailureZ_free(long _res);
	// uintptr_t CResult_NonePaymentSendFailureZ_clone_ptr(LDKCResult_NonePaymentSendFailureZ *NONNULL_PTR arg);
	public static native long CResult_NonePaymentSendFailureZ_clone_ptr(long arg);
	// struct LDKCResult_NonePaymentSendFailureZ CResult_NonePaymentSendFailureZ_clone(const struct LDKCResult_NonePaymentSendFailureZ *NONNULL_PTR orig);
	public static native long CResult_NonePaymentSendFailureZ_clone(long orig);
	// uintptr_t C2Tuple_PaymentHashPaymentIdZ_clone_ptr(LDKC2Tuple_PaymentHashPaymentIdZ *NONNULL_PTR arg);
	public static native long C2Tuple_PaymentHashPaymentIdZ_clone_ptr(long arg);
	// struct LDKC2Tuple_PaymentHashPaymentIdZ C2Tuple_PaymentHashPaymentIdZ_clone(const struct LDKC2Tuple_PaymentHashPaymentIdZ *NONNULL_PTR orig);
	public static native long C2Tuple_PaymentHashPaymentIdZ_clone(long orig);
	// struct LDKC2Tuple_PaymentHashPaymentIdZ C2Tuple_PaymentHashPaymentIdZ_new(struct LDKThirtyTwoBytes a, struct LDKThirtyTwoBytes b);
	public static native long C2Tuple_PaymentHashPaymentIdZ_new(byte[] a, byte[] b);
	// void C2Tuple_PaymentHashPaymentIdZ_free(struct LDKC2Tuple_PaymentHashPaymentIdZ _res);
	public static native void C2Tuple_PaymentHashPaymentIdZ_free(long _res);
	// struct LDKCResult_C2Tuple_PaymentHashPaymentIdZPaymentSendFailureZ CResult_C2Tuple_PaymentHashPaymentIdZPaymentSendFailureZ_ok(struct LDKC2Tuple_PaymentHashPaymentIdZ o);
	public static native long CResult_C2Tuple_PaymentHashPaymentIdZPaymentSendFailureZ_ok(long o);
	// struct LDKCResult_C2Tuple_PaymentHashPaymentIdZPaymentSendFailureZ CResult_C2Tuple_PaymentHashPaymentIdZPaymentSendFailureZ_err(struct LDKPaymentSendFailure e);
	public static native long CResult_C2Tuple_PaymentHashPaymentIdZPaymentSendFailureZ_err(long e);
	// bool CResult_C2Tuple_PaymentHashPaymentIdZPaymentSendFailureZ_is_ok(const struct LDKCResult_C2Tuple_PaymentHashPaymentIdZPaymentSendFailureZ *NONNULL_PTR o);
	public static native boolean CResult_C2Tuple_PaymentHashPaymentIdZPaymentSendFailureZ_is_ok(long o);
	// void CResult_C2Tuple_PaymentHashPaymentIdZPaymentSendFailureZ_free(struct LDKCResult_C2Tuple_PaymentHashPaymentIdZPaymentSendFailureZ _res);
	public static native void CResult_C2Tuple_PaymentHashPaymentIdZPaymentSendFailureZ_free(long _res);
	// uintptr_t CResult_C2Tuple_PaymentHashPaymentIdZPaymentSendFailureZ_clone_ptr(LDKCResult_C2Tuple_PaymentHashPaymentIdZPaymentSendFailureZ *NONNULL_PTR arg);
	public static native long CResult_C2Tuple_PaymentHashPaymentIdZPaymentSendFailureZ_clone_ptr(long arg);
	// struct LDKCResult_C2Tuple_PaymentHashPaymentIdZPaymentSendFailureZ CResult_C2Tuple_PaymentHashPaymentIdZPaymentSendFailureZ_clone(const struct LDKCResult_C2Tuple_PaymentHashPaymentIdZPaymentSendFailureZ *NONNULL_PTR orig);
	public static native long CResult_C2Tuple_PaymentHashPaymentIdZPaymentSendFailureZ_clone(long orig);
	// void CVec_NetAddressZ_free(struct LDKCVec_NetAddressZ _res);
	public static native void CVec_NetAddressZ_free(long[] _res);
	// uintptr_t C2Tuple_PaymentHashPaymentSecretZ_clone_ptr(LDKC2Tuple_PaymentHashPaymentSecretZ *NONNULL_PTR arg);
	public static native long C2Tuple_PaymentHashPaymentSecretZ_clone_ptr(long arg);
	// struct LDKC2Tuple_PaymentHashPaymentSecretZ C2Tuple_PaymentHashPaymentSecretZ_clone(const struct LDKC2Tuple_PaymentHashPaymentSecretZ *NONNULL_PTR orig);
	public static native long C2Tuple_PaymentHashPaymentSecretZ_clone(long orig);
	// struct LDKC2Tuple_PaymentHashPaymentSecretZ C2Tuple_PaymentHashPaymentSecretZ_new(struct LDKThirtyTwoBytes a, struct LDKThirtyTwoBytes b);
	public static native long C2Tuple_PaymentHashPaymentSecretZ_new(byte[] a, byte[] b);
	// void C2Tuple_PaymentHashPaymentSecretZ_free(struct LDKC2Tuple_PaymentHashPaymentSecretZ _res);
	public static native void C2Tuple_PaymentHashPaymentSecretZ_free(long _res);
	// struct LDKCResult_C2Tuple_PaymentHashPaymentSecretZNoneZ CResult_C2Tuple_PaymentHashPaymentSecretZNoneZ_ok(struct LDKC2Tuple_PaymentHashPaymentSecretZ o);
	public static native long CResult_C2Tuple_PaymentHashPaymentSecretZNoneZ_ok(long o);
	// struct LDKCResult_C2Tuple_PaymentHashPaymentSecretZNoneZ CResult_C2Tuple_PaymentHashPaymentSecretZNoneZ_err(void);
	public static native long CResult_C2Tuple_PaymentHashPaymentSecretZNoneZ_err();
	// bool CResult_C2Tuple_PaymentHashPaymentSecretZNoneZ_is_ok(const struct LDKCResult_C2Tuple_PaymentHashPaymentSecretZNoneZ *NONNULL_PTR o);
	public static native boolean CResult_C2Tuple_PaymentHashPaymentSecretZNoneZ_is_ok(long o);
	// void CResult_C2Tuple_PaymentHashPaymentSecretZNoneZ_free(struct LDKCResult_C2Tuple_PaymentHashPaymentSecretZNoneZ _res);
	public static native void CResult_C2Tuple_PaymentHashPaymentSecretZNoneZ_free(long _res);
	// uintptr_t CResult_C2Tuple_PaymentHashPaymentSecretZNoneZ_clone_ptr(LDKCResult_C2Tuple_PaymentHashPaymentSecretZNoneZ *NONNULL_PTR arg);
	public static native long CResult_C2Tuple_PaymentHashPaymentSecretZNoneZ_clone_ptr(long arg);
	// struct LDKCResult_C2Tuple_PaymentHashPaymentSecretZNoneZ CResult_C2Tuple_PaymentHashPaymentSecretZNoneZ_clone(const struct LDKCResult_C2Tuple_PaymentHashPaymentSecretZNoneZ *NONNULL_PTR orig);
	public static native long CResult_C2Tuple_PaymentHashPaymentSecretZNoneZ_clone(long orig);
	// struct LDKCResult_C2Tuple_PaymentHashPaymentSecretZAPIErrorZ CResult_C2Tuple_PaymentHashPaymentSecretZAPIErrorZ_ok(struct LDKC2Tuple_PaymentHashPaymentSecretZ o);
	public static native long CResult_C2Tuple_PaymentHashPaymentSecretZAPIErrorZ_ok(long o);
	// struct LDKCResult_C2Tuple_PaymentHashPaymentSecretZAPIErrorZ CResult_C2Tuple_PaymentHashPaymentSecretZAPIErrorZ_err(struct LDKAPIError e);
	public static native long CResult_C2Tuple_PaymentHashPaymentSecretZAPIErrorZ_err(long e);
	// bool CResult_C2Tuple_PaymentHashPaymentSecretZAPIErrorZ_is_ok(const struct LDKCResult_C2Tuple_PaymentHashPaymentSecretZAPIErrorZ *NONNULL_PTR o);
	public static native boolean CResult_C2Tuple_PaymentHashPaymentSecretZAPIErrorZ_is_ok(long o);
	// void CResult_C2Tuple_PaymentHashPaymentSecretZAPIErrorZ_free(struct LDKCResult_C2Tuple_PaymentHashPaymentSecretZAPIErrorZ _res);
	public static native void CResult_C2Tuple_PaymentHashPaymentSecretZAPIErrorZ_free(long _res);
	// uintptr_t CResult_C2Tuple_PaymentHashPaymentSecretZAPIErrorZ_clone_ptr(LDKCResult_C2Tuple_PaymentHashPaymentSecretZAPIErrorZ *NONNULL_PTR arg);
	public static native long CResult_C2Tuple_PaymentHashPaymentSecretZAPIErrorZ_clone_ptr(long arg);
	// struct LDKCResult_C2Tuple_PaymentHashPaymentSecretZAPIErrorZ CResult_C2Tuple_PaymentHashPaymentSecretZAPIErrorZ_clone(const struct LDKCResult_C2Tuple_PaymentHashPaymentSecretZAPIErrorZ *NONNULL_PTR orig);
	public static native long CResult_C2Tuple_PaymentHashPaymentSecretZAPIErrorZ_clone(long orig);
	// struct LDKCResult_PaymentSecretNoneZ CResult_PaymentSecretNoneZ_ok(struct LDKThirtyTwoBytes o);
	public static native long CResult_PaymentSecretNoneZ_ok(byte[] o);
	// struct LDKCResult_PaymentSecretNoneZ CResult_PaymentSecretNoneZ_err(void);
	public static native long CResult_PaymentSecretNoneZ_err();
	// bool CResult_PaymentSecretNoneZ_is_ok(const struct LDKCResult_PaymentSecretNoneZ *NONNULL_PTR o);
	public static native boolean CResult_PaymentSecretNoneZ_is_ok(long o);
	// void CResult_PaymentSecretNoneZ_free(struct LDKCResult_PaymentSecretNoneZ _res);
	public static native void CResult_PaymentSecretNoneZ_free(long _res);
	// uintptr_t CResult_PaymentSecretNoneZ_clone_ptr(LDKCResult_PaymentSecretNoneZ *NONNULL_PTR arg);
	public static native long CResult_PaymentSecretNoneZ_clone_ptr(long arg);
	// struct LDKCResult_PaymentSecretNoneZ CResult_PaymentSecretNoneZ_clone(const struct LDKCResult_PaymentSecretNoneZ *NONNULL_PTR orig);
	public static native long CResult_PaymentSecretNoneZ_clone(long orig);
	// struct LDKCResult_PaymentSecretAPIErrorZ CResult_PaymentSecretAPIErrorZ_ok(struct LDKThirtyTwoBytes o);
	public static native long CResult_PaymentSecretAPIErrorZ_ok(byte[] o);
	// struct LDKCResult_PaymentSecretAPIErrorZ CResult_PaymentSecretAPIErrorZ_err(struct LDKAPIError e);
	public static native long CResult_PaymentSecretAPIErrorZ_err(long e);
	// bool CResult_PaymentSecretAPIErrorZ_is_ok(const struct LDKCResult_PaymentSecretAPIErrorZ *NONNULL_PTR o);
	public static native boolean CResult_PaymentSecretAPIErrorZ_is_ok(long o);
	// void CResult_PaymentSecretAPIErrorZ_free(struct LDKCResult_PaymentSecretAPIErrorZ _res);
	public static native void CResult_PaymentSecretAPIErrorZ_free(long _res);
	// uintptr_t CResult_PaymentSecretAPIErrorZ_clone_ptr(LDKCResult_PaymentSecretAPIErrorZ *NONNULL_PTR arg);
	public static native long CResult_PaymentSecretAPIErrorZ_clone_ptr(long arg);
	// struct LDKCResult_PaymentSecretAPIErrorZ CResult_PaymentSecretAPIErrorZ_clone(const struct LDKCResult_PaymentSecretAPIErrorZ *NONNULL_PTR orig);
	public static native long CResult_PaymentSecretAPIErrorZ_clone(long orig);
	// struct LDKCResult_PaymentPreimageAPIErrorZ CResult_PaymentPreimageAPIErrorZ_ok(struct LDKThirtyTwoBytes o);
	public static native long CResult_PaymentPreimageAPIErrorZ_ok(byte[] o);
	// struct LDKCResult_PaymentPreimageAPIErrorZ CResult_PaymentPreimageAPIErrorZ_err(struct LDKAPIError e);
	public static native long CResult_PaymentPreimageAPIErrorZ_err(long e);
	// bool CResult_PaymentPreimageAPIErrorZ_is_ok(const struct LDKCResult_PaymentPreimageAPIErrorZ *NONNULL_PTR o);
	public static native boolean CResult_PaymentPreimageAPIErrorZ_is_ok(long o);
	// void CResult_PaymentPreimageAPIErrorZ_free(struct LDKCResult_PaymentPreimageAPIErrorZ _res);
	public static native void CResult_PaymentPreimageAPIErrorZ_free(long _res);
	// uintptr_t CResult_PaymentPreimageAPIErrorZ_clone_ptr(LDKCResult_PaymentPreimageAPIErrorZ *NONNULL_PTR arg);
	public static native long CResult_PaymentPreimageAPIErrorZ_clone_ptr(long arg);
	// struct LDKCResult_PaymentPreimageAPIErrorZ CResult_PaymentPreimageAPIErrorZ_clone(const struct LDKCResult_PaymentPreimageAPIErrorZ *NONNULL_PTR orig);
	public static native long CResult_PaymentPreimageAPIErrorZ_clone(long orig);
	// struct LDKCResult_CounterpartyForwardingInfoDecodeErrorZ CResult_CounterpartyForwardingInfoDecodeErrorZ_ok(struct LDKCounterpartyForwardingInfo o);
	public static native long CResult_CounterpartyForwardingInfoDecodeErrorZ_ok(long o);
	// struct LDKCResult_CounterpartyForwardingInfoDecodeErrorZ CResult_CounterpartyForwardingInfoDecodeErrorZ_err(struct LDKDecodeError e);
	public static native long CResult_CounterpartyForwardingInfoDecodeErrorZ_err(long e);
	// bool CResult_CounterpartyForwardingInfoDecodeErrorZ_is_ok(const struct LDKCResult_CounterpartyForwardingInfoDecodeErrorZ *NONNULL_PTR o);
	public static native boolean CResult_CounterpartyForwardingInfoDecodeErrorZ_is_ok(long o);
	// void CResult_CounterpartyForwardingInfoDecodeErrorZ_free(struct LDKCResult_CounterpartyForwardingInfoDecodeErrorZ _res);
	public static native void CResult_CounterpartyForwardingInfoDecodeErrorZ_free(long _res);
	// uintptr_t CResult_CounterpartyForwardingInfoDecodeErrorZ_clone_ptr(LDKCResult_CounterpartyForwardingInfoDecodeErrorZ *NONNULL_PTR arg);
	public static native long CResult_CounterpartyForwardingInfoDecodeErrorZ_clone_ptr(long arg);
	// struct LDKCResult_CounterpartyForwardingInfoDecodeErrorZ CResult_CounterpartyForwardingInfoDecodeErrorZ_clone(const struct LDKCResult_CounterpartyForwardingInfoDecodeErrorZ *NONNULL_PTR orig);
	public static native long CResult_CounterpartyForwardingInfoDecodeErrorZ_clone(long orig);
	// struct LDKCResult_ChannelCounterpartyDecodeErrorZ CResult_ChannelCounterpartyDecodeErrorZ_ok(struct LDKChannelCounterparty o);
	public static native long CResult_ChannelCounterpartyDecodeErrorZ_ok(long o);
	// struct LDKCResult_ChannelCounterpartyDecodeErrorZ CResult_ChannelCounterpartyDecodeErrorZ_err(struct LDKDecodeError e);
	public static native long CResult_ChannelCounterpartyDecodeErrorZ_err(long e);
	// bool CResult_ChannelCounterpartyDecodeErrorZ_is_ok(const struct LDKCResult_ChannelCounterpartyDecodeErrorZ *NONNULL_PTR o);
	public static native boolean CResult_ChannelCounterpartyDecodeErrorZ_is_ok(long o);
	// void CResult_ChannelCounterpartyDecodeErrorZ_free(struct LDKCResult_ChannelCounterpartyDecodeErrorZ _res);
	public static native void CResult_ChannelCounterpartyDecodeErrorZ_free(long _res);
	// uintptr_t CResult_ChannelCounterpartyDecodeErrorZ_clone_ptr(LDKCResult_ChannelCounterpartyDecodeErrorZ *NONNULL_PTR arg);
	public static native long CResult_ChannelCounterpartyDecodeErrorZ_clone_ptr(long arg);
	// struct LDKCResult_ChannelCounterpartyDecodeErrorZ CResult_ChannelCounterpartyDecodeErrorZ_clone(const struct LDKCResult_ChannelCounterpartyDecodeErrorZ *NONNULL_PTR orig);
	public static native long CResult_ChannelCounterpartyDecodeErrorZ_clone(long orig);
	// struct LDKCResult_ChannelDetailsDecodeErrorZ CResult_ChannelDetailsDecodeErrorZ_ok(struct LDKChannelDetails o);
	public static native long CResult_ChannelDetailsDecodeErrorZ_ok(long o);
	// struct LDKCResult_ChannelDetailsDecodeErrorZ CResult_ChannelDetailsDecodeErrorZ_err(struct LDKDecodeError e);
	public static native long CResult_ChannelDetailsDecodeErrorZ_err(long e);
	// bool CResult_ChannelDetailsDecodeErrorZ_is_ok(const struct LDKCResult_ChannelDetailsDecodeErrorZ *NONNULL_PTR o);
	public static native boolean CResult_ChannelDetailsDecodeErrorZ_is_ok(long o);
	// void CResult_ChannelDetailsDecodeErrorZ_free(struct LDKCResult_ChannelDetailsDecodeErrorZ _res);
	public static native void CResult_ChannelDetailsDecodeErrorZ_free(long _res);
	// uintptr_t CResult_ChannelDetailsDecodeErrorZ_clone_ptr(LDKCResult_ChannelDetailsDecodeErrorZ *NONNULL_PTR arg);
	public static native long CResult_ChannelDetailsDecodeErrorZ_clone_ptr(long arg);
	// struct LDKCResult_ChannelDetailsDecodeErrorZ CResult_ChannelDetailsDecodeErrorZ_clone(const struct LDKCResult_ChannelDetailsDecodeErrorZ *NONNULL_PTR orig);
	public static native long CResult_ChannelDetailsDecodeErrorZ_clone(long orig);
	// struct LDKCResult_PhantomRouteHintsDecodeErrorZ CResult_PhantomRouteHintsDecodeErrorZ_ok(struct LDKPhantomRouteHints o);
	public static native long CResult_PhantomRouteHintsDecodeErrorZ_ok(long o);
	// struct LDKCResult_PhantomRouteHintsDecodeErrorZ CResult_PhantomRouteHintsDecodeErrorZ_err(struct LDKDecodeError e);
	public static native long CResult_PhantomRouteHintsDecodeErrorZ_err(long e);
	// bool CResult_PhantomRouteHintsDecodeErrorZ_is_ok(const struct LDKCResult_PhantomRouteHintsDecodeErrorZ *NONNULL_PTR o);
	public static native boolean CResult_PhantomRouteHintsDecodeErrorZ_is_ok(long o);
	// void CResult_PhantomRouteHintsDecodeErrorZ_free(struct LDKCResult_PhantomRouteHintsDecodeErrorZ _res);
	public static native void CResult_PhantomRouteHintsDecodeErrorZ_free(long _res);
	// uintptr_t CResult_PhantomRouteHintsDecodeErrorZ_clone_ptr(LDKCResult_PhantomRouteHintsDecodeErrorZ *NONNULL_PTR arg);
	public static native long CResult_PhantomRouteHintsDecodeErrorZ_clone_ptr(long arg);
	// struct LDKCResult_PhantomRouteHintsDecodeErrorZ CResult_PhantomRouteHintsDecodeErrorZ_clone(const struct LDKCResult_PhantomRouteHintsDecodeErrorZ *NONNULL_PTR orig);
	public static native long CResult_PhantomRouteHintsDecodeErrorZ_clone(long orig);
	// void CVec_ChannelMonitorZ_free(struct LDKCVec_ChannelMonitorZ _res);
	public static native void CVec_ChannelMonitorZ_free(long[] _res);
	// struct LDKC2Tuple_BlockHashChannelManagerZ C2Tuple_BlockHashChannelManagerZ_new(struct LDKThirtyTwoBytes a, struct LDKChannelManager b);
	public static native long C2Tuple_BlockHashChannelManagerZ_new(byte[] a, long b);
	// void C2Tuple_BlockHashChannelManagerZ_free(struct LDKC2Tuple_BlockHashChannelManagerZ _res);
	public static native void C2Tuple_BlockHashChannelManagerZ_free(long _res);
	// struct LDKCResult_C2Tuple_BlockHashChannelManagerZDecodeErrorZ CResult_C2Tuple_BlockHashChannelManagerZDecodeErrorZ_ok(struct LDKC2Tuple_BlockHashChannelManagerZ o);
	public static native long CResult_C2Tuple_BlockHashChannelManagerZDecodeErrorZ_ok(long o);
	// struct LDKCResult_C2Tuple_BlockHashChannelManagerZDecodeErrorZ CResult_C2Tuple_BlockHashChannelManagerZDecodeErrorZ_err(struct LDKDecodeError e);
	public static native long CResult_C2Tuple_BlockHashChannelManagerZDecodeErrorZ_err(long e);
	// bool CResult_C2Tuple_BlockHashChannelManagerZDecodeErrorZ_is_ok(const struct LDKCResult_C2Tuple_BlockHashChannelManagerZDecodeErrorZ *NONNULL_PTR o);
	public static native boolean CResult_C2Tuple_BlockHashChannelManagerZDecodeErrorZ_is_ok(long o);
	// void CResult_C2Tuple_BlockHashChannelManagerZDecodeErrorZ_free(struct LDKCResult_C2Tuple_BlockHashChannelManagerZDecodeErrorZ _res);
	public static native void CResult_C2Tuple_BlockHashChannelManagerZDecodeErrorZ_free(long _res);
	// struct LDKCResult_ChannelConfigDecodeErrorZ CResult_ChannelConfigDecodeErrorZ_ok(struct LDKChannelConfig o);
	public static native long CResult_ChannelConfigDecodeErrorZ_ok(long o);
	// struct LDKCResult_ChannelConfigDecodeErrorZ CResult_ChannelConfigDecodeErrorZ_err(struct LDKDecodeError e);
	public static native long CResult_ChannelConfigDecodeErrorZ_err(long e);
	// bool CResult_ChannelConfigDecodeErrorZ_is_ok(const struct LDKCResult_ChannelConfigDecodeErrorZ *NONNULL_PTR o);
	public static native boolean CResult_ChannelConfigDecodeErrorZ_is_ok(long o);
	// void CResult_ChannelConfigDecodeErrorZ_free(struct LDKCResult_ChannelConfigDecodeErrorZ _res);
	public static native void CResult_ChannelConfigDecodeErrorZ_free(long _res);
	// uintptr_t CResult_ChannelConfigDecodeErrorZ_clone_ptr(LDKCResult_ChannelConfigDecodeErrorZ *NONNULL_PTR arg);
	public static native long CResult_ChannelConfigDecodeErrorZ_clone_ptr(long arg);
	// struct LDKCResult_ChannelConfigDecodeErrorZ CResult_ChannelConfigDecodeErrorZ_clone(const struct LDKCResult_ChannelConfigDecodeErrorZ *NONNULL_PTR orig);
	public static native long CResult_ChannelConfigDecodeErrorZ_clone(long orig);
	// struct LDKCResult_OutPointDecodeErrorZ CResult_OutPointDecodeErrorZ_ok(struct LDKOutPoint o);
	public static native long CResult_OutPointDecodeErrorZ_ok(long o);
	// struct LDKCResult_OutPointDecodeErrorZ CResult_OutPointDecodeErrorZ_err(struct LDKDecodeError e);
	public static native long CResult_OutPointDecodeErrorZ_err(long e);
	// bool CResult_OutPointDecodeErrorZ_is_ok(const struct LDKCResult_OutPointDecodeErrorZ *NONNULL_PTR o);
	public static native boolean CResult_OutPointDecodeErrorZ_is_ok(long o);
	// void CResult_OutPointDecodeErrorZ_free(struct LDKCResult_OutPointDecodeErrorZ _res);
	public static native void CResult_OutPointDecodeErrorZ_free(long _res);
	// uintptr_t CResult_OutPointDecodeErrorZ_clone_ptr(LDKCResult_OutPointDecodeErrorZ *NONNULL_PTR arg);
	public static native long CResult_OutPointDecodeErrorZ_clone_ptr(long arg);
	// struct LDKCResult_OutPointDecodeErrorZ CResult_OutPointDecodeErrorZ_clone(const struct LDKCResult_OutPointDecodeErrorZ *NONNULL_PTR orig);
	public static native long CResult_OutPointDecodeErrorZ_clone(long orig);
	// struct LDKCOption_TypeZ COption_TypeZ_some(struct LDKType o);
	public static native long COption_TypeZ_some(long o);
	// struct LDKCOption_TypeZ COption_TypeZ_none(void);
	public static native long COption_TypeZ_none();
	// void COption_TypeZ_free(struct LDKCOption_TypeZ _res);
	public static native void COption_TypeZ_free(long _res);
	// uintptr_t COption_TypeZ_clone_ptr(LDKCOption_TypeZ *NONNULL_PTR arg);
	public static native long COption_TypeZ_clone_ptr(long arg);
	// struct LDKCOption_TypeZ COption_TypeZ_clone(const struct LDKCOption_TypeZ *NONNULL_PTR orig);
	public static native long COption_TypeZ_clone(long orig);
	// struct LDKCResult_COption_TypeZDecodeErrorZ CResult_COption_TypeZDecodeErrorZ_ok(struct LDKCOption_TypeZ o);
	public static native long CResult_COption_TypeZDecodeErrorZ_ok(long o);
	// struct LDKCResult_COption_TypeZDecodeErrorZ CResult_COption_TypeZDecodeErrorZ_err(struct LDKDecodeError e);
	public static native long CResult_COption_TypeZDecodeErrorZ_err(long e);
	// bool CResult_COption_TypeZDecodeErrorZ_is_ok(const struct LDKCResult_COption_TypeZDecodeErrorZ *NONNULL_PTR o);
	public static native boolean CResult_COption_TypeZDecodeErrorZ_is_ok(long o);
	// void CResult_COption_TypeZDecodeErrorZ_free(struct LDKCResult_COption_TypeZDecodeErrorZ _res);
	public static native void CResult_COption_TypeZDecodeErrorZ_free(long _res);
	// uintptr_t CResult_COption_TypeZDecodeErrorZ_clone_ptr(LDKCResult_COption_TypeZDecodeErrorZ *NONNULL_PTR arg);
	public static native long CResult_COption_TypeZDecodeErrorZ_clone_ptr(long arg);
	// struct LDKCResult_COption_TypeZDecodeErrorZ CResult_COption_TypeZDecodeErrorZ_clone(const struct LDKCResult_COption_TypeZDecodeErrorZ *NONNULL_PTR orig);
	public static native long CResult_COption_TypeZDecodeErrorZ_clone(long orig);
	// struct LDKCResult_PaymentIdPaymentErrorZ CResult_PaymentIdPaymentErrorZ_ok(struct LDKThirtyTwoBytes o);
	public static native long CResult_PaymentIdPaymentErrorZ_ok(byte[] o);
	// struct LDKCResult_PaymentIdPaymentErrorZ CResult_PaymentIdPaymentErrorZ_err(struct LDKPaymentError e);
	public static native long CResult_PaymentIdPaymentErrorZ_err(long e);
	// bool CResult_PaymentIdPaymentErrorZ_is_ok(const struct LDKCResult_PaymentIdPaymentErrorZ *NONNULL_PTR o);
	public static native boolean CResult_PaymentIdPaymentErrorZ_is_ok(long o);
	// void CResult_PaymentIdPaymentErrorZ_free(struct LDKCResult_PaymentIdPaymentErrorZ _res);
	public static native void CResult_PaymentIdPaymentErrorZ_free(long _res);
	// uintptr_t CResult_PaymentIdPaymentErrorZ_clone_ptr(LDKCResult_PaymentIdPaymentErrorZ *NONNULL_PTR arg);
	public static native long CResult_PaymentIdPaymentErrorZ_clone_ptr(long arg);
	// struct LDKCResult_PaymentIdPaymentErrorZ CResult_PaymentIdPaymentErrorZ_clone(const struct LDKCResult_PaymentIdPaymentErrorZ *NONNULL_PTR orig);
	public static native long CResult_PaymentIdPaymentErrorZ_clone(long orig);
	// struct LDKCResult_SiPrefixParseErrorZ CResult_SiPrefixParseErrorZ_ok(enum LDKSiPrefix o);
	public static native long CResult_SiPrefixParseErrorZ_ok(SiPrefix o);
	// struct LDKCResult_SiPrefixParseErrorZ CResult_SiPrefixParseErrorZ_err(struct LDKParseError e);
	public static native long CResult_SiPrefixParseErrorZ_err(long e);
	// bool CResult_SiPrefixParseErrorZ_is_ok(const struct LDKCResult_SiPrefixParseErrorZ *NONNULL_PTR o);
	public static native boolean CResult_SiPrefixParseErrorZ_is_ok(long o);
	// void CResult_SiPrefixParseErrorZ_free(struct LDKCResult_SiPrefixParseErrorZ _res);
	public static native void CResult_SiPrefixParseErrorZ_free(long _res);
	// uintptr_t CResult_SiPrefixParseErrorZ_clone_ptr(LDKCResult_SiPrefixParseErrorZ *NONNULL_PTR arg);
	public static native long CResult_SiPrefixParseErrorZ_clone_ptr(long arg);
	// struct LDKCResult_SiPrefixParseErrorZ CResult_SiPrefixParseErrorZ_clone(const struct LDKCResult_SiPrefixParseErrorZ *NONNULL_PTR orig);
	public static native long CResult_SiPrefixParseErrorZ_clone(long orig);
	// struct LDKCResult_InvoiceParseOrSemanticErrorZ CResult_InvoiceParseOrSemanticErrorZ_ok(struct LDKInvoice o);
	public static native long CResult_InvoiceParseOrSemanticErrorZ_ok(long o);
	// struct LDKCResult_InvoiceParseOrSemanticErrorZ CResult_InvoiceParseOrSemanticErrorZ_err(struct LDKParseOrSemanticError e);
	public static native long CResult_InvoiceParseOrSemanticErrorZ_err(long e);
	// bool CResult_InvoiceParseOrSemanticErrorZ_is_ok(const struct LDKCResult_InvoiceParseOrSemanticErrorZ *NONNULL_PTR o);
	public static native boolean CResult_InvoiceParseOrSemanticErrorZ_is_ok(long o);
	// void CResult_InvoiceParseOrSemanticErrorZ_free(struct LDKCResult_InvoiceParseOrSemanticErrorZ _res);
	public static native void CResult_InvoiceParseOrSemanticErrorZ_free(long _res);
	// uintptr_t CResult_InvoiceParseOrSemanticErrorZ_clone_ptr(LDKCResult_InvoiceParseOrSemanticErrorZ *NONNULL_PTR arg);
	public static native long CResult_InvoiceParseOrSemanticErrorZ_clone_ptr(long arg);
	// struct LDKCResult_InvoiceParseOrSemanticErrorZ CResult_InvoiceParseOrSemanticErrorZ_clone(const struct LDKCResult_InvoiceParseOrSemanticErrorZ *NONNULL_PTR orig);
	public static native long CResult_InvoiceParseOrSemanticErrorZ_clone(long orig);
	// struct LDKCResult_SignedRawInvoiceParseErrorZ CResult_SignedRawInvoiceParseErrorZ_ok(struct LDKSignedRawInvoice o);
	public static native long CResult_SignedRawInvoiceParseErrorZ_ok(long o);
	// struct LDKCResult_SignedRawInvoiceParseErrorZ CResult_SignedRawInvoiceParseErrorZ_err(struct LDKParseError e);
	public static native long CResult_SignedRawInvoiceParseErrorZ_err(long e);
	// bool CResult_SignedRawInvoiceParseErrorZ_is_ok(const struct LDKCResult_SignedRawInvoiceParseErrorZ *NONNULL_PTR o);
	public static native boolean CResult_SignedRawInvoiceParseErrorZ_is_ok(long o);
	// void CResult_SignedRawInvoiceParseErrorZ_free(struct LDKCResult_SignedRawInvoiceParseErrorZ _res);
	public static native void CResult_SignedRawInvoiceParseErrorZ_free(long _res);
	// uintptr_t CResult_SignedRawInvoiceParseErrorZ_clone_ptr(LDKCResult_SignedRawInvoiceParseErrorZ *NONNULL_PTR arg);
	public static native long CResult_SignedRawInvoiceParseErrorZ_clone_ptr(long arg);
	// struct LDKCResult_SignedRawInvoiceParseErrorZ CResult_SignedRawInvoiceParseErrorZ_clone(const struct LDKCResult_SignedRawInvoiceParseErrorZ *NONNULL_PTR orig);
	public static native long CResult_SignedRawInvoiceParseErrorZ_clone(long orig);
	// uintptr_t C3Tuple_RawInvoice_u832InvoiceSignatureZ_clone_ptr(LDKC3Tuple_RawInvoice_u832InvoiceSignatureZ *NONNULL_PTR arg);
	public static native long C3Tuple_RawInvoice_u832InvoiceSignatureZ_clone_ptr(long arg);
	// struct LDKC3Tuple_RawInvoice_u832InvoiceSignatureZ C3Tuple_RawInvoice_u832InvoiceSignatureZ_clone(const struct LDKC3Tuple_RawInvoice_u832InvoiceSignatureZ *NONNULL_PTR orig);
	public static native long C3Tuple_RawInvoice_u832InvoiceSignatureZ_clone(long orig);
	// struct LDKC3Tuple_RawInvoice_u832InvoiceSignatureZ C3Tuple_RawInvoice_u832InvoiceSignatureZ_new(struct LDKRawInvoice a, struct LDKThirtyTwoBytes b, struct LDKInvoiceSignature c);
	public static native long C3Tuple_RawInvoice_u832InvoiceSignatureZ_new(long a, byte[] b, long c);
	// void C3Tuple_RawInvoice_u832InvoiceSignatureZ_free(struct LDKC3Tuple_RawInvoice_u832InvoiceSignatureZ _res);
	public static native void C3Tuple_RawInvoice_u832InvoiceSignatureZ_free(long _res);
	// struct LDKCResult_PayeePubKeyErrorZ CResult_PayeePubKeyErrorZ_ok(struct LDKPayeePubKey o);
	public static native long CResult_PayeePubKeyErrorZ_ok(long o);
	// struct LDKCResult_PayeePubKeyErrorZ CResult_PayeePubKeyErrorZ_err(enum LDKSecp256k1Error e);
	public static native long CResult_PayeePubKeyErrorZ_err(Secp256k1Error e);
	// bool CResult_PayeePubKeyErrorZ_is_ok(const struct LDKCResult_PayeePubKeyErrorZ *NONNULL_PTR o);
	public static native boolean CResult_PayeePubKeyErrorZ_is_ok(long o);
	// void CResult_PayeePubKeyErrorZ_free(struct LDKCResult_PayeePubKeyErrorZ _res);
	public static native void CResult_PayeePubKeyErrorZ_free(long _res);
	// uintptr_t CResult_PayeePubKeyErrorZ_clone_ptr(LDKCResult_PayeePubKeyErrorZ *NONNULL_PTR arg);
	public static native long CResult_PayeePubKeyErrorZ_clone_ptr(long arg);
	// struct LDKCResult_PayeePubKeyErrorZ CResult_PayeePubKeyErrorZ_clone(const struct LDKCResult_PayeePubKeyErrorZ *NONNULL_PTR orig);
	public static native long CResult_PayeePubKeyErrorZ_clone(long orig);
	// void CVec_PrivateRouteZ_free(struct LDKCVec_PrivateRouteZ _res);
	public static native void CVec_PrivateRouteZ_free(long[] _res);
	// struct LDKCResult_PositiveTimestampCreationErrorZ CResult_PositiveTimestampCreationErrorZ_ok(struct LDKPositiveTimestamp o);
	public static native long CResult_PositiveTimestampCreationErrorZ_ok(long o);
	// struct LDKCResult_PositiveTimestampCreationErrorZ CResult_PositiveTimestampCreationErrorZ_err(enum LDKCreationError e);
	public static native long CResult_PositiveTimestampCreationErrorZ_err(CreationError e);
	// bool CResult_PositiveTimestampCreationErrorZ_is_ok(const struct LDKCResult_PositiveTimestampCreationErrorZ *NONNULL_PTR o);
	public static native boolean CResult_PositiveTimestampCreationErrorZ_is_ok(long o);
	// void CResult_PositiveTimestampCreationErrorZ_free(struct LDKCResult_PositiveTimestampCreationErrorZ _res);
	public static native void CResult_PositiveTimestampCreationErrorZ_free(long _res);
	// uintptr_t CResult_PositiveTimestampCreationErrorZ_clone_ptr(LDKCResult_PositiveTimestampCreationErrorZ *NONNULL_PTR arg);
	public static native long CResult_PositiveTimestampCreationErrorZ_clone_ptr(long arg);
	// struct LDKCResult_PositiveTimestampCreationErrorZ CResult_PositiveTimestampCreationErrorZ_clone(const struct LDKCResult_PositiveTimestampCreationErrorZ *NONNULL_PTR orig);
	public static native long CResult_PositiveTimestampCreationErrorZ_clone(long orig);
	// struct LDKCResult_NoneSemanticErrorZ CResult_NoneSemanticErrorZ_ok(void);
	public static native long CResult_NoneSemanticErrorZ_ok();
	// struct LDKCResult_NoneSemanticErrorZ CResult_NoneSemanticErrorZ_err(enum LDKSemanticError e);
	public static native long CResult_NoneSemanticErrorZ_err(SemanticError e);
	// bool CResult_NoneSemanticErrorZ_is_ok(const struct LDKCResult_NoneSemanticErrorZ *NONNULL_PTR o);
	public static native boolean CResult_NoneSemanticErrorZ_is_ok(long o);
	// void CResult_NoneSemanticErrorZ_free(struct LDKCResult_NoneSemanticErrorZ _res);
	public static native void CResult_NoneSemanticErrorZ_free(long _res);
	// uintptr_t CResult_NoneSemanticErrorZ_clone_ptr(LDKCResult_NoneSemanticErrorZ *NONNULL_PTR arg);
	public static native long CResult_NoneSemanticErrorZ_clone_ptr(long arg);
	// struct LDKCResult_NoneSemanticErrorZ CResult_NoneSemanticErrorZ_clone(const struct LDKCResult_NoneSemanticErrorZ *NONNULL_PTR orig);
	public static native long CResult_NoneSemanticErrorZ_clone(long orig);
	// struct LDKCResult_InvoiceSemanticErrorZ CResult_InvoiceSemanticErrorZ_ok(struct LDKInvoice o);
	public static native long CResult_InvoiceSemanticErrorZ_ok(long o);
	// struct LDKCResult_InvoiceSemanticErrorZ CResult_InvoiceSemanticErrorZ_err(enum LDKSemanticError e);
	public static native long CResult_InvoiceSemanticErrorZ_err(SemanticError e);
	// bool CResult_InvoiceSemanticErrorZ_is_ok(const struct LDKCResult_InvoiceSemanticErrorZ *NONNULL_PTR o);
	public static native boolean CResult_InvoiceSemanticErrorZ_is_ok(long o);
	// void CResult_InvoiceSemanticErrorZ_free(struct LDKCResult_InvoiceSemanticErrorZ _res);
	public static native void CResult_InvoiceSemanticErrorZ_free(long _res);
	// uintptr_t CResult_InvoiceSemanticErrorZ_clone_ptr(LDKCResult_InvoiceSemanticErrorZ *NONNULL_PTR arg);
	public static native long CResult_InvoiceSemanticErrorZ_clone_ptr(long arg);
	// struct LDKCResult_InvoiceSemanticErrorZ CResult_InvoiceSemanticErrorZ_clone(const struct LDKCResult_InvoiceSemanticErrorZ *NONNULL_PTR orig);
	public static native long CResult_InvoiceSemanticErrorZ_clone(long orig);
	// struct LDKCResult_DescriptionCreationErrorZ CResult_DescriptionCreationErrorZ_ok(struct LDKDescription o);
	public static native long CResult_DescriptionCreationErrorZ_ok(long o);
	// struct LDKCResult_DescriptionCreationErrorZ CResult_DescriptionCreationErrorZ_err(enum LDKCreationError e);
	public static native long CResult_DescriptionCreationErrorZ_err(CreationError e);
	// bool CResult_DescriptionCreationErrorZ_is_ok(const struct LDKCResult_DescriptionCreationErrorZ *NONNULL_PTR o);
	public static native boolean CResult_DescriptionCreationErrorZ_is_ok(long o);
	// void CResult_DescriptionCreationErrorZ_free(struct LDKCResult_DescriptionCreationErrorZ _res);
	public static native void CResult_DescriptionCreationErrorZ_free(long _res);
	// uintptr_t CResult_DescriptionCreationErrorZ_clone_ptr(LDKCResult_DescriptionCreationErrorZ *NONNULL_PTR arg);
	public static native long CResult_DescriptionCreationErrorZ_clone_ptr(long arg);
	// struct LDKCResult_DescriptionCreationErrorZ CResult_DescriptionCreationErrorZ_clone(const struct LDKCResult_DescriptionCreationErrorZ *NONNULL_PTR orig);
	public static native long CResult_DescriptionCreationErrorZ_clone(long orig);
	// struct LDKCResult_PrivateRouteCreationErrorZ CResult_PrivateRouteCreationErrorZ_ok(struct LDKPrivateRoute o);
	public static native long CResult_PrivateRouteCreationErrorZ_ok(long o);
	// struct LDKCResult_PrivateRouteCreationErrorZ CResult_PrivateRouteCreationErrorZ_err(enum LDKCreationError e);
	public static native long CResult_PrivateRouteCreationErrorZ_err(CreationError e);
	// bool CResult_PrivateRouteCreationErrorZ_is_ok(const struct LDKCResult_PrivateRouteCreationErrorZ *NONNULL_PTR o);
	public static native boolean CResult_PrivateRouteCreationErrorZ_is_ok(long o);
	// void CResult_PrivateRouteCreationErrorZ_free(struct LDKCResult_PrivateRouteCreationErrorZ _res);
	public static native void CResult_PrivateRouteCreationErrorZ_free(long _res);
	// uintptr_t CResult_PrivateRouteCreationErrorZ_clone_ptr(LDKCResult_PrivateRouteCreationErrorZ *NONNULL_PTR arg);
	public static native long CResult_PrivateRouteCreationErrorZ_clone_ptr(long arg);
	// struct LDKCResult_PrivateRouteCreationErrorZ CResult_PrivateRouteCreationErrorZ_clone(const struct LDKCResult_PrivateRouteCreationErrorZ *NONNULL_PTR orig);
	public static native long CResult_PrivateRouteCreationErrorZ_clone(long orig);
	// struct LDKCResult_StringErrorZ CResult_StringErrorZ_ok(struct LDKStr o);
	public static native long CResult_StringErrorZ_ok(String o);
	// struct LDKCResult_StringErrorZ CResult_StringErrorZ_err(enum LDKSecp256k1Error e);
	public static native long CResult_StringErrorZ_err(Secp256k1Error e);
	// bool CResult_StringErrorZ_is_ok(const struct LDKCResult_StringErrorZ *NONNULL_PTR o);
	public static native boolean CResult_StringErrorZ_is_ok(long o);
	// void CResult_StringErrorZ_free(struct LDKCResult_StringErrorZ _res);
	public static native void CResult_StringErrorZ_free(long _res);
	// struct LDKCResult_ChannelMonitorUpdateDecodeErrorZ CResult_ChannelMonitorUpdateDecodeErrorZ_ok(struct LDKChannelMonitorUpdate o);
	public static native long CResult_ChannelMonitorUpdateDecodeErrorZ_ok(long o);
	// struct LDKCResult_ChannelMonitorUpdateDecodeErrorZ CResult_ChannelMonitorUpdateDecodeErrorZ_err(struct LDKDecodeError e);
	public static native long CResult_ChannelMonitorUpdateDecodeErrorZ_err(long e);
	// bool CResult_ChannelMonitorUpdateDecodeErrorZ_is_ok(const struct LDKCResult_ChannelMonitorUpdateDecodeErrorZ *NONNULL_PTR o);
	public static native boolean CResult_ChannelMonitorUpdateDecodeErrorZ_is_ok(long o);
	// void CResult_ChannelMonitorUpdateDecodeErrorZ_free(struct LDKCResult_ChannelMonitorUpdateDecodeErrorZ _res);
	public static native void CResult_ChannelMonitorUpdateDecodeErrorZ_free(long _res);
	// uintptr_t CResult_ChannelMonitorUpdateDecodeErrorZ_clone_ptr(LDKCResult_ChannelMonitorUpdateDecodeErrorZ *NONNULL_PTR arg);
	public static native long CResult_ChannelMonitorUpdateDecodeErrorZ_clone_ptr(long arg);
	// struct LDKCResult_ChannelMonitorUpdateDecodeErrorZ CResult_ChannelMonitorUpdateDecodeErrorZ_clone(const struct LDKCResult_ChannelMonitorUpdateDecodeErrorZ *NONNULL_PTR orig);
	public static native long CResult_ChannelMonitorUpdateDecodeErrorZ_clone(long orig);
	// struct LDKCOption_MonitorEventZ COption_MonitorEventZ_some(struct LDKMonitorEvent o);
	public static native long COption_MonitorEventZ_some(long o);
	// struct LDKCOption_MonitorEventZ COption_MonitorEventZ_none(void);
	public static native long COption_MonitorEventZ_none();
	// void COption_MonitorEventZ_free(struct LDKCOption_MonitorEventZ _res);
	public static native void COption_MonitorEventZ_free(long _res);
	// uintptr_t COption_MonitorEventZ_clone_ptr(LDKCOption_MonitorEventZ *NONNULL_PTR arg);
	public static native long COption_MonitorEventZ_clone_ptr(long arg);
	// struct LDKCOption_MonitorEventZ COption_MonitorEventZ_clone(const struct LDKCOption_MonitorEventZ *NONNULL_PTR orig);
	public static native long COption_MonitorEventZ_clone(long orig);
	// struct LDKCResult_COption_MonitorEventZDecodeErrorZ CResult_COption_MonitorEventZDecodeErrorZ_ok(struct LDKCOption_MonitorEventZ o);
	public static native long CResult_COption_MonitorEventZDecodeErrorZ_ok(long o);
	// struct LDKCResult_COption_MonitorEventZDecodeErrorZ CResult_COption_MonitorEventZDecodeErrorZ_err(struct LDKDecodeError e);
	public static native long CResult_COption_MonitorEventZDecodeErrorZ_err(long e);
	// bool CResult_COption_MonitorEventZDecodeErrorZ_is_ok(const struct LDKCResult_COption_MonitorEventZDecodeErrorZ *NONNULL_PTR o);
	public static native boolean CResult_COption_MonitorEventZDecodeErrorZ_is_ok(long o);
	// void CResult_COption_MonitorEventZDecodeErrorZ_free(struct LDKCResult_COption_MonitorEventZDecodeErrorZ _res);
	public static native void CResult_COption_MonitorEventZDecodeErrorZ_free(long _res);
	// uintptr_t CResult_COption_MonitorEventZDecodeErrorZ_clone_ptr(LDKCResult_COption_MonitorEventZDecodeErrorZ *NONNULL_PTR arg);
	public static native long CResult_COption_MonitorEventZDecodeErrorZ_clone_ptr(long arg);
	// struct LDKCResult_COption_MonitorEventZDecodeErrorZ CResult_COption_MonitorEventZDecodeErrorZ_clone(const struct LDKCResult_COption_MonitorEventZDecodeErrorZ *NONNULL_PTR orig);
	public static native long CResult_COption_MonitorEventZDecodeErrorZ_clone(long orig);
	// struct LDKCResult_HTLCUpdateDecodeErrorZ CResult_HTLCUpdateDecodeErrorZ_ok(struct LDKHTLCUpdate o);
	public static native long CResult_HTLCUpdateDecodeErrorZ_ok(long o);
	// struct LDKCResult_HTLCUpdateDecodeErrorZ CResult_HTLCUpdateDecodeErrorZ_err(struct LDKDecodeError e);
	public static native long CResult_HTLCUpdateDecodeErrorZ_err(long e);
	// bool CResult_HTLCUpdateDecodeErrorZ_is_ok(const struct LDKCResult_HTLCUpdateDecodeErrorZ *NONNULL_PTR o);
	public static native boolean CResult_HTLCUpdateDecodeErrorZ_is_ok(long o);
	// void CResult_HTLCUpdateDecodeErrorZ_free(struct LDKCResult_HTLCUpdateDecodeErrorZ _res);
	public static native void CResult_HTLCUpdateDecodeErrorZ_free(long _res);
	// uintptr_t CResult_HTLCUpdateDecodeErrorZ_clone_ptr(LDKCResult_HTLCUpdateDecodeErrorZ *NONNULL_PTR arg);
	public static native long CResult_HTLCUpdateDecodeErrorZ_clone_ptr(long arg);
	// struct LDKCResult_HTLCUpdateDecodeErrorZ CResult_HTLCUpdateDecodeErrorZ_clone(const struct LDKCResult_HTLCUpdateDecodeErrorZ *NONNULL_PTR orig);
	public static native long CResult_HTLCUpdateDecodeErrorZ_clone(long orig);
	// uintptr_t C2Tuple_OutPointScriptZ_clone_ptr(LDKC2Tuple_OutPointScriptZ *NONNULL_PTR arg);
	public static native long C2Tuple_OutPointScriptZ_clone_ptr(long arg);
	// struct LDKC2Tuple_OutPointScriptZ C2Tuple_OutPointScriptZ_clone(const struct LDKC2Tuple_OutPointScriptZ *NONNULL_PTR orig);
	public static native long C2Tuple_OutPointScriptZ_clone(long orig);
	// struct LDKC2Tuple_OutPointScriptZ C2Tuple_OutPointScriptZ_new(struct LDKOutPoint a, struct LDKCVec_u8Z b);
	public static native long C2Tuple_OutPointScriptZ_new(long a, byte[] b);
	// void C2Tuple_OutPointScriptZ_free(struct LDKC2Tuple_OutPointScriptZ _res);
	public static native void C2Tuple_OutPointScriptZ_free(long _res);
	// uintptr_t C2Tuple_u32ScriptZ_clone_ptr(LDKC2Tuple_u32ScriptZ *NONNULL_PTR arg);
	public static native long C2Tuple_u32ScriptZ_clone_ptr(long arg);
	// struct LDKC2Tuple_u32ScriptZ C2Tuple_u32ScriptZ_clone(const struct LDKC2Tuple_u32ScriptZ *NONNULL_PTR orig);
	public static native long C2Tuple_u32ScriptZ_clone(long orig);
	// struct LDKC2Tuple_u32ScriptZ C2Tuple_u32ScriptZ_new(uint32_t a, struct LDKCVec_u8Z b);
	public static native long C2Tuple_u32ScriptZ_new(int a, byte[] b);
	// void C2Tuple_u32ScriptZ_free(struct LDKC2Tuple_u32ScriptZ _res);
	public static native void C2Tuple_u32ScriptZ_free(long _res);
	// void CVec_C2Tuple_u32ScriptZZ_free(struct LDKCVec_C2Tuple_u32ScriptZZ _res);
	public static native void CVec_C2Tuple_u32ScriptZZ_free(long[] _res);
	// uintptr_t C2Tuple_TxidCVec_C2Tuple_u32ScriptZZZ_clone_ptr(LDKC2Tuple_TxidCVec_C2Tuple_u32ScriptZZZ *NONNULL_PTR arg);
	public static native long C2Tuple_TxidCVec_C2Tuple_u32ScriptZZZ_clone_ptr(long arg);
	// struct LDKC2Tuple_TxidCVec_C2Tuple_u32ScriptZZZ C2Tuple_TxidCVec_C2Tuple_u32ScriptZZZ_clone(const struct LDKC2Tuple_TxidCVec_C2Tuple_u32ScriptZZZ *NONNULL_PTR orig);
	public static native long C2Tuple_TxidCVec_C2Tuple_u32ScriptZZZ_clone(long orig);
	// struct LDKC2Tuple_TxidCVec_C2Tuple_u32ScriptZZZ C2Tuple_TxidCVec_C2Tuple_u32ScriptZZZ_new(struct LDKThirtyTwoBytes a, struct LDKCVec_C2Tuple_u32ScriptZZ b);
	public static native long C2Tuple_TxidCVec_C2Tuple_u32ScriptZZZ_new(byte[] a, long[] b);
	// void C2Tuple_TxidCVec_C2Tuple_u32ScriptZZZ_free(struct LDKC2Tuple_TxidCVec_C2Tuple_u32ScriptZZZ _res);
	public static native void C2Tuple_TxidCVec_C2Tuple_u32ScriptZZZ_free(long _res);
	// void CVec_C2Tuple_TxidCVec_C2Tuple_u32ScriptZZZZ_free(struct LDKCVec_C2Tuple_TxidCVec_C2Tuple_u32ScriptZZZZ _res);
	public static native void CVec_C2Tuple_TxidCVec_C2Tuple_u32ScriptZZZZ_free(long[] _res);
	// void CVec_EventZ_free(struct LDKCVec_EventZ _res);
	public static native void CVec_EventZ_free(long[] _res);
	// void CVec_TransactionZ_free(struct LDKCVec_TransactionZ _res);
	public static native void CVec_TransactionZ_free(byte[][] _res);
	// uintptr_t C2Tuple_u32TxOutZ_clone_ptr(LDKC2Tuple_u32TxOutZ *NONNULL_PTR arg);
	public static native long C2Tuple_u32TxOutZ_clone_ptr(long arg);
	// struct LDKC2Tuple_u32TxOutZ C2Tuple_u32TxOutZ_clone(const struct LDKC2Tuple_u32TxOutZ *NONNULL_PTR orig);
	public static native long C2Tuple_u32TxOutZ_clone(long orig);
	// struct LDKC2Tuple_u32TxOutZ C2Tuple_u32TxOutZ_new(uint32_t a, struct LDKTxOut b);
	public static native long C2Tuple_u32TxOutZ_new(int a, long b);
	// void C2Tuple_u32TxOutZ_free(struct LDKC2Tuple_u32TxOutZ _res);
	public static native void C2Tuple_u32TxOutZ_free(long _res);
	// void CVec_C2Tuple_u32TxOutZZ_free(struct LDKCVec_C2Tuple_u32TxOutZZ _res);
	public static native void CVec_C2Tuple_u32TxOutZZ_free(long[] _res);
	// uintptr_t C2Tuple_TxidCVec_C2Tuple_u32TxOutZZZ_clone_ptr(LDKC2Tuple_TxidCVec_C2Tuple_u32TxOutZZZ *NONNULL_PTR arg);
	public static native long C2Tuple_TxidCVec_C2Tuple_u32TxOutZZZ_clone_ptr(long arg);
	// struct LDKC2Tuple_TxidCVec_C2Tuple_u32TxOutZZZ C2Tuple_TxidCVec_C2Tuple_u32TxOutZZZ_clone(const struct LDKC2Tuple_TxidCVec_C2Tuple_u32TxOutZZZ *NONNULL_PTR orig);
	public static native long C2Tuple_TxidCVec_C2Tuple_u32TxOutZZZ_clone(long orig);
	// struct LDKC2Tuple_TxidCVec_C2Tuple_u32TxOutZZZ C2Tuple_TxidCVec_C2Tuple_u32TxOutZZZ_new(struct LDKThirtyTwoBytes a, struct LDKCVec_C2Tuple_u32TxOutZZ b);
	public static native long C2Tuple_TxidCVec_C2Tuple_u32TxOutZZZ_new(byte[] a, long[] b);
	// void C2Tuple_TxidCVec_C2Tuple_u32TxOutZZZ_free(struct LDKC2Tuple_TxidCVec_C2Tuple_u32TxOutZZZ _res);
	public static native void C2Tuple_TxidCVec_C2Tuple_u32TxOutZZZ_free(long _res);
	// void CVec_C2Tuple_TxidCVec_C2Tuple_u32TxOutZZZZ_free(struct LDKCVec_C2Tuple_TxidCVec_C2Tuple_u32TxOutZZZZ _res);
	public static native void CVec_C2Tuple_TxidCVec_C2Tuple_u32TxOutZZZZ_free(long[] _res);
	// void CVec_BalanceZ_free(struct LDKCVec_BalanceZ _res);
	public static native void CVec_BalanceZ_free(long[] _res);
	// struct LDKCResult_C2Tuple_BlockHashChannelMonitorZDecodeErrorZ CResult_C2Tuple_BlockHashChannelMonitorZDecodeErrorZ_ok(struct LDKC2Tuple_BlockHashChannelMonitorZ o);
	public static native long CResult_C2Tuple_BlockHashChannelMonitorZDecodeErrorZ_ok(long o);
	// struct LDKCResult_C2Tuple_BlockHashChannelMonitorZDecodeErrorZ CResult_C2Tuple_BlockHashChannelMonitorZDecodeErrorZ_err(struct LDKDecodeError e);
	public static native long CResult_C2Tuple_BlockHashChannelMonitorZDecodeErrorZ_err(long e);
	// bool CResult_C2Tuple_BlockHashChannelMonitorZDecodeErrorZ_is_ok(const struct LDKCResult_C2Tuple_BlockHashChannelMonitorZDecodeErrorZ *NONNULL_PTR o);
	public static native boolean CResult_C2Tuple_BlockHashChannelMonitorZDecodeErrorZ_is_ok(long o);
	// void CResult_C2Tuple_BlockHashChannelMonitorZDecodeErrorZ_free(struct LDKCResult_C2Tuple_BlockHashChannelMonitorZDecodeErrorZ _res);
	public static native void CResult_C2Tuple_BlockHashChannelMonitorZDecodeErrorZ_free(long _res);
	// uintptr_t CResult_C2Tuple_BlockHashChannelMonitorZDecodeErrorZ_clone_ptr(LDKCResult_C2Tuple_BlockHashChannelMonitorZDecodeErrorZ *NONNULL_PTR arg);
	public static native long CResult_C2Tuple_BlockHashChannelMonitorZDecodeErrorZ_clone_ptr(long arg);
	// struct LDKCResult_C2Tuple_BlockHashChannelMonitorZDecodeErrorZ CResult_C2Tuple_BlockHashChannelMonitorZDecodeErrorZ_clone(const struct LDKCResult_C2Tuple_BlockHashChannelMonitorZDecodeErrorZ *NONNULL_PTR orig);
	public static native long CResult_C2Tuple_BlockHashChannelMonitorZDecodeErrorZ_clone(long orig);
	// struct LDKCResult_NoneLightningErrorZ CResult_NoneLightningErrorZ_ok(void);
	public static native long CResult_NoneLightningErrorZ_ok();
	// struct LDKCResult_NoneLightningErrorZ CResult_NoneLightningErrorZ_err(struct LDKLightningError e);
	public static native long CResult_NoneLightningErrorZ_err(long e);
	// bool CResult_NoneLightningErrorZ_is_ok(const struct LDKCResult_NoneLightningErrorZ *NONNULL_PTR o);
	public static native boolean CResult_NoneLightningErrorZ_is_ok(long o);
	// void CResult_NoneLightningErrorZ_free(struct LDKCResult_NoneLightningErrorZ _res);
	public static native void CResult_NoneLightningErrorZ_free(long _res);
	// uintptr_t CResult_NoneLightningErrorZ_clone_ptr(LDKCResult_NoneLightningErrorZ *NONNULL_PTR arg);
	public static native long CResult_NoneLightningErrorZ_clone_ptr(long arg);
	// struct LDKCResult_NoneLightningErrorZ CResult_NoneLightningErrorZ_clone(const struct LDKCResult_NoneLightningErrorZ *NONNULL_PTR orig);
	public static native long CResult_NoneLightningErrorZ_clone(long orig);
	// uintptr_t C2Tuple_PublicKeyTypeZ_clone_ptr(LDKC2Tuple_PublicKeyTypeZ *NONNULL_PTR arg);
	public static native long C2Tuple_PublicKeyTypeZ_clone_ptr(long arg);
	// struct LDKC2Tuple_PublicKeyTypeZ C2Tuple_PublicKeyTypeZ_clone(const struct LDKC2Tuple_PublicKeyTypeZ *NONNULL_PTR orig);
	public static native long C2Tuple_PublicKeyTypeZ_clone(long orig);
	// struct LDKC2Tuple_PublicKeyTypeZ C2Tuple_PublicKeyTypeZ_new(struct LDKPublicKey a, struct LDKType b);
	public static native long C2Tuple_PublicKeyTypeZ_new(byte[] a, long b);
	// void C2Tuple_PublicKeyTypeZ_free(struct LDKC2Tuple_PublicKeyTypeZ _res);
	public static native void C2Tuple_PublicKeyTypeZ_free(long _res);
	// void CVec_C2Tuple_PublicKeyTypeZZ_free(struct LDKCVec_C2Tuple_PublicKeyTypeZZ _res);
	public static native void CVec_C2Tuple_PublicKeyTypeZZ_free(long[] _res);
	// struct LDKCResult_boolLightningErrorZ CResult_boolLightningErrorZ_ok(bool o);
	public static native long CResult_boolLightningErrorZ_ok(boolean o);
	// struct LDKCResult_boolLightningErrorZ CResult_boolLightningErrorZ_err(struct LDKLightningError e);
	public static native long CResult_boolLightningErrorZ_err(long e);
	// bool CResult_boolLightningErrorZ_is_ok(const struct LDKCResult_boolLightningErrorZ *NONNULL_PTR o);
	public static native boolean CResult_boolLightningErrorZ_is_ok(long o);
	// void CResult_boolLightningErrorZ_free(struct LDKCResult_boolLightningErrorZ _res);
	public static native void CResult_boolLightningErrorZ_free(long _res);
	// uintptr_t CResult_boolLightningErrorZ_clone_ptr(LDKCResult_boolLightningErrorZ *NONNULL_PTR arg);
	public static native long CResult_boolLightningErrorZ_clone_ptr(long arg);
	// struct LDKCResult_boolLightningErrorZ CResult_boolLightningErrorZ_clone(const struct LDKCResult_boolLightningErrorZ *NONNULL_PTR orig);
	public static native long CResult_boolLightningErrorZ_clone(long orig);
	// uintptr_t C3Tuple_ChannelAnnouncementChannelUpdateChannelUpdateZ_clone_ptr(LDKC3Tuple_ChannelAnnouncementChannelUpdateChannelUpdateZ *NONNULL_PTR arg);
	public static native long C3Tuple_ChannelAnnouncementChannelUpdateChannelUpdateZ_clone_ptr(long arg);
	// struct LDKC3Tuple_ChannelAnnouncementChannelUpdateChannelUpdateZ C3Tuple_ChannelAnnouncementChannelUpdateChannelUpdateZ_clone(const struct LDKC3Tuple_ChannelAnnouncementChannelUpdateChannelUpdateZ *NONNULL_PTR orig);
	public static native long C3Tuple_ChannelAnnouncementChannelUpdateChannelUpdateZ_clone(long orig);
	// struct LDKC3Tuple_ChannelAnnouncementChannelUpdateChannelUpdateZ C3Tuple_ChannelAnnouncementChannelUpdateChannelUpdateZ_new(struct LDKChannelAnnouncement a, struct LDKChannelUpdate b, struct LDKChannelUpdate c);
	public static native long C3Tuple_ChannelAnnouncementChannelUpdateChannelUpdateZ_new(long a, long b, long c);
	// void C3Tuple_ChannelAnnouncementChannelUpdateChannelUpdateZ_free(struct LDKC3Tuple_ChannelAnnouncementChannelUpdateChannelUpdateZ _res);
	public static native void C3Tuple_ChannelAnnouncementChannelUpdateChannelUpdateZ_free(long _res);
	// void CVec_C3Tuple_ChannelAnnouncementChannelUpdateChannelUpdateZZ_free(struct LDKCVec_C3Tuple_ChannelAnnouncementChannelUpdateChannelUpdateZZ _res);
	public static native void CVec_C3Tuple_ChannelAnnouncementChannelUpdateChannelUpdateZZ_free(long[] _res);
	// void CVec_NodeAnnouncementZ_free(struct LDKCVec_NodeAnnouncementZ _res);
	public static native void CVec_NodeAnnouncementZ_free(long[] _res);
	// void CVec_PublicKeyZ_free(struct LDKCVec_PublicKeyZ _res);
	public static native void CVec_PublicKeyZ_free(byte[][] _res);
	// struct LDKCOption_NetAddressZ COption_NetAddressZ_some(struct LDKNetAddress o);
	public static native long COption_NetAddressZ_some(long o);
	// struct LDKCOption_NetAddressZ COption_NetAddressZ_none(void);
	public static native long COption_NetAddressZ_none();
	// void COption_NetAddressZ_free(struct LDKCOption_NetAddressZ _res);
	public static native void COption_NetAddressZ_free(long _res);
	// uintptr_t COption_NetAddressZ_clone_ptr(LDKCOption_NetAddressZ *NONNULL_PTR arg);
	public static native long COption_NetAddressZ_clone_ptr(long arg);
	// struct LDKCOption_NetAddressZ COption_NetAddressZ_clone(const struct LDKCOption_NetAddressZ *NONNULL_PTR orig);
	public static native long COption_NetAddressZ_clone(long orig);
	// struct LDKCResult_CVec_u8ZPeerHandleErrorZ CResult_CVec_u8ZPeerHandleErrorZ_ok(struct LDKCVec_u8Z o);
	public static native long CResult_CVec_u8ZPeerHandleErrorZ_ok(byte[] o);
	// struct LDKCResult_CVec_u8ZPeerHandleErrorZ CResult_CVec_u8ZPeerHandleErrorZ_err(struct LDKPeerHandleError e);
	public static native long CResult_CVec_u8ZPeerHandleErrorZ_err(long e);
	// bool CResult_CVec_u8ZPeerHandleErrorZ_is_ok(const struct LDKCResult_CVec_u8ZPeerHandleErrorZ *NONNULL_PTR o);
	public static native boolean CResult_CVec_u8ZPeerHandleErrorZ_is_ok(long o);
	// void CResult_CVec_u8ZPeerHandleErrorZ_free(struct LDKCResult_CVec_u8ZPeerHandleErrorZ _res);
	public static native void CResult_CVec_u8ZPeerHandleErrorZ_free(long _res);
	// uintptr_t CResult_CVec_u8ZPeerHandleErrorZ_clone_ptr(LDKCResult_CVec_u8ZPeerHandleErrorZ *NONNULL_PTR arg);
	public static native long CResult_CVec_u8ZPeerHandleErrorZ_clone_ptr(long arg);
	// struct LDKCResult_CVec_u8ZPeerHandleErrorZ CResult_CVec_u8ZPeerHandleErrorZ_clone(const struct LDKCResult_CVec_u8ZPeerHandleErrorZ *NONNULL_PTR orig);
	public static native long CResult_CVec_u8ZPeerHandleErrorZ_clone(long orig);
	// struct LDKCResult_NonePeerHandleErrorZ CResult_NonePeerHandleErrorZ_ok(void);
	public static native long CResult_NonePeerHandleErrorZ_ok();
	// struct LDKCResult_NonePeerHandleErrorZ CResult_NonePeerHandleErrorZ_err(struct LDKPeerHandleError e);
	public static native long CResult_NonePeerHandleErrorZ_err(long e);
	// bool CResult_NonePeerHandleErrorZ_is_ok(const struct LDKCResult_NonePeerHandleErrorZ *NONNULL_PTR o);
	public static native boolean CResult_NonePeerHandleErrorZ_is_ok(long o);
	// void CResult_NonePeerHandleErrorZ_free(struct LDKCResult_NonePeerHandleErrorZ _res);
	public static native void CResult_NonePeerHandleErrorZ_free(long _res);
	// uintptr_t CResult_NonePeerHandleErrorZ_clone_ptr(LDKCResult_NonePeerHandleErrorZ *NONNULL_PTR arg);
	public static native long CResult_NonePeerHandleErrorZ_clone_ptr(long arg);
	// struct LDKCResult_NonePeerHandleErrorZ CResult_NonePeerHandleErrorZ_clone(const struct LDKCResult_NonePeerHandleErrorZ *NONNULL_PTR orig);
	public static native long CResult_NonePeerHandleErrorZ_clone(long orig);
	// struct LDKCResult_boolPeerHandleErrorZ CResult_boolPeerHandleErrorZ_ok(bool o);
	public static native long CResult_boolPeerHandleErrorZ_ok(boolean o);
	// struct LDKCResult_boolPeerHandleErrorZ CResult_boolPeerHandleErrorZ_err(struct LDKPeerHandleError e);
	public static native long CResult_boolPeerHandleErrorZ_err(long e);
	// bool CResult_boolPeerHandleErrorZ_is_ok(const struct LDKCResult_boolPeerHandleErrorZ *NONNULL_PTR o);
	public static native boolean CResult_boolPeerHandleErrorZ_is_ok(long o);
	// void CResult_boolPeerHandleErrorZ_free(struct LDKCResult_boolPeerHandleErrorZ _res);
	public static native void CResult_boolPeerHandleErrorZ_free(long _res);
	// uintptr_t CResult_boolPeerHandleErrorZ_clone_ptr(LDKCResult_boolPeerHandleErrorZ *NONNULL_PTR arg);
	public static native long CResult_boolPeerHandleErrorZ_clone_ptr(long arg);
	// struct LDKCResult_boolPeerHandleErrorZ CResult_boolPeerHandleErrorZ_clone(const struct LDKCResult_boolPeerHandleErrorZ *NONNULL_PTR orig);
	public static native long CResult_boolPeerHandleErrorZ_clone(long orig);
	// struct LDKCResult_NodeIdDecodeErrorZ CResult_NodeIdDecodeErrorZ_ok(struct LDKNodeId o);
	public static native long CResult_NodeIdDecodeErrorZ_ok(long o);
	// struct LDKCResult_NodeIdDecodeErrorZ CResult_NodeIdDecodeErrorZ_err(struct LDKDecodeError e);
	public static native long CResult_NodeIdDecodeErrorZ_err(long e);
	// bool CResult_NodeIdDecodeErrorZ_is_ok(const struct LDKCResult_NodeIdDecodeErrorZ *NONNULL_PTR o);
	public static native boolean CResult_NodeIdDecodeErrorZ_is_ok(long o);
	// void CResult_NodeIdDecodeErrorZ_free(struct LDKCResult_NodeIdDecodeErrorZ _res);
	public static native void CResult_NodeIdDecodeErrorZ_free(long _res);
	// uintptr_t CResult_NodeIdDecodeErrorZ_clone_ptr(LDKCResult_NodeIdDecodeErrorZ *NONNULL_PTR arg);
	public static native long CResult_NodeIdDecodeErrorZ_clone_ptr(long arg);
	// struct LDKCResult_NodeIdDecodeErrorZ CResult_NodeIdDecodeErrorZ_clone(const struct LDKCResult_NodeIdDecodeErrorZ *NONNULL_PTR orig);
	public static native long CResult_NodeIdDecodeErrorZ_clone(long orig);
	// struct LDKCResult_COption_NetworkUpdateZDecodeErrorZ CResult_COption_NetworkUpdateZDecodeErrorZ_ok(struct LDKCOption_NetworkUpdateZ o);
	public static native long CResult_COption_NetworkUpdateZDecodeErrorZ_ok(long o);
	// struct LDKCResult_COption_NetworkUpdateZDecodeErrorZ CResult_COption_NetworkUpdateZDecodeErrorZ_err(struct LDKDecodeError e);
	public static native long CResult_COption_NetworkUpdateZDecodeErrorZ_err(long e);
	// bool CResult_COption_NetworkUpdateZDecodeErrorZ_is_ok(const struct LDKCResult_COption_NetworkUpdateZDecodeErrorZ *NONNULL_PTR o);
	public static native boolean CResult_COption_NetworkUpdateZDecodeErrorZ_is_ok(long o);
	// void CResult_COption_NetworkUpdateZDecodeErrorZ_free(struct LDKCResult_COption_NetworkUpdateZDecodeErrorZ _res);
	public static native void CResult_COption_NetworkUpdateZDecodeErrorZ_free(long _res);
	// uintptr_t CResult_COption_NetworkUpdateZDecodeErrorZ_clone_ptr(LDKCResult_COption_NetworkUpdateZDecodeErrorZ *NONNULL_PTR arg);
	public static native long CResult_COption_NetworkUpdateZDecodeErrorZ_clone_ptr(long arg);
	// struct LDKCResult_COption_NetworkUpdateZDecodeErrorZ CResult_COption_NetworkUpdateZDecodeErrorZ_clone(const struct LDKCResult_COption_NetworkUpdateZDecodeErrorZ *NONNULL_PTR orig);
	public static native long CResult_COption_NetworkUpdateZDecodeErrorZ_clone(long orig);
	// struct LDKCOption_AccessZ COption_AccessZ_some(struct LDKAccess o);
	public static native long COption_AccessZ_some(long o);
	// struct LDKCOption_AccessZ COption_AccessZ_none(void);
	public static native long COption_AccessZ_none();
	// void COption_AccessZ_free(struct LDKCOption_AccessZ _res);
	public static native void COption_AccessZ_free(long _res);
	// struct LDKCResult_ChannelUpdateInfoDecodeErrorZ CResult_ChannelUpdateInfoDecodeErrorZ_ok(struct LDKChannelUpdateInfo o);
	public static native long CResult_ChannelUpdateInfoDecodeErrorZ_ok(long o);
	// struct LDKCResult_ChannelUpdateInfoDecodeErrorZ CResult_ChannelUpdateInfoDecodeErrorZ_err(struct LDKDecodeError e);
	public static native long CResult_ChannelUpdateInfoDecodeErrorZ_err(long e);
	// bool CResult_ChannelUpdateInfoDecodeErrorZ_is_ok(const struct LDKCResult_ChannelUpdateInfoDecodeErrorZ *NONNULL_PTR o);
	public static native boolean CResult_ChannelUpdateInfoDecodeErrorZ_is_ok(long o);
	// void CResult_ChannelUpdateInfoDecodeErrorZ_free(struct LDKCResult_ChannelUpdateInfoDecodeErrorZ _res);
	public static native void CResult_ChannelUpdateInfoDecodeErrorZ_free(long _res);
	// uintptr_t CResult_ChannelUpdateInfoDecodeErrorZ_clone_ptr(LDKCResult_ChannelUpdateInfoDecodeErrorZ *NONNULL_PTR arg);
	public static native long CResult_ChannelUpdateInfoDecodeErrorZ_clone_ptr(long arg);
	// struct LDKCResult_ChannelUpdateInfoDecodeErrorZ CResult_ChannelUpdateInfoDecodeErrorZ_clone(const struct LDKCResult_ChannelUpdateInfoDecodeErrorZ *NONNULL_PTR orig);
	public static native long CResult_ChannelUpdateInfoDecodeErrorZ_clone(long orig);
	// struct LDKCResult_ChannelInfoDecodeErrorZ CResult_ChannelInfoDecodeErrorZ_ok(struct LDKChannelInfo o);
	public static native long CResult_ChannelInfoDecodeErrorZ_ok(long o);
	// struct LDKCResult_ChannelInfoDecodeErrorZ CResult_ChannelInfoDecodeErrorZ_err(struct LDKDecodeError e);
	public static native long CResult_ChannelInfoDecodeErrorZ_err(long e);
	// bool CResult_ChannelInfoDecodeErrorZ_is_ok(const struct LDKCResult_ChannelInfoDecodeErrorZ *NONNULL_PTR o);
	public static native boolean CResult_ChannelInfoDecodeErrorZ_is_ok(long o);
	// void CResult_ChannelInfoDecodeErrorZ_free(struct LDKCResult_ChannelInfoDecodeErrorZ _res);
	public static native void CResult_ChannelInfoDecodeErrorZ_free(long _res);
	// uintptr_t CResult_ChannelInfoDecodeErrorZ_clone_ptr(LDKCResult_ChannelInfoDecodeErrorZ *NONNULL_PTR arg);
	public static native long CResult_ChannelInfoDecodeErrorZ_clone_ptr(long arg);
	// struct LDKCResult_ChannelInfoDecodeErrorZ CResult_ChannelInfoDecodeErrorZ_clone(const struct LDKCResult_ChannelInfoDecodeErrorZ *NONNULL_PTR orig);
	public static native long CResult_ChannelInfoDecodeErrorZ_clone(long orig);
	// struct LDKCResult_RoutingFeesDecodeErrorZ CResult_RoutingFeesDecodeErrorZ_ok(struct LDKRoutingFees o);
	public static native long CResult_RoutingFeesDecodeErrorZ_ok(long o);
	// struct LDKCResult_RoutingFeesDecodeErrorZ CResult_RoutingFeesDecodeErrorZ_err(struct LDKDecodeError e);
	public static native long CResult_RoutingFeesDecodeErrorZ_err(long e);
	// bool CResult_RoutingFeesDecodeErrorZ_is_ok(const struct LDKCResult_RoutingFeesDecodeErrorZ *NONNULL_PTR o);
	public static native boolean CResult_RoutingFeesDecodeErrorZ_is_ok(long o);
	// void CResult_RoutingFeesDecodeErrorZ_free(struct LDKCResult_RoutingFeesDecodeErrorZ _res);
	public static native void CResult_RoutingFeesDecodeErrorZ_free(long _res);
	// uintptr_t CResult_RoutingFeesDecodeErrorZ_clone_ptr(LDKCResult_RoutingFeesDecodeErrorZ *NONNULL_PTR arg);
	public static native long CResult_RoutingFeesDecodeErrorZ_clone_ptr(long arg);
	// struct LDKCResult_RoutingFeesDecodeErrorZ CResult_RoutingFeesDecodeErrorZ_clone(const struct LDKCResult_RoutingFeesDecodeErrorZ *NONNULL_PTR orig);
	public static native long CResult_RoutingFeesDecodeErrorZ_clone(long orig);
	// struct LDKCResult_NodeAnnouncementInfoDecodeErrorZ CResult_NodeAnnouncementInfoDecodeErrorZ_ok(struct LDKNodeAnnouncementInfo o);
	public static native long CResult_NodeAnnouncementInfoDecodeErrorZ_ok(long o);
	// struct LDKCResult_NodeAnnouncementInfoDecodeErrorZ CResult_NodeAnnouncementInfoDecodeErrorZ_err(struct LDKDecodeError e);
	public static native long CResult_NodeAnnouncementInfoDecodeErrorZ_err(long e);
	// bool CResult_NodeAnnouncementInfoDecodeErrorZ_is_ok(const struct LDKCResult_NodeAnnouncementInfoDecodeErrorZ *NONNULL_PTR o);
	public static native boolean CResult_NodeAnnouncementInfoDecodeErrorZ_is_ok(long o);
	// void CResult_NodeAnnouncementInfoDecodeErrorZ_free(struct LDKCResult_NodeAnnouncementInfoDecodeErrorZ _res);
	public static native void CResult_NodeAnnouncementInfoDecodeErrorZ_free(long _res);
	// uintptr_t CResult_NodeAnnouncementInfoDecodeErrorZ_clone_ptr(LDKCResult_NodeAnnouncementInfoDecodeErrorZ *NONNULL_PTR arg);
	public static native long CResult_NodeAnnouncementInfoDecodeErrorZ_clone_ptr(long arg);
	// struct LDKCResult_NodeAnnouncementInfoDecodeErrorZ CResult_NodeAnnouncementInfoDecodeErrorZ_clone(const struct LDKCResult_NodeAnnouncementInfoDecodeErrorZ *NONNULL_PTR orig);
	public static native long CResult_NodeAnnouncementInfoDecodeErrorZ_clone(long orig);
	// void CVec_u64Z_free(struct LDKCVec_u64Z _res);
	public static native void CVec_u64Z_free(long[] _res);
	// struct LDKCResult_NodeInfoDecodeErrorZ CResult_NodeInfoDecodeErrorZ_ok(struct LDKNodeInfo o);
	public static native long CResult_NodeInfoDecodeErrorZ_ok(long o);
	// struct LDKCResult_NodeInfoDecodeErrorZ CResult_NodeInfoDecodeErrorZ_err(struct LDKDecodeError e);
	public static native long CResult_NodeInfoDecodeErrorZ_err(long e);
	// bool CResult_NodeInfoDecodeErrorZ_is_ok(const struct LDKCResult_NodeInfoDecodeErrorZ *NONNULL_PTR o);
	public static native boolean CResult_NodeInfoDecodeErrorZ_is_ok(long o);
	// void CResult_NodeInfoDecodeErrorZ_free(struct LDKCResult_NodeInfoDecodeErrorZ _res);
	public static native void CResult_NodeInfoDecodeErrorZ_free(long _res);
	// uintptr_t CResult_NodeInfoDecodeErrorZ_clone_ptr(LDKCResult_NodeInfoDecodeErrorZ *NONNULL_PTR arg);
	public static native long CResult_NodeInfoDecodeErrorZ_clone_ptr(long arg);
	// struct LDKCResult_NodeInfoDecodeErrorZ CResult_NodeInfoDecodeErrorZ_clone(const struct LDKCResult_NodeInfoDecodeErrorZ *NONNULL_PTR orig);
	public static native long CResult_NodeInfoDecodeErrorZ_clone(long orig);
	// struct LDKCResult_NetworkGraphDecodeErrorZ CResult_NetworkGraphDecodeErrorZ_ok(struct LDKNetworkGraph o);
	public static native long CResult_NetworkGraphDecodeErrorZ_ok(long o);
	// struct LDKCResult_NetworkGraphDecodeErrorZ CResult_NetworkGraphDecodeErrorZ_err(struct LDKDecodeError e);
	public static native long CResult_NetworkGraphDecodeErrorZ_err(long e);
	// bool CResult_NetworkGraphDecodeErrorZ_is_ok(const struct LDKCResult_NetworkGraphDecodeErrorZ *NONNULL_PTR o);
	public static native boolean CResult_NetworkGraphDecodeErrorZ_is_ok(long o);
	// void CResult_NetworkGraphDecodeErrorZ_free(struct LDKCResult_NetworkGraphDecodeErrorZ _res);
	public static native void CResult_NetworkGraphDecodeErrorZ_free(long _res);
	// uintptr_t CResult_NetworkGraphDecodeErrorZ_clone_ptr(LDKCResult_NetworkGraphDecodeErrorZ *NONNULL_PTR arg);
	public static native long CResult_NetworkGraphDecodeErrorZ_clone_ptr(long arg);
	// struct LDKCResult_NetworkGraphDecodeErrorZ CResult_NetworkGraphDecodeErrorZ_clone(const struct LDKCResult_NetworkGraphDecodeErrorZ *NONNULL_PTR orig);
	public static native long CResult_NetworkGraphDecodeErrorZ_clone(long orig);
	// struct LDKCOption_CVec_NetAddressZZ COption_CVec_NetAddressZZ_some(struct LDKCVec_NetAddressZ o);
	public static native long COption_CVec_NetAddressZZ_some(long[] o);
	// struct LDKCOption_CVec_NetAddressZZ COption_CVec_NetAddressZZ_none(void);
	public static native long COption_CVec_NetAddressZZ_none();
	// void COption_CVec_NetAddressZZ_free(struct LDKCOption_CVec_NetAddressZZ _res);
	public static native void COption_CVec_NetAddressZZ_free(long _res);
	// uintptr_t COption_CVec_NetAddressZZ_clone_ptr(LDKCOption_CVec_NetAddressZZ *NONNULL_PTR arg);
	public static native long COption_CVec_NetAddressZZ_clone_ptr(long arg);
	// struct LDKCOption_CVec_NetAddressZZ COption_CVec_NetAddressZZ_clone(const struct LDKCOption_CVec_NetAddressZZ *NONNULL_PTR orig);
	public static native long COption_CVec_NetAddressZZ_clone(long orig);
	// struct LDKCResult_NetAddressDecodeErrorZ CResult_NetAddressDecodeErrorZ_ok(struct LDKNetAddress o);
	public static native long CResult_NetAddressDecodeErrorZ_ok(long o);
	// struct LDKCResult_NetAddressDecodeErrorZ CResult_NetAddressDecodeErrorZ_err(struct LDKDecodeError e);
	public static native long CResult_NetAddressDecodeErrorZ_err(long e);
	// bool CResult_NetAddressDecodeErrorZ_is_ok(const struct LDKCResult_NetAddressDecodeErrorZ *NONNULL_PTR o);
	public static native boolean CResult_NetAddressDecodeErrorZ_is_ok(long o);
	// void CResult_NetAddressDecodeErrorZ_free(struct LDKCResult_NetAddressDecodeErrorZ _res);
	public static native void CResult_NetAddressDecodeErrorZ_free(long _res);
	// uintptr_t CResult_NetAddressDecodeErrorZ_clone_ptr(LDKCResult_NetAddressDecodeErrorZ *NONNULL_PTR arg);
	public static native long CResult_NetAddressDecodeErrorZ_clone_ptr(long arg);
	// struct LDKCResult_NetAddressDecodeErrorZ CResult_NetAddressDecodeErrorZ_clone(const struct LDKCResult_NetAddressDecodeErrorZ *NONNULL_PTR orig);
	public static native long CResult_NetAddressDecodeErrorZ_clone(long orig);
	// void CVec_UpdateAddHTLCZ_free(struct LDKCVec_UpdateAddHTLCZ _res);
	public static native void CVec_UpdateAddHTLCZ_free(long[] _res);
	// void CVec_UpdateFulfillHTLCZ_free(struct LDKCVec_UpdateFulfillHTLCZ _res);
	public static native void CVec_UpdateFulfillHTLCZ_free(long[] _res);
	// void CVec_UpdateFailHTLCZ_free(struct LDKCVec_UpdateFailHTLCZ _res);
	public static native void CVec_UpdateFailHTLCZ_free(long[] _res);
	// void CVec_UpdateFailMalformedHTLCZ_free(struct LDKCVec_UpdateFailMalformedHTLCZ _res);
	public static native void CVec_UpdateFailMalformedHTLCZ_free(long[] _res);
	// struct LDKCResult_AcceptChannelDecodeErrorZ CResult_AcceptChannelDecodeErrorZ_ok(struct LDKAcceptChannel o);
	public static native long CResult_AcceptChannelDecodeErrorZ_ok(long o);
	// struct LDKCResult_AcceptChannelDecodeErrorZ CResult_AcceptChannelDecodeErrorZ_err(struct LDKDecodeError e);
	public static native long CResult_AcceptChannelDecodeErrorZ_err(long e);
	// bool CResult_AcceptChannelDecodeErrorZ_is_ok(const struct LDKCResult_AcceptChannelDecodeErrorZ *NONNULL_PTR o);
	public static native boolean CResult_AcceptChannelDecodeErrorZ_is_ok(long o);
	// void CResult_AcceptChannelDecodeErrorZ_free(struct LDKCResult_AcceptChannelDecodeErrorZ _res);
	public static native void CResult_AcceptChannelDecodeErrorZ_free(long _res);
	// uintptr_t CResult_AcceptChannelDecodeErrorZ_clone_ptr(LDKCResult_AcceptChannelDecodeErrorZ *NONNULL_PTR arg);
	public static native long CResult_AcceptChannelDecodeErrorZ_clone_ptr(long arg);
	// struct LDKCResult_AcceptChannelDecodeErrorZ CResult_AcceptChannelDecodeErrorZ_clone(const struct LDKCResult_AcceptChannelDecodeErrorZ *NONNULL_PTR orig);
	public static native long CResult_AcceptChannelDecodeErrorZ_clone(long orig);
	// struct LDKCResult_AnnouncementSignaturesDecodeErrorZ CResult_AnnouncementSignaturesDecodeErrorZ_ok(struct LDKAnnouncementSignatures o);
	public static native long CResult_AnnouncementSignaturesDecodeErrorZ_ok(long o);
	// struct LDKCResult_AnnouncementSignaturesDecodeErrorZ CResult_AnnouncementSignaturesDecodeErrorZ_err(struct LDKDecodeError e);
	public static native long CResult_AnnouncementSignaturesDecodeErrorZ_err(long e);
	// bool CResult_AnnouncementSignaturesDecodeErrorZ_is_ok(const struct LDKCResult_AnnouncementSignaturesDecodeErrorZ *NONNULL_PTR o);
	public static native boolean CResult_AnnouncementSignaturesDecodeErrorZ_is_ok(long o);
	// void CResult_AnnouncementSignaturesDecodeErrorZ_free(struct LDKCResult_AnnouncementSignaturesDecodeErrorZ _res);
	public static native void CResult_AnnouncementSignaturesDecodeErrorZ_free(long _res);
	// uintptr_t CResult_AnnouncementSignaturesDecodeErrorZ_clone_ptr(LDKCResult_AnnouncementSignaturesDecodeErrorZ *NONNULL_PTR arg);
	public static native long CResult_AnnouncementSignaturesDecodeErrorZ_clone_ptr(long arg);
	// struct LDKCResult_AnnouncementSignaturesDecodeErrorZ CResult_AnnouncementSignaturesDecodeErrorZ_clone(const struct LDKCResult_AnnouncementSignaturesDecodeErrorZ *NONNULL_PTR orig);
	public static native long CResult_AnnouncementSignaturesDecodeErrorZ_clone(long orig);
	// struct LDKCResult_ChannelReestablishDecodeErrorZ CResult_ChannelReestablishDecodeErrorZ_ok(struct LDKChannelReestablish o);
	public static native long CResult_ChannelReestablishDecodeErrorZ_ok(long o);
	// struct LDKCResult_ChannelReestablishDecodeErrorZ CResult_ChannelReestablishDecodeErrorZ_err(struct LDKDecodeError e);
	public static native long CResult_ChannelReestablishDecodeErrorZ_err(long e);
	// bool CResult_ChannelReestablishDecodeErrorZ_is_ok(const struct LDKCResult_ChannelReestablishDecodeErrorZ *NONNULL_PTR o);
	public static native boolean CResult_ChannelReestablishDecodeErrorZ_is_ok(long o);
	// void CResult_ChannelReestablishDecodeErrorZ_free(struct LDKCResult_ChannelReestablishDecodeErrorZ _res);
	public static native void CResult_ChannelReestablishDecodeErrorZ_free(long _res);
	// uintptr_t CResult_ChannelReestablishDecodeErrorZ_clone_ptr(LDKCResult_ChannelReestablishDecodeErrorZ *NONNULL_PTR arg);
	public static native long CResult_ChannelReestablishDecodeErrorZ_clone_ptr(long arg);
	// struct LDKCResult_ChannelReestablishDecodeErrorZ CResult_ChannelReestablishDecodeErrorZ_clone(const struct LDKCResult_ChannelReestablishDecodeErrorZ *NONNULL_PTR orig);
	public static native long CResult_ChannelReestablishDecodeErrorZ_clone(long orig);
	// struct LDKCResult_ClosingSignedDecodeErrorZ CResult_ClosingSignedDecodeErrorZ_ok(struct LDKClosingSigned o);
	public static native long CResult_ClosingSignedDecodeErrorZ_ok(long o);
	// struct LDKCResult_ClosingSignedDecodeErrorZ CResult_ClosingSignedDecodeErrorZ_err(struct LDKDecodeError e);
	public static native long CResult_ClosingSignedDecodeErrorZ_err(long e);
	// bool CResult_ClosingSignedDecodeErrorZ_is_ok(const struct LDKCResult_ClosingSignedDecodeErrorZ *NONNULL_PTR o);
	public static native boolean CResult_ClosingSignedDecodeErrorZ_is_ok(long o);
	// void CResult_ClosingSignedDecodeErrorZ_free(struct LDKCResult_ClosingSignedDecodeErrorZ _res);
	public static native void CResult_ClosingSignedDecodeErrorZ_free(long _res);
	// uintptr_t CResult_ClosingSignedDecodeErrorZ_clone_ptr(LDKCResult_ClosingSignedDecodeErrorZ *NONNULL_PTR arg);
	public static native long CResult_ClosingSignedDecodeErrorZ_clone_ptr(long arg);
	// struct LDKCResult_ClosingSignedDecodeErrorZ CResult_ClosingSignedDecodeErrorZ_clone(const struct LDKCResult_ClosingSignedDecodeErrorZ *NONNULL_PTR orig);
	public static native long CResult_ClosingSignedDecodeErrorZ_clone(long orig);
	// struct LDKCResult_ClosingSignedFeeRangeDecodeErrorZ CResult_ClosingSignedFeeRangeDecodeErrorZ_ok(struct LDKClosingSignedFeeRange o);
	public static native long CResult_ClosingSignedFeeRangeDecodeErrorZ_ok(long o);
	// struct LDKCResult_ClosingSignedFeeRangeDecodeErrorZ CResult_ClosingSignedFeeRangeDecodeErrorZ_err(struct LDKDecodeError e);
	public static native long CResult_ClosingSignedFeeRangeDecodeErrorZ_err(long e);
	// bool CResult_ClosingSignedFeeRangeDecodeErrorZ_is_ok(const struct LDKCResult_ClosingSignedFeeRangeDecodeErrorZ *NONNULL_PTR o);
	public static native boolean CResult_ClosingSignedFeeRangeDecodeErrorZ_is_ok(long o);
	// void CResult_ClosingSignedFeeRangeDecodeErrorZ_free(struct LDKCResult_ClosingSignedFeeRangeDecodeErrorZ _res);
	public static native void CResult_ClosingSignedFeeRangeDecodeErrorZ_free(long _res);
	// uintptr_t CResult_ClosingSignedFeeRangeDecodeErrorZ_clone_ptr(LDKCResult_ClosingSignedFeeRangeDecodeErrorZ *NONNULL_PTR arg);
	public static native long CResult_ClosingSignedFeeRangeDecodeErrorZ_clone_ptr(long arg);
	// struct LDKCResult_ClosingSignedFeeRangeDecodeErrorZ CResult_ClosingSignedFeeRangeDecodeErrorZ_clone(const struct LDKCResult_ClosingSignedFeeRangeDecodeErrorZ *NONNULL_PTR orig);
	public static native long CResult_ClosingSignedFeeRangeDecodeErrorZ_clone(long orig);
	// struct LDKCResult_CommitmentSignedDecodeErrorZ CResult_CommitmentSignedDecodeErrorZ_ok(struct LDKCommitmentSigned o);
	public static native long CResult_CommitmentSignedDecodeErrorZ_ok(long o);
	// struct LDKCResult_CommitmentSignedDecodeErrorZ CResult_CommitmentSignedDecodeErrorZ_err(struct LDKDecodeError e);
	public static native long CResult_CommitmentSignedDecodeErrorZ_err(long e);
	// bool CResult_CommitmentSignedDecodeErrorZ_is_ok(const struct LDKCResult_CommitmentSignedDecodeErrorZ *NONNULL_PTR o);
	public static native boolean CResult_CommitmentSignedDecodeErrorZ_is_ok(long o);
	// void CResult_CommitmentSignedDecodeErrorZ_free(struct LDKCResult_CommitmentSignedDecodeErrorZ _res);
	public static native void CResult_CommitmentSignedDecodeErrorZ_free(long _res);
	// uintptr_t CResult_CommitmentSignedDecodeErrorZ_clone_ptr(LDKCResult_CommitmentSignedDecodeErrorZ *NONNULL_PTR arg);
	public static native long CResult_CommitmentSignedDecodeErrorZ_clone_ptr(long arg);
	// struct LDKCResult_CommitmentSignedDecodeErrorZ CResult_CommitmentSignedDecodeErrorZ_clone(const struct LDKCResult_CommitmentSignedDecodeErrorZ *NONNULL_PTR orig);
	public static native long CResult_CommitmentSignedDecodeErrorZ_clone(long orig);
	// struct LDKCResult_FundingCreatedDecodeErrorZ CResult_FundingCreatedDecodeErrorZ_ok(struct LDKFundingCreated o);
	public static native long CResult_FundingCreatedDecodeErrorZ_ok(long o);
	// struct LDKCResult_FundingCreatedDecodeErrorZ CResult_FundingCreatedDecodeErrorZ_err(struct LDKDecodeError e);
	public static native long CResult_FundingCreatedDecodeErrorZ_err(long e);
	// bool CResult_FundingCreatedDecodeErrorZ_is_ok(const struct LDKCResult_FundingCreatedDecodeErrorZ *NONNULL_PTR o);
	public static native boolean CResult_FundingCreatedDecodeErrorZ_is_ok(long o);
	// void CResult_FundingCreatedDecodeErrorZ_free(struct LDKCResult_FundingCreatedDecodeErrorZ _res);
	public static native void CResult_FundingCreatedDecodeErrorZ_free(long _res);
	// uintptr_t CResult_FundingCreatedDecodeErrorZ_clone_ptr(LDKCResult_FundingCreatedDecodeErrorZ *NONNULL_PTR arg);
	public static native long CResult_FundingCreatedDecodeErrorZ_clone_ptr(long arg);
	// struct LDKCResult_FundingCreatedDecodeErrorZ CResult_FundingCreatedDecodeErrorZ_clone(const struct LDKCResult_FundingCreatedDecodeErrorZ *NONNULL_PTR orig);
	public static native long CResult_FundingCreatedDecodeErrorZ_clone(long orig);
	// struct LDKCResult_FundingSignedDecodeErrorZ CResult_FundingSignedDecodeErrorZ_ok(struct LDKFundingSigned o);
	public static native long CResult_FundingSignedDecodeErrorZ_ok(long o);
	// struct LDKCResult_FundingSignedDecodeErrorZ CResult_FundingSignedDecodeErrorZ_err(struct LDKDecodeError e);
	public static native long CResult_FundingSignedDecodeErrorZ_err(long e);
	// bool CResult_FundingSignedDecodeErrorZ_is_ok(const struct LDKCResult_FundingSignedDecodeErrorZ *NONNULL_PTR o);
	public static native boolean CResult_FundingSignedDecodeErrorZ_is_ok(long o);
	// void CResult_FundingSignedDecodeErrorZ_free(struct LDKCResult_FundingSignedDecodeErrorZ _res);
	public static native void CResult_FundingSignedDecodeErrorZ_free(long _res);
	// uintptr_t CResult_FundingSignedDecodeErrorZ_clone_ptr(LDKCResult_FundingSignedDecodeErrorZ *NONNULL_PTR arg);
	public static native long CResult_FundingSignedDecodeErrorZ_clone_ptr(long arg);
	// struct LDKCResult_FundingSignedDecodeErrorZ CResult_FundingSignedDecodeErrorZ_clone(const struct LDKCResult_FundingSignedDecodeErrorZ *NONNULL_PTR orig);
	public static native long CResult_FundingSignedDecodeErrorZ_clone(long orig);
	// struct LDKCResult_FundingLockedDecodeErrorZ CResult_FundingLockedDecodeErrorZ_ok(struct LDKFundingLocked o);
	public static native long CResult_FundingLockedDecodeErrorZ_ok(long o);
	// struct LDKCResult_FundingLockedDecodeErrorZ CResult_FundingLockedDecodeErrorZ_err(struct LDKDecodeError e);
	public static native long CResult_FundingLockedDecodeErrorZ_err(long e);
	// bool CResult_FundingLockedDecodeErrorZ_is_ok(const struct LDKCResult_FundingLockedDecodeErrorZ *NONNULL_PTR o);
	public static native boolean CResult_FundingLockedDecodeErrorZ_is_ok(long o);
	// void CResult_FundingLockedDecodeErrorZ_free(struct LDKCResult_FundingLockedDecodeErrorZ _res);
	public static native void CResult_FundingLockedDecodeErrorZ_free(long _res);
	// uintptr_t CResult_FundingLockedDecodeErrorZ_clone_ptr(LDKCResult_FundingLockedDecodeErrorZ *NONNULL_PTR arg);
	public static native long CResult_FundingLockedDecodeErrorZ_clone_ptr(long arg);
	// struct LDKCResult_FundingLockedDecodeErrorZ CResult_FundingLockedDecodeErrorZ_clone(const struct LDKCResult_FundingLockedDecodeErrorZ *NONNULL_PTR orig);
	public static native long CResult_FundingLockedDecodeErrorZ_clone(long orig);
	// struct LDKCResult_InitDecodeErrorZ CResult_InitDecodeErrorZ_ok(struct LDKInit o);
	public static native long CResult_InitDecodeErrorZ_ok(long o);
	// struct LDKCResult_InitDecodeErrorZ CResult_InitDecodeErrorZ_err(struct LDKDecodeError e);
	public static native long CResult_InitDecodeErrorZ_err(long e);
	// bool CResult_InitDecodeErrorZ_is_ok(const struct LDKCResult_InitDecodeErrorZ *NONNULL_PTR o);
	public static native boolean CResult_InitDecodeErrorZ_is_ok(long o);
	// void CResult_InitDecodeErrorZ_free(struct LDKCResult_InitDecodeErrorZ _res);
	public static native void CResult_InitDecodeErrorZ_free(long _res);
	// uintptr_t CResult_InitDecodeErrorZ_clone_ptr(LDKCResult_InitDecodeErrorZ *NONNULL_PTR arg);
	public static native long CResult_InitDecodeErrorZ_clone_ptr(long arg);
	// struct LDKCResult_InitDecodeErrorZ CResult_InitDecodeErrorZ_clone(const struct LDKCResult_InitDecodeErrorZ *NONNULL_PTR orig);
	public static native long CResult_InitDecodeErrorZ_clone(long orig);
	// struct LDKCResult_OpenChannelDecodeErrorZ CResult_OpenChannelDecodeErrorZ_ok(struct LDKOpenChannel o);
	public static native long CResult_OpenChannelDecodeErrorZ_ok(long o);
	// struct LDKCResult_OpenChannelDecodeErrorZ CResult_OpenChannelDecodeErrorZ_err(struct LDKDecodeError e);
	public static native long CResult_OpenChannelDecodeErrorZ_err(long e);
	// bool CResult_OpenChannelDecodeErrorZ_is_ok(const struct LDKCResult_OpenChannelDecodeErrorZ *NONNULL_PTR o);
	public static native boolean CResult_OpenChannelDecodeErrorZ_is_ok(long o);
	// void CResult_OpenChannelDecodeErrorZ_free(struct LDKCResult_OpenChannelDecodeErrorZ _res);
	public static native void CResult_OpenChannelDecodeErrorZ_free(long _res);
	// uintptr_t CResult_OpenChannelDecodeErrorZ_clone_ptr(LDKCResult_OpenChannelDecodeErrorZ *NONNULL_PTR arg);
	public static native long CResult_OpenChannelDecodeErrorZ_clone_ptr(long arg);
	// struct LDKCResult_OpenChannelDecodeErrorZ CResult_OpenChannelDecodeErrorZ_clone(const struct LDKCResult_OpenChannelDecodeErrorZ *NONNULL_PTR orig);
	public static native long CResult_OpenChannelDecodeErrorZ_clone(long orig);
	// struct LDKCResult_RevokeAndACKDecodeErrorZ CResult_RevokeAndACKDecodeErrorZ_ok(struct LDKRevokeAndACK o);
	public static native long CResult_RevokeAndACKDecodeErrorZ_ok(long o);
	// struct LDKCResult_RevokeAndACKDecodeErrorZ CResult_RevokeAndACKDecodeErrorZ_err(struct LDKDecodeError e);
	public static native long CResult_RevokeAndACKDecodeErrorZ_err(long e);
	// bool CResult_RevokeAndACKDecodeErrorZ_is_ok(const struct LDKCResult_RevokeAndACKDecodeErrorZ *NONNULL_PTR o);
	public static native boolean CResult_RevokeAndACKDecodeErrorZ_is_ok(long o);
	// void CResult_RevokeAndACKDecodeErrorZ_free(struct LDKCResult_RevokeAndACKDecodeErrorZ _res);
	public static native void CResult_RevokeAndACKDecodeErrorZ_free(long _res);
	// uintptr_t CResult_RevokeAndACKDecodeErrorZ_clone_ptr(LDKCResult_RevokeAndACKDecodeErrorZ *NONNULL_PTR arg);
	public static native long CResult_RevokeAndACKDecodeErrorZ_clone_ptr(long arg);
	// struct LDKCResult_RevokeAndACKDecodeErrorZ CResult_RevokeAndACKDecodeErrorZ_clone(const struct LDKCResult_RevokeAndACKDecodeErrorZ *NONNULL_PTR orig);
	public static native long CResult_RevokeAndACKDecodeErrorZ_clone(long orig);
	// struct LDKCResult_ShutdownDecodeErrorZ CResult_ShutdownDecodeErrorZ_ok(struct LDKShutdown o);
	public static native long CResult_ShutdownDecodeErrorZ_ok(long o);
	// struct LDKCResult_ShutdownDecodeErrorZ CResult_ShutdownDecodeErrorZ_err(struct LDKDecodeError e);
	public static native long CResult_ShutdownDecodeErrorZ_err(long e);
	// bool CResult_ShutdownDecodeErrorZ_is_ok(const struct LDKCResult_ShutdownDecodeErrorZ *NONNULL_PTR o);
	public static native boolean CResult_ShutdownDecodeErrorZ_is_ok(long o);
	// void CResult_ShutdownDecodeErrorZ_free(struct LDKCResult_ShutdownDecodeErrorZ _res);
	public static native void CResult_ShutdownDecodeErrorZ_free(long _res);
	// uintptr_t CResult_ShutdownDecodeErrorZ_clone_ptr(LDKCResult_ShutdownDecodeErrorZ *NONNULL_PTR arg);
	public static native long CResult_ShutdownDecodeErrorZ_clone_ptr(long arg);
	// struct LDKCResult_ShutdownDecodeErrorZ CResult_ShutdownDecodeErrorZ_clone(const struct LDKCResult_ShutdownDecodeErrorZ *NONNULL_PTR orig);
	public static native long CResult_ShutdownDecodeErrorZ_clone(long orig);
	// struct LDKCResult_UpdateFailHTLCDecodeErrorZ CResult_UpdateFailHTLCDecodeErrorZ_ok(struct LDKUpdateFailHTLC o);
	public static native long CResult_UpdateFailHTLCDecodeErrorZ_ok(long o);
	// struct LDKCResult_UpdateFailHTLCDecodeErrorZ CResult_UpdateFailHTLCDecodeErrorZ_err(struct LDKDecodeError e);
	public static native long CResult_UpdateFailHTLCDecodeErrorZ_err(long e);
	// bool CResult_UpdateFailHTLCDecodeErrorZ_is_ok(const struct LDKCResult_UpdateFailHTLCDecodeErrorZ *NONNULL_PTR o);
	public static native boolean CResult_UpdateFailHTLCDecodeErrorZ_is_ok(long o);
	// void CResult_UpdateFailHTLCDecodeErrorZ_free(struct LDKCResult_UpdateFailHTLCDecodeErrorZ _res);
	public static native void CResult_UpdateFailHTLCDecodeErrorZ_free(long _res);
	// uintptr_t CResult_UpdateFailHTLCDecodeErrorZ_clone_ptr(LDKCResult_UpdateFailHTLCDecodeErrorZ *NONNULL_PTR arg);
	public static native long CResult_UpdateFailHTLCDecodeErrorZ_clone_ptr(long arg);
	// struct LDKCResult_UpdateFailHTLCDecodeErrorZ CResult_UpdateFailHTLCDecodeErrorZ_clone(const struct LDKCResult_UpdateFailHTLCDecodeErrorZ *NONNULL_PTR orig);
	public static native long CResult_UpdateFailHTLCDecodeErrorZ_clone(long orig);
	// struct LDKCResult_UpdateFailMalformedHTLCDecodeErrorZ CResult_UpdateFailMalformedHTLCDecodeErrorZ_ok(struct LDKUpdateFailMalformedHTLC o);
	public static native long CResult_UpdateFailMalformedHTLCDecodeErrorZ_ok(long o);
	// struct LDKCResult_UpdateFailMalformedHTLCDecodeErrorZ CResult_UpdateFailMalformedHTLCDecodeErrorZ_err(struct LDKDecodeError e);
	public static native long CResult_UpdateFailMalformedHTLCDecodeErrorZ_err(long e);
	// bool CResult_UpdateFailMalformedHTLCDecodeErrorZ_is_ok(const struct LDKCResult_UpdateFailMalformedHTLCDecodeErrorZ *NONNULL_PTR o);
	public static native boolean CResult_UpdateFailMalformedHTLCDecodeErrorZ_is_ok(long o);
	// void CResult_UpdateFailMalformedHTLCDecodeErrorZ_free(struct LDKCResult_UpdateFailMalformedHTLCDecodeErrorZ _res);
	public static native void CResult_UpdateFailMalformedHTLCDecodeErrorZ_free(long _res);
	// uintptr_t CResult_UpdateFailMalformedHTLCDecodeErrorZ_clone_ptr(LDKCResult_UpdateFailMalformedHTLCDecodeErrorZ *NONNULL_PTR arg);
	public static native long CResult_UpdateFailMalformedHTLCDecodeErrorZ_clone_ptr(long arg);
	// struct LDKCResult_UpdateFailMalformedHTLCDecodeErrorZ CResult_UpdateFailMalformedHTLCDecodeErrorZ_clone(const struct LDKCResult_UpdateFailMalformedHTLCDecodeErrorZ *NONNULL_PTR orig);
	public static native long CResult_UpdateFailMalformedHTLCDecodeErrorZ_clone(long orig);
	// struct LDKCResult_UpdateFeeDecodeErrorZ CResult_UpdateFeeDecodeErrorZ_ok(struct LDKUpdateFee o);
	public static native long CResult_UpdateFeeDecodeErrorZ_ok(long o);
	// struct LDKCResult_UpdateFeeDecodeErrorZ CResult_UpdateFeeDecodeErrorZ_err(struct LDKDecodeError e);
	public static native long CResult_UpdateFeeDecodeErrorZ_err(long e);
	// bool CResult_UpdateFeeDecodeErrorZ_is_ok(const struct LDKCResult_UpdateFeeDecodeErrorZ *NONNULL_PTR o);
	public static native boolean CResult_UpdateFeeDecodeErrorZ_is_ok(long o);
	// void CResult_UpdateFeeDecodeErrorZ_free(struct LDKCResult_UpdateFeeDecodeErrorZ _res);
	public static native void CResult_UpdateFeeDecodeErrorZ_free(long _res);
	// uintptr_t CResult_UpdateFeeDecodeErrorZ_clone_ptr(LDKCResult_UpdateFeeDecodeErrorZ *NONNULL_PTR arg);
	public static native long CResult_UpdateFeeDecodeErrorZ_clone_ptr(long arg);
	// struct LDKCResult_UpdateFeeDecodeErrorZ CResult_UpdateFeeDecodeErrorZ_clone(const struct LDKCResult_UpdateFeeDecodeErrorZ *NONNULL_PTR orig);
	public static native long CResult_UpdateFeeDecodeErrorZ_clone(long orig);
	// struct LDKCResult_UpdateFulfillHTLCDecodeErrorZ CResult_UpdateFulfillHTLCDecodeErrorZ_ok(struct LDKUpdateFulfillHTLC o);
	public static native long CResult_UpdateFulfillHTLCDecodeErrorZ_ok(long o);
	// struct LDKCResult_UpdateFulfillHTLCDecodeErrorZ CResult_UpdateFulfillHTLCDecodeErrorZ_err(struct LDKDecodeError e);
	public static native long CResult_UpdateFulfillHTLCDecodeErrorZ_err(long e);
	// bool CResult_UpdateFulfillHTLCDecodeErrorZ_is_ok(const struct LDKCResult_UpdateFulfillHTLCDecodeErrorZ *NONNULL_PTR o);
	public static native boolean CResult_UpdateFulfillHTLCDecodeErrorZ_is_ok(long o);
	// void CResult_UpdateFulfillHTLCDecodeErrorZ_free(struct LDKCResult_UpdateFulfillHTLCDecodeErrorZ _res);
	public static native void CResult_UpdateFulfillHTLCDecodeErrorZ_free(long _res);
	// uintptr_t CResult_UpdateFulfillHTLCDecodeErrorZ_clone_ptr(LDKCResult_UpdateFulfillHTLCDecodeErrorZ *NONNULL_PTR arg);
	public static native long CResult_UpdateFulfillHTLCDecodeErrorZ_clone_ptr(long arg);
	// struct LDKCResult_UpdateFulfillHTLCDecodeErrorZ CResult_UpdateFulfillHTLCDecodeErrorZ_clone(const struct LDKCResult_UpdateFulfillHTLCDecodeErrorZ *NONNULL_PTR orig);
	public static native long CResult_UpdateFulfillHTLCDecodeErrorZ_clone(long orig);
	// struct LDKCResult_UpdateAddHTLCDecodeErrorZ CResult_UpdateAddHTLCDecodeErrorZ_ok(struct LDKUpdateAddHTLC o);
	public static native long CResult_UpdateAddHTLCDecodeErrorZ_ok(long o);
	// struct LDKCResult_UpdateAddHTLCDecodeErrorZ CResult_UpdateAddHTLCDecodeErrorZ_err(struct LDKDecodeError e);
	public static native long CResult_UpdateAddHTLCDecodeErrorZ_err(long e);
	// bool CResult_UpdateAddHTLCDecodeErrorZ_is_ok(const struct LDKCResult_UpdateAddHTLCDecodeErrorZ *NONNULL_PTR o);
	public static native boolean CResult_UpdateAddHTLCDecodeErrorZ_is_ok(long o);
	// void CResult_UpdateAddHTLCDecodeErrorZ_free(struct LDKCResult_UpdateAddHTLCDecodeErrorZ _res);
	public static native void CResult_UpdateAddHTLCDecodeErrorZ_free(long _res);
	// uintptr_t CResult_UpdateAddHTLCDecodeErrorZ_clone_ptr(LDKCResult_UpdateAddHTLCDecodeErrorZ *NONNULL_PTR arg);
	public static native long CResult_UpdateAddHTLCDecodeErrorZ_clone_ptr(long arg);
	// struct LDKCResult_UpdateAddHTLCDecodeErrorZ CResult_UpdateAddHTLCDecodeErrorZ_clone(const struct LDKCResult_UpdateAddHTLCDecodeErrorZ *NONNULL_PTR orig);
	public static native long CResult_UpdateAddHTLCDecodeErrorZ_clone(long orig);
	// struct LDKCResult_PingDecodeErrorZ CResult_PingDecodeErrorZ_ok(struct LDKPing o);
	public static native long CResult_PingDecodeErrorZ_ok(long o);
	// struct LDKCResult_PingDecodeErrorZ CResult_PingDecodeErrorZ_err(struct LDKDecodeError e);
	public static native long CResult_PingDecodeErrorZ_err(long e);
	// bool CResult_PingDecodeErrorZ_is_ok(const struct LDKCResult_PingDecodeErrorZ *NONNULL_PTR o);
	public static native boolean CResult_PingDecodeErrorZ_is_ok(long o);
	// void CResult_PingDecodeErrorZ_free(struct LDKCResult_PingDecodeErrorZ _res);
	public static native void CResult_PingDecodeErrorZ_free(long _res);
	// uintptr_t CResult_PingDecodeErrorZ_clone_ptr(LDKCResult_PingDecodeErrorZ *NONNULL_PTR arg);
	public static native long CResult_PingDecodeErrorZ_clone_ptr(long arg);
	// struct LDKCResult_PingDecodeErrorZ CResult_PingDecodeErrorZ_clone(const struct LDKCResult_PingDecodeErrorZ *NONNULL_PTR orig);
	public static native long CResult_PingDecodeErrorZ_clone(long orig);
	// struct LDKCResult_PongDecodeErrorZ CResult_PongDecodeErrorZ_ok(struct LDKPong o);
	public static native long CResult_PongDecodeErrorZ_ok(long o);
	// struct LDKCResult_PongDecodeErrorZ CResult_PongDecodeErrorZ_err(struct LDKDecodeError e);
	public static native long CResult_PongDecodeErrorZ_err(long e);
	// bool CResult_PongDecodeErrorZ_is_ok(const struct LDKCResult_PongDecodeErrorZ *NONNULL_PTR o);
	public static native boolean CResult_PongDecodeErrorZ_is_ok(long o);
	// void CResult_PongDecodeErrorZ_free(struct LDKCResult_PongDecodeErrorZ _res);
	public static native void CResult_PongDecodeErrorZ_free(long _res);
	// uintptr_t CResult_PongDecodeErrorZ_clone_ptr(LDKCResult_PongDecodeErrorZ *NONNULL_PTR arg);
	public static native long CResult_PongDecodeErrorZ_clone_ptr(long arg);
	// struct LDKCResult_PongDecodeErrorZ CResult_PongDecodeErrorZ_clone(const struct LDKCResult_PongDecodeErrorZ *NONNULL_PTR orig);
	public static native long CResult_PongDecodeErrorZ_clone(long orig);
	// struct LDKCResult_UnsignedChannelAnnouncementDecodeErrorZ CResult_UnsignedChannelAnnouncementDecodeErrorZ_ok(struct LDKUnsignedChannelAnnouncement o);
	public static native long CResult_UnsignedChannelAnnouncementDecodeErrorZ_ok(long o);
	// struct LDKCResult_UnsignedChannelAnnouncementDecodeErrorZ CResult_UnsignedChannelAnnouncementDecodeErrorZ_err(struct LDKDecodeError e);
	public static native long CResult_UnsignedChannelAnnouncementDecodeErrorZ_err(long e);
	// bool CResult_UnsignedChannelAnnouncementDecodeErrorZ_is_ok(const struct LDKCResult_UnsignedChannelAnnouncementDecodeErrorZ *NONNULL_PTR o);
	public static native boolean CResult_UnsignedChannelAnnouncementDecodeErrorZ_is_ok(long o);
	// void CResult_UnsignedChannelAnnouncementDecodeErrorZ_free(struct LDKCResult_UnsignedChannelAnnouncementDecodeErrorZ _res);
	public static native void CResult_UnsignedChannelAnnouncementDecodeErrorZ_free(long _res);
	// uintptr_t CResult_UnsignedChannelAnnouncementDecodeErrorZ_clone_ptr(LDKCResult_UnsignedChannelAnnouncementDecodeErrorZ *NONNULL_PTR arg);
	public static native long CResult_UnsignedChannelAnnouncementDecodeErrorZ_clone_ptr(long arg);
	// struct LDKCResult_UnsignedChannelAnnouncementDecodeErrorZ CResult_UnsignedChannelAnnouncementDecodeErrorZ_clone(const struct LDKCResult_UnsignedChannelAnnouncementDecodeErrorZ *NONNULL_PTR orig);
	public static native long CResult_UnsignedChannelAnnouncementDecodeErrorZ_clone(long orig);
	// struct LDKCResult_ChannelAnnouncementDecodeErrorZ CResult_ChannelAnnouncementDecodeErrorZ_ok(struct LDKChannelAnnouncement o);
	public static native long CResult_ChannelAnnouncementDecodeErrorZ_ok(long o);
	// struct LDKCResult_ChannelAnnouncementDecodeErrorZ CResult_ChannelAnnouncementDecodeErrorZ_err(struct LDKDecodeError e);
	public static native long CResult_ChannelAnnouncementDecodeErrorZ_err(long e);
	// bool CResult_ChannelAnnouncementDecodeErrorZ_is_ok(const struct LDKCResult_ChannelAnnouncementDecodeErrorZ *NONNULL_PTR o);
	public static native boolean CResult_ChannelAnnouncementDecodeErrorZ_is_ok(long o);
	// void CResult_ChannelAnnouncementDecodeErrorZ_free(struct LDKCResult_ChannelAnnouncementDecodeErrorZ _res);
	public static native void CResult_ChannelAnnouncementDecodeErrorZ_free(long _res);
	// uintptr_t CResult_ChannelAnnouncementDecodeErrorZ_clone_ptr(LDKCResult_ChannelAnnouncementDecodeErrorZ *NONNULL_PTR arg);
	public static native long CResult_ChannelAnnouncementDecodeErrorZ_clone_ptr(long arg);
	// struct LDKCResult_ChannelAnnouncementDecodeErrorZ CResult_ChannelAnnouncementDecodeErrorZ_clone(const struct LDKCResult_ChannelAnnouncementDecodeErrorZ *NONNULL_PTR orig);
	public static native long CResult_ChannelAnnouncementDecodeErrorZ_clone(long orig);
	// struct LDKCResult_UnsignedChannelUpdateDecodeErrorZ CResult_UnsignedChannelUpdateDecodeErrorZ_ok(struct LDKUnsignedChannelUpdate o);
	public static native long CResult_UnsignedChannelUpdateDecodeErrorZ_ok(long o);
	// struct LDKCResult_UnsignedChannelUpdateDecodeErrorZ CResult_UnsignedChannelUpdateDecodeErrorZ_err(struct LDKDecodeError e);
	public static native long CResult_UnsignedChannelUpdateDecodeErrorZ_err(long e);
	// bool CResult_UnsignedChannelUpdateDecodeErrorZ_is_ok(const struct LDKCResult_UnsignedChannelUpdateDecodeErrorZ *NONNULL_PTR o);
	public static native boolean CResult_UnsignedChannelUpdateDecodeErrorZ_is_ok(long o);
	// void CResult_UnsignedChannelUpdateDecodeErrorZ_free(struct LDKCResult_UnsignedChannelUpdateDecodeErrorZ _res);
	public static native void CResult_UnsignedChannelUpdateDecodeErrorZ_free(long _res);
	// uintptr_t CResult_UnsignedChannelUpdateDecodeErrorZ_clone_ptr(LDKCResult_UnsignedChannelUpdateDecodeErrorZ *NONNULL_PTR arg);
	public static native long CResult_UnsignedChannelUpdateDecodeErrorZ_clone_ptr(long arg);
	// struct LDKCResult_UnsignedChannelUpdateDecodeErrorZ CResult_UnsignedChannelUpdateDecodeErrorZ_clone(const struct LDKCResult_UnsignedChannelUpdateDecodeErrorZ *NONNULL_PTR orig);
	public static native long CResult_UnsignedChannelUpdateDecodeErrorZ_clone(long orig);
	// struct LDKCResult_ChannelUpdateDecodeErrorZ CResult_ChannelUpdateDecodeErrorZ_ok(struct LDKChannelUpdate o);
	public static native long CResult_ChannelUpdateDecodeErrorZ_ok(long o);
	// struct LDKCResult_ChannelUpdateDecodeErrorZ CResult_ChannelUpdateDecodeErrorZ_err(struct LDKDecodeError e);
	public static native long CResult_ChannelUpdateDecodeErrorZ_err(long e);
	// bool CResult_ChannelUpdateDecodeErrorZ_is_ok(const struct LDKCResult_ChannelUpdateDecodeErrorZ *NONNULL_PTR o);
	public static native boolean CResult_ChannelUpdateDecodeErrorZ_is_ok(long o);
	// void CResult_ChannelUpdateDecodeErrorZ_free(struct LDKCResult_ChannelUpdateDecodeErrorZ _res);
	public static native void CResult_ChannelUpdateDecodeErrorZ_free(long _res);
	// uintptr_t CResult_ChannelUpdateDecodeErrorZ_clone_ptr(LDKCResult_ChannelUpdateDecodeErrorZ *NONNULL_PTR arg);
	public static native long CResult_ChannelUpdateDecodeErrorZ_clone_ptr(long arg);
	// struct LDKCResult_ChannelUpdateDecodeErrorZ CResult_ChannelUpdateDecodeErrorZ_clone(const struct LDKCResult_ChannelUpdateDecodeErrorZ *NONNULL_PTR orig);
	public static native long CResult_ChannelUpdateDecodeErrorZ_clone(long orig);
	// struct LDKCResult_ErrorMessageDecodeErrorZ CResult_ErrorMessageDecodeErrorZ_ok(struct LDKErrorMessage o);
	public static native long CResult_ErrorMessageDecodeErrorZ_ok(long o);
	// struct LDKCResult_ErrorMessageDecodeErrorZ CResult_ErrorMessageDecodeErrorZ_err(struct LDKDecodeError e);
	public static native long CResult_ErrorMessageDecodeErrorZ_err(long e);
	// bool CResult_ErrorMessageDecodeErrorZ_is_ok(const struct LDKCResult_ErrorMessageDecodeErrorZ *NONNULL_PTR o);
	public static native boolean CResult_ErrorMessageDecodeErrorZ_is_ok(long o);
	// void CResult_ErrorMessageDecodeErrorZ_free(struct LDKCResult_ErrorMessageDecodeErrorZ _res);
	public static native void CResult_ErrorMessageDecodeErrorZ_free(long _res);
	// uintptr_t CResult_ErrorMessageDecodeErrorZ_clone_ptr(LDKCResult_ErrorMessageDecodeErrorZ *NONNULL_PTR arg);
	public static native long CResult_ErrorMessageDecodeErrorZ_clone_ptr(long arg);
	// struct LDKCResult_ErrorMessageDecodeErrorZ CResult_ErrorMessageDecodeErrorZ_clone(const struct LDKCResult_ErrorMessageDecodeErrorZ *NONNULL_PTR orig);
	public static native long CResult_ErrorMessageDecodeErrorZ_clone(long orig);
	// struct LDKCResult_WarningMessageDecodeErrorZ CResult_WarningMessageDecodeErrorZ_ok(struct LDKWarningMessage o);
	public static native long CResult_WarningMessageDecodeErrorZ_ok(long o);
	// struct LDKCResult_WarningMessageDecodeErrorZ CResult_WarningMessageDecodeErrorZ_err(struct LDKDecodeError e);
	public static native long CResult_WarningMessageDecodeErrorZ_err(long e);
	// bool CResult_WarningMessageDecodeErrorZ_is_ok(const struct LDKCResult_WarningMessageDecodeErrorZ *NONNULL_PTR o);
	public static native boolean CResult_WarningMessageDecodeErrorZ_is_ok(long o);
	// void CResult_WarningMessageDecodeErrorZ_free(struct LDKCResult_WarningMessageDecodeErrorZ _res);
	public static native void CResult_WarningMessageDecodeErrorZ_free(long _res);
	// uintptr_t CResult_WarningMessageDecodeErrorZ_clone_ptr(LDKCResult_WarningMessageDecodeErrorZ *NONNULL_PTR arg);
	public static native long CResult_WarningMessageDecodeErrorZ_clone_ptr(long arg);
	// struct LDKCResult_WarningMessageDecodeErrorZ CResult_WarningMessageDecodeErrorZ_clone(const struct LDKCResult_WarningMessageDecodeErrorZ *NONNULL_PTR orig);
	public static native long CResult_WarningMessageDecodeErrorZ_clone(long orig);
	// struct LDKCResult_UnsignedNodeAnnouncementDecodeErrorZ CResult_UnsignedNodeAnnouncementDecodeErrorZ_ok(struct LDKUnsignedNodeAnnouncement o);
	public static native long CResult_UnsignedNodeAnnouncementDecodeErrorZ_ok(long o);
	// struct LDKCResult_UnsignedNodeAnnouncementDecodeErrorZ CResult_UnsignedNodeAnnouncementDecodeErrorZ_err(struct LDKDecodeError e);
	public static native long CResult_UnsignedNodeAnnouncementDecodeErrorZ_err(long e);
	// bool CResult_UnsignedNodeAnnouncementDecodeErrorZ_is_ok(const struct LDKCResult_UnsignedNodeAnnouncementDecodeErrorZ *NONNULL_PTR o);
	public static native boolean CResult_UnsignedNodeAnnouncementDecodeErrorZ_is_ok(long o);
	// void CResult_UnsignedNodeAnnouncementDecodeErrorZ_free(struct LDKCResult_UnsignedNodeAnnouncementDecodeErrorZ _res);
	public static native void CResult_UnsignedNodeAnnouncementDecodeErrorZ_free(long _res);
	// uintptr_t CResult_UnsignedNodeAnnouncementDecodeErrorZ_clone_ptr(LDKCResult_UnsignedNodeAnnouncementDecodeErrorZ *NONNULL_PTR arg);
	public static native long CResult_UnsignedNodeAnnouncementDecodeErrorZ_clone_ptr(long arg);
	// struct LDKCResult_UnsignedNodeAnnouncementDecodeErrorZ CResult_UnsignedNodeAnnouncementDecodeErrorZ_clone(const struct LDKCResult_UnsignedNodeAnnouncementDecodeErrorZ *NONNULL_PTR orig);
	public static native long CResult_UnsignedNodeAnnouncementDecodeErrorZ_clone(long orig);
	// struct LDKCResult_NodeAnnouncementDecodeErrorZ CResult_NodeAnnouncementDecodeErrorZ_ok(struct LDKNodeAnnouncement o);
	public static native long CResult_NodeAnnouncementDecodeErrorZ_ok(long o);
	// struct LDKCResult_NodeAnnouncementDecodeErrorZ CResult_NodeAnnouncementDecodeErrorZ_err(struct LDKDecodeError e);
	public static native long CResult_NodeAnnouncementDecodeErrorZ_err(long e);
	// bool CResult_NodeAnnouncementDecodeErrorZ_is_ok(const struct LDKCResult_NodeAnnouncementDecodeErrorZ *NONNULL_PTR o);
	public static native boolean CResult_NodeAnnouncementDecodeErrorZ_is_ok(long o);
	// void CResult_NodeAnnouncementDecodeErrorZ_free(struct LDKCResult_NodeAnnouncementDecodeErrorZ _res);
	public static native void CResult_NodeAnnouncementDecodeErrorZ_free(long _res);
	// uintptr_t CResult_NodeAnnouncementDecodeErrorZ_clone_ptr(LDKCResult_NodeAnnouncementDecodeErrorZ *NONNULL_PTR arg);
	public static native long CResult_NodeAnnouncementDecodeErrorZ_clone_ptr(long arg);
	// struct LDKCResult_NodeAnnouncementDecodeErrorZ CResult_NodeAnnouncementDecodeErrorZ_clone(const struct LDKCResult_NodeAnnouncementDecodeErrorZ *NONNULL_PTR orig);
	public static native long CResult_NodeAnnouncementDecodeErrorZ_clone(long orig);
	// struct LDKCResult_QueryShortChannelIdsDecodeErrorZ CResult_QueryShortChannelIdsDecodeErrorZ_ok(struct LDKQueryShortChannelIds o);
	public static native long CResult_QueryShortChannelIdsDecodeErrorZ_ok(long o);
	// struct LDKCResult_QueryShortChannelIdsDecodeErrorZ CResult_QueryShortChannelIdsDecodeErrorZ_err(struct LDKDecodeError e);
	public static native long CResult_QueryShortChannelIdsDecodeErrorZ_err(long e);
	// bool CResult_QueryShortChannelIdsDecodeErrorZ_is_ok(const struct LDKCResult_QueryShortChannelIdsDecodeErrorZ *NONNULL_PTR o);
	public static native boolean CResult_QueryShortChannelIdsDecodeErrorZ_is_ok(long o);
	// void CResult_QueryShortChannelIdsDecodeErrorZ_free(struct LDKCResult_QueryShortChannelIdsDecodeErrorZ _res);
	public static native void CResult_QueryShortChannelIdsDecodeErrorZ_free(long _res);
	// uintptr_t CResult_QueryShortChannelIdsDecodeErrorZ_clone_ptr(LDKCResult_QueryShortChannelIdsDecodeErrorZ *NONNULL_PTR arg);
	public static native long CResult_QueryShortChannelIdsDecodeErrorZ_clone_ptr(long arg);
	// struct LDKCResult_QueryShortChannelIdsDecodeErrorZ CResult_QueryShortChannelIdsDecodeErrorZ_clone(const struct LDKCResult_QueryShortChannelIdsDecodeErrorZ *NONNULL_PTR orig);
	public static native long CResult_QueryShortChannelIdsDecodeErrorZ_clone(long orig);
	// struct LDKCResult_ReplyShortChannelIdsEndDecodeErrorZ CResult_ReplyShortChannelIdsEndDecodeErrorZ_ok(struct LDKReplyShortChannelIdsEnd o);
	public static native long CResult_ReplyShortChannelIdsEndDecodeErrorZ_ok(long o);
	// struct LDKCResult_ReplyShortChannelIdsEndDecodeErrorZ CResult_ReplyShortChannelIdsEndDecodeErrorZ_err(struct LDKDecodeError e);
	public static native long CResult_ReplyShortChannelIdsEndDecodeErrorZ_err(long e);
	// bool CResult_ReplyShortChannelIdsEndDecodeErrorZ_is_ok(const struct LDKCResult_ReplyShortChannelIdsEndDecodeErrorZ *NONNULL_PTR o);
	public static native boolean CResult_ReplyShortChannelIdsEndDecodeErrorZ_is_ok(long o);
	// void CResult_ReplyShortChannelIdsEndDecodeErrorZ_free(struct LDKCResult_ReplyShortChannelIdsEndDecodeErrorZ _res);
	public static native void CResult_ReplyShortChannelIdsEndDecodeErrorZ_free(long _res);
	// uintptr_t CResult_ReplyShortChannelIdsEndDecodeErrorZ_clone_ptr(LDKCResult_ReplyShortChannelIdsEndDecodeErrorZ *NONNULL_PTR arg);
	public static native long CResult_ReplyShortChannelIdsEndDecodeErrorZ_clone_ptr(long arg);
	// struct LDKCResult_ReplyShortChannelIdsEndDecodeErrorZ CResult_ReplyShortChannelIdsEndDecodeErrorZ_clone(const struct LDKCResult_ReplyShortChannelIdsEndDecodeErrorZ *NONNULL_PTR orig);
	public static native long CResult_ReplyShortChannelIdsEndDecodeErrorZ_clone(long orig);
	// struct LDKCResult_QueryChannelRangeDecodeErrorZ CResult_QueryChannelRangeDecodeErrorZ_ok(struct LDKQueryChannelRange o);
	public static native long CResult_QueryChannelRangeDecodeErrorZ_ok(long o);
	// struct LDKCResult_QueryChannelRangeDecodeErrorZ CResult_QueryChannelRangeDecodeErrorZ_err(struct LDKDecodeError e);
	public static native long CResult_QueryChannelRangeDecodeErrorZ_err(long e);
	// bool CResult_QueryChannelRangeDecodeErrorZ_is_ok(const struct LDKCResult_QueryChannelRangeDecodeErrorZ *NONNULL_PTR o);
	public static native boolean CResult_QueryChannelRangeDecodeErrorZ_is_ok(long o);
	// void CResult_QueryChannelRangeDecodeErrorZ_free(struct LDKCResult_QueryChannelRangeDecodeErrorZ _res);
	public static native void CResult_QueryChannelRangeDecodeErrorZ_free(long _res);
	// uintptr_t CResult_QueryChannelRangeDecodeErrorZ_clone_ptr(LDKCResult_QueryChannelRangeDecodeErrorZ *NONNULL_PTR arg);
	public static native long CResult_QueryChannelRangeDecodeErrorZ_clone_ptr(long arg);
	// struct LDKCResult_QueryChannelRangeDecodeErrorZ CResult_QueryChannelRangeDecodeErrorZ_clone(const struct LDKCResult_QueryChannelRangeDecodeErrorZ *NONNULL_PTR orig);
	public static native long CResult_QueryChannelRangeDecodeErrorZ_clone(long orig);
	// struct LDKCResult_ReplyChannelRangeDecodeErrorZ CResult_ReplyChannelRangeDecodeErrorZ_ok(struct LDKReplyChannelRange o);
	public static native long CResult_ReplyChannelRangeDecodeErrorZ_ok(long o);
	// struct LDKCResult_ReplyChannelRangeDecodeErrorZ CResult_ReplyChannelRangeDecodeErrorZ_err(struct LDKDecodeError e);
	public static native long CResult_ReplyChannelRangeDecodeErrorZ_err(long e);
	// bool CResult_ReplyChannelRangeDecodeErrorZ_is_ok(const struct LDKCResult_ReplyChannelRangeDecodeErrorZ *NONNULL_PTR o);
	public static native boolean CResult_ReplyChannelRangeDecodeErrorZ_is_ok(long o);
	// void CResult_ReplyChannelRangeDecodeErrorZ_free(struct LDKCResult_ReplyChannelRangeDecodeErrorZ _res);
	public static native void CResult_ReplyChannelRangeDecodeErrorZ_free(long _res);
	// uintptr_t CResult_ReplyChannelRangeDecodeErrorZ_clone_ptr(LDKCResult_ReplyChannelRangeDecodeErrorZ *NONNULL_PTR arg);
	public static native long CResult_ReplyChannelRangeDecodeErrorZ_clone_ptr(long arg);
	// struct LDKCResult_ReplyChannelRangeDecodeErrorZ CResult_ReplyChannelRangeDecodeErrorZ_clone(const struct LDKCResult_ReplyChannelRangeDecodeErrorZ *NONNULL_PTR orig);
	public static native long CResult_ReplyChannelRangeDecodeErrorZ_clone(long orig);
	// struct LDKCResult_GossipTimestampFilterDecodeErrorZ CResult_GossipTimestampFilterDecodeErrorZ_ok(struct LDKGossipTimestampFilter o);
	public static native long CResult_GossipTimestampFilterDecodeErrorZ_ok(long o);
	// struct LDKCResult_GossipTimestampFilterDecodeErrorZ CResult_GossipTimestampFilterDecodeErrorZ_err(struct LDKDecodeError e);
	public static native long CResult_GossipTimestampFilterDecodeErrorZ_err(long e);
	// bool CResult_GossipTimestampFilterDecodeErrorZ_is_ok(const struct LDKCResult_GossipTimestampFilterDecodeErrorZ *NONNULL_PTR o);
	public static native boolean CResult_GossipTimestampFilterDecodeErrorZ_is_ok(long o);
	// void CResult_GossipTimestampFilterDecodeErrorZ_free(struct LDKCResult_GossipTimestampFilterDecodeErrorZ _res);
	public static native void CResult_GossipTimestampFilterDecodeErrorZ_free(long _res);
	// uintptr_t CResult_GossipTimestampFilterDecodeErrorZ_clone_ptr(LDKCResult_GossipTimestampFilterDecodeErrorZ *NONNULL_PTR arg);
	public static native long CResult_GossipTimestampFilterDecodeErrorZ_clone_ptr(long arg);
	// struct LDKCResult_GossipTimestampFilterDecodeErrorZ CResult_GossipTimestampFilterDecodeErrorZ_clone(const struct LDKCResult_GossipTimestampFilterDecodeErrorZ *NONNULL_PTR orig);
	public static native long CResult_GossipTimestampFilterDecodeErrorZ_clone(long orig);
	// void CVec_PhantomRouteHintsZ_free(struct LDKCVec_PhantomRouteHintsZ _res);
	public static native void CVec_PhantomRouteHintsZ_free(long[] _res);
	// struct LDKCResult_InvoiceSignOrCreationErrorZ CResult_InvoiceSignOrCreationErrorZ_ok(struct LDKInvoice o);
	public static native long CResult_InvoiceSignOrCreationErrorZ_ok(long o);
	// struct LDKCResult_InvoiceSignOrCreationErrorZ CResult_InvoiceSignOrCreationErrorZ_err(struct LDKSignOrCreationError e);
	public static native long CResult_InvoiceSignOrCreationErrorZ_err(long e);
	// bool CResult_InvoiceSignOrCreationErrorZ_is_ok(const struct LDKCResult_InvoiceSignOrCreationErrorZ *NONNULL_PTR o);
	public static native boolean CResult_InvoiceSignOrCreationErrorZ_is_ok(long o);
	// void CResult_InvoiceSignOrCreationErrorZ_free(struct LDKCResult_InvoiceSignOrCreationErrorZ _res);
	public static native void CResult_InvoiceSignOrCreationErrorZ_free(long _res);
	// uintptr_t CResult_InvoiceSignOrCreationErrorZ_clone_ptr(LDKCResult_InvoiceSignOrCreationErrorZ *NONNULL_PTR arg);
	public static native long CResult_InvoiceSignOrCreationErrorZ_clone_ptr(long arg);
	// struct LDKCResult_InvoiceSignOrCreationErrorZ CResult_InvoiceSignOrCreationErrorZ_clone(const struct LDKCResult_InvoiceSignOrCreationErrorZ *NONNULL_PTR orig);
	public static native long CResult_InvoiceSignOrCreationErrorZ_clone(long orig);
	// struct LDKCOption_FilterZ COption_FilterZ_some(struct LDKFilter o);
	public static native long COption_FilterZ_some(long o);
	// struct LDKCOption_FilterZ COption_FilterZ_none(void);
	public static native long COption_FilterZ_none();
	// void COption_FilterZ_free(struct LDKCOption_FilterZ _res);
	public static native void COption_FilterZ_free(long _res);
	// struct LDKCResult_LockedChannelMonitorNoneZ CResult_LockedChannelMonitorNoneZ_ok(struct LDKLockedChannelMonitor o);
	public static native long CResult_LockedChannelMonitorNoneZ_ok(long o);
	// struct LDKCResult_LockedChannelMonitorNoneZ CResult_LockedChannelMonitorNoneZ_err(void);
	public static native long CResult_LockedChannelMonitorNoneZ_err();
	// bool CResult_LockedChannelMonitorNoneZ_is_ok(const struct LDKCResult_LockedChannelMonitorNoneZ *NONNULL_PTR o);
	public static native boolean CResult_LockedChannelMonitorNoneZ_is_ok(long o);
	// void CResult_LockedChannelMonitorNoneZ_free(struct LDKCResult_LockedChannelMonitorNoneZ _res);
	public static native void CResult_LockedChannelMonitorNoneZ_free(long _res);
	// void CVec_OutPointZ_free(struct LDKCVec_OutPointZ _res);
	public static native void CVec_OutPointZ_free(long[] _res);
	// void PaymentPurpose_free(struct LDKPaymentPurpose this_ptr);
	public static native void PaymentPurpose_free(long this_ptr);
	// uintptr_t PaymentPurpose_clone_ptr(LDKPaymentPurpose *NONNULL_PTR arg);
	public static native long PaymentPurpose_clone_ptr(long arg);
	// struct LDKPaymentPurpose PaymentPurpose_clone(const struct LDKPaymentPurpose *NONNULL_PTR orig);
	public static native long PaymentPurpose_clone(long orig);
	// struct LDKPaymentPurpose PaymentPurpose_invoice_payment(struct LDKThirtyTwoBytes payment_preimage, struct LDKThirtyTwoBytes payment_secret);
	public static native long PaymentPurpose_invoice_payment(byte[] payment_preimage, byte[] payment_secret);
	// struct LDKPaymentPurpose PaymentPurpose_spontaneous_payment(struct LDKThirtyTwoBytes a);
	public static native long PaymentPurpose_spontaneous_payment(byte[] a);
	// void ClosureReason_free(struct LDKClosureReason this_ptr);
	public static native void ClosureReason_free(long this_ptr);
	// uintptr_t ClosureReason_clone_ptr(LDKClosureReason *NONNULL_PTR arg);
	public static native long ClosureReason_clone_ptr(long arg);
	// struct LDKClosureReason ClosureReason_clone(const struct LDKClosureReason *NONNULL_PTR orig);
	public static native long ClosureReason_clone(long orig);
	// struct LDKClosureReason ClosureReason_counterparty_force_closed(struct LDKStr peer_msg);
	public static native long ClosureReason_counterparty_force_closed(String peer_msg);
	// struct LDKClosureReason ClosureReason_holder_force_closed(void);
	public static native long ClosureReason_holder_force_closed();
	// struct LDKClosureReason ClosureReason_cooperative_closure(void);
	public static native long ClosureReason_cooperative_closure();
	// struct LDKClosureReason ClosureReason_commitment_tx_confirmed(void);
	public static native long ClosureReason_commitment_tx_confirmed();
	// struct LDKClosureReason ClosureReason_funding_timed_out(void);
	public static native long ClosureReason_funding_timed_out();
	// struct LDKClosureReason ClosureReason_processing_error(struct LDKStr err);
	public static native long ClosureReason_processing_error(String err);
	// struct LDKClosureReason ClosureReason_disconnected_peer(void);
	public static native long ClosureReason_disconnected_peer();
	// struct LDKClosureReason ClosureReason_outdated_channel_manager(void);
	public static native long ClosureReason_outdated_channel_manager();
	// struct LDKCVec_u8Z ClosureReason_write(const struct LDKClosureReason *NONNULL_PTR obj);
	public static native byte[] ClosureReason_write(long obj);
	// struct LDKCResult_COption_ClosureReasonZDecodeErrorZ ClosureReason_read(struct LDKu8slice ser);
	public static native long ClosureReason_read(byte[] ser);
	// void Event_free(struct LDKEvent this_ptr);
	public static native void Event_free(long this_ptr);
	// uintptr_t Event_clone_ptr(LDKEvent *NONNULL_PTR arg);
	public static native long Event_clone_ptr(long arg);
	// struct LDKEvent Event_clone(const struct LDKEvent *NONNULL_PTR orig);
	public static native long Event_clone(long orig);
	// struct LDKEvent Event_funding_generation_ready(struct LDKThirtyTwoBytes temporary_channel_id, uint64_t channel_value_satoshis, struct LDKCVec_u8Z output_script, uint64_t user_channel_id);
	public static native long Event_funding_generation_ready(byte[] temporary_channel_id, long channel_value_satoshis, byte[] output_script, long user_channel_id);
	// struct LDKEvent Event_payment_received(struct LDKThirtyTwoBytes payment_hash, uint64_t amt, struct LDKPaymentPurpose purpose);
	public static native long Event_payment_received(byte[] payment_hash, long amt, long purpose);
	// struct LDKEvent Event_payment_sent(struct LDKThirtyTwoBytes payment_id, struct LDKThirtyTwoBytes payment_preimage, struct LDKThirtyTwoBytes payment_hash, struct LDKCOption_u64Z fee_paid_msat);
	public static native long Event_payment_sent(byte[] payment_id, byte[] payment_preimage, byte[] payment_hash, long fee_paid_msat);
	// struct LDKEvent Event_payment_path_failed(struct LDKThirtyTwoBytes payment_id, struct LDKThirtyTwoBytes payment_hash, bool rejected_by_dest, struct LDKCOption_NetworkUpdateZ network_update, bool all_paths_failed, struct LDKCVec_RouteHopZ path, struct LDKCOption_u64Z short_channel_id, struct LDKRouteParameters retry);
	public static native long Event_payment_path_failed(byte[] payment_id, byte[] payment_hash, boolean rejected_by_dest, long network_update, boolean all_paths_failed, long[] path, long short_channel_id, long retry);
	// struct LDKEvent Event_payment_failed(struct LDKThirtyTwoBytes payment_id, struct LDKThirtyTwoBytes payment_hash);
	public static native long Event_payment_failed(byte[] payment_id, byte[] payment_hash);
	// struct LDKEvent Event_pending_htlcs_forwardable(uint64_t time_forwardable);
	public static native long Event_pending_htlcs_forwardable(long time_forwardable);
	// struct LDKEvent Event_spendable_outputs(struct LDKCVec_SpendableOutputDescriptorZ outputs);
	public static native long Event_spendable_outputs(long[] outputs);
	// struct LDKEvent Event_payment_forwarded(struct LDKCOption_u64Z fee_earned_msat, bool claim_from_onchain_tx);
	public static native long Event_payment_forwarded(long fee_earned_msat, boolean claim_from_onchain_tx);
	// struct LDKEvent Event_channel_closed(struct LDKThirtyTwoBytes channel_id, uint64_t user_channel_id, struct LDKClosureReason reason);
	public static native long Event_channel_closed(byte[] channel_id, long user_channel_id, long reason);
	// struct LDKEvent Event_discard_funding(struct LDKThirtyTwoBytes channel_id, struct LDKTransaction transaction);
	public static native long Event_discard_funding(byte[] channel_id, byte[] transaction);
	// struct LDKEvent Event_payment_path_successful(struct LDKThirtyTwoBytes payment_id, struct LDKThirtyTwoBytes payment_hash, struct LDKCVec_RouteHopZ path);
	public static native long Event_payment_path_successful(byte[] payment_id, byte[] payment_hash, long[] path);
	// struct LDKEvent Event_open_channel_request(struct LDKThirtyTwoBytes temporary_channel_id, struct LDKPublicKey counterparty_node_id, uint64_t funding_satoshis, uint64_t push_msat, struct LDKChannelTypeFeatures channel_type);
	public static native long Event_open_channel_request(byte[] temporary_channel_id, byte[] counterparty_node_id, long funding_satoshis, long push_msat, long channel_type);
	// struct LDKCVec_u8Z Event_write(const struct LDKEvent *NONNULL_PTR obj);
	public static native byte[] Event_write(long obj);
	// struct LDKCResult_COption_EventZDecodeErrorZ Event_read(struct LDKu8slice ser);
	public static native long Event_read(byte[] ser);
	// void MessageSendEvent_free(struct LDKMessageSendEvent this_ptr);
	public static native void MessageSendEvent_free(long this_ptr);
	// uintptr_t MessageSendEvent_clone_ptr(LDKMessageSendEvent *NONNULL_PTR arg);
	public static native long MessageSendEvent_clone_ptr(long arg);
	// struct LDKMessageSendEvent MessageSendEvent_clone(const struct LDKMessageSendEvent *NONNULL_PTR orig);
	public static native long MessageSendEvent_clone(long orig);
	// struct LDKMessageSendEvent MessageSendEvent_send_accept_channel(struct LDKPublicKey node_id, struct LDKAcceptChannel msg);
	public static native long MessageSendEvent_send_accept_channel(byte[] node_id, long msg);
	// struct LDKMessageSendEvent MessageSendEvent_send_open_channel(struct LDKPublicKey node_id, struct LDKOpenChannel msg);
	public static native long MessageSendEvent_send_open_channel(byte[] node_id, long msg);
	// struct LDKMessageSendEvent MessageSendEvent_send_funding_created(struct LDKPublicKey node_id, struct LDKFundingCreated msg);
	public static native long MessageSendEvent_send_funding_created(byte[] node_id, long msg);
	// struct LDKMessageSendEvent MessageSendEvent_send_funding_signed(struct LDKPublicKey node_id, struct LDKFundingSigned msg);
	public static native long MessageSendEvent_send_funding_signed(byte[] node_id, long msg);
	// struct LDKMessageSendEvent MessageSendEvent_send_funding_locked(struct LDKPublicKey node_id, struct LDKFundingLocked msg);
	public static native long MessageSendEvent_send_funding_locked(byte[] node_id, long msg);
	// struct LDKMessageSendEvent MessageSendEvent_send_announcement_signatures(struct LDKPublicKey node_id, struct LDKAnnouncementSignatures msg);
	public static native long MessageSendEvent_send_announcement_signatures(byte[] node_id, long msg);
	// struct LDKMessageSendEvent MessageSendEvent_update_htlcs(struct LDKPublicKey node_id, struct LDKCommitmentUpdate updates);
	public static native long MessageSendEvent_update_htlcs(byte[] node_id, long updates);
	// struct LDKMessageSendEvent MessageSendEvent_send_revoke_and_ack(struct LDKPublicKey node_id, struct LDKRevokeAndACK msg);
	public static native long MessageSendEvent_send_revoke_and_ack(byte[] node_id, long msg);
	// struct LDKMessageSendEvent MessageSendEvent_send_closing_signed(struct LDKPublicKey node_id, struct LDKClosingSigned msg);
	public static native long MessageSendEvent_send_closing_signed(byte[] node_id, long msg);
	// struct LDKMessageSendEvent MessageSendEvent_send_shutdown(struct LDKPublicKey node_id, struct LDKShutdown msg);
	public static native long MessageSendEvent_send_shutdown(byte[] node_id, long msg);
	// struct LDKMessageSendEvent MessageSendEvent_send_channel_reestablish(struct LDKPublicKey node_id, struct LDKChannelReestablish msg);
	public static native long MessageSendEvent_send_channel_reestablish(byte[] node_id, long msg);
	// struct LDKMessageSendEvent MessageSendEvent_broadcast_channel_announcement(struct LDKChannelAnnouncement msg, struct LDKChannelUpdate update_msg);
	public static native long MessageSendEvent_broadcast_channel_announcement(long msg, long update_msg);
	// struct LDKMessageSendEvent MessageSendEvent_broadcast_node_announcement(struct LDKNodeAnnouncement msg);
	public static native long MessageSendEvent_broadcast_node_announcement(long msg);
	// struct LDKMessageSendEvent MessageSendEvent_broadcast_channel_update(struct LDKChannelUpdate msg);
	public static native long MessageSendEvent_broadcast_channel_update(long msg);
	// struct LDKMessageSendEvent MessageSendEvent_send_channel_update(struct LDKPublicKey node_id, struct LDKChannelUpdate msg);
	public static native long MessageSendEvent_send_channel_update(byte[] node_id, long msg);
	// struct LDKMessageSendEvent MessageSendEvent_handle_error(struct LDKPublicKey node_id, struct LDKErrorAction action);
	public static native long MessageSendEvent_handle_error(byte[] node_id, long action);
	// struct LDKMessageSendEvent MessageSendEvent_send_channel_range_query(struct LDKPublicKey node_id, struct LDKQueryChannelRange msg);
	public static native long MessageSendEvent_send_channel_range_query(byte[] node_id, long msg);
	// struct LDKMessageSendEvent MessageSendEvent_send_short_ids_query(struct LDKPublicKey node_id, struct LDKQueryShortChannelIds msg);
	public static native long MessageSendEvent_send_short_ids_query(byte[] node_id, long msg);
	// struct LDKMessageSendEvent MessageSendEvent_send_reply_channel_range(struct LDKPublicKey node_id, struct LDKReplyChannelRange msg);
	public static native long MessageSendEvent_send_reply_channel_range(byte[] node_id, long msg);
	// struct LDKMessageSendEvent MessageSendEvent_send_gossip_timestamp_filter(struct LDKPublicKey node_id, struct LDKGossipTimestampFilter msg);
	public static native long MessageSendEvent_send_gossip_timestamp_filter(byte[] node_id, long msg);
	// void MessageSendEventsProvider_free(struct LDKMessageSendEventsProvider this_ptr);
	public static native void MessageSendEventsProvider_free(long this_ptr);
	// void EventsProvider_free(struct LDKEventsProvider this_ptr);
	public static native void EventsProvider_free(long this_ptr);
	// void EventHandler_free(struct LDKEventHandler this_ptr);
	public static native void EventHandler_free(long this_ptr);
	// void APIError_free(struct LDKAPIError this_ptr);
	public static native void APIError_free(long this_ptr);
	// uintptr_t APIError_clone_ptr(LDKAPIError *NONNULL_PTR arg);
	public static native long APIError_clone_ptr(long arg);
	// struct LDKAPIError APIError_clone(const struct LDKAPIError *NONNULL_PTR orig);
	public static native long APIError_clone(long orig);
	// struct LDKAPIError APIError_apimisuse_error(struct LDKStr err);
	public static native long APIError_apimisuse_error(String err);
	// struct LDKAPIError APIError_fee_rate_too_high(struct LDKStr err, uint32_t feerate);
	public static native long APIError_fee_rate_too_high(String err, int feerate);
	// struct LDKAPIError APIError_route_error(struct LDKStr err);
	public static native long APIError_route_error(String err);
	// struct LDKAPIError APIError_channel_unavailable(struct LDKStr err);
	public static native long APIError_channel_unavailable(String err);
	// struct LDKAPIError APIError_monitor_update_failed(void);
	public static native long APIError_monitor_update_failed();
	// struct LDKAPIError APIError_incompatible_shutdown_script(struct LDKShutdownScript script);
	public static native long APIError_incompatible_shutdown_script(long script);
	// struct LDKCResult_StringErrorZ sign(struct LDKu8slice msg, const uint8_t (*sk)[32]);
	public static native long sign(byte[] msg, byte[] sk);
	// struct LDKCResult_PublicKeyErrorZ recover_pk(struct LDKu8slice msg, struct LDKStr sig);
	public static native long recover_pk(byte[] msg, String sig);
	// bool verify(struct LDKu8slice msg, struct LDKStr sig, struct LDKPublicKey pk);
	public static native boolean verify(byte[] msg, String sig, byte[] pk);
	// struct LDKCVec_u8Z construct_invoice_preimage(struct LDKu8slice hrp_bytes, struct LDKCVec_u5Z data_without_signature);
	public static native byte[] construct_invoice_preimage(byte[] hrp_bytes, byte[] data_without_signature);
	// enum LDKLevel Level_clone(const enum LDKLevel *NONNULL_PTR orig);
	public static native Level Level_clone(long orig);
	// enum LDKLevel Level_gossip(void);
	public static native Level Level_gossip();
	// enum LDKLevel Level_trace(void);
	public static native Level Level_trace();
	// enum LDKLevel Level_debug(void);
	public static native Level Level_debug();
	// enum LDKLevel Level_info(void);
	public static native Level Level_info();
	// enum LDKLevel Level_warn(void);
	public static native Level Level_warn();
	// enum LDKLevel Level_error(void);
	public static native Level Level_error();
	// bool Level_eq(const enum LDKLevel *NONNULL_PTR a, const enum LDKLevel *NONNULL_PTR b);
	public static native boolean Level_eq(long a, long b);
	// uint64_t Level_hash(const enum LDKLevel *NONNULL_PTR o);
	public static native long Level_hash(long o);
	// MUST_USE_RES enum LDKLevel Level_max(void);
	public static native Level Level_max();
	// void Record_free(struct LDKRecord this_obj);
	public static native void Record_free(long this_obj);
	// enum LDKLevel Record_get_level(const struct LDKRecord *NONNULL_PTR this_ptr);
	public static native Level Record_get_level(long this_ptr);
	// void Record_set_level(struct LDKRecord *NONNULL_PTR this_ptr, enum LDKLevel val);
	public static native void Record_set_level(long this_ptr, Level val);
	// struct LDKStr Record_get_args(const struct LDKRecord *NONNULL_PTR this_ptr);
	public static native String Record_get_args(long this_ptr);
	// void Record_set_args(struct LDKRecord *NONNULL_PTR this_ptr, struct LDKStr val);
	public static native void Record_set_args(long this_ptr, String val);
	// struct LDKStr Record_get_module_path(const struct LDKRecord *NONNULL_PTR this_ptr);
	public static native String Record_get_module_path(long this_ptr);
	// void Record_set_module_path(struct LDKRecord *NONNULL_PTR this_ptr, struct LDKStr val);
	public static native void Record_set_module_path(long this_ptr, String val);
	// struct LDKStr Record_get_file(const struct LDKRecord *NONNULL_PTR this_ptr);
	public static native String Record_get_file(long this_ptr);
	// void Record_set_file(struct LDKRecord *NONNULL_PTR this_ptr, struct LDKStr val);
	public static native void Record_set_file(long this_ptr, String val);
	// uint32_t Record_get_line(const struct LDKRecord *NONNULL_PTR this_ptr);
	public static native int Record_get_line(long this_ptr);
	// void Record_set_line(struct LDKRecord *NONNULL_PTR this_ptr, uint32_t val);
	public static native void Record_set_line(long this_ptr, int val);
	// uintptr_t Record_clone_ptr(LDKRecord *NONNULL_PTR arg);
	public static native long Record_clone_ptr(long arg);
	// struct LDKRecord Record_clone(const struct LDKRecord *NONNULL_PTR orig);
	public static native long Record_clone(long orig);
	// void Logger_free(struct LDKLogger this_ptr);
	public static native void Logger_free(long this_ptr);
	// void ChannelHandshakeConfig_free(struct LDKChannelHandshakeConfig this_obj);
	public static native void ChannelHandshakeConfig_free(long this_obj);
	// uint32_t ChannelHandshakeConfig_get_minimum_depth(const struct LDKChannelHandshakeConfig *NONNULL_PTR this_ptr);
	public static native int ChannelHandshakeConfig_get_minimum_depth(long this_ptr);
	// void ChannelHandshakeConfig_set_minimum_depth(struct LDKChannelHandshakeConfig *NONNULL_PTR this_ptr, uint32_t val);
	public static native void ChannelHandshakeConfig_set_minimum_depth(long this_ptr, int val);
	// uint16_t ChannelHandshakeConfig_get_our_to_self_delay(const struct LDKChannelHandshakeConfig *NONNULL_PTR this_ptr);
	public static native short ChannelHandshakeConfig_get_our_to_self_delay(long this_ptr);
	// void ChannelHandshakeConfig_set_our_to_self_delay(struct LDKChannelHandshakeConfig *NONNULL_PTR this_ptr, uint16_t val);
	public static native void ChannelHandshakeConfig_set_our_to_self_delay(long this_ptr, short val);
	// uint64_t ChannelHandshakeConfig_get_our_htlc_minimum_msat(const struct LDKChannelHandshakeConfig *NONNULL_PTR this_ptr);
	public static native long ChannelHandshakeConfig_get_our_htlc_minimum_msat(long this_ptr);
	// void ChannelHandshakeConfig_set_our_htlc_minimum_msat(struct LDKChannelHandshakeConfig *NONNULL_PTR this_ptr, uint64_t val);
	public static native void ChannelHandshakeConfig_set_our_htlc_minimum_msat(long this_ptr, long val);
	// bool ChannelHandshakeConfig_get_negotiate_scid_privacy(const struct LDKChannelHandshakeConfig *NONNULL_PTR this_ptr);
	public static native boolean ChannelHandshakeConfig_get_negotiate_scid_privacy(long this_ptr);
	// void ChannelHandshakeConfig_set_negotiate_scid_privacy(struct LDKChannelHandshakeConfig *NONNULL_PTR this_ptr, bool val);
	public static native void ChannelHandshakeConfig_set_negotiate_scid_privacy(long this_ptr, boolean val);
	// MUST_USE_RES struct LDKChannelHandshakeConfig ChannelHandshakeConfig_new(uint32_t minimum_depth_arg, uint16_t our_to_self_delay_arg, uint64_t our_htlc_minimum_msat_arg, bool negotiate_scid_privacy_arg);
	public static native long ChannelHandshakeConfig_new(int minimum_depth_arg, short our_to_self_delay_arg, long our_htlc_minimum_msat_arg, boolean negotiate_scid_privacy_arg);
	// uintptr_t ChannelHandshakeConfig_clone_ptr(LDKChannelHandshakeConfig *NONNULL_PTR arg);
	public static native long ChannelHandshakeConfig_clone_ptr(long arg);
	// struct LDKChannelHandshakeConfig ChannelHandshakeConfig_clone(const struct LDKChannelHandshakeConfig *NONNULL_PTR orig);
	public static native long ChannelHandshakeConfig_clone(long orig);
	// MUST_USE_RES struct LDKChannelHandshakeConfig ChannelHandshakeConfig_default(void);
	public static native long ChannelHandshakeConfig_default();
	// void ChannelHandshakeLimits_free(struct LDKChannelHandshakeLimits this_obj);
	public static native void ChannelHandshakeLimits_free(long this_obj);
	// uint64_t ChannelHandshakeLimits_get_min_funding_satoshis(const struct LDKChannelHandshakeLimits *NONNULL_PTR this_ptr);
	public static native long ChannelHandshakeLimits_get_min_funding_satoshis(long this_ptr);
	// void ChannelHandshakeLimits_set_min_funding_satoshis(struct LDKChannelHandshakeLimits *NONNULL_PTR this_ptr, uint64_t val);
	public static native void ChannelHandshakeLimits_set_min_funding_satoshis(long this_ptr, long val);
	// uint64_t ChannelHandshakeLimits_get_max_htlc_minimum_msat(const struct LDKChannelHandshakeLimits *NONNULL_PTR this_ptr);
	public static native long ChannelHandshakeLimits_get_max_htlc_minimum_msat(long this_ptr);
	// void ChannelHandshakeLimits_set_max_htlc_minimum_msat(struct LDKChannelHandshakeLimits *NONNULL_PTR this_ptr, uint64_t val);
	public static native void ChannelHandshakeLimits_set_max_htlc_minimum_msat(long this_ptr, long val);
	// uint64_t ChannelHandshakeLimits_get_min_max_htlc_value_in_flight_msat(const struct LDKChannelHandshakeLimits *NONNULL_PTR this_ptr);
	public static native long ChannelHandshakeLimits_get_min_max_htlc_value_in_flight_msat(long this_ptr);
	// void ChannelHandshakeLimits_set_min_max_htlc_value_in_flight_msat(struct LDKChannelHandshakeLimits *NONNULL_PTR this_ptr, uint64_t val);
	public static native void ChannelHandshakeLimits_set_min_max_htlc_value_in_flight_msat(long this_ptr, long val);
	// uint64_t ChannelHandshakeLimits_get_max_channel_reserve_satoshis(const struct LDKChannelHandshakeLimits *NONNULL_PTR this_ptr);
	public static native long ChannelHandshakeLimits_get_max_channel_reserve_satoshis(long this_ptr);
	// void ChannelHandshakeLimits_set_max_channel_reserve_satoshis(struct LDKChannelHandshakeLimits *NONNULL_PTR this_ptr, uint64_t val);
	public static native void ChannelHandshakeLimits_set_max_channel_reserve_satoshis(long this_ptr, long val);
	// uint16_t ChannelHandshakeLimits_get_min_max_accepted_htlcs(const struct LDKChannelHandshakeLimits *NONNULL_PTR this_ptr);
	public static native short ChannelHandshakeLimits_get_min_max_accepted_htlcs(long this_ptr);
	// void ChannelHandshakeLimits_set_min_max_accepted_htlcs(struct LDKChannelHandshakeLimits *NONNULL_PTR this_ptr, uint16_t val);
	public static native void ChannelHandshakeLimits_set_min_max_accepted_htlcs(long this_ptr, short val);
	// uint32_t ChannelHandshakeLimits_get_max_minimum_depth(const struct LDKChannelHandshakeLimits *NONNULL_PTR this_ptr);
	public static native int ChannelHandshakeLimits_get_max_minimum_depth(long this_ptr);
	// void ChannelHandshakeLimits_set_max_minimum_depth(struct LDKChannelHandshakeLimits *NONNULL_PTR this_ptr, uint32_t val);
	public static native void ChannelHandshakeLimits_set_max_minimum_depth(long this_ptr, int val);
	// bool ChannelHandshakeLimits_get_force_announced_channel_preference(const struct LDKChannelHandshakeLimits *NONNULL_PTR this_ptr);
	public static native boolean ChannelHandshakeLimits_get_force_announced_channel_preference(long this_ptr);
	// void ChannelHandshakeLimits_set_force_announced_channel_preference(struct LDKChannelHandshakeLimits *NONNULL_PTR this_ptr, bool val);
	public static native void ChannelHandshakeLimits_set_force_announced_channel_preference(long this_ptr, boolean val);
	// uint16_t ChannelHandshakeLimits_get_their_to_self_delay(const struct LDKChannelHandshakeLimits *NONNULL_PTR this_ptr);
	public static native short ChannelHandshakeLimits_get_their_to_self_delay(long this_ptr);
	// void ChannelHandshakeLimits_set_their_to_self_delay(struct LDKChannelHandshakeLimits *NONNULL_PTR this_ptr, uint16_t val);
	public static native void ChannelHandshakeLimits_set_their_to_self_delay(long this_ptr, short val);
	// MUST_USE_RES struct LDKChannelHandshakeLimits ChannelHandshakeLimits_new(uint64_t min_funding_satoshis_arg, uint64_t max_htlc_minimum_msat_arg, uint64_t min_max_htlc_value_in_flight_msat_arg, uint64_t max_channel_reserve_satoshis_arg, uint16_t min_max_accepted_htlcs_arg, uint32_t max_minimum_depth_arg, bool force_announced_channel_preference_arg, uint16_t their_to_self_delay_arg);
	public static native long ChannelHandshakeLimits_new(long min_funding_satoshis_arg, long max_htlc_minimum_msat_arg, long min_max_htlc_value_in_flight_msat_arg, long max_channel_reserve_satoshis_arg, short min_max_accepted_htlcs_arg, int max_minimum_depth_arg, boolean force_announced_channel_preference_arg, short their_to_self_delay_arg);
	// uintptr_t ChannelHandshakeLimits_clone_ptr(LDKChannelHandshakeLimits *NONNULL_PTR arg);
	public static native long ChannelHandshakeLimits_clone_ptr(long arg);
	// struct LDKChannelHandshakeLimits ChannelHandshakeLimits_clone(const struct LDKChannelHandshakeLimits *NONNULL_PTR orig);
	public static native long ChannelHandshakeLimits_clone(long orig);
	// MUST_USE_RES struct LDKChannelHandshakeLimits ChannelHandshakeLimits_default(void);
	public static native long ChannelHandshakeLimits_default();
	// void ChannelConfig_free(struct LDKChannelConfig this_obj);
	public static native void ChannelConfig_free(long this_obj);
	// uint32_t ChannelConfig_get_forwarding_fee_proportional_millionths(const struct LDKChannelConfig *NONNULL_PTR this_ptr);
	public static native int ChannelConfig_get_forwarding_fee_proportional_millionths(long this_ptr);
	// void ChannelConfig_set_forwarding_fee_proportional_millionths(struct LDKChannelConfig *NONNULL_PTR this_ptr, uint32_t val);
	public static native void ChannelConfig_set_forwarding_fee_proportional_millionths(long this_ptr, int val);
	// uint32_t ChannelConfig_get_forwarding_fee_base_msat(const struct LDKChannelConfig *NONNULL_PTR this_ptr);
	public static native int ChannelConfig_get_forwarding_fee_base_msat(long this_ptr);
	// void ChannelConfig_set_forwarding_fee_base_msat(struct LDKChannelConfig *NONNULL_PTR this_ptr, uint32_t val);
	public static native void ChannelConfig_set_forwarding_fee_base_msat(long this_ptr, int val);
	// uint16_t ChannelConfig_get_cltv_expiry_delta(const struct LDKChannelConfig *NONNULL_PTR this_ptr);
	public static native short ChannelConfig_get_cltv_expiry_delta(long this_ptr);
	// void ChannelConfig_set_cltv_expiry_delta(struct LDKChannelConfig *NONNULL_PTR this_ptr, uint16_t val);
	public static native void ChannelConfig_set_cltv_expiry_delta(long this_ptr, short val);
	// bool ChannelConfig_get_announced_channel(const struct LDKChannelConfig *NONNULL_PTR this_ptr);
	public static native boolean ChannelConfig_get_announced_channel(long this_ptr);
	// void ChannelConfig_set_announced_channel(struct LDKChannelConfig *NONNULL_PTR this_ptr, bool val);
	public static native void ChannelConfig_set_announced_channel(long this_ptr, boolean val);
	// bool ChannelConfig_get_commit_upfront_shutdown_pubkey(const struct LDKChannelConfig *NONNULL_PTR this_ptr);
	public static native boolean ChannelConfig_get_commit_upfront_shutdown_pubkey(long this_ptr);
	// void ChannelConfig_set_commit_upfront_shutdown_pubkey(struct LDKChannelConfig *NONNULL_PTR this_ptr, bool val);
	public static native void ChannelConfig_set_commit_upfront_shutdown_pubkey(long this_ptr, boolean val);
	// uint64_t ChannelConfig_get_max_dust_htlc_exposure_msat(const struct LDKChannelConfig *NONNULL_PTR this_ptr);
	public static native long ChannelConfig_get_max_dust_htlc_exposure_msat(long this_ptr);
	// void ChannelConfig_set_max_dust_htlc_exposure_msat(struct LDKChannelConfig *NONNULL_PTR this_ptr, uint64_t val);
	public static native void ChannelConfig_set_max_dust_htlc_exposure_msat(long this_ptr, long val);
	// uint64_t ChannelConfig_get_force_close_avoidance_max_fee_satoshis(const struct LDKChannelConfig *NONNULL_PTR this_ptr);
	public static native long ChannelConfig_get_force_close_avoidance_max_fee_satoshis(long this_ptr);
	// void ChannelConfig_set_force_close_avoidance_max_fee_satoshis(struct LDKChannelConfig *NONNULL_PTR this_ptr, uint64_t val);
	public static native void ChannelConfig_set_force_close_avoidance_max_fee_satoshis(long this_ptr, long val);
	// MUST_USE_RES struct LDKChannelConfig ChannelConfig_new(uint32_t forwarding_fee_proportional_millionths_arg, uint32_t forwarding_fee_base_msat_arg, uint16_t cltv_expiry_delta_arg, bool announced_channel_arg, bool commit_upfront_shutdown_pubkey_arg, uint64_t max_dust_htlc_exposure_msat_arg, uint64_t force_close_avoidance_max_fee_satoshis_arg);
	public static native long ChannelConfig_new(int forwarding_fee_proportional_millionths_arg, int forwarding_fee_base_msat_arg, short cltv_expiry_delta_arg, boolean announced_channel_arg, boolean commit_upfront_shutdown_pubkey_arg, long max_dust_htlc_exposure_msat_arg, long force_close_avoidance_max_fee_satoshis_arg);
	// uintptr_t ChannelConfig_clone_ptr(LDKChannelConfig *NONNULL_PTR arg);
	public static native long ChannelConfig_clone_ptr(long arg);
	// struct LDKChannelConfig ChannelConfig_clone(const struct LDKChannelConfig *NONNULL_PTR orig);
	public static native long ChannelConfig_clone(long orig);
	// MUST_USE_RES struct LDKChannelConfig ChannelConfig_default(void);
	public static native long ChannelConfig_default();
	// struct LDKCVec_u8Z ChannelConfig_write(const struct LDKChannelConfig *NONNULL_PTR obj);
	public static native byte[] ChannelConfig_write(long obj);
	// struct LDKCResult_ChannelConfigDecodeErrorZ ChannelConfig_read(struct LDKu8slice ser);
	public static native long ChannelConfig_read(byte[] ser);
	// void UserConfig_free(struct LDKUserConfig this_obj);
	public static native void UserConfig_free(long this_obj);
	// struct LDKChannelHandshakeConfig UserConfig_get_own_channel_config(const struct LDKUserConfig *NONNULL_PTR this_ptr);
	public static native long UserConfig_get_own_channel_config(long this_ptr);
	// void UserConfig_set_own_channel_config(struct LDKUserConfig *NONNULL_PTR this_ptr, struct LDKChannelHandshakeConfig val);
	public static native void UserConfig_set_own_channel_config(long this_ptr, long val);
	// struct LDKChannelHandshakeLimits UserConfig_get_peer_channel_config_limits(const struct LDKUserConfig *NONNULL_PTR this_ptr);
	public static native long UserConfig_get_peer_channel_config_limits(long this_ptr);
	// void UserConfig_set_peer_channel_config_limits(struct LDKUserConfig *NONNULL_PTR this_ptr, struct LDKChannelHandshakeLimits val);
	public static native void UserConfig_set_peer_channel_config_limits(long this_ptr, long val);
	// struct LDKChannelConfig UserConfig_get_channel_options(const struct LDKUserConfig *NONNULL_PTR this_ptr);
	public static native long UserConfig_get_channel_options(long this_ptr);
	// void UserConfig_set_channel_options(struct LDKUserConfig *NONNULL_PTR this_ptr, struct LDKChannelConfig val);
	public static native void UserConfig_set_channel_options(long this_ptr, long val);
	// bool UserConfig_get_accept_forwards_to_priv_channels(const struct LDKUserConfig *NONNULL_PTR this_ptr);
	public static native boolean UserConfig_get_accept_forwards_to_priv_channels(long this_ptr);
	// void UserConfig_set_accept_forwards_to_priv_channels(struct LDKUserConfig *NONNULL_PTR this_ptr, bool val);
	public static native void UserConfig_set_accept_forwards_to_priv_channels(long this_ptr, boolean val);
	// bool UserConfig_get_accept_inbound_channels(const struct LDKUserConfig *NONNULL_PTR this_ptr);
	public static native boolean UserConfig_get_accept_inbound_channels(long this_ptr);
	// void UserConfig_set_accept_inbound_channels(struct LDKUserConfig *NONNULL_PTR this_ptr, bool val);
	public static native void UserConfig_set_accept_inbound_channels(long this_ptr, boolean val);
	// bool UserConfig_get_manually_accept_inbound_channels(const struct LDKUserConfig *NONNULL_PTR this_ptr);
	public static native boolean UserConfig_get_manually_accept_inbound_channels(long this_ptr);
	// void UserConfig_set_manually_accept_inbound_channels(struct LDKUserConfig *NONNULL_PTR this_ptr, bool val);
	public static native void UserConfig_set_manually_accept_inbound_channels(long this_ptr, boolean val);
	// MUST_USE_RES struct LDKUserConfig UserConfig_new(struct LDKChannelHandshakeConfig own_channel_config_arg, struct LDKChannelHandshakeLimits peer_channel_config_limits_arg, struct LDKChannelConfig channel_options_arg, bool accept_forwards_to_priv_channels_arg, bool accept_inbound_channels_arg, bool manually_accept_inbound_channels_arg);
	public static native long UserConfig_new(long own_channel_config_arg, long peer_channel_config_limits_arg, long channel_options_arg, boolean accept_forwards_to_priv_channels_arg, boolean accept_inbound_channels_arg, boolean manually_accept_inbound_channels_arg);
	// uintptr_t UserConfig_clone_ptr(LDKUserConfig *NONNULL_PTR arg);
	public static native long UserConfig_clone_ptr(long arg);
	// struct LDKUserConfig UserConfig_clone(const struct LDKUserConfig *NONNULL_PTR orig);
	public static native long UserConfig_clone(long orig);
	// MUST_USE_RES struct LDKUserConfig UserConfig_default(void);
	public static native long UserConfig_default();
	// void BestBlock_free(struct LDKBestBlock this_obj);
	public static native void BestBlock_free(long this_obj);
	// uintptr_t BestBlock_clone_ptr(LDKBestBlock *NONNULL_PTR arg);
	public static native long BestBlock_clone_ptr(long arg);
	// struct LDKBestBlock BestBlock_clone(const struct LDKBestBlock *NONNULL_PTR orig);
	public static native long BestBlock_clone(long orig);
	// MUST_USE_RES struct LDKBestBlock BestBlock_from_genesis(enum LDKNetwork network);
	public static native long BestBlock_from_genesis(Network network);
	// MUST_USE_RES struct LDKBestBlock BestBlock_new(struct LDKThirtyTwoBytes block_hash, uint32_t height);
	public static native long BestBlock_new(byte[] block_hash, int height);
	// MUST_USE_RES struct LDKThirtyTwoBytes BestBlock_block_hash(const struct LDKBestBlock *NONNULL_PTR this_arg);
	public static native byte[] BestBlock_block_hash(long this_arg);
	// MUST_USE_RES uint32_t BestBlock_height(const struct LDKBestBlock *NONNULL_PTR this_arg);
	public static native int BestBlock_height(long this_arg);
	// enum LDKAccessError AccessError_clone(const enum LDKAccessError *NONNULL_PTR orig);
	public static native AccessError AccessError_clone(long orig);
	// enum LDKAccessError AccessError_unknown_chain(void);
	public static native AccessError AccessError_unknown_chain();
	// enum LDKAccessError AccessError_unknown_tx(void);
	public static native AccessError AccessError_unknown_tx();
	// void Access_free(struct LDKAccess this_ptr);
	public static native void Access_free(long this_ptr);
	// void Listen_free(struct LDKListen this_ptr);
	public static native void Listen_free(long this_ptr);
	// void Confirm_free(struct LDKConfirm this_ptr);
	public static native void Confirm_free(long this_ptr);
	// enum LDKChannelMonitorUpdateErr ChannelMonitorUpdateErr_clone(const enum LDKChannelMonitorUpdateErr *NONNULL_PTR orig);
	public static native ChannelMonitorUpdateErr ChannelMonitorUpdateErr_clone(long orig);
	// enum LDKChannelMonitorUpdateErr ChannelMonitorUpdateErr_temporary_failure(void);
	public static native ChannelMonitorUpdateErr ChannelMonitorUpdateErr_temporary_failure();
	// enum LDKChannelMonitorUpdateErr ChannelMonitorUpdateErr_permanent_failure(void);
	public static native ChannelMonitorUpdateErr ChannelMonitorUpdateErr_permanent_failure();
	// void Watch_free(struct LDKWatch this_ptr);
	public static native void Watch_free(long this_ptr);
	// void Filter_free(struct LDKFilter this_ptr);
	public static native void Filter_free(long this_ptr);
	// void WatchedOutput_free(struct LDKWatchedOutput this_obj);
	public static native void WatchedOutput_free(long this_obj);
	// struct LDKThirtyTwoBytes WatchedOutput_get_block_hash(const struct LDKWatchedOutput *NONNULL_PTR this_ptr);
	public static native byte[] WatchedOutput_get_block_hash(long this_ptr);
	// void WatchedOutput_set_block_hash(struct LDKWatchedOutput *NONNULL_PTR this_ptr, struct LDKThirtyTwoBytes val);
	public static native void WatchedOutput_set_block_hash(long this_ptr, byte[] val);
	// struct LDKOutPoint WatchedOutput_get_outpoint(const struct LDKWatchedOutput *NONNULL_PTR this_ptr);
	public static native long WatchedOutput_get_outpoint(long this_ptr);
	// void WatchedOutput_set_outpoint(struct LDKWatchedOutput *NONNULL_PTR this_ptr, struct LDKOutPoint val);
	public static native void WatchedOutput_set_outpoint(long this_ptr, long val);
	// struct LDKu8slice WatchedOutput_get_script_pubkey(const struct LDKWatchedOutput *NONNULL_PTR this_ptr);
	public static native byte[] WatchedOutput_get_script_pubkey(long this_ptr);
	// void WatchedOutput_set_script_pubkey(struct LDKWatchedOutput *NONNULL_PTR this_ptr, struct LDKCVec_u8Z val);
	public static native void WatchedOutput_set_script_pubkey(long this_ptr, byte[] val);
	// MUST_USE_RES struct LDKWatchedOutput WatchedOutput_new(struct LDKThirtyTwoBytes block_hash_arg, struct LDKOutPoint outpoint_arg, struct LDKCVec_u8Z script_pubkey_arg);
	public static native long WatchedOutput_new(byte[] block_hash_arg, long outpoint_arg, byte[] script_pubkey_arg);
	// uintptr_t WatchedOutput_clone_ptr(LDKWatchedOutput *NONNULL_PTR arg);
	public static native long WatchedOutput_clone_ptr(long arg);
	// struct LDKWatchedOutput WatchedOutput_clone(const struct LDKWatchedOutput *NONNULL_PTR orig);
	public static native long WatchedOutput_clone(long orig);
	// uint64_t WatchedOutput_hash(const struct LDKWatchedOutput *NONNULL_PTR o);
	public static native long WatchedOutput_hash(long o);
	// void BroadcasterInterface_free(struct LDKBroadcasterInterface this_ptr);
	public static native void BroadcasterInterface_free(long this_ptr);
	// enum LDKConfirmationTarget ConfirmationTarget_clone(const enum LDKConfirmationTarget *NONNULL_PTR orig);
	public static native ConfirmationTarget ConfirmationTarget_clone(long orig);
	// enum LDKConfirmationTarget ConfirmationTarget_background(void);
	public static native ConfirmationTarget ConfirmationTarget_background();
	// enum LDKConfirmationTarget ConfirmationTarget_normal(void);
	public static native ConfirmationTarget ConfirmationTarget_normal();
	// enum LDKConfirmationTarget ConfirmationTarget_high_priority(void);
	public static native ConfirmationTarget ConfirmationTarget_high_priority();
	// bool ConfirmationTarget_eq(const enum LDKConfirmationTarget *NONNULL_PTR a, const enum LDKConfirmationTarget *NONNULL_PTR b);
	public static native boolean ConfirmationTarget_eq(long a, long b);
	// void FeeEstimator_free(struct LDKFeeEstimator this_ptr);
	public static native void FeeEstimator_free(long this_ptr);
	// void MonitorUpdateId_free(struct LDKMonitorUpdateId this_obj);
	public static native void MonitorUpdateId_free(long this_obj);
	// uintptr_t MonitorUpdateId_clone_ptr(LDKMonitorUpdateId *NONNULL_PTR arg);
	public static native long MonitorUpdateId_clone_ptr(long arg);
	// struct LDKMonitorUpdateId MonitorUpdateId_clone(const struct LDKMonitorUpdateId *NONNULL_PTR orig);
	public static native long MonitorUpdateId_clone(long orig);
	// uint64_t MonitorUpdateId_hash(const struct LDKMonitorUpdateId *NONNULL_PTR o);
	public static native long MonitorUpdateId_hash(long o);
	// bool MonitorUpdateId_eq(const struct LDKMonitorUpdateId *NONNULL_PTR a, const struct LDKMonitorUpdateId *NONNULL_PTR b);
	public static native boolean MonitorUpdateId_eq(long a, long b);
	// void Persist_free(struct LDKPersist this_ptr);
	public static native void Persist_free(long this_ptr);
	// void LockedChannelMonitor_free(struct LDKLockedChannelMonitor this_obj);
	public static native void LockedChannelMonitor_free(long this_obj);
	// void ChainMonitor_free(struct LDKChainMonitor this_obj);
	public static native void ChainMonitor_free(long this_obj);
	// MUST_USE_RES struct LDKChainMonitor ChainMonitor_new(struct LDKCOption_FilterZ chain_source, struct LDKBroadcasterInterface broadcaster, struct LDKLogger logger, struct LDKFeeEstimator feeest, struct LDKPersist persister);
	public static native long ChainMonitor_new(long chain_source, long broadcaster, long logger, long feeest, long persister);
	// MUST_USE_RES struct LDKCVec_BalanceZ ChainMonitor_get_claimable_balances(const struct LDKChainMonitor *NONNULL_PTR this_arg, struct LDKCVec_ChannelDetailsZ ignored_channels);
	public static native long[] ChainMonitor_get_claimable_balances(long this_arg, long[] ignored_channels);
	// MUST_USE_RES struct LDKCResult_LockedChannelMonitorNoneZ ChainMonitor_get_monitor(const struct LDKChainMonitor *NONNULL_PTR this_arg, struct LDKOutPoint funding_txo);
	public static native long ChainMonitor_get_monitor(long this_arg, long funding_txo);
	// MUST_USE_RES struct LDKCVec_OutPointZ ChainMonitor_list_monitors(const struct LDKChainMonitor *NONNULL_PTR this_arg);
	public static native long[] ChainMonitor_list_monitors(long this_arg);
	// MUST_USE_RES struct LDKCResult_NoneAPIErrorZ ChainMonitor_channel_monitor_updated(const struct LDKChainMonitor *NONNULL_PTR this_arg, struct LDKOutPoint funding_txo, struct LDKMonitorUpdateId completed_update_id);
	public static native long ChainMonitor_channel_monitor_updated(long this_arg, long funding_txo, long completed_update_id);
	// struct LDKListen ChainMonitor_as_Listen(const struct LDKChainMonitor *NONNULL_PTR this_arg);
	public static native long ChainMonitor_as_Listen(long this_arg);
	// struct LDKConfirm ChainMonitor_as_Confirm(const struct LDKChainMonitor *NONNULL_PTR this_arg);
	public static native long ChainMonitor_as_Confirm(long this_arg);
	// struct LDKWatch ChainMonitor_as_Watch(const struct LDKChainMonitor *NONNULL_PTR this_arg);
	public static native long ChainMonitor_as_Watch(long this_arg);
	// struct LDKEventsProvider ChainMonitor_as_EventsProvider(const struct LDKChainMonitor *NONNULL_PTR this_arg);
	public static native long ChainMonitor_as_EventsProvider(long this_arg);
	// void ChannelMonitorUpdate_free(struct LDKChannelMonitorUpdate this_obj);
	public static native void ChannelMonitorUpdate_free(long this_obj);
	// uint64_t ChannelMonitorUpdate_get_update_id(const struct LDKChannelMonitorUpdate *NONNULL_PTR this_ptr);
	public static native long ChannelMonitorUpdate_get_update_id(long this_ptr);
	// void ChannelMonitorUpdate_set_update_id(struct LDKChannelMonitorUpdate *NONNULL_PTR this_ptr, uint64_t val);
	public static native void ChannelMonitorUpdate_set_update_id(long this_ptr, long val);
	// uintptr_t ChannelMonitorUpdate_clone_ptr(LDKChannelMonitorUpdate *NONNULL_PTR arg);
	public static native long ChannelMonitorUpdate_clone_ptr(long arg);
	// struct LDKChannelMonitorUpdate ChannelMonitorUpdate_clone(const struct LDKChannelMonitorUpdate *NONNULL_PTR orig);
	public static native long ChannelMonitorUpdate_clone(long orig);
	// struct LDKCVec_u8Z ChannelMonitorUpdate_write(const struct LDKChannelMonitorUpdate *NONNULL_PTR obj);
	public static native byte[] ChannelMonitorUpdate_write(long obj);
	// struct LDKCResult_ChannelMonitorUpdateDecodeErrorZ ChannelMonitorUpdate_read(struct LDKu8slice ser);
	public static native long ChannelMonitorUpdate_read(byte[] ser);
	// void MonitorEvent_free(struct LDKMonitorEvent this_ptr);
	public static native void MonitorEvent_free(long this_ptr);
	// uintptr_t MonitorEvent_clone_ptr(LDKMonitorEvent *NONNULL_PTR arg);
	public static native long MonitorEvent_clone_ptr(long arg);
	// struct LDKMonitorEvent MonitorEvent_clone(const struct LDKMonitorEvent *NONNULL_PTR orig);
	public static native long MonitorEvent_clone(long orig);
	// struct LDKMonitorEvent MonitorEvent_htlcevent(struct LDKHTLCUpdate a);
	public static native long MonitorEvent_htlcevent(long a);
	// struct LDKMonitorEvent MonitorEvent_commitment_tx_confirmed(struct LDKOutPoint a);
	public static native long MonitorEvent_commitment_tx_confirmed(long a);
	// struct LDKMonitorEvent MonitorEvent_update_completed(struct LDKOutPoint funding_txo, uint64_t monitor_update_id);
	public static native long MonitorEvent_update_completed(long funding_txo, long monitor_update_id);
	// struct LDKMonitorEvent MonitorEvent_update_failed(struct LDKOutPoint a);
	public static native long MonitorEvent_update_failed(long a);
	// struct LDKCVec_u8Z MonitorEvent_write(const struct LDKMonitorEvent *NONNULL_PTR obj);
	public static native byte[] MonitorEvent_write(long obj);
	// struct LDKCResult_COption_MonitorEventZDecodeErrorZ MonitorEvent_read(struct LDKu8slice ser);
	public static native long MonitorEvent_read(byte[] ser);
	// void HTLCUpdate_free(struct LDKHTLCUpdate this_obj);
	public static native void HTLCUpdate_free(long this_obj);
	// uintptr_t HTLCUpdate_clone_ptr(LDKHTLCUpdate *NONNULL_PTR arg);
	public static native long HTLCUpdate_clone_ptr(long arg);
	// struct LDKHTLCUpdate HTLCUpdate_clone(const struct LDKHTLCUpdate *NONNULL_PTR orig);
	public static native long HTLCUpdate_clone(long orig);
	// struct LDKCVec_u8Z HTLCUpdate_write(const struct LDKHTLCUpdate *NONNULL_PTR obj);
	public static native byte[] HTLCUpdate_write(long obj);
	// struct LDKCResult_HTLCUpdateDecodeErrorZ HTLCUpdate_read(struct LDKu8slice ser);
	public static native long HTLCUpdate_read(byte[] ser);
	// void Balance_free(struct LDKBalance this_ptr);
	public static native void Balance_free(long this_ptr);
	// uintptr_t Balance_clone_ptr(LDKBalance *NONNULL_PTR arg);
	public static native long Balance_clone_ptr(long arg);
	// struct LDKBalance Balance_clone(const struct LDKBalance *NONNULL_PTR orig);
	public static native long Balance_clone(long orig);
	// struct LDKBalance Balance_claimable_on_channel_close(uint64_t claimable_amount_satoshis);
	public static native long Balance_claimable_on_channel_close(long claimable_amount_satoshis);
	// struct LDKBalance Balance_claimable_awaiting_confirmations(uint64_t claimable_amount_satoshis, uint32_t confirmation_height);
	public static native long Balance_claimable_awaiting_confirmations(long claimable_amount_satoshis, int confirmation_height);
	// struct LDKBalance Balance_contentious_claimable(uint64_t claimable_amount_satoshis, uint32_t timeout_height);
	public static native long Balance_contentious_claimable(long claimable_amount_satoshis, int timeout_height);
	// struct LDKBalance Balance_maybe_claimable_htlcawaiting_timeout(uint64_t claimable_amount_satoshis, uint32_t claimable_height);
	public static native long Balance_maybe_claimable_htlcawaiting_timeout(long claimable_amount_satoshis, int claimable_height);
	// bool Balance_eq(const struct LDKBalance *NONNULL_PTR a, const struct LDKBalance *NONNULL_PTR b);
	public static native boolean Balance_eq(long a, long b);
	// void ChannelMonitor_free(struct LDKChannelMonitor this_obj);
	public static native void ChannelMonitor_free(long this_obj);
	// uintptr_t ChannelMonitor_clone_ptr(LDKChannelMonitor *NONNULL_PTR arg);
	public static native long ChannelMonitor_clone_ptr(long arg);
	// struct LDKChannelMonitor ChannelMonitor_clone(const struct LDKChannelMonitor *NONNULL_PTR orig);
	public static native long ChannelMonitor_clone(long orig);
	// struct LDKCVec_u8Z ChannelMonitor_write(const struct LDKChannelMonitor *NONNULL_PTR obj);
	public static native byte[] ChannelMonitor_write(long obj);
	// MUST_USE_RES struct LDKCResult_NoneNoneZ ChannelMonitor_update_monitor(const struct LDKChannelMonitor *NONNULL_PTR this_arg, const struct LDKChannelMonitorUpdate *NONNULL_PTR updates, const struct LDKBroadcasterInterface *NONNULL_PTR broadcaster, const struct LDKFeeEstimator *NONNULL_PTR fee_estimator, const struct LDKLogger *NONNULL_PTR logger);
	public static native long ChannelMonitor_update_monitor(long this_arg, long updates, long broadcaster, long fee_estimator, long logger);
	// MUST_USE_RES uint64_t ChannelMonitor_get_latest_update_id(const struct LDKChannelMonitor *NONNULL_PTR this_arg);
	public static native long ChannelMonitor_get_latest_update_id(long this_arg);
	// MUST_USE_RES struct LDKC2Tuple_OutPointScriptZ ChannelMonitor_get_funding_txo(const struct LDKChannelMonitor *NONNULL_PTR this_arg);
	public static native long ChannelMonitor_get_funding_txo(long this_arg);
	// MUST_USE_RES struct LDKCVec_C2Tuple_TxidCVec_C2Tuple_u32ScriptZZZZ ChannelMonitor_get_outputs_to_watch(const struct LDKChannelMonitor *NONNULL_PTR this_arg);
	public static native long[] ChannelMonitor_get_outputs_to_watch(long this_arg);
	// void ChannelMonitor_load_outputs_to_watch(const struct LDKChannelMonitor *NONNULL_PTR this_arg, const struct LDKFilter *NONNULL_PTR filter);
	public static native void ChannelMonitor_load_outputs_to_watch(long this_arg, long filter);
	// MUST_USE_RES struct LDKCVec_MonitorEventZ ChannelMonitor_get_and_clear_pending_monitor_events(const struct LDKChannelMonitor *NONNULL_PTR this_arg);
	public static native long[] ChannelMonitor_get_and_clear_pending_monitor_events(long this_arg);
	// MUST_USE_RES struct LDKCVec_EventZ ChannelMonitor_get_and_clear_pending_events(const struct LDKChannelMonitor *NONNULL_PTR this_arg);
	public static native long[] ChannelMonitor_get_and_clear_pending_events(long this_arg);
	// MUST_USE_RES struct LDKCVec_TransactionZ ChannelMonitor_get_latest_holder_commitment_txn(const struct LDKChannelMonitor *NONNULL_PTR this_arg, const struct LDKLogger *NONNULL_PTR logger);
	public static native byte[][] ChannelMonitor_get_latest_holder_commitment_txn(long this_arg, long logger);
	// MUST_USE_RES struct LDKCVec_C2Tuple_TxidCVec_C2Tuple_u32TxOutZZZZ ChannelMonitor_block_connected(const struct LDKChannelMonitor *NONNULL_PTR this_arg, const uint8_t (*header)[80], struct LDKCVec_C2Tuple_usizeTransactionZZ txdata, uint32_t height, struct LDKBroadcasterInterface broadcaster, struct LDKFeeEstimator fee_estimator, struct LDKLogger logger);
	public static native long[] ChannelMonitor_block_connected(long this_arg, byte[] header, long[] txdata, int height, long broadcaster, long fee_estimator, long logger);
	// void ChannelMonitor_block_disconnected(const struct LDKChannelMonitor *NONNULL_PTR this_arg, const uint8_t (*header)[80], uint32_t height, struct LDKBroadcasterInterface broadcaster, struct LDKFeeEstimator fee_estimator, struct LDKLogger logger);
	public static native void ChannelMonitor_block_disconnected(long this_arg, byte[] header, int height, long broadcaster, long fee_estimator, long logger);
	// MUST_USE_RES struct LDKCVec_C2Tuple_TxidCVec_C2Tuple_u32TxOutZZZZ ChannelMonitor_transactions_confirmed(const struct LDKChannelMonitor *NONNULL_PTR this_arg, const uint8_t (*header)[80], struct LDKCVec_C2Tuple_usizeTransactionZZ txdata, uint32_t height, struct LDKBroadcasterInterface broadcaster, struct LDKFeeEstimator fee_estimator, struct LDKLogger logger);
	public static native long[] ChannelMonitor_transactions_confirmed(long this_arg, byte[] header, long[] txdata, int height, long broadcaster, long fee_estimator, long logger);
	// void ChannelMonitor_transaction_unconfirmed(const struct LDKChannelMonitor *NONNULL_PTR this_arg, const uint8_t (*txid)[32], struct LDKBroadcasterInterface broadcaster, struct LDKFeeEstimator fee_estimator, struct LDKLogger logger);
	public static native void ChannelMonitor_transaction_unconfirmed(long this_arg, byte[] txid, long broadcaster, long fee_estimator, long logger);
	// MUST_USE_RES struct LDKCVec_C2Tuple_TxidCVec_C2Tuple_u32TxOutZZZZ ChannelMonitor_best_block_updated(const struct LDKChannelMonitor *NONNULL_PTR this_arg, const uint8_t (*header)[80], uint32_t height, struct LDKBroadcasterInterface broadcaster, struct LDKFeeEstimator fee_estimator, struct LDKLogger logger);
	public static native long[] ChannelMonitor_best_block_updated(long this_arg, byte[] header, int height, long broadcaster, long fee_estimator, long logger);
	// MUST_USE_RES struct LDKCVec_TxidZ ChannelMonitor_get_relevant_txids(const struct LDKChannelMonitor *NONNULL_PTR this_arg);
	public static native byte[][] ChannelMonitor_get_relevant_txids(long this_arg);
	// MUST_USE_RES struct LDKBestBlock ChannelMonitor_current_best_block(const struct LDKChannelMonitor *NONNULL_PTR this_arg);
	public static native long ChannelMonitor_current_best_block(long this_arg);
	// MUST_USE_RES struct LDKCVec_BalanceZ ChannelMonitor_get_claimable_balances(const struct LDKChannelMonitor *NONNULL_PTR this_arg);
	public static native long[] ChannelMonitor_get_claimable_balances(long this_arg);
	// struct LDKCResult_C2Tuple_BlockHashChannelMonitorZDecodeErrorZ C2Tuple_BlockHashChannelMonitorZ_read(struct LDKu8slice ser, const struct LDKKeysInterface *NONNULL_PTR arg);
	public static native long C2Tuple_BlockHashChannelMonitorZ_read(byte[] ser, long arg);
	// void OutPoint_free(struct LDKOutPoint this_obj);
	public static native void OutPoint_free(long this_obj);
	// const uint8_t (*OutPoint_get_txid(const struct LDKOutPoint *NONNULL_PTR this_ptr))[32];
	public static native byte[] OutPoint_get_txid(long this_ptr);
	// void OutPoint_set_txid(struct LDKOutPoint *NONNULL_PTR this_ptr, struct LDKThirtyTwoBytes val);
	public static native void OutPoint_set_txid(long this_ptr, byte[] val);
	// uint16_t OutPoint_get_index(const struct LDKOutPoint *NONNULL_PTR this_ptr);
	public static native short OutPoint_get_index(long this_ptr);
	// void OutPoint_set_index(struct LDKOutPoint *NONNULL_PTR this_ptr, uint16_t val);
	public static native void OutPoint_set_index(long this_ptr, short val);
	// MUST_USE_RES struct LDKOutPoint OutPoint_new(struct LDKThirtyTwoBytes txid_arg, uint16_t index_arg);
	public static native long OutPoint_new(byte[] txid_arg, short index_arg);
	// uintptr_t OutPoint_clone_ptr(LDKOutPoint *NONNULL_PTR arg);
	public static native long OutPoint_clone_ptr(long arg);
	// struct LDKOutPoint OutPoint_clone(const struct LDKOutPoint *NONNULL_PTR orig);
	public static native long OutPoint_clone(long orig);
	// bool OutPoint_eq(const struct LDKOutPoint *NONNULL_PTR a, const struct LDKOutPoint *NONNULL_PTR b);
	public static native boolean OutPoint_eq(long a, long b);
	// uint64_t OutPoint_hash(const struct LDKOutPoint *NONNULL_PTR o);
	public static native long OutPoint_hash(long o);
	// MUST_USE_RES struct LDKThirtyTwoBytes OutPoint_to_channel_id(const struct LDKOutPoint *NONNULL_PTR this_arg);
	public static native byte[] OutPoint_to_channel_id(long this_arg);
	// struct LDKCVec_u8Z OutPoint_write(const struct LDKOutPoint *NONNULL_PTR obj);
	public static native byte[] OutPoint_write(long obj);
	// struct LDKCResult_OutPointDecodeErrorZ OutPoint_read(struct LDKu8slice ser);
	public static native long OutPoint_read(byte[] ser);
	// void DelayedPaymentOutputDescriptor_free(struct LDKDelayedPaymentOutputDescriptor this_obj);
	public static native void DelayedPaymentOutputDescriptor_free(long this_obj);
	// struct LDKOutPoint DelayedPaymentOutputDescriptor_get_outpoint(const struct LDKDelayedPaymentOutputDescriptor *NONNULL_PTR this_ptr);
	public static native long DelayedPaymentOutputDescriptor_get_outpoint(long this_ptr);
	// void DelayedPaymentOutputDescriptor_set_outpoint(struct LDKDelayedPaymentOutputDescriptor *NONNULL_PTR this_ptr, struct LDKOutPoint val);
	public static native void DelayedPaymentOutputDescriptor_set_outpoint(long this_ptr, long val);
	// struct LDKPublicKey DelayedPaymentOutputDescriptor_get_per_commitment_point(const struct LDKDelayedPaymentOutputDescriptor *NONNULL_PTR this_ptr);
	public static native byte[] DelayedPaymentOutputDescriptor_get_per_commitment_point(long this_ptr);
	// void DelayedPaymentOutputDescriptor_set_per_commitment_point(struct LDKDelayedPaymentOutputDescriptor *NONNULL_PTR this_ptr, struct LDKPublicKey val);
	public static native void DelayedPaymentOutputDescriptor_set_per_commitment_point(long this_ptr, byte[] val);
	// uint16_t DelayedPaymentOutputDescriptor_get_to_self_delay(const struct LDKDelayedPaymentOutputDescriptor *NONNULL_PTR this_ptr);
	public static native short DelayedPaymentOutputDescriptor_get_to_self_delay(long this_ptr);
	// void DelayedPaymentOutputDescriptor_set_to_self_delay(struct LDKDelayedPaymentOutputDescriptor *NONNULL_PTR this_ptr, uint16_t val);
	public static native void DelayedPaymentOutputDescriptor_set_to_self_delay(long this_ptr, short val);
	// void DelayedPaymentOutputDescriptor_set_output(struct LDKDelayedPaymentOutputDescriptor *NONNULL_PTR this_ptr, struct LDKTxOut val);
	public static native void DelayedPaymentOutputDescriptor_set_output(long this_ptr, long val);
	// struct LDKPublicKey DelayedPaymentOutputDescriptor_get_revocation_pubkey(const struct LDKDelayedPaymentOutputDescriptor *NONNULL_PTR this_ptr);
	public static native byte[] DelayedPaymentOutputDescriptor_get_revocation_pubkey(long this_ptr);
	// void DelayedPaymentOutputDescriptor_set_revocation_pubkey(struct LDKDelayedPaymentOutputDescriptor *NONNULL_PTR this_ptr, struct LDKPublicKey val);
	public static native void DelayedPaymentOutputDescriptor_set_revocation_pubkey(long this_ptr, byte[] val);
	// const uint8_t (*DelayedPaymentOutputDescriptor_get_channel_keys_id(const struct LDKDelayedPaymentOutputDescriptor *NONNULL_PTR this_ptr))[32];
	public static native byte[] DelayedPaymentOutputDescriptor_get_channel_keys_id(long this_ptr);
	// void DelayedPaymentOutputDescriptor_set_channel_keys_id(struct LDKDelayedPaymentOutputDescriptor *NONNULL_PTR this_ptr, struct LDKThirtyTwoBytes val);
	public static native void DelayedPaymentOutputDescriptor_set_channel_keys_id(long this_ptr, byte[] val);
	// uint64_t DelayedPaymentOutputDescriptor_get_channel_value_satoshis(const struct LDKDelayedPaymentOutputDescriptor *NONNULL_PTR this_ptr);
	public static native long DelayedPaymentOutputDescriptor_get_channel_value_satoshis(long this_ptr);
	// void DelayedPaymentOutputDescriptor_set_channel_value_satoshis(struct LDKDelayedPaymentOutputDescriptor *NONNULL_PTR this_ptr, uint64_t val);
	public static native void DelayedPaymentOutputDescriptor_set_channel_value_satoshis(long this_ptr, long val);
	// MUST_USE_RES struct LDKDelayedPaymentOutputDescriptor DelayedPaymentOutputDescriptor_new(struct LDKOutPoint outpoint_arg, struct LDKPublicKey per_commitment_point_arg, uint16_t to_self_delay_arg, struct LDKTxOut output_arg, struct LDKPublicKey revocation_pubkey_arg, struct LDKThirtyTwoBytes channel_keys_id_arg, uint64_t channel_value_satoshis_arg);
	public static native long DelayedPaymentOutputDescriptor_new(long outpoint_arg, byte[] per_commitment_point_arg, short to_self_delay_arg, long output_arg, byte[] revocation_pubkey_arg, byte[] channel_keys_id_arg, long channel_value_satoshis_arg);
	// uintptr_t DelayedPaymentOutputDescriptor_clone_ptr(LDKDelayedPaymentOutputDescriptor *NONNULL_PTR arg);
	public static native long DelayedPaymentOutputDescriptor_clone_ptr(long arg);
	// struct LDKDelayedPaymentOutputDescriptor DelayedPaymentOutputDescriptor_clone(const struct LDKDelayedPaymentOutputDescriptor *NONNULL_PTR orig);
	public static native long DelayedPaymentOutputDescriptor_clone(long orig);
	// struct LDKCVec_u8Z DelayedPaymentOutputDescriptor_write(const struct LDKDelayedPaymentOutputDescriptor *NONNULL_PTR obj);
	public static native byte[] DelayedPaymentOutputDescriptor_write(long obj);
	// struct LDKCResult_DelayedPaymentOutputDescriptorDecodeErrorZ DelayedPaymentOutputDescriptor_read(struct LDKu8slice ser);
	public static native long DelayedPaymentOutputDescriptor_read(byte[] ser);
	// void StaticPaymentOutputDescriptor_free(struct LDKStaticPaymentOutputDescriptor this_obj);
	public static native void StaticPaymentOutputDescriptor_free(long this_obj);
	// struct LDKOutPoint StaticPaymentOutputDescriptor_get_outpoint(const struct LDKStaticPaymentOutputDescriptor *NONNULL_PTR this_ptr);
	public static native long StaticPaymentOutputDescriptor_get_outpoint(long this_ptr);
	// void StaticPaymentOutputDescriptor_set_outpoint(struct LDKStaticPaymentOutputDescriptor *NONNULL_PTR this_ptr, struct LDKOutPoint val);
	public static native void StaticPaymentOutputDescriptor_set_outpoint(long this_ptr, long val);
	// void StaticPaymentOutputDescriptor_set_output(struct LDKStaticPaymentOutputDescriptor *NONNULL_PTR this_ptr, struct LDKTxOut val);
	public static native void StaticPaymentOutputDescriptor_set_output(long this_ptr, long val);
	// const uint8_t (*StaticPaymentOutputDescriptor_get_channel_keys_id(const struct LDKStaticPaymentOutputDescriptor *NONNULL_PTR this_ptr))[32];
	public static native byte[] StaticPaymentOutputDescriptor_get_channel_keys_id(long this_ptr);
	// void StaticPaymentOutputDescriptor_set_channel_keys_id(struct LDKStaticPaymentOutputDescriptor *NONNULL_PTR this_ptr, struct LDKThirtyTwoBytes val);
	public static native void StaticPaymentOutputDescriptor_set_channel_keys_id(long this_ptr, byte[] val);
	// uint64_t StaticPaymentOutputDescriptor_get_channel_value_satoshis(const struct LDKStaticPaymentOutputDescriptor *NONNULL_PTR this_ptr);
	public static native long StaticPaymentOutputDescriptor_get_channel_value_satoshis(long this_ptr);
	// void StaticPaymentOutputDescriptor_set_channel_value_satoshis(struct LDKStaticPaymentOutputDescriptor *NONNULL_PTR this_ptr, uint64_t val);
	public static native void StaticPaymentOutputDescriptor_set_channel_value_satoshis(long this_ptr, long val);
	// MUST_USE_RES struct LDKStaticPaymentOutputDescriptor StaticPaymentOutputDescriptor_new(struct LDKOutPoint outpoint_arg, struct LDKTxOut output_arg, struct LDKThirtyTwoBytes channel_keys_id_arg, uint64_t channel_value_satoshis_arg);
	public static native long StaticPaymentOutputDescriptor_new(long outpoint_arg, long output_arg, byte[] channel_keys_id_arg, long channel_value_satoshis_arg);
	// uintptr_t StaticPaymentOutputDescriptor_clone_ptr(LDKStaticPaymentOutputDescriptor *NONNULL_PTR arg);
	public static native long StaticPaymentOutputDescriptor_clone_ptr(long arg);
	// struct LDKStaticPaymentOutputDescriptor StaticPaymentOutputDescriptor_clone(const struct LDKStaticPaymentOutputDescriptor *NONNULL_PTR orig);
	public static native long StaticPaymentOutputDescriptor_clone(long orig);
	// struct LDKCVec_u8Z StaticPaymentOutputDescriptor_write(const struct LDKStaticPaymentOutputDescriptor *NONNULL_PTR obj);
	public static native byte[] StaticPaymentOutputDescriptor_write(long obj);
	// struct LDKCResult_StaticPaymentOutputDescriptorDecodeErrorZ StaticPaymentOutputDescriptor_read(struct LDKu8slice ser);
	public static native long StaticPaymentOutputDescriptor_read(byte[] ser);
	// void SpendableOutputDescriptor_free(struct LDKSpendableOutputDescriptor this_ptr);
	public static native void SpendableOutputDescriptor_free(long this_ptr);
	// uintptr_t SpendableOutputDescriptor_clone_ptr(LDKSpendableOutputDescriptor *NONNULL_PTR arg);
	public static native long SpendableOutputDescriptor_clone_ptr(long arg);
	// struct LDKSpendableOutputDescriptor SpendableOutputDescriptor_clone(const struct LDKSpendableOutputDescriptor *NONNULL_PTR orig);
	public static native long SpendableOutputDescriptor_clone(long orig);
	// struct LDKSpendableOutputDescriptor SpendableOutputDescriptor_static_output(struct LDKOutPoint outpoint, struct LDKTxOut output);
	public static native long SpendableOutputDescriptor_static_output(long outpoint, long output);
	// struct LDKSpendableOutputDescriptor SpendableOutputDescriptor_delayed_payment_output(struct LDKDelayedPaymentOutputDescriptor a);
	public static native long SpendableOutputDescriptor_delayed_payment_output(long a);
	// struct LDKSpendableOutputDescriptor SpendableOutputDescriptor_static_payment_output(struct LDKStaticPaymentOutputDescriptor a);
	public static native long SpendableOutputDescriptor_static_payment_output(long a);
	// struct LDKCVec_u8Z SpendableOutputDescriptor_write(const struct LDKSpendableOutputDescriptor *NONNULL_PTR obj);
	public static native byte[] SpendableOutputDescriptor_write(long obj);
	// struct LDKCResult_SpendableOutputDescriptorDecodeErrorZ SpendableOutputDescriptor_read(struct LDKu8slice ser);
	public static native long SpendableOutputDescriptor_read(byte[] ser);
	// void BaseSign_free(struct LDKBaseSign this_ptr);
	public static native void BaseSign_free(long this_ptr);
	// uintptr_t Sign_clone_ptr(LDKSign *NONNULL_PTR arg);
	public static native long Sign_clone_ptr(long arg);
	// struct LDKSign Sign_clone(const struct LDKSign *NONNULL_PTR orig);
	public static native long Sign_clone(long orig);
	// void Sign_free(struct LDKSign this_ptr);
	public static native void Sign_free(long this_ptr);
	// enum LDKRecipient Recipient_clone(const enum LDKRecipient *NONNULL_PTR orig);
	public static native Recipient Recipient_clone(long orig);
	// enum LDKRecipient Recipient_node(void);
	public static native Recipient Recipient_node();
	// enum LDKRecipient Recipient_phantom_node(void);
	public static native Recipient Recipient_phantom_node();
	// void KeysInterface_free(struct LDKKeysInterface this_ptr);
	public static native void KeysInterface_free(long this_ptr);
	// void InMemorySigner_free(struct LDKInMemorySigner this_obj);
	public static native void InMemorySigner_free(long this_obj);
	// const uint8_t (*InMemorySigner_get_funding_key(const struct LDKInMemorySigner *NONNULL_PTR this_ptr))[32];
	public static native byte[] InMemorySigner_get_funding_key(long this_ptr);
	// void InMemorySigner_set_funding_key(struct LDKInMemorySigner *NONNULL_PTR this_ptr, struct LDKSecretKey val);
	public static native void InMemorySigner_set_funding_key(long this_ptr, byte[] val);
	// const uint8_t (*InMemorySigner_get_revocation_base_key(const struct LDKInMemorySigner *NONNULL_PTR this_ptr))[32];
	public static native byte[] InMemorySigner_get_revocation_base_key(long this_ptr);
	// void InMemorySigner_set_revocation_base_key(struct LDKInMemorySigner *NONNULL_PTR this_ptr, struct LDKSecretKey val);
	public static native void InMemorySigner_set_revocation_base_key(long this_ptr, byte[] val);
	// const uint8_t (*InMemorySigner_get_payment_key(const struct LDKInMemorySigner *NONNULL_PTR this_ptr))[32];
	public static native byte[] InMemorySigner_get_payment_key(long this_ptr);
	// void InMemorySigner_set_payment_key(struct LDKInMemorySigner *NONNULL_PTR this_ptr, struct LDKSecretKey val);
	public static native void InMemorySigner_set_payment_key(long this_ptr, byte[] val);
	// const uint8_t (*InMemorySigner_get_delayed_payment_base_key(const struct LDKInMemorySigner *NONNULL_PTR this_ptr))[32];
	public static native byte[] InMemorySigner_get_delayed_payment_base_key(long this_ptr);
	// void InMemorySigner_set_delayed_payment_base_key(struct LDKInMemorySigner *NONNULL_PTR this_ptr, struct LDKSecretKey val);
	public static native void InMemorySigner_set_delayed_payment_base_key(long this_ptr, byte[] val);
	// const uint8_t (*InMemorySigner_get_htlc_base_key(const struct LDKInMemorySigner *NONNULL_PTR this_ptr))[32];
	public static native byte[] InMemorySigner_get_htlc_base_key(long this_ptr);
	// void InMemorySigner_set_htlc_base_key(struct LDKInMemorySigner *NONNULL_PTR this_ptr, struct LDKSecretKey val);
	public static native void InMemorySigner_set_htlc_base_key(long this_ptr, byte[] val);
	// const uint8_t (*InMemorySigner_get_commitment_seed(const struct LDKInMemorySigner *NONNULL_PTR this_ptr))[32];
	public static native byte[] InMemorySigner_get_commitment_seed(long this_ptr);
	// void InMemorySigner_set_commitment_seed(struct LDKInMemorySigner *NONNULL_PTR this_ptr, struct LDKThirtyTwoBytes val);
	public static native void InMemorySigner_set_commitment_seed(long this_ptr, byte[] val);
	// uintptr_t InMemorySigner_clone_ptr(LDKInMemorySigner *NONNULL_PTR arg);
	public static native long InMemorySigner_clone_ptr(long arg);
	// struct LDKInMemorySigner InMemorySigner_clone(const struct LDKInMemorySigner *NONNULL_PTR orig);
	public static native long InMemorySigner_clone(long orig);
	// MUST_USE_RES struct LDKInMemorySigner InMemorySigner_new(struct LDKSecretKey node_secret, struct LDKSecretKey funding_key, struct LDKSecretKey revocation_base_key, struct LDKSecretKey payment_key, struct LDKSecretKey delayed_payment_base_key, struct LDKSecretKey htlc_base_key, struct LDKThirtyTwoBytes commitment_seed, uint64_t channel_value_satoshis, struct LDKThirtyTwoBytes channel_keys_id);
	public static native long InMemorySigner_new(byte[] node_secret, byte[] funding_key, byte[] revocation_base_key, byte[] payment_key, byte[] delayed_payment_base_key, byte[] htlc_base_key, byte[] commitment_seed, long channel_value_satoshis, byte[] channel_keys_id);
	// MUST_USE_RES struct LDKChannelPublicKeys InMemorySigner_counterparty_pubkeys(const struct LDKInMemorySigner *NONNULL_PTR this_arg);
	public static native long InMemorySigner_counterparty_pubkeys(long this_arg);
	// MUST_USE_RES uint16_t InMemorySigner_counterparty_selected_contest_delay(const struct LDKInMemorySigner *NONNULL_PTR this_arg);
	public static native short InMemorySigner_counterparty_selected_contest_delay(long this_arg);
	// MUST_USE_RES uint16_t InMemorySigner_holder_selected_contest_delay(const struct LDKInMemorySigner *NONNULL_PTR this_arg);
	public static native short InMemorySigner_holder_selected_contest_delay(long this_arg);
	// MUST_USE_RES bool InMemorySigner_is_outbound(const struct LDKInMemorySigner *NONNULL_PTR this_arg);
	public static native boolean InMemorySigner_is_outbound(long this_arg);
	// MUST_USE_RES struct LDKOutPoint InMemorySigner_funding_outpoint(const struct LDKInMemorySigner *NONNULL_PTR this_arg);
	public static native long InMemorySigner_funding_outpoint(long this_arg);
	// MUST_USE_RES struct LDKChannelTransactionParameters InMemorySigner_get_channel_parameters(const struct LDKInMemorySigner *NONNULL_PTR this_arg);
	public static native long InMemorySigner_get_channel_parameters(long this_arg);
	// MUST_USE_RES bool InMemorySigner_opt_anchors(const struct LDKInMemorySigner *NONNULL_PTR this_arg);
	public static native boolean InMemorySigner_opt_anchors(long this_arg);
	// MUST_USE_RES struct LDKCResult_CVec_CVec_u8ZZNoneZ InMemorySigner_sign_counterparty_payment_input(const struct LDKInMemorySigner *NONNULL_PTR this_arg, struct LDKTransaction spend_tx, uintptr_t input_idx, const struct LDKStaticPaymentOutputDescriptor *NONNULL_PTR descriptor);
	public static native long InMemorySigner_sign_counterparty_payment_input(long this_arg, byte[] spend_tx, long input_idx, long descriptor);
	// MUST_USE_RES struct LDKCResult_CVec_CVec_u8ZZNoneZ InMemorySigner_sign_dynamic_p2wsh_input(const struct LDKInMemorySigner *NONNULL_PTR this_arg, struct LDKTransaction spend_tx, uintptr_t input_idx, const struct LDKDelayedPaymentOutputDescriptor *NONNULL_PTR descriptor);
	public static native long InMemorySigner_sign_dynamic_p2wsh_input(long this_arg, byte[] spend_tx, long input_idx, long descriptor);
	// struct LDKBaseSign InMemorySigner_as_BaseSign(const struct LDKInMemorySigner *NONNULL_PTR this_arg);
	public static native long InMemorySigner_as_BaseSign(long this_arg);
	// struct LDKSign InMemorySigner_as_Sign(const struct LDKInMemorySigner *NONNULL_PTR this_arg);
	public static native long InMemorySigner_as_Sign(long this_arg);
	// struct LDKCVec_u8Z InMemorySigner_write(const struct LDKInMemorySigner *NONNULL_PTR obj);
	public static native byte[] InMemorySigner_write(long obj);
	// struct LDKCResult_InMemorySignerDecodeErrorZ InMemorySigner_read(struct LDKu8slice ser, struct LDKSecretKey arg);
	public static native long InMemorySigner_read(byte[] ser, byte[] arg);
	// void KeysManager_free(struct LDKKeysManager this_obj);
	public static native void KeysManager_free(long this_obj);
	// MUST_USE_RES struct LDKKeysManager KeysManager_new(const uint8_t (*seed)[32], uint64_t starting_time_secs, uint32_t starting_time_nanos);
	public static native long KeysManager_new(byte[] seed, long starting_time_secs, int starting_time_nanos);
	// MUST_USE_RES struct LDKInMemorySigner KeysManager_derive_channel_keys(const struct LDKKeysManager *NONNULL_PTR this_arg, uint64_t channel_value_satoshis, const uint8_t (*params)[32]);
	public static native long KeysManager_derive_channel_keys(long this_arg, long channel_value_satoshis, byte[] params);
	// MUST_USE_RES struct LDKCResult_TransactionNoneZ KeysManager_spend_spendable_outputs(const struct LDKKeysManager *NONNULL_PTR this_arg, struct LDKCVec_SpendableOutputDescriptorZ descriptors, struct LDKCVec_TxOutZ outputs, struct LDKCVec_u8Z change_destination_script, uint32_t feerate_sat_per_1000_weight);
	public static native long KeysManager_spend_spendable_outputs(long this_arg, long[] descriptors, long[] outputs, byte[] change_destination_script, int feerate_sat_per_1000_weight);
	// struct LDKKeysInterface KeysManager_as_KeysInterface(const struct LDKKeysManager *NONNULL_PTR this_arg);
	public static native long KeysManager_as_KeysInterface(long this_arg);
	// void PhantomKeysManager_free(struct LDKPhantomKeysManager this_obj);
	public static native void PhantomKeysManager_free(long this_obj);
	// struct LDKKeysInterface PhantomKeysManager_as_KeysInterface(const struct LDKPhantomKeysManager *NONNULL_PTR this_arg);
	public static native long PhantomKeysManager_as_KeysInterface(long this_arg);
	// MUST_USE_RES struct LDKPhantomKeysManager PhantomKeysManager_new(const uint8_t (*seed)[32], uint64_t starting_time_secs, uint32_t starting_time_nanos, const uint8_t (*cross_node_seed)[32]);
	public static native long PhantomKeysManager_new(byte[] seed, long starting_time_secs, int starting_time_nanos, byte[] cross_node_seed);
	// MUST_USE_RES struct LDKCResult_TransactionNoneZ PhantomKeysManager_spend_spendable_outputs(const struct LDKPhantomKeysManager *NONNULL_PTR this_arg, struct LDKCVec_SpendableOutputDescriptorZ descriptors, struct LDKCVec_TxOutZ outputs, struct LDKCVec_u8Z change_destination_script, uint32_t feerate_sat_per_1000_weight);
	public static native long PhantomKeysManager_spend_spendable_outputs(long this_arg, long[] descriptors, long[] outputs, byte[] change_destination_script, int feerate_sat_per_1000_weight);
	// MUST_USE_RES struct LDKInMemorySigner PhantomKeysManager_derive_channel_keys(const struct LDKPhantomKeysManager *NONNULL_PTR this_arg, uint64_t channel_value_satoshis, const uint8_t (*params)[32]);
	public static native long PhantomKeysManager_derive_channel_keys(long this_arg, long channel_value_satoshis, byte[] params);
	// void ChannelManager_free(struct LDKChannelManager this_obj);
	public static native void ChannelManager_free(long this_obj);
	// void ChainParameters_free(struct LDKChainParameters this_obj);
	public static native void ChainParameters_free(long this_obj);
	// enum LDKNetwork ChainParameters_get_network(const struct LDKChainParameters *NONNULL_PTR this_ptr);
	public static native Network ChainParameters_get_network(long this_ptr);
	// void ChainParameters_set_network(struct LDKChainParameters *NONNULL_PTR this_ptr, enum LDKNetwork val);
	public static native void ChainParameters_set_network(long this_ptr, Network val);
	// struct LDKBestBlock ChainParameters_get_best_block(const struct LDKChainParameters *NONNULL_PTR this_ptr);
	public static native long ChainParameters_get_best_block(long this_ptr);
	// void ChainParameters_set_best_block(struct LDKChainParameters *NONNULL_PTR this_ptr, struct LDKBestBlock val);
	public static native void ChainParameters_set_best_block(long this_ptr, long val);
	// MUST_USE_RES struct LDKChainParameters ChainParameters_new(enum LDKNetwork network_arg, struct LDKBestBlock best_block_arg);
	public static native long ChainParameters_new(Network network_arg, long best_block_arg);
	// uintptr_t ChainParameters_clone_ptr(LDKChainParameters *NONNULL_PTR arg);
	public static native long ChainParameters_clone_ptr(long arg);
	// struct LDKChainParameters ChainParameters_clone(const struct LDKChainParameters *NONNULL_PTR orig);
	public static native long ChainParameters_clone(long orig);
	// void CounterpartyForwardingInfo_free(struct LDKCounterpartyForwardingInfo this_obj);
	public static native void CounterpartyForwardingInfo_free(long this_obj);
	// uint32_t CounterpartyForwardingInfo_get_fee_base_msat(const struct LDKCounterpartyForwardingInfo *NONNULL_PTR this_ptr);
	public static native int CounterpartyForwardingInfo_get_fee_base_msat(long this_ptr);
	// void CounterpartyForwardingInfo_set_fee_base_msat(struct LDKCounterpartyForwardingInfo *NONNULL_PTR this_ptr, uint32_t val);
	public static native void CounterpartyForwardingInfo_set_fee_base_msat(long this_ptr, int val);
	// uint32_t CounterpartyForwardingInfo_get_fee_proportional_millionths(const struct LDKCounterpartyForwardingInfo *NONNULL_PTR this_ptr);
	public static native int CounterpartyForwardingInfo_get_fee_proportional_millionths(long this_ptr);
	// void CounterpartyForwardingInfo_set_fee_proportional_millionths(struct LDKCounterpartyForwardingInfo *NONNULL_PTR this_ptr, uint32_t val);
	public static native void CounterpartyForwardingInfo_set_fee_proportional_millionths(long this_ptr, int val);
	// uint16_t CounterpartyForwardingInfo_get_cltv_expiry_delta(const struct LDKCounterpartyForwardingInfo *NONNULL_PTR this_ptr);
	public static native short CounterpartyForwardingInfo_get_cltv_expiry_delta(long this_ptr);
	// void CounterpartyForwardingInfo_set_cltv_expiry_delta(struct LDKCounterpartyForwardingInfo *NONNULL_PTR this_ptr, uint16_t val);
	public static native void CounterpartyForwardingInfo_set_cltv_expiry_delta(long this_ptr, short val);
	// MUST_USE_RES struct LDKCounterpartyForwardingInfo CounterpartyForwardingInfo_new(uint32_t fee_base_msat_arg, uint32_t fee_proportional_millionths_arg, uint16_t cltv_expiry_delta_arg);
	public static native long CounterpartyForwardingInfo_new(int fee_base_msat_arg, int fee_proportional_millionths_arg, short cltv_expiry_delta_arg);
	// uintptr_t CounterpartyForwardingInfo_clone_ptr(LDKCounterpartyForwardingInfo *NONNULL_PTR arg);
	public static native long CounterpartyForwardingInfo_clone_ptr(long arg);
	// struct LDKCounterpartyForwardingInfo CounterpartyForwardingInfo_clone(const struct LDKCounterpartyForwardingInfo *NONNULL_PTR orig);
	public static native long CounterpartyForwardingInfo_clone(long orig);
	// void ChannelCounterparty_free(struct LDKChannelCounterparty this_obj);
	public static native void ChannelCounterparty_free(long this_obj);
	// struct LDKPublicKey ChannelCounterparty_get_node_id(const struct LDKChannelCounterparty *NONNULL_PTR this_ptr);
	public static native byte[] ChannelCounterparty_get_node_id(long this_ptr);
	// void ChannelCounterparty_set_node_id(struct LDKChannelCounterparty *NONNULL_PTR this_ptr, struct LDKPublicKey val);
	public static native void ChannelCounterparty_set_node_id(long this_ptr, byte[] val);
	// struct LDKInitFeatures ChannelCounterparty_get_features(const struct LDKChannelCounterparty *NONNULL_PTR this_ptr);
	public static native long ChannelCounterparty_get_features(long this_ptr);
	// void ChannelCounterparty_set_features(struct LDKChannelCounterparty *NONNULL_PTR this_ptr, struct LDKInitFeatures val);
	public static native void ChannelCounterparty_set_features(long this_ptr, long val);
	// uint64_t ChannelCounterparty_get_unspendable_punishment_reserve(const struct LDKChannelCounterparty *NONNULL_PTR this_ptr);
	public static native long ChannelCounterparty_get_unspendable_punishment_reserve(long this_ptr);
	// void ChannelCounterparty_set_unspendable_punishment_reserve(struct LDKChannelCounterparty *NONNULL_PTR this_ptr, uint64_t val);
	public static native void ChannelCounterparty_set_unspendable_punishment_reserve(long this_ptr, long val);
	// struct LDKCounterpartyForwardingInfo ChannelCounterparty_get_forwarding_info(const struct LDKChannelCounterparty *NONNULL_PTR this_ptr);
	public static native long ChannelCounterparty_get_forwarding_info(long this_ptr);
	// void ChannelCounterparty_set_forwarding_info(struct LDKChannelCounterparty *NONNULL_PTR this_ptr, struct LDKCounterpartyForwardingInfo val);
	public static native void ChannelCounterparty_set_forwarding_info(long this_ptr, long val);
	// MUST_USE_RES struct LDKChannelCounterparty ChannelCounterparty_new(struct LDKPublicKey node_id_arg, struct LDKInitFeatures features_arg, uint64_t unspendable_punishment_reserve_arg, struct LDKCounterpartyForwardingInfo forwarding_info_arg);
	public static native long ChannelCounterparty_new(byte[] node_id_arg, long features_arg, long unspendable_punishment_reserve_arg, long forwarding_info_arg);
	// uintptr_t ChannelCounterparty_clone_ptr(LDKChannelCounterparty *NONNULL_PTR arg);
	public static native long ChannelCounterparty_clone_ptr(long arg);
	// struct LDKChannelCounterparty ChannelCounterparty_clone(const struct LDKChannelCounterparty *NONNULL_PTR orig);
	public static native long ChannelCounterparty_clone(long orig);
	// void ChannelDetails_free(struct LDKChannelDetails this_obj);
	public static native void ChannelDetails_free(long this_obj);
	// const uint8_t (*ChannelDetails_get_channel_id(const struct LDKChannelDetails *NONNULL_PTR this_ptr))[32];
	public static native byte[] ChannelDetails_get_channel_id(long this_ptr);
	// void ChannelDetails_set_channel_id(struct LDKChannelDetails *NONNULL_PTR this_ptr, struct LDKThirtyTwoBytes val);
	public static native void ChannelDetails_set_channel_id(long this_ptr, byte[] val);
	// struct LDKChannelCounterparty ChannelDetails_get_counterparty(const struct LDKChannelDetails *NONNULL_PTR this_ptr);
	public static native long ChannelDetails_get_counterparty(long this_ptr);
	// void ChannelDetails_set_counterparty(struct LDKChannelDetails *NONNULL_PTR this_ptr, struct LDKChannelCounterparty val);
	public static native void ChannelDetails_set_counterparty(long this_ptr, long val);
	// struct LDKOutPoint ChannelDetails_get_funding_txo(const struct LDKChannelDetails *NONNULL_PTR this_ptr);
	public static native long ChannelDetails_get_funding_txo(long this_ptr);
	// void ChannelDetails_set_funding_txo(struct LDKChannelDetails *NONNULL_PTR this_ptr, struct LDKOutPoint val);
	public static native void ChannelDetails_set_funding_txo(long this_ptr, long val);
	// struct LDKChannelTypeFeatures ChannelDetails_get_channel_type(const struct LDKChannelDetails *NONNULL_PTR this_ptr);
	public static native long ChannelDetails_get_channel_type(long this_ptr);
	// void ChannelDetails_set_channel_type(struct LDKChannelDetails *NONNULL_PTR this_ptr, struct LDKChannelTypeFeatures val);
	public static native void ChannelDetails_set_channel_type(long this_ptr, long val);
	// struct LDKCOption_u64Z ChannelDetails_get_short_channel_id(const struct LDKChannelDetails *NONNULL_PTR this_ptr);
	public static native long ChannelDetails_get_short_channel_id(long this_ptr);
	// void ChannelDetails_set_short_channel_id(struct LDKChannelDetails *NONNULL_PTR this_ptr, struct LDKCOption_u64Z val);
	public static native void ChannelDetails_set_short_channel_id(long this_ptr, long val);
	// struct LDKCOption_u64Z ChannelDetails_get_inbound_scid_alias(const struct LDKChannelDetails *NONNULL_PTR this_ptr);
	public static native long ChannelDetails_get_inbound_scid_alias(long this_ptr);
	// void ChannelDetails_set_inbound_scid_alias(struct LDKChannelDetails *NONNULL_PTR this_ptr, struct LDKCOption_u64Z val);
	public static native void ChannelDetails_set_inbound_scid_alias(long this_ptr, long val);
	// uint64_t ChannelDetails_get_channel_value_satoshis(const struct LDKChannelDetails *NONNULL_PTR this_ptr);
	public static native long ChannelDetails_get_channel_value_satoshis(long this_ptr);
	// void ChannelDetails_set_channel_value_satoshis(struct LDKChannelDetails *NONNULL_PTR this_ptr, uint64_t val);
	public static native void ChannelDetails_set_channel_value_satoshis(long this_ptr, long val);
	// struct LDKCOption_u64Z ChannelDetails_get_unspendable_punishment_reserve(const struct LDKChannelDetails *NONNULL_PTR this_ptr);
	public static native long ChannelDetails_get_unspendable_punishment_reserve(long this_ptr);
	// void ChannelDetails_set_unspendable_punishment_reserve(struct LDKChannelDetails *NONNULL_PTR this_ptr, struct LDKCOption_u64Z val);
	public static native void ChannelDetails_set_unspendable_punishment_reserve(long this_ptr, long val);
	// uint64_t ChannelDetails_get_user_channel_id(const struct LDKChannelDetails *NONNULL_PTR this_ptr);
	public static native long ChannelDetails_get_user_channel_id(long this_ptr);
	// void ChannelDetails_set_user_channel_id(struct LDKChannelDetails *NONNULL_PTR this_ptr, uint64_t val);
	public static native void ChannelDetails_set_user_channel_id(long this_ptr, long val);
	// uint64_t ChannelDetails_get_balance_msat(const struct LDKChannelDetails *NONNULL_PTR this_ptr);
	public static native long ChannelDetails_get_balance_msat(long this_ptr);
	// void ChannelDetails_set_balance_msat(struct LDKChannelDetails *NONNULL_PTR this_ptr, uint64_t val);
	public static native void ChannelDetails_set_balance_msat(long this_ptr, long val);
	// uint64_t ChannelDetails_get_outbound_capacity_msat(const struct LDKChannelDetails *NONNULL_PTR this_ptr);
	public static native long ChannelDetails_get_outbound_capacity_msat(long this_ptr);
	// void ChannelDetails_set_outbound_capacity_msat(struct LDKChannelDetails *NONNULL_PTR this_ptr, uint64_t val);
	public static native void ChannelDetails_set_outbound_capacity_msat(long this_ptr, long val);
	// uint64_t ChannelDetails_get_inbound_capacity_msat(const struct LDKChannelDetails *NONNULL_PTR this_ptr);
	public static native long ChannelDetails_get_inbound_capacity_msat(long this_ptr);
	// void ChannelDetails_set_inbound_capacity_msat(struct LDKChannelDetails *NONNULL_PTR this_ptr, uint64_t val);
	public static native void ChannelDetails_set_inbound_capacity_msat(long this_ptr, long val);
	// struct LDKCOption_u32Z ChannelDetails_get_confirmations_required(const struct LDKChannelDetails *NONNULL_PTR this_ptr);
	public static native long ChannelDetails_get_confirmations_required(long this_ptr);
	// void ChannelDetails_set_confirmations_required(struct LDKChannelDetails *NONNULL_PTR this_ptr, struct LDKCOption_u32Z val);
	public static native void ChannelDetails_set_confirmations_required(long this_ptr, long val);
	// struct LDKCOption_u16Z ChannelDetails_get_force_close_spend_delay(const struct LDKChannelDetails *NONNULL_PTR this_ptr);
	public static native long ChannelDetails_get_force_close_spend_delay(long this_ptr);
	// void ChannelDetails_set_force_close_spend_delay(struct LDKChannelDetails *NONNULL_PTR this_ptr, struct LDKCOption_u16Z val);
	public static native void ChannelDetails_set_force_close_spend_delay(long this_ptr, long val);
	// bool ChannelDetails_get_is_outbound(const struct LDKChannelDetails *NONNULL_PTR this_ptr);
	public static native boolean ChannelDetails_get_is_outbound(long this_ptr);
	// void ChannelDetails_set_is_outbound(struct LDKChannelDetails *NONNULL_PTR this_ptr, bool val);
	public static native void ChannelDetails_set_is_outbound(long this_ptr, boolean val);
	// bool ChannelDetails_get_is_funding_locked(const struct LDKChannelDetails *NONNULL_PTR this_ptr);
	public static native boolean ChannelDetails_get_is_funding_locked(long this_ptr);
	// void ChannelDetails_set_is_funding_locked(struct LDKChannelDetails *NONNULL_PTR this_ptr, bool val);
	public static native void ChannelDetails_set_is_funding_locked(long this_ptr, boolean val);
	// bool ChannelDetails_get_is_usable(const struct LDKChannelDetails *NONNULL_PTR this_ptr);
	public static native boolean ChannelDetails_get_is_usable(long this_ptr);
	// void ChannelDetails_set_is_usable(struct LDKChannelDetails *NONNULL_PTR this_ptr, bool val);
	public static native void ChannelDetails_set_is_usable(long this_ptr, boolean val);
	// bool ChannelDetails_get_is_public(const struct LDKChannelDetails *NONNULL_PTR this_ptr);
	public static native boolean ChannelDetails_get_is_public(long this_ptr);
	// void ChannelDetails_set_is_public(struct LDKChannelDetails *NONNULL_PTR this_ptr, bool val);
	public static native void ChannelDetails_set_is_public(long this_ptr, boolean val);
	// MUST_USE_RES struct LDKChannelDetails ChannelDetails_new(struct LDKThirtyTwoBytes channel_id_arg, struct LDKChannelCounterparty counterparty_arg, struct LDKOutPoint funding_txo_arg, struct LDKChannelTypeFeatures channel_type_arg, struct LDKCOption_u64Z short_channel_id_arg, struct LDKCOption_u64Z inbound_scid_alias_arg, uint64_t channel_value_satoshis_arg, struct LDKCOption_u64Z unspendable_punishment_reserve_arg, uint64_t user_channel_id_arg, uint64_t balance_msat_arg, uint64_t outbound_capacity_msat_arg, uint64_t inbound_capacity_msat_arg, struct LDKCOption_u32Z confirmations_required_arg, struct LDKCOption_u16Z force_close_spend_delay_arg, bool is_outbound_arg, bool is_funding_locked_arg, bool is_usable_arg, bool is_public_arg);
	public static native long ChannelDetails_new(byte[] channel_id_arg, long counterparty_arg, long funding_txo_arg, long channel_type_arg, long short_channel_id_arg, long inbound_scid_alias_arg, long channel_value_satoshis_arg, long unspendable_punishment_reserve_arg, long user_channel_id_arg, long balance_msat_arg, long outbound_capacity_msat_arg, long inbound_capacity_msat_arg, long confirmations_required_arg, long force_close_spend_delay_arg, boolean is_outbound_arg, boolean is_funding_locked_arg, boolean is_usable_arg, boolean is_public_arg);
	// uintptr_t ChannelDetails_clone_ptr(LDKChannelDetails *NONNULL_PTR arg);
	public static native long ChannelDetails_clone_ptr(long arg);
	// struct LDKChannelDetails ChannelDetails_clone(const struct LDKChannelDetails *NONNULL_PTR orig);
	public static native long ChannelDetails_clone(long orig);
	// MUST_USE_RES struct LDKCOption_u64Z ChannelDetails_get_inbound_payment_scid(const struct LDKChannelDetails *NONNULL_PTR this_arg);
	public static native long ChannelDetails_get_inbound_payment_scid(long this_arg);
	// void PaymentSendFailure_free(struct LDKPaymentSendFailure this_ptr);
	public static native void PaymentSendFailure_free(long this_ptr);
	// uintptr_t PaymentSendFailure_clone_ptr(LDKPaymentSendFailure *NONNULL_PTR arg);
	public static native long PaymentSendFailure_clone_ptr(long arg);
	// struct LDKPaymentSendFailure PaymentSendFailure_clone(const struct LDKPaymentSendFailure *NONNULL_PTR orig);
	public static native long PaymentSendFailure_clone(long orig);
	// struct LDKPaymentSendFailure PaymentSendFailure_parameter_error(struct LDKAPIError a);
	public static native long PaymentSendFailure_parameter_error(long a);
	// struct LDKPaymentSendFailure PaymentSendFailure_path_parameter_error(struct LDKCVec_CResult_NoneAPIErrorZZ a);
	public static native long PaymentSendFailure_path_parameter_error(long[] a);
	// struct LDKPaymentSendFailure PaymentSendFailure_all_failed_retry_safe(struct LDKCVec_APIErrorZ a);
	public static native long PaymentSendFailure_all_failed_retry_safe(long[] a);
	// struct LDKPaymentSendFailure PaymentSendFailure_partial_failure(struct LDKCVec_CResult_NoneAPIErrorZZ results, struct LDKRouteParameters failed_paths_retry, struct LDKThirtyTwoBytes payment_id);
	public static native long PaymentSendFailure_partial_failure(long[] results, long failed_paths_retry, byte[] payment_id);
	// void PhantomRouteHints_free(struct LDKPhantomRouteHints this_obj);
	public static native void PhantomRouteHints_free(long this_obj);
	// struct LDKCVec_ChannelDetailsZ PhantomRouteHints_get_channels(const struct LDKPhantomRouteHints *NONNULL_PTR this_ptr);
	public static native long[] PhantomRouteHints_get_channels(long this_ptr);
	// void PhantomRouteHints_set_channels(struct LDKPhantomRouteHints *NONNULL_PTR this_ptr, struct LDKCVec_ChannelDetailsZ val);
	public static native void PhantomRouteHints_set_channels(long this_ptr, long[] val);
	// uint64_t PhantomRouteHints_get_phantom_scid(const struct LDKPhantomRouteHints *NONNULL_PTR this_ptr);
	public static native long PhantomRouteHints_get_phantom_scid(long this_ptr);
	// void PhantomRouteHints_set_phantom_scid(struct LDKPhantomRouteHints *NONNULL_PTR this_ptr, uint64_t val);
	public static native void PhantomRouteHints_set_phantom_scid(long this_ptr, long val);
	// struct LDKPublicKey PhantomRouteHints_get_real_node_pubkey(const struct LDKPhantomRouteHints *NONNULL_PTR this_ptr);
	public static native byte[] PhantomRouteHints_get_real_node_pubkey(long this_ptr);
	// void PhantomRouteHints_set_real_node_pubkey(struct LDKPhantomRouteHints *NONNULL_PTR this_ptr, struct LDKPublicKey val);
	public static native void PhantomRouteHints_set_real_node_pubkey(long this_ptr, byte[] val);
	// MUST_USE_RES struct LDKPhantomRouteHints PhantomRouteHints_new(struct LDKCVec_ChannelDetailsZ channels_arg, uint64_t phantom_scid_arg, struct LDKPublicKey real_node_pubkey_arg);
	public static native long PhantomRouteHints_new(long[] channels_arg, long phantom_scid_arg, byte[] real_node_pubkey_arg);
	// uintptr_t PhantomRouteHints_clone_ptr(LDKPhantomRouteHints *NONNULL_PTR arg);
	public static native long PhantomRouteHints_clone_ptr(long arg);
	// struct LDKPhantomRouteHints PhantomRouteHints_clone(const struct LDKPhantomRouteHints *NONNULL_PTR orig);
	public static native long PhantomRouteHints_clone(long orig);
	// MUST_USE_RES struct LDKChannelManager ChannelManager_new(struct LDKFeeEstimator fee_est, struct LDKWatch chain_monitor, struct LDKBroadcasterInterface tx_broadcaster, struct LDKLogger logger, struct LDKKeysInterface keys_manager, struct LDKUserConfig config, struct LDKChainParameters params);
	public static native long ChannelManager_new(long fee_est, long chain_monitor, long tx_broadcaster, long logger, long keys_manager, long config, long params);
	// MUST_USE_RES struct LDKUserConfig ChannelManager_get_current_default_configuration(const struct LDKChannelManager *NONNULL_PTR this_arg);
	public static native long ChannelManager_get_current_default_configuration(long this_arg);
	// MUST_USE_RES struct LDKCResult__u832APIErrorZ ChannelManager_create_channel(const struct LDKChannelManager *NONNULL_PTR this_arg, struct LDKPublicKey their_network_key, uint64_t channel_value_satoshis, uint64_t push_msat, uint64_t user_channel_id, struct LDKUserConfig override_config);
	public static native long ChannelManager_create_channel(long this_arg, byte[] their_network_key, long channel_value_satoshis, long push_msat, long user_channel_id, long override_config);
	// MUST_USE_RES struct LDKCVec_ChannelDetailsZ ChannelManager_list_channels(const struct LDKChannelManager *NONNULL_PTR this_arg);
	public static native long[] ChannelManager_list_channels(long this_arg);
	// MUST_USE_RES struct LDKCVec_ChannelDetailsZ ChannelManager_list_usable_channels(const struct LDKChannelManager *NONNULL_PTR this_arg);
	public static native long[] ChannelManager_list_usable_channels(long this_arg);
	// MUST_USE_RES struct LDKCResult_NoneAPIErrorZ ChannelManager_close_channel(const struct LDKChannelManager *NONNULL_PTR this_arg, const uint8_t (*channel_id)[32]);
	public static native long ChannelManager_close_channel(long this_arg, byte[] channel_id);
	// MUST_USE_RES struct LDKCResult_NoneAPIErrorZ ChannelManager_close_channel_with_target_feerate(const struct LDKChannelManager *NONNULL_PTR this_arg, const uint8_t (*channel_id)[32], uint32_t target_feerate_sats_per_1000_weight);
	public static native long ChannelManager_close_channel_with_target_feerate(long this_arg, byte[] channel_id, int target_feerate_sats_per_1000_weight);
	// MUST_USE_RES struct LDKCResult_NoneAPIErrorZ ChannelManager_force_close_channel(const struct LDKChannelManager *NONNULL_PTR this_arg, const uint8_t (*channel_id)[32]);
	public static native long ChannelManager_force_close_channel(long this_arg, byte[] channel_id);
	// void ChannelManager_force_close_all_channels(const struct LDKChannelManager *NONNULL_PTR this_arg);
	public static native void ChannelManager_force_close_all_channels(long this_arg);
	// MUST_USE_RES struct LDKCResult_PaymentIdPaymentSendFailureZ ChannelManager_send_payment(const struct LDKChannelManager *NONNULL_PTR this_arg, const struct LDKRoute *NONNULL_PTR route, struct LDKThirtyTwoBytes payment_hash, struct LDKThirtyTwoBytes payment_secret);
	public static native long ChannelManager_send_payment(long this_arg, long route, byte[] payment_hash, byte[] payment_secret);
	// MUST_USE_RES struct LDKCResult_NonePaymentSendFailureZ ChannelManager_retry_payment(const struct LDKChannelManager *NONNULL_PTR this_arg, const struct LDKRoute *NONNULL_PTR route, struct LDKThirtyTwoBytes payment_id);
	public static native long ChannelManager_retry_payment(long this_arg, long route, byte[] payment_id);
	// void ChannelManager_abandon_payment(const struct LDKChannelManager *NONNULL_PTR this_arg, struct LDKThirtyTwoBytes payment_id);
	public static native void ChannelManager_abandon_payment(long this_arg, byte[] payment_id);
	// MUST_USE_RES struct LDKCResult_C2Tuple_PaymentHashPaymentIdZPaymentSendFailureZ ChannelManager_send_spontaneous_payment(const struct LDKChannelManager *NONNULL_PTR this_arg, const struct LDKRoute *NONNULL_PTR route, struct LDKThirtyTwoBytes payment_preimage);
	public static native long ChannelManager_send_spontaneous_payment(long this_arg, long route, byte[] payment_preimage);
	// MUST_USE_RES struct LDKCResult_NoneAPIErrorZ ChannelManager_funding_transaction_generated(const struct LDKChannelManager *NONNULL_PTR this_arg, const uint8_t (*temporary_channel_id)[32], struct LDKTransaction funding_transaction);
	public static native long ChannelManager_funding_transaction_generated(long this_arg, byte[] temporary_channel_id, byte[] funding_transaction);
	// void ChannelManager_broadcast_node_announcement(const struct LDKChannelManager *NONNULL_PTR this_arg, struct LDKThreeBytes rgb, struct LDKThirtyTwoBytes alias, struct LDKCVec_NetAddressZ addresses);
	public static native void ChannelManager_broadcast_node_announcement(long this_arg, byte[] rgb, byte[] alias, long[] addresses);
	// void ChannelManager_process_pending_htlc_forwards(const struct LDKChannelManager *NONNULL_PTR this_arg);
	public static native void ChannelManager_process_pending_htlc_forwards(long this_arg);
	// void ChannelManager_timer_tick_occurred(const struct LDKChannelManager *NONNULL_PTR this_arg);
	public static native void ChannelManager_timer_tick_occurred(long this_arg);
	// MUST_USE_RES bool ChannelManager_fail_htlc_backwards(const struct LDKChannelManager *NONNULL_PTR this_arg, const uint8_t (*payment_hash)[32]);
	public static native boolean ChannelManager_fail_htlc_backwards(long this_arg, byte[] payment_hash);
	// MUST_USE_RES bool ChannelManager_claim_funds(const struct LDKChannelManager *NONNULL_PTR this_arg, struct LDKThirtyTwoBytes payment_preimage);
	public static native boolean ChannelManager_claim_funds(long this_arg, byte[] payment_preimage);
	// MUST_USE_RES struct LDKPublicKey ChannelManager_get_our_node_id(const struct LDKChannelManager *NONNULL_PTR this_arg);
	public static native byte[] ChannelManager_get_our_node_id(long this_arg);
	// MUST_USE_RES struct LDKCResult_NoneAPIErrorZ ChannelManager_accept_inbound_channel(const struct LDKChannelManager *NONNULL_PTR this_arg, const uint8_t (*temporary_channel_id)[32], uint64_t user_channel_id);
	public static native long ChannelManager_accept_inbound_channel(long this_arg, byte[] temporary_channel_id, long user_channel_id);
	// MUST_USE_RES struct LDKCResult_C2Tuple_PaymentHashPaymentSecretZNoneZ ChannelManager_create_inbound_payment(const struct LDKChannelManager *NONNULL_PTR this_arg, struct LDKCOption_u64Z min_value_msat, uint32_t invoice_expiry_delta_secs);
	public static native long ChannelManager_create_inbound_payment(long this_arg, long min_value_msat, int invoice_expiry_delta_secs);
	// MUST_USE_RES struct LDKCResult_C2Tuple_PaymentHashPaymentSecretZAPIErrorZ ChannelManager_create_inbound_payment_legacy(const struct LDKChannelManager *NONNULL_PTR this_arg, struct LDKCOption_u64Z min_value_msat, uint32_t invoice_expiry_delta_secs);
	public static native long ChannelManager_create_inbound_payment_legacy(long this_arg, long min_value_msat, int invoice_expiry_delta_secs);
	// MUST_USE_RES struct LDKCResult_PaymentSecretNoneZ ChannelManager_create_inbound_payment_for_hash(const struct LDKChannelManager *NONNULL_PTR this_arg, struct LDKThirtyTwoBytes payment_hash, struct LDKCOption_u64Z min_value_msat, uint32_t invoice_expiry_delta_secs);
	public static native long ChannelManager_create_inbound_payment_for_hash(long this_arg, byte[] payment_hash, long min_value_msat, int invoice_expiry_delta_secs);
	// MUST_USE_RES struct LDKCResult_PaymentSecretAPIErrorZ ChannelManager_create_inbound_payment_for_hash_legacy(const struct LDKChannelManager *NONNULL_PTR this_arg, struct LDKThirtyTwoBytes payment_hash, struct LDKCOption_u64Z min_value_msat, uint32_t invoice_expiry_delta_secs);
	public static native long ChannelManager_create_inbound_payment_for_hash_legacy(long this_arg, byte[] payment_hash, long min_value_msat, int invoice_expiry_delta_secs);
	// MUST_USE_RES struct LDKCResult_PaymentPreimageAPIErrorZ ChannelManager_get_payment_preimage(const struct LDKChannelManager *NONNULL_PTR this_arg, struct LDKThirtyTwoBytes payment_hash, struct LDKThirtyTwoBytes payment_secret);
	public static native long ChannelManager_get_payment_preimage(long this_arg, byte[] payment_hash, byte[] payment_secret);
	// MUST_USE_RES uint64_t ChannelManager_get_phantom_scid(const struct LDKChannelManager *NONNULL_PTR this_arg);
	public static native long ChannelManager_get_phantom_scid(long this_arg);
	// MUST_USE_RES struct LDKPhantomRouteHints ChannelManager_get_phantom_route_hints(const struct LDKChannelManager *NONNULL_PTR this_arg);
	public static native long ChannelManager_get_phantom_route_hints(long this_arg);
	// struct LDKMessageSendEventsProvider ChannelManager_as_MessageSendEventsProvider(const struct LDKChannelManager *NONNULL_PTR this_arg);
	public static native long ChannelManager_as_MessageSendEventsProvider(long this_arg);
	// struct LDKEventsProvider ChannelManager_as_EventsProvider(const struct LDKChannelManager *NONNULL_PTR this_arg);
	public static native long ChannelManager_as_EventsProvider(long this_arg);
	// struct LDKListen ChannelManager_as_Listen(const struct LDKChannelManager *NONNULL_PTR this_arg);
	public static native long ChannelManager_as_Listen(long this_arg);
	// struct LDKConfirm ChannelManager_as_Confirm(const struct LDKChannelManager *NONNULL_PTR this_arg);
	public static native long ChannelManager_as_Confirm(long this_arg);
	// MUST_USE_RES bool ChannelManager_await_persistable_update_timeout(const struct LDKChannelManager *NONNULL_PTR this_arg, uint64_t max_wait);
	public static native boolean ChannelManager_await_persistable_update_timeout(long this_arg, long max_wait);
	// void ChannelManager_await_persistable_update(const struct LDKChannelManager *NONNULL_PTR this_arg);
	public static native void ChannelManager_await_persistable_update(long this_arg);
	// MUST_USE_RES struct LDKBestBlock ChannelManager_current_best_block(const struct LDKChannelManager *NONNULL_PTR this_arg);
	public static native long ChannelManager_current_best_block(long this_arg);
	// struct LDKChannelMessageHandler ChannelManager_as_ChannelMessageHandler(const struct LDKChannelManager *NONNULL_PTR this_arg);
	public static native long ChannelManager_as_ChannelMessageHandler(long this_arg);
	// struct LDKCVec_u8Z CounterpartyForwardingInfo_write(const struct LDKCounterpartyForwardingInfo *NONNULL_PTR obj);
	public static native byte[] CounterpartyForwardingInfo_write(long obj);
	// struct LDKCResult_CounterpartyForwardingInfoDecodeErrorZ CounterpartyForwardingInfo_read(struct LDKu8slice ser);
	public static native long CounterpartyForwardingInfo_read(byte[] ser);
	// struct LDKCVec_u8Z ChannelCounterparty_write(const struct LDKChannelCounterparty *NONNULL_PTR obj);
	public static native byte[] ChannelCounterparty_write(long obj);
	// struct LDKCResult_ChannelCounterpartyDecodeErrorZ ChannelCounterparty_read(struct LDKu8slice ser);
	public static native long ChannelCounterparty_read(byte[] ser);
	// struct LDKCVec_u8Z ChannelDetails_write(const struct LDKChannelDetails *NONNULL_PTR obj);
	public static native byte[] ChannelDetails_write(long obj);
	// struct LDKCResult_ChannelDetailsDecodeErrorZ ChannelDetails_read(struct LDKu8slice ser);
	public static native long ChannelDetails_read(byte[] ser);
	// struct LDKCVec_u8Z PhantomRouteHints_write(const struct LDKPhantomRouteHints *NONNULL_PTR obj);
	public static native byte[] PhantomRouteHints_write(long obj);
	// struct LDKCResult_PhantomRouteHintsDecodeErrorZ PhantomRouteHints_read(struct LDKu8slice ser);
	public static native long PhantomRouteHints_read(byte[] ser);
	// struct LDKCVec_u8Z ChannelManager_write(const struct LDKChannelManager *NONNULL_PTR obj);
	public static native byte[] ChannelManager_write(long obj);
	// void ChannelManagerReadArgs_free(struct LDKChannelManagerReadArgs this_obj);
	public static native void ChannelManagerReadArgs_free(long this_obj);
	// const struct LDKKeysInterface *ChannelManagerReadArgs_get_keys_manager(const struct LDKChannelManagerReadArgs *NONNULL_PTR this_ptr);
	public static native long ChannelManagerReadArgs_get_keys_manager(long this_ptr);
	// void ChannelManagerReadArgs_set_keys_manager(struct LDKChannelManagerReadArgs *NONNULL_PTR this_ptr, struct LDKKeysInterface val);
	public static native void ChannelManagerReadArgs_set_keys_manager(long this_ptr, long val);
	// const struct LDKFeeEstimator *ChannelManagerReadArgs_get_fee_estimator(const struct LDKChannelManagerReadArgs *NONNULL_PTR this_ptr);
	public static native long ChannelManagerReadArgs_get_fee_estimator(long this_ptr);
	// void ChannelManagerReadArgs_set_fee_estimator(struct LDKChannelManagerReadArgs *NONNULL_PTR this_ptr, struct LDKFeeEstimator val);
	public static native void ChannelManagerReadArgs_set_fee_estimator(long this_ptr, long val);
	// const struct LDKWatch *ChannelManagerReadArgs_get_chain_monitor(const struct LDKChannelManagerReadArgs *NONNULL_PTR this_ptr);
	public static native long ChannelManagerReadArgs_get_chain_monitor(long this_ptr);
	// void ChannelManagerReadArgs_set_chain_monitor(struct LDKChannelManagerReadArgs *NONNULL_PTR this_ptr, struct LDKWatch val);
	public static native void ChannelManagerReadArgs_set_chain_monitor(long this_ptr, long val);
	// const struct LDKBroadcasterInterface *ChannelManagerReadArgs_get_tx_broadcaster(const struct LDKChannelManagerReadArgs *NONNULL_PTR this_ptr);
	public static native long ChannelManagerReadArgs_get_tx_broadcaster(long this_ptr);
	// void ChannelManagerReadArgs_set_tx_broadcaster(struct LDKChannelManagerReadArgs *NONNULL_PTR this_ptr, struct LDKBroadcasterInterface val);
	public static native void ChannelManagerReadArgs_set_tx_broadcaster(long this_ptr, long val);
	// const struct LDKLogger *ChannelManagerReadArgs_get_logger(const struct LDKChannelManagerReadArgs *NONNULL_PTR this_ptr);
	public static native long ChannelManagerReadArgs_get_logger(long this_ptr);
	// void ChannelManagerReadArgs_set_logger(struct LDKChannelManagerReadArgs *NONNULL_PTR this_ptr, struct LDKLogger val);
	public static native void ChannelManagerReadArgs_set_logger(long this_ptr, long val);
	// struct LDKUserConfig ChannelManagerReadArgs_get_default_config(const struct LDKChannelManagerReadArgs *NONNULL_PTR this_ptr);
	public static native long ChannelManagerReadArgs_get_default_config(long this_ptr);
	// void ChannelManagerReadArgs_set_default_config(struct LDKChannelManagerReadArgs *NONNULL_PTR this_ptr, struct LDKUserConfig val);
	public static native void ChannelManagerReadArgs_set_default_config(long this_ptr, long val);
	// MUST_USE_RES struct LDKChannelManagerReadArgs ChannelManagerReadArgs_new(struct LDKKeysInterface keys_manager, struct LDKFeeEstimator fee_estimator, struct LDKWatch chain_monitor, struct LDKBroadcasterInterface tx_broadcaster, struct LDKLogger logger, struct LDKUserConfig default_config, struct LDKCVec_ChannelMonitorZ channel_monitors);
	public static native long ChannelManagerReadArgs_new(long keys_manager, long fee_estimator, long chain_monitor, long tx_broadcaster, long logger, long default_config, long[] channel_monitors);
	// struct LDKCResult_C2Tuple_BlockHashChannelManagerZDecodeErrorZ C2Tuple_BlockHashChannelManagerZ_read(struct LDKu8slice ser, struct LDKChannelManagerReadArgs arg);
	public static native long C2Tuple_BlockHashChannelManagerZ_read(byte[] ser, long arg);
	// void DecodeError_free(struct LDKDecodeError this_obj);
	public static native void DecodeError_free(long this_obj);
	// uintptr_t DecodeError_clone_ptr(LDKDecodeError *NONNULL_PTR arg);
	public static native long DecodeError_clone_ptr(long arg);
	// struct LDKDecodeError DecodeError_clone(const struct LDKDecodeError *NONNULL_PTR orig);
	public static native long DecodeError_clone(long orig);
	// void Init_free(struct LDKInit this_obj);
	public static native void Init_free(long this_obj);
	// struct LDKInitFeatures Init_get_features(const struct LDKInit *NONNULL_PTR this_ptr);
	public static native long Init_get_features(long this_ptr);
	// void Init_set_features(struct LDKInit *NONNULL_PTR this_ptr, struct LDKInitFeatures val);
	public static native void Init_set_features(long this_ptr, long val);
	// struct LDKCOption_NetAddressZ Init_get_remote_network_address(const struct LDKInit *NONNULL_PTR this_ptr);
	public static native long Init_get_remote_network_address(long this_ptr);
	// void Init_set_remote_network_address(struct LDKInit *NONNULL_PTR this_ptr, struct LDKCOption_NetAddressZ val);
	public static native void Init_set_remote_network_address(long this_ptr, long val);
	// MUST_USE_RES struct LDKInit Init_new(struct LDKInitFeatures features_arg, struct LDKCOption_NetAddressZ remote_network_address_arg);
	public static native long Init_new(long features_arg, long remote_network_address_arg);
	// uintptr_t Init_clone_ptr(LDKInit *NONNULL_PTR arg);
	public static native long Init_clone_ptr(long arg);
	// struct LDKInit Init_clone(const struct LDKInit *NONNULL_PTR orig);
	public static native long Init_clone(long orig);
	// void ErrorMessage_free(struct LDKErrorMessage this_obj);
	public static native void ErrorMessage_free(long this_obj);
	// const uint8_t (*ErrorMessage_get_channel_id(const struct LDKErrorMessage *NONNULL_PTR this_ptr))[32];
	public static native byte[] ErrorMessage_get_channel_id(long this_ptr);
	// void ErrorMessage_set_channel_id(struct LDKErrorMessage *NONNULL_PTR this_ptr, struct LDKThirtyTwoBytes val);
	public static native void ErrorMessage_set_channel_id(long this_ptr, byte[] val);
	// struct LDKStr ErrorMessage_get_data(const struct LDKErrorMessage *NONNULL_PTR this_ptr);
	public static native String ErrorMessage_get_data(long this_ptr);
	// void ErrorMessage_set_data(struct LDKErrorMessage *NONNULL_PTR this_ptr, struct LDKStr val);
	public static native void ErrorMessage_set_data(long this_ptr, String val);
	// MUST_USE_RES struct LDKErrorMessage ErrorMessage_new(struct LDKThirtyTwoBytes channel_id_arg, struct LDKStr data_arg);
	public static native long ErrorMessage_new(byte[] channel_id_arg, String data_arg);
	// uintptr_t ErrorMessage_clone_ptr(LDKErrorMessage *NONNULL_PTR arg);
	public static native long ErrorMessage_clone_ptr(long arg);
	// struct LDKErrorMessage ErrorMessage_clone(const struct LDKErrorMessage *NONNULL_PTR orig);
	public static native long ErrorMessage_clone(long orig);
	// void WarningMessage_free(struct LDKWarningMessage this_obj);
	public static native void WarningMessage_free(long this_obj);
	// const uint8_t (*WarningMessage_get_channel_id(const struct LDKWarningMessage *NONNULL_PTR this_ptr))[32];
	public static native byte[] WarningMessage_get_channel_id(long this_ptr);
	// void WarningMessage_set_channel_id(struct LDKWarningMessage *NONNULL_PTR this_ptr, struct LDKThirtyTwoBytes val);
	public static native void WarningMessage_set_channel_id(long this_ptr, byte[] val);
	// struct LDKStr WarningMessage_get_data(const struct LDKWarningMessage *NONNULL_PTR this_ptr);
	public static native String WarningMessage_get_data(long this_ptr);
	// void WarningMessage_set_data(struct LDKWarningMessage *NONNULL_PTR this_ptr, struct LDKStr val);
	public static native void WarningMessage_set_data(long this_ptr, String val);
	// MUST_USE_RES struct LDKWarningMessage WarningMessage_new(struct LDKThirtyTwoBytes channel_id_arg, struct LDKStr data_arg);
	public static native long WarningMessage_new(byte[] channel_id_arg, String data_arg);
	// uintptr_t WarningMessage_clone_ptr(LDKWarningMessage *NONNULL_PTR arg);
	public static native long WarningMessage_clone_ptr(long arg);
	// struct LDKWarningMessage WarningMessage_clone(const struct LDKWarningMessage *NONNULL_PTR orig);
	public static native long WarningMessage_clone(long orig);
	// void Ping_free(struct LDKPing this_obj);
	public static native void Ping_free(long this_obj);
	// uint16_t Ping_get_ponglen(const struct LDKPing *NONNULL_PTR this_ptr);
	public static native short Ping_get_ponglen(long this_ptr);
	// void Ping_set_ponglen(struct LDKPing *NONNULL_PTR this_ptr, uint16_t val);
	public static native void Ping_set_ponglen(long this_ptr, short val);
	// uint16_t Ping_get_byteslen(const struct LDKPing *NONNULL_PTR this_ptr);
	public static native short Ping_get_byteslen(long this_ptr);
	// void Ping_set_byteslen(struct LDKPing *NONNULL_PTR this_ptr, uint16_t val);
	public static native void Ping_set_byteslen(long this_ptr, short val);
	// MUST_USE_RES struct LDKPing Ping_new(uint16_t ponglen_arg, uint16_t byteslen_arg);
	public static native long Ping_new(short ponglen_arg, short byteslen_arg);
	// uintptr_t Ping_clone_ptr(LDKPing *NONNULL_PTR arg);
	public static native long Ping_clone_ptr(long arg);
	// struct LDKPing Ping_clone(const struct LDKPing *NONNULL_PTR orig);
	public static native long Ping_clone(long orig);
	// void Pong_free(struct LDKPong this_obj);
	public static native void Pong_free(long this_obj);
	// uint16_t Pong_get_byteslen(const struct LDKPong *NONNULL_PTR this_ptr);
	public static native short Pong_get_byteslen(long this_ptr);
	// void Pong_set_byteslen(struct LDKPong *NONNULL_PTR this_ptr, uint16_t val);
	public static native void Pong_set_byteslen(long this_ptr, short val);
	// MUST_USE_RES struct LDKPong Pong_new(uint16_t byteslen_arg);
	public static native long Pong_new(short byteslen_arg);
	// uintptr_t Pong_clone_ptr(LDKPong *NONNULL_PTR arg);
	public static native long Pong_clone_ptr(long arg);
	// struct LDKPong Pong_clone(const struct LDKPong *NONNULL_PTR orig);
	public static native long Pong_clone(long orig);
	// void OpenChannel_free(struct LDKOpenChannel this_obj);
	public static native void OpenChannel_free(long this_obj);
	// const uint8_t (*OpenChannel_get_chain_hash(const struct LDKOpenChannel *NONNULL_PTR this_ptr))[32];
	public static native byte[] OpenChannel_get_chain_hash(long this_ptr);
	// void OpenChannel_set_chain_hash(struct LDKOpenChannel *NONNULL_PTR this_ptr, struct LDKThirtyTwoBytes val);
	public static native void OpenChannel_set_chain_hash(long this_ptr, byte[] val);
	// const uint8_t (*OpenChannel_get_temporary_channel_id(const struct LDKOpenChannel *NONNULL_PTR this_ptr))[32];
	public static native byte[] OpenChannel_get_temporary_channel_id(long this_ptr);
	// void OpenChannel_set_temporary_channel_id(struct LDKOpenChannel *NONNULL_PTR this_ptr, struct LDKThirtyTwoBytes val);
	public static native void OpenChannel_set_temporary_channel_id(long this_ptr, byte[] val);
	// uint64_t OpenChannel_get_funding_satoshis(const struct LDKOpenChannel *NONNULL_PTR this_ptr);
	public static native long OpenChannel_get_funding_satoshis(long this_ptr);
	// void OpenChannel_set_funding_satoshis(struct LDKOpenChannel *NONNULL_PTR this_ptr, uint64_t val);
	public static native void OpenChannel_set_funding_satoshis(long this_ptr, long val);
	// uint64_t OpenChannel_get_push_msat(const struct LDKOpenChannel *NONNULL_PTR this_ptr);
	public static native long OpenChannel_get_push_msat(long this_ptr);
	// void OpenChannel_set_push_msat(struct LDKOpenChannel *NONNULL_PTR this_ptr, uint64_t val);
	public static native void OpenChannel_set_push_msat(long this_ptr, long val);
	// uint64_t OpenChannel_get_dust_limit_satoshis(const struct LDKOpenChannel *NONNULL_PTR this_ptr);
	public static native long OpenChannel_get_dust_limit_satoshis(long this_ptr);
	// void OpenChannel_set_dust_limit_satoshis(struct LDKOpenChannel *NONNULL_PTR this_ptr, uint64_t val);
	public static native void OpenChannel_set_dust_limit_satoshis(long this_ptr, long val);
	// uint64_t OpenChannel_get_max_htlc_value_in_flight_msat(const struct LDKOpenChannel *NONNULL_PTR this_ptr);
	public static native long OpenChannel_get_max_htlc_value_in_flight_msat(long this_ptr);
	// void OpenChannel_set_max_htlc_value_in_flight_msat(struct LDKOpenChannel *NONNULL_PTR this_ptr, uint64_t val);
	public static native void OpenChannel_set_max_htlc_value_in_flight_msat(long this_ptr, long val);
	// uint64_t OpenChannel_get_channel_reserve_satoshis(const struct LDKOpenChannel *NONNULL_PTR this_ptr);
	public static native long OpenChannel_get_channel_reserve_satoshis(long this_ptr);
	// void OpenChannel_set_channel_reserve_satoshis(struct LDKOpenChannel *NONNULL_PTR this_ptr, uint64_t val);
	public static native void OpenChannel_set_channel_reserve_satoshis(long this_ptr, long val);
	// uint64_t OpenChannel_get_htlc_minimum_msat(const struct LDKOpenChannel *NONNULL_PTR this_ptr);
	public static native long OpenChannel_get_htlc_minimum_msat(long this_ptr);
	// void OpenChannel_set_htlc_minimum_msat(struct LDKOpenChannel *NONNULL_PTR this_ptr, uint64_t val);
	public static native void OpenChannel_set_htlc_minimum_msat(long this_ptr, long val);
	// uint32_t OpenChannel_get_feerate_per_kw(const struct LDKOpenChannel *NONNULL_PTR this_ptr);
	public static native int OpenChannel_get_feerate_per_kw(long this_ptr);
	// void OpenChannel_set_feerate_per_kw(struct LDKOpenChannel *NONNULL_PTR this_ptr, uint32_t val);
	public static native void OpenChannel_set_feerate_per_kw(long this_ptr, int val);
	// uint16_t OpenChannel_get_to_self_delay(const struct LDKOpenChannel *NONNULL_PTR this_ptr);
	public static native short OpenChannel_get_to_self_delay(long this_ptr);
	// void OpenChannel_set_to_self_delay(struct LDKOpenChannel *NONNULL_PTR this_ptr, uint16_t val);
	public static native void OpenChannel_set_to_self_delay(long this_ptr, short val);
	// uint16_t OpenChannel_get_max_accepted_htlcs(const struct LDKOpenChannel *NONNULL_PTR this_ptr);
	public static native short OpenChannel_get_max_accepted_htlcs(long this_ptr);
	// void OpenChannel_set_max_accepted_htlcs(struct LDKOpenChannel *NONNULL_PTR this_ptr, uint16_t val);
	public static native void OpenChannel_set_max_accepted_htlcs(long this_ptr, short val);
	// struct LDKPublicKey OpenChannel_get_funding_pubkey(const struct LDKOpenChannel *NONNULL_PTR this_ptr);
	public static native byte[] OpenChannel_get_funding_pubkey(long this_ptr);
	// void OpenChannel_set_funding_pubkey(struct LDKOpenChannel *NONNULL_PTR this_ptr, struct LDKPublicKey val);
	public static native void OpenChannel_set_funding_pubkey(long this_ptr, byte[] val);
	// struct LDKPublicKey OpenChannel_get_revocation_basepoint(const struct LDKOpenChannel *NONNULL_PTR this_ptr);
	public static native byte[] OpenChannel_get_revocation_basepoint(long this_ptr);
	// void OpenChannel_set_revocation_basepoint(struct LDKOpenChannel *NONNULL_PTR this_ptr, struct LDKPublicKey val);
	public static native void OpenChannel_set_revocation_basepoint(long this_ptr, byte[] val);
	// struct LDKPublicKey OpenChannel_get_payment_point(const struct LDKOpenChannel *NONNULL_PTR this_ptr);
	public static native byte[] OpenChannel_get_payment_point(long this_ptr);
	// void OpenChannel_set_payment_point(struct LDKOpenChannel *NONNULL_PTR this_ptr, struct LDKPublicKey val);
	public static native void OpenChannel_set_payment_point(long this_ptr, byte[] val);
	// struct LDKPublicKey OpenChannel_get_delayed_payment_basepoint(const struct LDKOpenChannel *NONNULL_PTR this_ptr);
	public static native byte[] OpenChannel_get_delayed_payment_basepoint(long this_ptr);
	// void OpenChannel_set_delayed_payment_basepoint(struct LDKOpenChannel *NONNULL_PTR this_ptr, struct LDKPublicKey val);
	public static native void OpenChannel_set_delayed_payment_basepoint(long this_ptr, byte[] val);
	// struct LDKPublicKey OpenChannel_get_htlc_basepoint(const struct LDKOpenChannel *NONNULL_PTR this_ptr);
	public static native byte[] OpenChannel_get_htlc_basepoint(long this_ptr);
	// void OpenChannel_set_htlc_basepoint(struct LDKOpenChannel *NONNULL_PTR this_ptr, struct LDKPublicKey val);
	public static native void OpenChannel_set_htlc_basepoint(long this_ptr, byte[] val);
	// struct LDKPublicKey OpenChannel_get_first_per_commitment_point(const struct LDKOpenChannel *NONNULL_PTR this_ptr);
	public static native byte[] OpenChannel_get_first_per_commitment_point(long this_ptr);
	// void OpenChannel_set_first_per_commitment_point(struct LDKOpenChannel *NONNULL_PTR this_ptr, struct LDKPublicKey val);
	public static native void OpenChannel_set_first_per_commitment_point(long this_ptr, byte[] val);
	// uint8_t OpenChannel_get_channel_flags(const struct LDKOpenChannel *NONNULL_PTR this_ptr);
	public static native byte OpenChannel_get_channel_flags(long this_ptr);
	// void OpenChannel_set_channel_flags(struct LDKOpenChannel *NONNULL_PTR this_ptr, uint8_t val);
	public static native void OpenChannel_set_channel_flags(long this_ptr, byte val);
	// struct LDKChannelTypeFeatures OpenChannel_get_channel_type(const struct LDKOpenChannel *NONNULL_PTR this_ptr);
	public static native long OpenChannel_get_channel_type(long this_ptr);
	// void OpenChannel_set_channel_type(struct LDKOpenChannel *NONNULL_PTR this_ptr, struct LDKChannelTypeFeatures val);
	public static native void OpenChannel_set_channel_type(long this_ptr, long val);
	// uintptr_t OpenChannel_clone_ptr(LDKOpenChannel *NONNULL_PTR arg);
	public static native long OpenChannel_clone_ptr(long arg);
	// struct LDKOpenChannel OpenChannel_clone(const struct LDKOpenChannel *NONNULL_PTR orig);
	public static native long OpenChannel_clone(long orig);
	// void AcceptChannel_free(struct LDKAcceptChannel this_obj);
	public static native void AcceptChannel_free(long this_obj);
	// const uint8_t (*AcceptChannel_get_temporary_channel_id(const struct LDKAcceptChannel *NONNULL_PTR this_ptr))[32];
	public static native byte[] AcceptChannel_get_temporary_channel_id(long this_ptr);
	// void AcceptChannel_set_temporary_channel_id(struct LDKAcceptChannel *NONNULL_PTR this_ptr, struct LDKThirtyTwoBytes val);
	public static native void AcceptChannel_set_temporary_channel_id(long this_ptr, byte[] val);
	// uint64_t AcceptChannel_get_dust_limit_satoshis(const struct LDKAcceptChannel *NONNULL_PTR this_ptr);
	public static native long AcceptChannel_get_dust_limit_satoshis(long this_ptr);
	// void AcceptChannel_set_dust_limit_satoshis(struct LDKAcceptChannel *NONNULL_PTR this_ptr, uint64_t val);
	public static native void AcceptChannel_set_dust_limit_satoshis(long this_ptr, long val);
	// uint64_t AcceptChannel_get_max_htlc_value_in_flight_msat(const struct LDKAcceptChannel *NONNULL_PTR this_ptr);
	public static native long AcceptChannel_get_max_htlc_value_in_flight_msat(long this_ptr);
	// void AcceptChannel_set_max_htlc_value_in_flight_msat(struct LDKAcceptChannel *NONNULL_PTR this_ptr, uint64_t val);
	public static native void AcceptChannel_set_max_htlc_value_in_flight_msat(long this_ptr, long val);
	// uint64_t AcceptChannel_get_channel_reserve_satoshis(const struct LDKAcceptChannel *NONNULL_PTR this_ptr);
	public static native long AcceptChannel_get_channel_reserve_satoshis(long this_ptr);
	// void AcceptChannel_set_channel_reserve_satoshis(struct LDKAcceptChannel *NONNULL_PTR this_ptr, uint64_t val);
	public static native void AcceptChannel_set_channel_reserve_satoshis(long this_ptr, long val);
	// uint64_t AcceptChannel_get_htlc_minimum_msat(const struct LDKAcceptChannel *NONNULL_PTR this_ptr);
	public static native long AcceptChannel_get_htlc_minimum_msat(long this_ptr);
	// void AcceptChannel_set_htlc_minimum_msat(struct LDKAcceptChannel *NONNULL_PTR this_ptr, uint64_t val);
	public static native void AcceptChannel_set_htlc_minimum_msat(long this_ptr, long val);
	// uint32_t AcceptChannel_get_minimum_depth(const struct LDKAcceptChannel *NONNULL_PTR this_ptr);
	public static native int AcceptChannel_get_minimum_depth(long this_ptr);
	// void AcceptChannel_set_minimum_depth(struct LDKAcceptChannel *NONNULL_PTR this_ptr, uint32_t val);
	public static native void AcceptChannel_set_minimum_depth(long this_ptr, int val);
	// uint16_t AcceptChannel_get_to_self_delay(const struct LDKAcceptChannel *NONNULL_PTR this_ptr);
	public static native short AcceptChannel_get_to_self_delay(long this_ptr);
	// void AcceptChannel_set_to_self_delay(struct LDKAcceptChannel *NONNULL_PTR this_ptr, uint16_t val);
	public static native void AcceptChannel_set_to_self_delay(long this_ptr, short val);
	// uint16_t AcceptChannel_get_max_accepted_htlcs(const struct LDKAcceptChannel *NONNULL_PTR this_ptr);
	public static native short AcceptChannel_get_max_accepted_htlcs(long this_ptr);
	// void AcceptChannel_set_max_accepted_htlcs(struct LDKAcceptChannel *NONNULL_PTR this_ptr, uint16_t val);
	public static native void AcceptChannel_set_max_accepted_htlcs(long this_ptr, short val);
	// struct LDKPublicKey AcceptChannel_get_funding_pubkey(const struct LDKAcceptChannel *NONNULL_PTR this_ptr);
	public static native byte[] AcceptChannel_get_funding_pubkey(long this_ptr);
	// void AcceptChannel_set_funding_pubkey(struct LDKAcceptChannel *NONNULL_PTR this_ptr, struct LDKPublicKey val);
	public static native void AcceptChannel_set_funding_pubkey(long this_ptr, byte[] val);
	// struct LDKPublicKey AcceptChannel_get_revocation_basepoint(const struct LDKAcceptChannel *NONNULL_PTR this_ptr);
	public static native byte[] AcceptChannel_get_revocation_basepoint(long this_ptr);
	// void AcceptChannel_set_revocation_basepoint(struct LDKAcceptChannel *NONNULL_PTR this_ptr, struct LDKPublicKey val);
	public static native void AcceptChannel_set_revocation_basepoint(long this_ptr, byte[] val);
	// struct LDKPublicKey AcceptChannel_get_payment_point(const struct LDKAcceptChannel *NONNULL_PTR this_ptr);
	public static native byte[] AcceptChannel_get_payment_point(long this_ptr);
	// void AcceptChannel_set_payment_point(struct LDKAcceptChannel *NONNULL_PTR this_ptr, struct LDKPublicKey val);
	public static native void AcceptChannel_set_payment_point(long this_ptr, byte[] val);
	// struct LDKPublicKey AcceptChannel_get_delayed_payment_basepoint(const struct LDKAcceptChannel *NONNULL_PTR this_ptr);
	public static native byte[] AcceptChannel_get_delayed_payment_basepoint(long this_ptr);
	// void AcceptChannel_set_delayed_payment_basepoint(struct LDKAcceptChannel *NONNULL_PTR this_ptr, struct LDKPublicKey val);
	public static native void AcceptChannel_set_delayed_payment_basepoint(long this_ptr, byte[] val);
	// struct LDKPublicKey AcceptChannel_get_htlc_basepoint(const struct LDKAcceptChannel *NONNULL_PTR this_ptr);
	public static native byte[] AcceptChannel_get_htlc_basepoint(long this_ptr);
	// void AcceptChannel_set_htlc_basepoint(struct LDKAcceptChannel *NONNULL_PTR this_ptr, struct LDKPublicKey val);
	public static native void AcceptChannel_set_htlc_basepoint(long this_ptr, byte[] val);
	// struct LDKPublicKey AcceptChannel_get_first_per_commitment_point(const struct LDKAcceptChannel *NONNULL_PTR this_ptr);
	public static native byte[] AcceptChannel_get_first_per_commitment_point(long this_ptr);
	// void AcceptChannel_set_first_per_commitment_point(struct LDKAcceptChannel *NONNULL_PTR this_ptr, struct LDKPublicKey val);
	public static native void AcceptChannel_set_first_per_commitment_point(long this_ptr, byte[] val);
	// struct LDKChannelTypeFeatures AcceptChannel_get_channel_type(const struct LDKAcceptChannel *NONNULL_PTR this_ptr);
	public static native long AcceptChannel_get_channel_type(long this_ptr);
	// void AcceptChannel_set_channel_type(struct LDKAcceptChannel *NONNULL_PTR this_ptr, struct LDKChannelTypeFeatures val);
	public static native void AcceptChannel_set_channel_type(long this_ptr, long val);
	// uintptr_t AcceptChannel_clone_ptr(LDKAcceptChannel *NONNULL_PTR arg);
	public static native long AcceptChannel_clone_ptr(long arg);
	// struct LDKAcceptChannel AcceptChannel_clone(const struct LDKAcceptChannel *NONNULL_PTR orig);
	public static native long AcceptChannel_clone(long orig);
	// void FundingCreated_free(struct LDKFundingCreated this_obj);
	public static native void FundingCreated_free(long this_obj);
	// const uint8_t (*FundingCreated_get_temporary_channel_id(const struct LDKFundingCreated *NONNULL_PTR this_ptr))[32];
	public static native byte[] FundingCreated_get_temporary_channel_id(long this_ptr);
	// void FundingCreated_set_temporary_channel_id(struct LDKFundingCreated *NONNULL_PTR this_ptr, struct LDKThirtyTwoBytes val);
	public static native void FundingCreated_set_temporary_channel_id(long this_ptr, byte[] val);
	// const uint8_t (*FundingCreated_get_funding_txid(const struct LDKFundingCreated *NONNULL_PTR this_ptr))[32];
	public static native byte[] FundingCreated_get_funding_txid(long this_ptr);
	// void FundingCreated_set_funding_txid(struct LDKFundingCreated *NONNULL_PTR this_ptr, struct LDKThirtyTwoBytes val);
	public static native void FundingCreated_set_funding_txid(long this_ptr, byte[] val);
	// uint16_t FundingCreated_get_funding_output_index(const struct LDKFundingCreated *NONNULL_PTR this_ptr);
	public static native short FundingCreated_get_funding_output_index(long this_ptr);
	// void FundingCreated_set_funding_output_index(struct LDKFundingCreated *NONNULL_PTR this_ptr, uint16_t val);
	public static native void FundingCreated_set_funding_output_index(long this_ptr, short val);
	// struct LDKSignature FundingCreated_get_signature(const struct LDKFundingCreated *NONNULL_PTR this_ptr);
	public static native byte[] FundingCreated_get_signature(long this_ptr);
	// void FundingCreated_set_signature(struct LDKFundingCreated *NONNULL_PTR this_ptr, struct LDKSignature val);
	public static native void FundingCreated_set_signature(long this_ptr, byte[] val);
	// MUST_USE_RES struct LDKFundingCreated FundingCreated_new(struct LDKThirtyTwoBytes temporary_channel_id_arg, struct LDKThirtyTwoBytes funding_txid_arg, uint16_t funding_output_index_arg, struct LDKSignature signature_arg);
	public static native long FundingCreated_new(byte[] temporary_channel_id_arg, byte[] funding_txid_arg, short funding_output_index_arg, byte[] signature_arg);
	// uintptr_t FundingCreated_clone_ptr(LDKFundingCreated *NONNULL_PTR arg);
	public static native long FundingCreated_clone_ptr(long arg);
	// struct LDKFundingCreated FundingCreated_clone(const struct LDKFundingCreated *NONNULL_PTR orig);
	public static native long FundingCreated_clone(long orig);
	// void FundingSigned_free(struct LDKFundingSigned this_obj);
	public static native void FundingSigned_free(long this_obj);
	// const uint8_t (*FundingSigned_get_channel_id(const struct LDKFundingSigned *NONNULL_PTR this_ptr))[32];
	public static native byte[] FundingSigned_get_channel_id(long this_ptr);
	// void FundingSigned_set_channel_id(struct LDKFundingSigned *NONNULL_PTR this_ptr, struct LDKThirtyTwoBytes val);
	public static native void FundingSigned_set_channel_id(long this_ptr, byte[] val);
	// struct LDKSignature FundingSigned_get_signature(const struct LDKFundingSigned *NONNULL_PTR this_ptr);
	public static native byte[] FundingSigned_get_signature(long this_ptr);
	// void FundingSigned_set_signature(struct LDKFundingSigned *NONNULL_PTR this_ptr, struct LDKSignature val);
	public static native void FundingSigned_set_signature(long this_ptr, byte[] val);
	// MUST_USE_RES struct LDKFundingSigned FundingSigned_new(struct LDKThirtyTwoBytes channel_id_arg, struct LDKSignature signature_arg);
	public static native long FundingSigned_new(byte[] channel_id_arg, byte[] signature_arg);
	// uintptr_t FundingSigned_clone_ptr(LDKFundingSigned *NONNULL_PTR arg);
	public static native long FundingSigned_clone_ptr(long arg);
	// struct LDKFundingSigned FundingSigned_clone(const struct LDKFundingSigned *NONNULL_PTR orig);
	public static native long FundingSigned_clone(long orig);
	// void FundingLocked_free(struct LDKFundingLocked this_obj);
	public static native void FundingLocked_free(long this_obj);
	// const uint8_t (*FundingLocked_get_channel_id(const struct LDKFundingLocked *NONNULL_PTR this_ptr))[32];
	public static native byte[] FundingLocked_get_channel_id(long this_ptr);
	// void FundingLocked_set_channel_id(struct LDKFundingLocked *NONNULL_PTR this_ptr, struct LDKThirtyTwoBytes val);
	public static native void FundingLocked_set_channel_id(long this_ptr, byte[] val);
	// struct LDKPublicKey FundingLocked_get_next_per_commitment_point(const struct LDKFundingLocked *NONNULL_PTR this_ptr);
	public static native byte[] FundingLocked_get_next_per_commitment_point(long this_ptr);
	// void FundingLocked_set_next_per_commitment_point(struct LDKFundingLocked *NONNULL_PTR this_ptr, struct LDKPublicKey val);
	public static native void FundingLocked_set_next_per_commitment_point(long this_ptr, byte[] val);
	// struct LDKCOption_u64Z FundingLocked_get_short_channel_id_alias(const struct LDKFundingLocked *NONNULL_PTR this_ptr);
	public static native long FundingLocked_get_short_channel_id_alias(long this_ptr);
	// void FundingLocked_set_short_channel_id_alias(struct LDKFundingLocked *NONNULL_PTR this_ptr, struct LDKCOption_u64Z val);
	public static native void FundingLocked_set_short_channel_id_alias(long this_ptr, long val);
	// MUST_USE_RES struct LDKFundingLocked FundingLocked_new(struct LDKThirtyTwoBytes channel_id_arg, struct LDKPublicKey next_per_commitment_point_arg, struct LDKCOption_u64Z short_channel_id_alias_arg);
	public static native long FundingLocked_new(byte[] channel_id_arg, byte[] next_per_commitment_point_arg, long short_channel_id_alias_arg);
	// uintptr_t FundingLocked_clone_ptr(LDKFundingLocked *NONNULL_PTR arg);
	public static native long FundingLocked_clone_ptr(long arg);
	// struct LDKFundingLocked FundingLocked_clone(const struct LDKFundingLocked *NONNULL_PTR orig);
	public static native long FundingLocked_clone(long orig);
	// void Shutdown_free(struct LDKShutdown this_obj);
	public static native void Shutdown_free(long this_obj);
	// const uint8_t (*Shutdown_get_channel_id(const struct LDKShutdown *NONNULL_PTR this_ptr))[32];
	public static native byte[] Shutdown_get_channel_id(long this_ptr);
	// void Shutdown_set_channel_id(struct LDKShutdown *NONNULL_PTR this_ptr, struct LDKThirtyTwoBytes val);
	public static native void Shutdown_set_channel_id(long this_ptr, byte[] val);
	// struct LDKu8slice Shutdown_get_scriptpubkey(const struct LDKShutdown *NONNULL_PTR this_ptr);
	public static native byte[] Shutdown_get_scriptpubkey(long this_ptr);
	// void Shutdown_set_scriptpubkey(struct LDKShutdown *NONNULL_PTR this_ptr, struct LDKCVec_u8Z val);
	public static native void Shutdown_set_scriptpubkey(long this_ptr, byte[] val);
	// MUST_USE_RES struct LDKShutdown Shutdown_new(struct LDKThirtyTwoBytes channel_id_arg, struct LDKCVec_u8Z scriptpubkey_arg);
	public static native long Shutdown_new(byte[] channel_id_arg, byte[] scriptpubkey_arg);
	// uintptr_t Shutdown_clone_ptr(LDKShutdown *NONNULL_PTR arg);
	public static native long Shutdown_clone_ptr(long arg);
	// struct LDKShutdown Shutdown_clone(const struct LDKShutdown *NONNULL_PTR orig);
	public static native long Shutdown_clone(long orig);
	// void ClosingSignedFeeRange_free(struct LDKClosingSignedFeeRange this_obj);
	public static native void ClosingSignedFeeRange_free(long this_obj);
	// uint64_t ClosingSignedFeeRange_get_min_fee_satoshis(const struct LDKClosingSignedFeeRange *NONNULL_PTR this_ptr);
	public static native long ClosingSignedFeeRange_get_min_fee_satoshis(long this_ptr);
	// void ClosingSignedFeeRange_set_min_fee_satoshis(struct LDKClosingSignedFeeRange *NONNULL_PTR this_ptr, uint64_t val);
	public static native void ClosingSignedFeeRange_set_min_fee_satoshis(long this_ptr, long val);
	// uint64_t ClosingSignedFeeRange_get_max_fee_satoshis(const struct LDKClosingSignedFeeRange *NONNULL_PTR this_ptr);
	public static native long ClosingSignedFeeRange_get_max_fee_satoshis(long this_ptr);
	// void ClosingSignedFeeRange_set_max_fee_satoshis(struct LDKClosingSignedFeeRange *NONNULL_PTR this_ptr, uint64_t val);
	public static native void ClosingSignedFeeRange_set_max_fee_satoshis(long this_ptr, long val);
	// MUST_USE_RES struct LDKClosingSignedFeeRange ClosingSignedFeeRange_new(uint64_t min_fee_satoshis_arg, uint64_t max_fee_satoshis_arg);
	public static native long ClosingSignedFeeRange_new(long min_fee_satoshis_arg, long max_fee_satoshis_arg);
	// uintptr_t ClosingSignedFeeRange_clone_ptr(LDKClosingSignedFeeRange *NONNULL_PTR arg);
	public static native long ClosingSignedFeeRange_clone_ptr(long arg);
	// struct LDKClosingSignedFeeRange ClosingSignedFeeRange_clone(const struct LDKClosingSignedFeeRange *NONNULL_PTR orig);
	public static native long ClosingSignedFeeRange_clone(long orig);
	// void ClosingSigned_free(struct LDKClosingSigned this_obj);
	public static native void ClosingSigned_free(long this_obj);
	// const uint8_t (*ClosingSigned_get_channel_id(const struct LDKClosingSigned *NONNULL_PTR this_ptr))[32];
	public static native byte[] ClosingSigned_get_channel_id(long this_ptr);
	// void ClosingSigned_set_channel_id(struct LDKClosingSigned *NONNULL_PTR this_ptr, struct LDKThirtyTwoBytes val);
	public static native void ClosingSigned_set_channel_id(long this_ptr, byte[] val);
	// uint64_t ClosingSigned_get_fee_satoshis(const struct LDKClosingSigned *NONNULL_PTR this_ptr);
	public static native long ClosingSigned_get_fee_satoshis(long this_ptr);
	// void ClosingSigned_set_fee_satoshis(struct LDKClosingSigned *NONNULL_PTR this_ptr, uint64_t val);
	public static native void ClosingSigned_set_fee_satoshis(long this_ptr, long val);
	// struct LDKSignature ClosingSigned_get_signature(const struct LDKClosingSigned *NONNULL_PTR this_ptr);
	public static native byte[] ClosingSigned_get_signature(long this_ptr);
	// void ClosingSigned_set_signature(struct LDKClosingSigned *NONNULL_PTR this_ptr, struct LDKSignature val);
	public static native void ClosingSigned_set_signature(long this_ptr, byte[] val);
	// struct LDKClosingSignedFeeRange ClosingSigned_get_fee_range(const struct LDKClosingSigned *NONNULL_PTR this_ptr);
	public static native long ClosingSigned_get_fee_range(long this_ptr);
	// void ClosingSigned_set_fee_range(struct LDKClosingSigned *NONNULL_PTR this_ptr, struct LDKClosingSignedFeeRange val);
	public static native void ClosingSigned_set_fee_range(long this_ptr, long val);
	// MUST_USE_RES struct LDKClosingSigned ClosingSigned_new(struct LDKThirtyTwoBytes channel_id_arg, uint64_t fee_satoshis_arg, struct LDKSignature signature_arg, struct LDKClosingSignedFeeRange fee_range_arg);
	public static native long ClosingSigned_new(byte[] channel_id_arg, long fee_satoshis_arg, byte[] signature_arg, long fee_range_arg);
	// uintptr_t ClosingSigned_clone_ptr(LDKClosingSigned *NONNULL_PTR arg);
	public static native long ClosingSigned_clone_ptr(long arg);
	// struct LDKClosingSigned ClosingSigned_clone(const struct LDKClosingSigned *NONNULL_PTR orig);
	public static native long ClosingSigned_clone(long orig);
	// void UpdateAddHTLC_free(struct LDKUpdateAddHTLC this_obj);
	public static native void UpdateAddHTLC_free(long this_obj);
	// const uint8_t (*UpdateAddHTLC_get_channel_id(const struct LDKUpdateAddHTLC *NONNULL_PTR this_ptr))[32];
	public static native byte[] UpdateAddHTLC_get_channel_id(long this_ptr);
	// void UpdateAddHTLC_set_channel_id(struct LDKUpdateAddHTLC *NONNULL_PTR this_ptr, struct LDKThirtyTwoBytes val);
	public static native void UpdateAddHTLC_set_channel_id(long this_ptr, byte[] val);
	// uint64_t UpdateAddHTLC_get_htlc_id(const struct LDKUpdateAddHTLC *NONNULL_PTR this_ptr);
	public static native long UpdateAddHTLC_get_htlc_id(long this_ptr);
	// void UpdateAddHTLC_set_htlc_id(struct LDKUpdateAddHTLC *NONNULL_PTR this_ptr, uint64_t val);
	public static native void UpdateAddHTLC_set_htlc_id(long this_ptr, long val);
	// uint64_t UpdateAddHTLC_get_amount_msat(const struct LDKUpdateAddHTLC *NONNULL_PTR this_ptr);
	public static native long UpdateAddHTLC_get_amount_msat(long this_ptr);
	// void UpdateAddHTLC_set_amount_msat(struct LDKUpdateAddHTLC *NONNULL_PTR this_ptr, uint64_t val);
	public static native void UpdateAddHTLC_set_amount_msat(long this_ptr, long val);
	// const uint8_t (*UpdateAddHTLC_get_payment_hash(const struct LDKUpdateAddHTLC *NONNULL_PTR this_ptr))[32];
	public static native byte[] UpdateAddHTLC_get_payment_hash(long this_ptr);
	// void UpdateAddHTLC_set_payment_hash(struct LDKUpdateAddHTLC *NONNULL_PTR this_ptr, struct LDKThirtyTwoBytes val);
	public static native void UpdateAddHTLC_set_payment_hash(long this_ptr, byte[] val);
	// uint32_t UpdateAddHTLC_get_cltv_expiry(const struct LDKUpdateAddHTLC *NONNULL_PTR this_ptr);
	public static native int UpdateAddHTLC_get_cltv_expiry(long this_ptr);
	// void UpdateAddHTLC_set_cltv_expiry(struct LDKUpdateAddHTLC *NONNULL_PTR this_ptr, uint32_t val);
	public static native void UpdateAddHTLC_set_cltv_expiry(long this_ptr, int val);
	// uintptr_t UpdateAddHTLC_clone_ptr(LDKUpdateAddHTLC *NONNULL_PTR arg);
	public static native long UpdateAddHTLC_clone_ptr(long arg);
	// struct LDKUpdateAddHTLC UpdateAddHTLC_clone(const struct LDKUpdateAddHTLC *NONNULL_PTR orig);
	public static native long UpdateAddHTLC_clone(long orig);
	// void UpdateFulfillHTLC_free(struct LDKUpdateFulfillHTLC this_obj);
	public static native void UpdateFulfillHTLC_free(long this_obj);
	// const uint8_t (*UpdateFulfillHTLC_get_channel_id(const struct LDKUpdateFulfillHTLC *NONNULL_PTR this_ptr))[32];
	public static native byte[] UpdateFulfillHTLC_get_channel_id(long this_ptr);
	// void UpdateFulfillHTLC_set_channel_id(struct LDKUpdateFulfillHTLC *NONNULL_PTR this_ptr, struct LDKThirtyTwoBytes val);
	public static native void UpdateFulfillHTLC_set_channel_id(long this_ptr, byte[] val);
	// uint64_t UpdateFulfillHTLC_get_htlc_id(const struct LDKUpdateFulfillHTLC *NONNULL_PTR this_ptr);
	public static native long UpdateFulfillHTLC_get_htlc_id(long this_ptr);
	// void UpdateFulfillHTLC_set_htlc_id(struct LDKUpdateFulfillHTLC *NONNULL_PTR this_ptr, uint64_t val);
	public static native void UpdateFulfillHTLC_set_htlc_id(long this_ptr, long val);
	// const uint8_t (*UpdateFulfillHTLC_get_payment_preimage(const struct LDKUpdateFulfillHTLC *NONNULL_PTR this_ptr))[32];
	public static native byte[] UpdateFulfillHTLC_get_payment_preimage(long this_ptr);
	// void UpdateFulfillHTLC_set_payment_preimage(struct LDKUpdateFulfillHTLC *NONNULL_PTR this_ptr, struct LDKThirtyTwoBytes val);
	public static native void UpdateFulfillHTLC_set_payment_preimage(long this_ptr, byte[] val);
	// MUST_USE_RES struct LDKUpdateFulfillHTLC UpdateFulfillHTLC_new(struct LDKThirtyTwoBytes channel_id_arg, uint64_t htlc_id_arg, struct LDKThirtyTwoBytes payment_preimage_arg);
	public static native long UpdateFulfillHTLC_new(byte[] channel_id_arg, long htlc_id_arg, byte[] payment_preimage_arg);
	// uintptr_t UpdateFulfillHTLC_clone_ptr(LDKUpdateFulfillHTLC *NONNULL_PTR arg);
	public static native long UpdateFulfillHTLC_clone_ptr(long arg);
	// struct LDKUpdateFulfillHTLC UpdateFulfillHTLC_clone(const struct LDKUpdateFulfillHTLC *NONNULL_PTR orig);
	public static native long UpdateFulfillHTLC_clone(long orig);
	// void UpdateFailHTLC_free(struct LDKUpdateFailHTLC this_obj);
	public static native void UpdateFailHTLC_free(long this_obj);
	// const uint8_t (*UpdateFailHTLC_get_channel_id(const struct LDKUpdateFailHTLC *NONNULL_PTR this_ptr))[32];
	public static native byte[] UpdateFailHTLC_get_channel_id(long this_ptr);
	// void UpdateFailHTLC_set_channel_id(struct LDKUpdateFailHTLC *NONNULL_PTR this_ptr, struct LDKThirtyTwoBytes val);
	public static native void UpdateFailHTLC_set_channel_id(long this_ptr, byte[] val);
	// uint64_t UpdateFailHTLC_get_htlc_id(const struct LDKUpdateFailHTLC *NONNULL_PTR this_ptr);
	public static native long UpdateFailHTLC_get_htlc_id(long this_ptr);
	// void UpdateFailHTLC_set_htlc_id(struct LDKUpdateFailHTLC *NONNULL_PTR this_ptr, uint64_t val);
	public static native void UpdateFailHTLC_set_htlc_id(long this_ptr, long val);
	// uintptr_t UpdateFailHTLC_clone_ptr(LDKUpdateFailHTLC *NONNULL_PTR arg);
	public static native long UpdateFailHTLC_clone_ptr(long arg);
	// struct LDKUpdateFailHTLC UpdateFailHTLC_clone(const struct LDKUpdateFailHTLC *NONNULL_PTR orig);
	public static native long UpdateFailHTLC_clone(long orig);
	// void UpdateFailMalformedHTLC_free(struct LDKUpdateFailMalformedHTLC this_obj);
	public static native void UpdateFailMalformedHTLC_free(long this_obj);
	// const uint8_t (*UpdateFailMalformedHTLC_get_channel_id(const struct LDKUpdateFailMalformedHTLC *NONNULL_PTR this_ptr))[32];
	public static native byte[] UpdateFailMalformedHTLC_get_channel_id(long this_ptr);
	// void UpdateFailMalformedHTLC_set_channel_id(struct LDKUpdateFailMalformedHTLC *NONNULL_PTR this_ptr, struct LDKThirtyTwoBytes val);
	public static native void UpdateFailMalformedHTLC_set_channel_id(long this_ptr, byte[] val);
	// uint64_t UpdateFailMalformedHTLC_get_htlc_id(const struct LDKUpdateFailMalformedHTLC *NONNULL_PTR this_ptr);
	public static native long UpdateFailMalformedHTLC_get_htlc_id(long this_ptr);
	// void UpdateFailMalformedHTLC_set_htlc_id(struct LDKUpdateFailMalformedHTLC *NONNULL_PTR this_ptr, uint64_t val);
	public static native void UpdateFailMalformedHTLC_set_htlc_id(long this_ptr, long val);
	// uint16_t UpdateFailMalformedHTLC_get_failure_code(const struct LDKUpdateFailMalformedHTLC *NONNULL_PTR this_ptr);
	public static native short UpdateFailMalformedHTLC_get_failure_code(long this_ptr);
	// void UpdateFailMalformedHTLC_set_failure_code(struct LDKUpdateFailMalformedHTLC *NONNULL_PTR this_ptr, uint16_t val);
	public static native void UpdateFailMalformedHTLC_set_failure_code(long this_ptr, short val);
	// uintptr_t UpdateFailMalformedHTLC_clone_ptr(LDKUpdateFailMalformedHTLC *NONNULL_PTR arg);
	public static native long UpdateFailMalformedHTLC_clone_ptr(long arg);
	// struct LDKUpdateFailMalformedHTLC UpdateFailMalformedHTLC_clone(const struct LDKUpdateFailMalformedHTLC *NONNULL_PTR orig);
	public static native long UpdateFailMalformedHTLC_clone(long orig);
	// void CommitmentSigned_free(struct LDKCommitmentSigned this_obj);
	public static native void CommitmentSigned_free(long this_obj);
	// const uint8_t (*CommitmentSigned_get_channel_id(const struct LDKCommitmentSigned *NONNULL_PTR this_ptr))[32];
	public static native byte[] CommitmentSigned_get_channel_id(long this_ptr);
	// void CommitmentSigned_set_channel_id(struct LDKCommitmentSigned *NONNULL_PTR this_ptr, struct LDKThirtyTwoBytes val);
	public static native void CommitmentSigned_set_channel_id(long this_ptr, byte[] val);
	// struct LDKSignature CommitmentSigned_get_signature(const struct LDKCommitmentSigned *NONNULL_PTR this_ptr);
	public static native byte[] CommitmentSigned_get_signature(long this_ptr);
	// void CommitmentSigned_set_signature(struct LDKCommitmentSigned *NONNULL_PTR this_ptr, struct LDKSignature val);
	public static native void CommitmentSigned_set_signature(long this_ptr, byte[] val);
	// void CommitmentSigned_set_htlc_signatures(struct LDKCommitmentSigned *NONNULL_PTR this_ptr, struct LDKCVec_SignatureZ val);
	public static native void CommitmentSigned_set_htlc_signatures(long this_ptr, byte[][] val);
	// MUST_USE_RES struct LDKCommitmentSigned CommitmentSigned_new(struct LDKThirtyTwoBytes channel_id_arg, struct LDKSignature signature_arg, struct LDKCVec_SignatureZ htlc_signatures_arg);
	public static native long CommitmentSigned_new(byte[] channel_id_arg, byte[] signature_arg, byte[][] htlc_signatures_arg);
	// uintptr_t CommitmentSigned_clone_ptr(LDKCommitmentSigned *NONNULL_PTR arg);
	public static native long CommitmentSigned_clone_ptr(long arg);
	// struct LDKCommitmentSigned CommitmentSigned_clone(const struct LDKCommitmentSigned *NONNULL_PTR orig);
	public static native long CommitmentSigned_clone(long orig);
	// void RevokeAndACK_free(struct LDKRevokeAndACK this_obj);
	public static native void RevokeAndACK_free(long this_obj);
	// const uint8_t (*RevokeAndACK_get_channel_id(const struct LDKRevokeAndACK *NONNULL_PTR this_ptr))[32];
	public static native byte[] RevokeAndACK_get_channel_id(long this_ptr);
	// void RevokeAndACK_set_channel_id(struct LDKRevokeAndACK *NONNULL_PTR this_ptr, struct LDKThirtyTwoBytes val);
	public static native void RevokeAndACK_set_channel_id(long this_ptr, byte[] val);
	// const uint8_t (*RevokeAndACK_get_per_commitment_secret(const struct LDKRevokeAndACK *NONNULL_PTR this_ptr))[32];
	public static native byte[] RevokeAndACK_get_per_commitment_secret(long this_ptr);
	// void RevokeAndACK_set_per_commitment_secret(struct LDKRevokeAndACK *NONNULL_PTR this_ptr, struct LDKThirtyTwoBytes val);
	public static native void RevokeAndACK_set_per_commitment_secret(long this_ptr, byte[] val);
	// struct LDKPublicKey RevokeAndACK_get_next_per_commitment_point(const struct LDKRevokeAndACK *NONNULL_PTR this_ptr);
	public static native byte[] RevokeAndACK_get_next_per_commitment_point(long this_ptr);
	// void RevokeAndACK_set_next_per_commitment_point(struct LDKRevokeAndACK *NONNULL_PTR this_ptr, struct LDKPublicKey val);
	public static native void RevokeAndACK_set_next_per_commitment_point(long this_ptr, byte[] val);
	// MUST_USE_RES struct LDKRevokeAndACK RevokeAndACK_new(struct LDKThirtyTwoBytes channel_id_arg, struct LDKThirtyTwoBytes per_commitment_secret_arg, struct LDKPublicKey next_per_commitment_point_arg);
	public static native long RevokeAndACK_new(byte[] channel_id_arg, byte[] per_commitment_secret_arg, byte[] next_per_commitment_point_arg);
	// uintptr_t RevokeAndACK_clone_ptr(LDKRevokeAndACK *NONNULL_PTR arg);
	public static native long RevokeAndACK_clone_ptr(long arg);
	// struct LDKRevokeAndACK RevokeAndACK_clone(const struct LDKRevokeAndACK *NONNULL_PTR orig);
	public static native long RevokeAndACK_clone(long orig);
	// void UpdateFee_free(struct LDKUpdateFee this_obj);
	public static native void UpdateFee_free(long this_obj);
	// const uint8_t (*UpdateFee_get_channel_id(const struct LDKUpdateFee *NONNULL_PTR this_ptr))[32];
	public static native byte[] UpdateFee_get_channel_id(long this_ptr);
	// void UpdateFee_set_channel_id(struct LDKUpdateFee *NONNULL_PTR this_ptr, struct LDKThirtyTwoBytes val);
	public static native void UpdateFee_set_channel_id(long this_ptr, byte[] val);
	// uint32_t UpdateFee_get_feerate_per_kw(const struct LDKUpdateFee *NONNULL_PTR this_ptr);
	public static native int UpdateFee_get_feerate_per_kw(long this_ptr);
	// void UpdateFee_set_feerate_per_kw(struct LDKUpdateFee *NONNULL_PTR this_ptr, uint32_t val);
	public static native void UpdateFee_set_feerate_per_kw(long this_ptr, int val);
	// MUST_USE_RES struct LDKUpdateFee UpdateFee_new(struct LDKThirtyTwoBytes channel_id_arg, uint32_t feerate_per_kw_arg);
	public static native long UpdateFee_new(byte[] channel_id_arg, int feerate_per_kw_arg);
	// uintptr_t UpdateFee_clone_ptr(LDKUpdateFee *NONNULL_PTR arg);
	public static native long UpdateFee_clone_ptr(long arg);
	// struct LDKUpdateFee UpdateFee_clone(const struct LDKUpdateFee *NONNULL_PTR orig);
	public static native long UpdateFee_clone(long orig);
	// void DataLossProtect_free(struct LDKDataLossProtect this_obj);
	public static native void DataLossProtect_free(long this_obj);
	// const uint8_t (*DataLossProtect_get_your_last_per_commitment_secret(const struct LDKDataLossProtect *NONNULL_PTR this_ptr))[32];
	public static native byte[] DataLossProtect_get_your_last_per_commitment_secret(long this_ptr);
	// void DataLossProtect_set_your_last_per_commitment_secret(struct LDKDataLossProtect *NONNULL_PTR this_ptr, struct LDKThirtyTwoBytes val);
	public static native void DataLossProtect_set_your_last_per_commitment_secret(long this_ptr, byte[] val);
	// struct LDKPublicKey DataLossProtect_get_my_current_per_commitment_point(const struct LDKDataLossProtect *NONNULL_PTR this_ptr);
	public static native byte[] DataLossProtect_get_my_current_per_commitment_point(long this_ptr);
	// void DataLossProtect_set_my_current_per_commitment_point(struct LDKDataLossProtect *NONNULL_PTR this_ptr, struct LDKPublicKey val);
	public static native void DataLossProtect_set_my_current_per_commitment_point(long this_ptr, byte[] val);
	// MUST_USE_RES struct LDKDataLossProtect DataLossProtect_new(struct LDKThirtyTwoBytes your_last_per_commitment_secret_arg, struct LDKPublicKey my_current_per_commitment_point_arg);
	public static native long DataLossProtect_new(byte[] your_last_per_commitment_secret_arg, byte[] my_current_per_commitment_point_arg);
	// uintptr_t DataLossProtect_clone_ptr(LDKDataLossProtect *NONNULL_PTR arg);
	public static native long DataLossProtect_clone_ptr(long arg);
	// struct LDKDataLossProtect DataLossProtect_clone(const struct LDKDataLossProtect *NONNULL_PTR orig);
	public static native long DataLossProtect_clone(long orig);
	// void ChannelReestablish_free(struct LDKChannelReestablish this_obj);
	public static native void ChannelReestablish_free(long this_obj);
	// const uint8_t (*ChannelReestablish_get_channel_id(const struct LDKChannelReestablish *NONNULL_PTR this_ptr))[32];
	public static native byte[] ChannelReestablish_get_channel_id(long this_ptr);
	// void ChannelReestablish_set_channel_id(struct LDKChannelReestablish *NONNULL_PTR this_ptr, struct LDKThirtyTwoBytes val);
	public static native void ChannelReestablish_set_channel_id(long this_ptr, byte[] val);
	// uint64_t ChannelReestablish_get_next_local_commitment_number(const struct LDKChannelReestablish *NONNULL_PTR this_ptr);
	public static native long ChannelReestablish_get_next_local_commitment_number(long this_ptr);
	// void ChannelReestablish_set_next_local_commitment_number(struct LDKChannelReestablish *NONNULL_PTR this_ptr, uint64_t val);
	public static native void ChannelReestablish_set_next_local_commitment_number(long this_ptr, long val);
	// uint64_t ChannelReestablish_get_next_remote_commitment_number(const struct LDKChannelReestablish *NONNULL_PTR this_ptr);
	public static native long ChannelReestablish_get_next_remote_commitment_number(long this_ptr);
	// void ChannelReestablish_set_next_remote_commitment_number(struct LDKChannelReestablish *NONNULL_PTR this_ptr, uint64_t val);
	public static native void ChannelReestablish_set_next_remote_commitment_number(long this_ptr, long val);
	// uintptr_t ChannelReestablish_clone_ptr(LDKChannelReestablish *NONNULL_PTR arg);
	public static native long ChannelReestablish_clone_ptr(long arg);
	// struct LDKChannelReestablish ChannelReestablish_clone(const struct LDKChannelReestablish *NONNULL_PTR orig);
	public static native long ChannelReestablish_clone(long orig);
	// void AnnouncementSignatures_free(struct LDKAnnouncementSignatures this_obj);
	public static native void AnnouncementSignatures_free(long this_obj);
	// const uint8_t (*AnnouncementSignatures_get_channel_id(const struct LDKAnnouncementSignatures *NONNULL_PTR this_ptr))[32];
	public static native byte[] AnnouncementSignatures_get_channel_id(long this_ptr);
	// void AnnouncementSignatures_set_channel_id(struct LDKAnnouncementSignatures *NONNULL_PTR this_ptr, struct LDKThirtyTwoBytes val);
	public static native void AnnouncementSignatures_set_channel_id(long this_ptr, byte[] val);
	// uint64_t AnnouncementSignatures_get_short_channel_id(const struct LDKAnnouncementSignatures *NONNULL_PTR this_ptr);
	public static native long AnnouncementSignatures_get_short_channel_id(long this_ptr);
	// void AnnouncementSignatures_set_short_channel_id(struct LDKAnnouncementSignatures *NONNULL_PTR this_ptr, uint64_t val);
	public static native void AnnouncementSignatures_set_short_channel_id(long this_ptr, long val);
	// struct LDKSignature AnnouncementSignatures_get_node_signature(const struct LDKAnnouncementSignatures *NONNULL_PTR this_ptr);
	public static native byte[] AnnouncementSignatures_get_node_signature(long this_ptr);
	// void AnnouncementSignatures_set_node_signature(struct LDKAnnouncementSignatures *NONNULL_PTR this_ptr, struct LDKSignature val);
	public static native void AnnouncementSignatures_set_node_signature(long this_ptr, byte[] val);
	// struct LDKSignature AnnouncementSignatures_get_bitcoin_signature(const struct LDKAnnouncementSignatures *NONNULL_PTR this_ptr);
	public static native byte[] AnnouncementSignatures_get_bitcoin_signature(long this_ptr);
	// void AnnouncementSignatures_set_bitcoin_signature(struct LDKAnnouncementSignatures *NONNULL_PTR this_ptr, struct LDKSignature val);
	public static native void AnnouncementSignatures_set_bitcoin_signature(long this_ptr, byte[] val);
	// MUST_USE_RES struct LDKAnnouncementSignatures AnnouncementSignatures_new(struct LDKThirtyTwoBytes channel_id_arg, uint64_t short_channel_id_arg, struct LDKSignature node_signature_arg, struct LDKSignature bitcoin_signature_arg);
	public static native long AnnouncementSignatures_new(byte[] channel_id_arg, long short_channel_id_arg, byte[] node_signature_arg, byte[] bitcoin_signature_arg);
	// uintptr_t AnnouncementSignatures_clone_ptr(LDKAnnouncementSignatures *NONNULL_PTR arg);
	public static native long AnnouncementSignatures_clone_ptr(long arg);
	// struct LDKAnnouncementSignatures AnnouncementSignatures_clone(const struct LDKAnnouncementSignatures *NONNULL_PTR orig);
	public static native long AnnouncementSignatures_clone(long orig);
	// void NetAddress_free(struct LDKNetAddress this_ptr);
	public static native void NetAddress_free(long this_ptr);
	// uintptr_t NetAddress_clone_ptr(LDKNetAddress *NONNULL_PTR arg);
	public static native long NetAddress_clone_ptr(long arg);
	// struct LDKNetAddress NetAddress_clone(const struct LDKNetAddress *NONNULL_PTR orig);
	public static native long NetAddress_clone(long orig);
	// struct LDKNetAddress NetAddress_ipv4(struct LDKFourBytes addr, uint16_t port);
	public static native long NetAddress_ipv4(byte[] addr, short port);
	// struct LDKNetAddress NetAddress_ipv6(struct LDKSixteenBytes addr, uint16_t port);
	public static native long NetAddress_ipv6(byte[] addr, short port);
	// struct LDKNetAddress NetAddress_onion_v2(struct LDKTwelveBytes a);
	public static native long NetAddress_onion_v2(byte[] a);
	// struct LDKNetAddress NetAddress_onion_v3(struct LDKThirtyTwoBytes ed25519_pubkey, uint16_t checksum, uint8_t version, uint16_t port);
	public static native long NetAddress_onion_v3(byte[] ed25519_pubkey, short checksum, byte version, short port);
	// struct LDKCVec_u8Z NetAddress_write(const struct LDKNetAddress *NONNULL_PTR obj);
	public static native byte[] NetAddress_write(long obj);
	// struct LDKCResult_NetAddressDecodeErrorZ NetAddress_read(struct LDKu8slice ser);
	public static native long NetAddress_read(byte[] ser);
	// void UnsignedNodeAnnouncement_free(struct LDKUnsignedNodeAnnouncement this_obj);
	public static native void UnsignedNodeAnnouncement_free(long this_obj);
	// struct LDKNodeFeatures UnsignedNodeAnnouncement_get_features(const struct LDKUnsignedNodeAnnouncement *NONNULL_PTR this_ptr);
	public static native long UnsignedNodeAnnouncement_get_features(long this_ptr);
	// void UnsignedNodeAnnouncement_set_features(struct LDKUnsignedNodeAnnouncement *NONNULL_PTR this_ptr, struct LDKNodeFeatures val);
	public static native void UnsignedNodeAnnouncement_set_features(long this_ptr, long val);
	// uint32_t UnsignedNodeAnnouncement_get_timestamp(const struct LDKUnsignedNodeAnnouncement *NONNULL_PTR this_ptr);
	public static native int UnsignedNodeAnnouncement_get_timestamp(long this_ptr);
	// void UnsignedNodeAnnouncement_set_timestamp(struct LDKUnsignedNodeAnnouncement *NONNULL_PTR this_ptr, uint32_t val);
	public static native void UnsignedNodeAnnouncement_set_timestamp(long this_ptr, int val);
	// struct LDKPublicKey UnsignedNodeAnnouncement_get_node_id(const struct LDKUnsignedNodeAnnouncement *NONNULL_PTR this_ptr);
	public static native byte[] UnsignedNodeAnnouncement_get_node_id(long this_ptr);
	// void UnsignedNodeAnnouncement_set_node_id(struct LDKUnsignedNodeAnnouncement *NONNULL_PTR this_ptr, struct LDKPublicKey val);
	public static native void UnsignedNodeAnnouncement_set_node_id(long this_ptr, byte[] val);
	// const uint8_t (*UnsignedNodeAnnouncement_get_rgb(const struct LDKUnsignedNodeAnnouncement *NONNULL_PTR this_ptr))[3];
	public static native byte[] UnsignedNodeAnnouncement_get_rgb(long this_ptr);
	// void UnsignedNodeAnnouncement_set_rgb(struct LDKUnsignedNodeAnnouncement *NONNULL_PTR this_ptr, struct LDKThreeBytes val);
	public static native void UnsignedNodeAnnouncement_set_rgb(long this_ptr, byte[] val);
	// const uint8_t (*UnsignedNodeAnnouncement_get_alias(const struct LDKUnsignedNodeAnnouncement *NONNULL_PTR this_ptr))[32];
	public static native byte[] UnsignedNodeAnnouncement_get_alias(long this_ptr);
	// void UnsignedNodeAnnouncement_set_alias(struct LDKUnsignedNodeAnnouncement *NONNULL_PTR this_ptr, struct LDKThirtyTwoBytes val);
	public static native void UnsignedNodeAnnouncement_set_alias(long this_ptr, byte[] val);
	// void UnsignedNodeAnnouncement_set_addresses(struct LDKUnsignedNodeAnnouncement *NONNULL_PTR this_ptr, struct LDKCVec_NetAddressZ val);
	public static native void UnsignedNodeAnnouncement_set_addresses(long this_ptr, long[] val);
	// uintptr_t UnsignedNodeAnnouncement_clone_ptr(LDKUnsignedNodeAnnouncement *NONNULL_PTR arg);
	public static native long UnsignedNodeAnnouncement_clone_ptr(long arg);
	// struct LDKUnsignedNodeAnnouncement UnsignedNodeAnnouncement_clone(const struct LDKUnsignedNodeAnnouncement *NONNULL_PTR orig);
	public static native long UnsignedNodeAnnouncement_clone(long orig);
	// void NodeAnnouncement_free(struct LDKNodeAnnouncement this_obj);
	public static native void NodeAnnouncement_free(long this_obj);
	// struct LDKSignature NodeAnnouncement_get_signature(const struct LDKNodeAnnouncement *NONNULL_PTR this_ptr);
	public static native byte[] NodeAnnouncement_get_signature(long this_ptr);
	// void NodeAnnouncement_set_signature(struct LDKNodeAnnouncement *NONNULL_PTR this_ptr, struct LDKSignature val);
	public static native void NodeAnnouncement_set_signature(long this_ptr, byte[] val);
	// struct LDKUnsignedNodeAnnouncement NodeAnnouncement_get_contents(const struct LDKNodeAnnouncement *NONNULL_PTR this_ptr);
	public static native long NodeAnnouncement_get_contents(long this_ptr);
	// void NodeAnnouncement_set_contents(struct LDKNodeAnnouncement *NONNULL_PTR this_ptr, struct LDKUnsignedNodeAnnouncement val);
	public static native void NodeAnnouncement_set_contents(long this_ptr, long val);
	// MUST_USE_RES struct LDKNodeAnnouncement NodeAnnouncement_new(struct LDKSignature signature_arg, struct LDKUnsignedNodeAnnouncement contents_arg);
	public static native long NodeAnnouncement_new(byte[] signature_arg, long contents_arg);
	// uintptr_t NodeAnnouncement_clone_ptr(LDKNodeAnnouncement *NONNULL_PTR arg);
	public static native long NodeAnnouncement_clone_ptr(long arg);
	// struct LDKNodeAnnouncement NodeAnnouncement_clone(const struct LDKNodeAnnouncement *NONNULL_PTR orig);
	public static native long NodeAnnouncement_clone(long orig);
	// void UnsignedChannelAnnouncement_free(struct LDKUnsignedChannelAnnouncement this_obj);
	public static native void UnsignedChannelAnnouncement_free(long this_obj);
	// struct LDKChannelFeatures UnsignedChannelAnnouncement_get_features(const struct LDKUnsignedChannelAnnouncement *NONNULL_PTR this_ptr);
	public static native long UnsignedChannelAnnouncement_get_features(long this_ptr);
	// void UnsignedChannelAnnouncement_set_features(struct LDKUnsignedChannelAnnouncement *NONNULL_PTR this_ptr, struct LDKChannelFeatures val);
	public static native void UnsignedChannelAnnouncement_set_features(long this_ptr, long val);
	// const uint8_t (*UnsignedChannelAnnouncement_get_chain_hash(const struct LDKUnsignedChannelAnnouncement *NONNULL_PTR this_ptr))[32];
	public static native byte[] UnsignedChannelAnnouncement_get_chain_hash(long this_ptr);
	// void UnsignedChannelAnnouncement_set_chain_hash(struct LDKUnsignedChannelAnnouncement *NONNULL_PTR this_ptr, struct LDKThirtyTwoBytes val);
	public static native void UnsignedChannelAnnouncement_set_chain_hash(long this_ptr, byte[] val);
	// uint64_t UnsignedChannelAnnouncement_get_short_channel_id(const struct LDKUnsignedChannelAnnouncement *NONNULL_PTR this_ptr);
	public static native long UnsignedChannelAnnouncement_get_short_channel_id(long this_ptr);
	// void UnsignedChannelAnnouncement_set_short_channel_id(struct LDKUnsignedChannelAnnouncement *NONNULL_PTR this_ptr, uint64_t val);
	public static native void UnsignedChannelAnnouncement_set_short_channel_id(long this_ptr, long val);
	// struct LDKPublicKey UnsignedChannelAnnouncement_get_node_id_1(const struct LDKUnsignedChannelAnnouncement *NONNULL_PTR this_ptr);
	public static native byte[] UnsignedChannelAnnouncement_get_node_id_1(long this_ptr);
	// void UnsignedChannelAnnouncement_set_node_id_1(struct LDKUnsignedChannelAnnouncement *NONNULL_PTR this_ptr, struct LDKPublicKey val);
	public static native void UnsignedChannelAnnouncement_set_node_id_1(long this_ptr, byte[] val);
	// struct LDKPublicKey UnsignedChannelAnnouncement_get_node_id_2(const struct LDKUnsignedChannelAnnouncement *NONNULL_PTR this_ptr);
	public static native byte[] UnsignedChannelAnnouncement_get_node_id_2(long this_ptr);
	// void UnsignedChannelAnnouncement_set_node_id_2(struct LDKUnsignedChannelAnnouncement *NONNULL_PTR this_ptr, struct LDKPublicKey val);
	public static native void UnsignedChannelAnnouncement_set_node_id_2(long this_ptr, byte[] val);
	// struct LDKPublicKey UnsignedChannelAnnouncement_get_bitcoin_key_1(const struct LDKUnsignedChannelAnnouncement *NONNULL_PTR this_ptr);
	public static native byte[] UnsignedChannelAnnouncement_get_bitcoin_key_1(long this_ptr);
	// void UnsignedChannelAnnouncement_set_bitcoin_key_1(struct LDKUnsignedChannelAnnouncement *NONNULL_PTR this_ptr, struct LDKPublicKey val);
	public static native void UnsignedChannelAnnouncement_set_bitcoin_key_1(long this_ptr, byte[] val);
	// struct LDKPublicKey UnsignedChannelAnnouncement_get_bitcoin_key_2(const struct LDKUnsignedChannelAnnouncement *NONNULL_PTR this_ptr);
	public static native byte[] UnsignedChannelAnnouncement_get_bitcoin_key_2(long this_ptr);
	// void UnsignedChannelAnnouncement_set_bitcoin_key_2(struct LDKUnsignedChannelAnnouncement *NONNULL_PTR this_ptr, struct LDKPublicKey val);
	public static native void UnsignedChannelAnnouncement_set_bitcoin_key_2(long this_ptr, byte[] val);
	// uintptr_t UnsignedChannelAnnouncement_clone_ptr(LDKUnsignedChannelAnnouncement *NONNULL_PTR arg);
	public static native long UnsignedChannelAnnouncement_clone_ptr(long arg);
	// struct LDKUnsignedChannelAnnouncement UnsignedChannelAnnouncement_clone(const struct LDKUnsignedChannelAnnouncement *NONNULL_PTR orig);
	public static native long UnsignedChannelAnnouncement_clone(long orig);
	// void ChannelAnnouncement_free(struct LDKChannelAnnouncement this_obj);
	public static native void ChannelAnnouncement_free(long this_obj);
	// struct LDKSignature ChannelAnnouncement_get_node_signature_1(const struct LDKChannelAnnouncement *NONNULL_PTR this_ptr);
	public static native byte[] ChannelAnnouncement_get_node_signature_1(long this_ptr);
	// void ChannelAnnouncement_set_node_signature_1(struct LDKChannelAnnouncement *NONNULL_PTR this_ptr, struct LDKSignature val);
	public static native void ChannelAnnouncement_set_node_signature_1(long this_ptr, byte[] val);
	// struct LDKSignature ChannelAnnouncement_get_node_signature_2(const struct LDKChannelAnnouncement *NONNULL_PTR this_ptr);
	public static native byte[] ChannelAnnouncement_get_node_signature_2(long this_ptr);
	// void ChannelAnnouncement_set_node_signature_2(struct LDKChannelAnnouncement *NONNULL_PTR this_ptr, struct LDKSignature val);
	public static native void ChannelAnnouncement_set_node_signature_2(long this_ptr, byte[] val);
	// struct LDKSignature ChannelAnnouncement_get_bitcoin_signature_1(const struct LDKChannelAnnouncement *NONNULL_PTR this_ptr);
	public static native byte[] ChannelAnnouncement_get_bitcoin_signature_1(long this_ptr);
	// void ChannelAnnouncement_set_bitcoin_signature_1(struct LDKChannelAnnouncement *NONNULL_PTR this_ptr, struct LDKSignature val);
	public static native void ChannelAnnouncement_set_bitcoin_signature_1(long this_ptr, byte[] val);
	// struct LDKSignature ChannelAnnouncement_get_bitcoin_signature_2(const struct LDKChannelAnnouncement *NONNULL_PTR this_ptr);
	public static native byte[] ChannelAnnouncement_get_bitcoin_signature_2(long this_ptr);
	// void ChannelAnnouncement_set_bitcoin_signature_2(struct LDKChannelAnnouncement *NONNULL_PTR this_ptr, struct LDKSignature val);
	public static native void ChannelAnnouncement_set_bitcoin_signature_2(long this_ptr, byte[] val);
	// struct LDKUnsignedChannelAnnouncement ChannelAnnouncement_get_contents(const struct LDKChannelAnnouncement *NONNULL_PTR this_ptr);
	public static native long ChannelAnnouncement_get_contents(long this_ptr);
	// void ChannelAnnouncement_set_contents(struct LDKChannelAnnouncement *NONNULL_PTR this_ptr, struct LDKUnsignedChannelAnnouncement val);
	public static native void ChannelAnnouncement_set_contents(long this_ptr, long val);
	// MUST_USE_RES struct LDKChannelAnnouncement ChannelAnnouncement_new(struct LDKSignature node_signature_1_arg, struct LDKSignature node_signature_2_arg, struct LDKSignature bitcoin_signature_1_arg, struct LDKSignature bitcoin_signature_2_arg, struct LDKUnsignedChannelAnnouncement contents_arg);
	public static native long ChannelAnnouncement_new(byte[] node_signature_1_arg, byte[] node_signature_2_arg, byte[] bitcoin_signature_1_arg, byte[] bitcoin_signature_2_arg, long contents_arg);
	// uintptr_t ChannelAnnouncement_clone_ptr(LDKChannelAnnouncement *NONNULL_PTR arg);
	public static native long ChannelAnnouncement_clone_ptr(long arg);
	// struct LDKChannelAnnouncement ChannelAnnouncement_clone(const struct LDKChannelAnnouncement *NONNULL_PTR orig);
	public static native long ChannelAnnouncement_clone(long orig);
	// void UnsignedChannelUpdate_free(struct LDKUnsignedChannelUpdate this_obj);
	public static native void UnsignedChannelUpdate_free(long this_obj);
	// const uint8_t (*UnsignedChannelUpdate_get_chain_hash(const struct LDKUnsignedChannelUpdate *NONNULL_PTR this_ptr))[32];
	public static native byte[] UnsignedChannelUpdate_get_chain_hash(long this_ptr);
	// void UnsignedChannelUpdate_set_chain_hash(struct LDKUnsignedChannelUpdate *NONNULL_PTR this_ptr, struct LDKThirtyTwoBytes val);
	public static native void UnsignedChannelUpdate_set_chain_hash(long this_ptr, byte[] val);
	// uint64_t UnsignedChannelUpdate_get_short_channel_id(const struct LDKUnsignedChannelUpdate *NONNULL_PTR this_ptr);
	public static native long UnsignedChannelUpdate_get_short_channel_id(long this_ptr);
	// void UnsignedChannelUpdate_set_short_channel_id(struct LDKUnsignedChannelUpdate *NONNULL_PTR this_ptr, uint64_t val);
	public static native void UnsignedChannelUpdate_set_short_channel_id(long this_ptr, long val);
	// uint32_t UnsignedChannelUpdate_get_timestamp(const struct LDKUnsignedChannelUpdate *NONNULL_PTR this_ptr);
	public static native int UnsignedChannelUpdate_get_timestamp(long this_ptr);
	// void UnsignedChannelUpdate_set_timestamp(struct LDKUnsignedChannelUpdate *NONNULL_PTR this_ptr, uint32_t val);
	public static native void UnsignedChannelUpdate_set_timestamp(long this_ptr, int val);
	// uint8_t UnsignedChannelUpdate_get_flags(const struct LDKUnsignedChannelUpdate *NONNULL_PTR this_ptr);
	public static native byte UnsignedChannelUpdate_get_flags(long this_ptr);
	// void UnsignedChannelUpdate_set_flags(struct LDKUnsignedChannelUpdate *NONNULL_PTR this_ptr, uint8_t val);
	public static native void UnsignedChannelUpdate_set_flags(long this_ptr, byte val);
	// uint16_t UnsignedChannelUpdate_get_cltv_expiry_delta(const struct LDKUnsignedChannelUpdate *NONNULL_PTR this_ptr);
	public static native short UnsignedChannelUpdate_get_cltv_expiry_delta(long this_ptr);
	// void UnsignedChannelUpdate_set_cltv_expiry_delta(struct LDKUnsignedChannelUpdate *NONNULL_PTR this_ptr, uint16_t val);
	public static native void UnsignedChannelUpdate_set_cltv_expiry_delta(long this_ptr, short val);
	// uint64_t UnsignedChannelUpdate_get_htlc_minimum_msat(const struct LDKUnsignedChannelUpdate *NONNULL_PTR this_ptr);
	public static native long UnsignedChannelUpdate_get_htlc_minimum_msat(long this_ptr);
	// void UnsignedChannelUpdate_set_htlc_minimum_msat(struct LDKUnsignedChannelUpdate *NONNULL_PTR this_ptr, uint64_t val);
	public static native void UnsignedChannelUpdate_set_htlc_minimum_msat(long this_ptr, long val);
	// uint32_t UnsignedChannelUpdate_get_fee_base_msat(const struct LDKUnsignedChannelUpdate *NONNULL_PTR this_ptr);
	public static native int UnsignedChannelUpdate_get_fee_base_msat(long this_ptr);
	// void UnsignedChannelUpdate_set_fee_base_msat(struct LDKUnsignedChannelUpdate *NONNULL_PTR this_ptr, uint32_t val);
	public static native void UnsignedChannelUpdate_set_fee_base_msat(long this_ptr, int val);
	// uint32_t UnsignedChannelUpdate_get_fee_proportional_millionths(const struct LDKUnsignedChannelUpdate *NONNULL_PTR this_ptr);
	public static native int UnsignedChannelUpdate_get_fee_proportional_millionths(long this_ptr);
	// void UnsignedChannelUpdate_set_fee_proportional_millionths(struct LDKUnsignedChannelUpdate *NONNULL_PTR this_ptr, uint32_t val);
	public static native void UnsignedChannelUpdate_set_fee_proportional_millionths(long this_ptr, int val);
	// uintptr_t UnsignedChannelUpdate_clone_ptr(LDKUnsignedChannelUpdate *NONNULL_PTR arg);
	public static native long UnsignedChannelUpdate_clone_ptr(long arg);
	// struct LDKUnsignedChannelUpdate UnsignedChannelUpdate_clone(const struct LDKUnsignedChannelUpdate *NONNULL_PTR orig);
	public static native long UnsignedChannelUpdate_clone(long orig);
	// void ChannelUpdate_free(struct LDKChannelUpdate this_obj);
	public static native void ChannelUpdate_free(long this_obj);
	// struct LDKSignature ChannelUpdate_get_signature(const struct LDKChannelUpdate *NONNULL_PTR this_ptr);
	public static native byte[] ChannelUpdate_get_signature(long this_ptr);
	// void ChannelUpdate_set_signature(struct LDKChannelUpdate *NONNULL_PTR this_ptr, struct LDKSignature val);
	public static native void ChannelUpdate_set_signature(long this_ptr, byte[] val);
	// struct LDKUnsignedChannelUpdate ChannelUpdate_get_contents(const struct LDKChannelUpdate *NONNULL_PTR this_ptr);
	public static native long ChannelUpdate_get_contents(long this_ptr);
	// void ChannelUpdate_set_contents(struct LDKChannelUpdate *NONNULL_PTR this_ptr, struct LDKUnsignedChannelUpdate val);
	public static native void ChannelUpdate_set_contents(long this_ptr, long val);
	// MUST_USE_RES struct LDKChannelUpdate ChannelUpdate_new(struct LDKSignature signature_arg, struct LDKUnsignedChannelUpdate contents_arg);
	public static native long ChannelUpdate_new(byte[] signature_arg, long contents_arg);
	// uintptr_t ChannelUpdate_clone_ptr(LDKChannelUpdate *NONNULL_PTR arg);
	public static native long ChannelUpdate_clone_ptr(long arg);
	// struct LDKChannelUpdate ChannelUpdate_clone(const struct LDKChannelUpdate *NONNULL_PTR orig);
	public static native long ChannelUpdate_clone(long orig);
	// void QueryChannelRange_free(struct LDKQueryChannelRange this_obj);
	public static native void QueryChannelRange_free(long this_obj);
	// const uint8_t (*QueryChannelRange_get_chain_hash(const struct LDKQueryChannelRange *NONNULL_PTR this_ptr))[32];
	public static native byte[] QueryChannelRange_get_chain_hash(long this_ptr);
	// void QueryChannelRange_set_chain_hash(struct LDKQueryChannelRange *NONNULL_PTR this_ptr, struct LDKThirtyTwoBytes val);
	public static native void QueryChannelRange_set_chain_hash(long this_ptr, byte[] val);
	// uint32_t QueryChannelRange_get_first_blocknum(const struct LDKQueryChannelRange *NONNULL_PTR this_ptr);
	public static native int QueryChannelRange_get_first_blocknum(long this_ptr);
	// void QueryChannelRange_set_first_blocknum(struct LDKQueryChannelRange *NONNULL_PTR this_ptr, uint32_t val);
	public static native void QueryChannelRange_set_first_blocknum(long this_ptr, int val);
	// uint32_t QueryChannelRange_get_number_of_blocks(const struct LDKQueryChannelRange *NONNULL_PTR this_ptr);
	public static native int QueryChannelRange_get_number_of_blocks(long this_ptr);
	// void QueryChannelRange_set_number_of_blocks(struct LDKQueryChannelRange *NONNULL_PTR this_ptr, uint32_t val);
	public static native void QueryChannelRange_set_number_of_blocks(long this_ptr, int val);
	// MUST_USE_RES struct LDKQueryChannelRange QueryChannelRange_new(struct LDKThirtyTwoBytes chain_hash_arg, uint32_t first_blocknum_arg, uint32_t number_of_blocks_arg);
	public static native long QueryChannelRange_new(byte[] chain_hash_arg, int first_blocknum_arg, int number_of_blocks_arg);
	// uintptr_t QueryChannelRange_clone_ptr(LDKQueryChannelRange *NONNULL_PTR arg);
	public static native long QueryChannelRange_clone_ptr(long arg);
	// struct LDKQueryChannelRange QueryChannelRange_clone(const struct LDKQueryChannelRange *NONNULL_PTR orig);
	public static native long QueryChannelRange_clone(long orig);
	// void ReplyChannelRange_free(struct LDKReplyChannelRange this_obj);
	public static native void ReplyChannelRange_free(long this_obj);
	// const uint8_t (*ReplyChannelRange_get_chain_hash(const struct LDKReplyChannelRange *NONNULL_PTR this_ptr))[32];
	public static native byte[] ReplyChannelRange_get_chain_hash(long this_ptr);
	// void ReplyChannelRange_set_chain_hash(struct LDKReplyChannelRange *NONNULL_PTR this_ptr, struct LDKThirtyTwoBytes val);
	public static native void ReplyChannelRange_set_chain_hash(long this_ptr, byte[] val);
	// uint32_t ReplyChannelRange_get_first_blocknum(const struct LDKReplyChannelRange *NONNULL_PTR this_ptr);
	public static native int ReplyChannelRange_get_first_blocknum(long this_ptr);
	// void ReplyChannelRange_set_first_blocknum(struct LDKReplyChannelRange *NONNULL_PTR this_ptr, uint32_t val);
	public static native void ReplyChannelRange_set_first_blocknum(long this_ptr, int val);
	// uint32_t ReplyChannelRange_get_number_of_blocks(const struct LDKReplyChannelRange *NONNULL_PTR this_ptr);
	public static native int ReplyChannelRange_get_number_of_blocks(long this_ptr);
	// void ReplyChannelRange_set_number_of_blocks(struct LDKReplyChannelRange *NONNULL_PTR this_ptr, uint32_t val);
	public static native void ReplyChannelRange_set_number_of_blocks(long this_ptr, int val);
	// bool ReplyChannelRange_get_sync_complete(const struct LDKReplyChannelRange *NONNULL_PTR this_ptr);
	public static native boolean ReplyChannelRange_get_sync_complete(long this_ptr);
	// void ReplyChannelRange_set_sync_complete(struct LDKReplyChannelRange *NONNULL_PTR this_ptr, bool val);
	public static native void ReplyChannelRange_set_sync_complete(long this_ptr, boolean val);
	// void ReplyChannelRange_set_short_channel_ids(struct LDKReplyChannelRange *NONNULL_PTR this_ptr, struct LDKCVec_u64Z val);
	public static native void ReplyChannelRange_set_short_channel_ids(long this_ptr, long[] val);
	// MUST_USE_RES struct LDKReplyChannelRange ReplyChannelRange_new(struct LDKThirtyTwoBytes chain_hash_arg, uint32_t first_blocknum_arg, uint32_t number_of_blocks_arg, bool sync_complete_arg, struct LDKCVec_u64Z short_channel_ids_arg);
	public static native long ReplyChannelRange_new(byte[] chain_hash_arg, int first_blocknum_arg, int number_of_blocks_arg, boolean sync_complete_arg, long[] short_channel_ids_arg);
	// uintptr_t ReplyChannelRange_clone_ptr(LDKReplyChannelRange *NONNULL_PTR arg);
	public static native long ReplyChannelRange_clone_ptr(long arg);
	// struct LDKReplyChannelRange ReplyChannelRange_clone(const struct LDKReplyChannelRange *NONNULL_PTR orig);
	public static native long ReplyChannelRange_clone(long orig);
	// void QueryShortChannelIds_free(struct LDKQueryShortChannelIds this_obj);
	public static native void QueryShortChannelIds_free(long this_obj);
	// const uint8_t (*QueryShortChannelIds_get_chain_hash(const struct LDKQueryShortChannelIds *NONNULL_PTR this_ptr))[32];
	public static native byte[] QueryShortChannelIds_get_chain_hash(long this_ptr);
	// void QueryShortChannelIds_set_chain_hash(struct LDKQueryShortChannelIds *NONNULL_PTR this_ptr, struct LDKThirtyTwoBytes val);
	public static native void QueryShortChannelIds_set_chain_hash(long this_ptr, byte[] val);
	// void QueryShortChannelIds_set_short_channel_ids(struct LDKQueryShortChannelIds *NONNULL_PTR this_ptr, struct LDKCVec_u64Z val);
	public static native void QueryShortChannelIds_set_short_channel_ids(long this_ptr, long[] val);
	// MUST_USE_RES struct LDKQueryShortChannelIds QueryShortChannelIds_new(struct LDKThirtyTwoBytes chain_hash_arg, struct LDKCVec_u64Z short_channel_ids_arg);
	public static native long QueryShortChannelIds_new(byte[] chain_hash_arg, long[] short_channel_ids_arg);
	// uintptr_t QueryShortChannelIds_clone_ptr(LDKQueryShortChannelIds *NONNULL_PTR arg);
	public static native long QueryShortChannelIds_clone_ptr(long arg);
	// struct LDKQueryShortChannelIds QueryShortChannelIds_clone(const struct LDKQueryShortChannelIds *NONNULL_PTR orig);
	public static native long QueryShortChannelIds_clone(long orig);
	// void ReplyShortChannelIdsEnd_free(struct LDKReplyShortChannelIdsEnd this_obj);
	public static native void ReplyShortChannelIdsEnd_free(long this_obj);
	// const uint8_t (*ReplyShortChannelIdsEnd_get_chain_hash(const struct LDKReplyShortChannelIdsEnd *NONNULL_PTR this_ptr))[32];
	public static native byte[] ReplyShortChannelIdsEnd_get_chain_hash(long this_ptr);
	// void ReplyShortChannelIdsEnd_set_chain_hash(struct LDKReplyShortChannelIdsEnd *NONNULL_PTR this_ptr, struct LDKThirtyTwoBytes val);
	public static native void ReplyShortChannelIdsEnd_set_chain_hash(long this_ptr, byte[] val);
	// bool ReplyShortChannelIdsEnd_get_full_information(const struct LDKReplyShortChannelIdsEnd *NONNULL_PTR this_ptr);
	public static native boolean ReplyShortChannelIdsEnd_get_full_information(long this_ptr);
	// void ReplyShortChannelIdsEnd_set_full_information(struct LDKReplyShortChannelIdsEnd *NONNULL_PTR this_ptr, bool val);
	public static native void ReplyShortChannelIdsEnd_set_full_information(long this_ptr, boolean val);
	// MUST_USE_RES struct LDKReplyShortChannelIdsEnd ReplyShortChannelIdsEnd_new(struct LDKThirtyTwoBytes chain_hash_arg, bool full_information_arg);
	public static native long ReplyShortChannelIdsEnd_new(byte[] chain_hash_arg, boolean full_information_arg);
	// uintptr_t ReplyShortChannelIdsEnd_clone_ptr(LDKReplyShortChannelIdsEnd *NONNULL_PTR arg);
	public static native long ReplyShortChannelIdsEnd_clone_ptr(long arg);
	// struct LDKReplyShortChannelIdsEnd ReplyShortChannelIdsEnd_clone(const struct LDKReplyShortChannelIdsEnd *NONNULL_PTR orig);
	public static native long ReplyShortChannelIdsEnd_clone(long orig);
	// void GossipTimestampFilter_free(struct LDKGossipTimestampFilter this_obj);
	public static native void GossipTimestampFilter_free(long this_obj);
	// const uint8_t (*GossipTimestampFilter_get_chain_hash(const struct LDKGossipTimestampFilter *NONNULL_PTR this_ptr))[32];
	public static native byte[] GossipTimestampFilter_get_chain_hash(long this_ptr);
	// void GossipTimestampFilter_set_chain_hash(struct LDKGossipTimestampFilter *NONNULL_PTR this_ptr, struct LDKThirtyTwoBytes val);
	public static native void GossipTimestampFilter_set_chain_hash(long this_ptr, byte[] val);
	// uint32_t GossipTimestampFilter_get_first_timestamp(const struct LDKGossipTimestampFilter *NONNULL_PTR this_ptr);
	public static native int GossipTimestampFilter_get_first_timestamp(long this_ptr);
	// void GossipTimestampFilter_set_first_timestamp(struct LDKGossipTimestampFilter *NONNULL_PTR this_ptr, uint32_t val);
	public static native void GossipTimestampFilter_set_first_timestamp(long this_ptr, int val);
	// uint32_t GossipTimestampFilter_get_timestamp_range(const struct LDKGossipTimestampFilter *NONNULL_PTR this_ptr);
	public static native int GossipTimestampFilter_get_timestamp_range(long this_ptr);
	// void GossipTimestampFilter_set_timestamp_range(struct LDKGossipTimestampFilter *NONNULL_PTR this_ptr, uint32_t val);
	public static native void GossipTimestampFilter_set_timestamp_range(long this_ptr, int val);
	// MUST_USE_RES struct LDKGossipTimestampFilter GossipTimestampFilter_new(struct LDKThirtyTwoBytes chain_hash_arg, uint32_t first_timestamp_arg, uint32_t timestamp_range_arg);
	public static native long GossipTimestampFilter_new(byte[] chain_hash_arg, int first_timestamp_arg, int timestamp_range_arg);
	// uintptr_t GossipTimestampFilter_clone_ptr(LDKGossipTimestampFilter *NONNULL_PTR arg);
	public static native long GossipTimestampFilter_clone_ptr(long arg);
	// struct LDKGossipTimestampFilter GossipTimestampFilter_clone(const struct LDKGossipTimestampFilter *NONNULL_PTR orig);
	public static native long GossipTimestampFilter_clone(long orig);
	// void ErrorAction_free(struct LDKErrorAction this_ptr);
	public static native void ErrorAction_free(long this_ptr);
	// uintptr_t ErrorAction_clone_ptr(LDKErrorAction *NONNULL_PTR arg);
	public static native long ErrorAction_clone_ptr(long arg);
	// struct LDKErrorAction ErrorAction_clone(const struct LDKErrorAction *NONNULL_PTR orig);
	public static native long ErrorAction_clone(long orig);
	// struct LDKErrorAction ErrorAction_disconnect_peer(struct LDKErrorMessage msg);
	public static native long ErrorAction_disconnect_peer(long msg);
	// struct LDKErrorAction ErrorAction_ignore_error(void);
	public static native long ErrorAction_ignore_error();
	// struct LDKErrorAction ErrorAction_ignore_and_log(enum LDKLevel a);
	public static native long ErrorAction_ignore_and_log(Level a);
	// struct LDKErrorAction ErrorAction_ignore_duplicate_gossip(void);
	public static native long ErrorAction_ignore_duplicate_gossip();
	// struct LDKErrorAction ErrorAction_send_error_message(struct LDKErrorMessage msg);
	public static native long ErrorAction_send_error_message(long msg);
	// struct LDKErrorAction ErrorAction_send_warning_message(struct LDKWarningMessage msg, enum LDKLevel log_level);
	public static native long ErrorAction_send_warning_message(long msg, Level log_level);
	// void LightningError_free(struct LDKLightningError this_obj);
	public static native void LightningError_free(long this_obj);
	// struct LDKStr LightningError_get_err(const struct LDKLightningError *NONNULL_PTR this_ptr);
	public static native String LightningError_get_err(long this_ptr);
	// void LightningError_set_err(struct LDKLightningError *NONNULL_PTR this_ptr, struct LDKStr val);
	public static native void LightningError_set_err(long this_ptr, String val);
	// struct LDKErrorAction LightningError_get_action(const struct LDKLightningError *NONNULL_PTR this_ptr);
	public static native long LightningError_get_action(long this_ptr);
	// void LightningError_set_action(struct LDKLightningError *NONNULL_PTR this_ptr, struct LDKErrorAction val);
	public static native void LightningError_set_action(long this_ptr, long val);
	// MUST_USE_RES struct LDKLightningError LightningError_new(struct LDKStr err_arg, struct LDKErrorAction action_arg);
	public static native long LightningError_new(String err_arg, long action_arg);
	// uintptr_t LightningError_clone_ptr(LDKLightningError *NONNULL_PTR arg);
	public static native long LightningError_clone_ptr(long arg);
	// struct LDKLightningError LightningError_clone(const struct LDKLightningError *NONNULL_PTR orig);
	public static native long LightningError_clone(long orig);
	// void CommitmentUpdate_free(struct LDKCommitmentUpdate this_obj);
	public static native void CommitmentUpdate_free(long this_obj);
	// struct LDKCVec_UpdateAddHTLCZ CommitmentUpdate_get_update_add_htlcs(const struct LDKCommitmentUpdate *NONNULL_PTR this_ptr);
	public static native long[] CommitmentUpdate_get_update_add_htlcs(long this_ptr);
	// void CommitmentUpdate_set_update_add_htlcs(struct LDKCommitmentUpdate *NONNULL_PTR this_ptr, struct LDKCVec_UpdateAddHTLCZ val);
	public static native void CommitmentUpdate_set_update_add_htlcs(long this_ptr, long[] val);
	// struct LDKCVec_UpdateFulfillHTLCZ CommitmentUpdate_get_update_fulfill_htlcs(const struct LDKCommitmentUpdate *NONNULL_PTR this_ptr);
	public static native long[] CommitmentUpdate_get_update_fulfill_htlcs(long this_ptr);
	// void CommitmentUpdate_set_update_fulfill_htlcs(struct LDKCommitmentUpdate *NONNULL_PTR this_ptr, struct LDKCVec_UpdateFulfillHTLCZ val);
	public static native void CommitmentUpdate_set_update_fulfill_htlcs(long this_ptr, long[] val);
	// struct LDKCVec_UpdateFailHTLCZ CommitmentUpdate_get_update_fail_htlcs(const struct LDKCommitmentUpdate *NONNULL_PTR this_ptr);
	public static native long[] CommitmentUpdate_get_update_fail_htlcs(long this_ptr);
	// void CommitmentUpdate_set_update_fail_htlcs(struct LDKCommitmentUpdate *NONNULL_PTR this_ptr, struct LDKCVec_UpdateFailHTLCZ val);
	public static native void CommitmentUpdate_set_update_fail_htlcs(long this_ptr, long[] val);
	// struct LDKCVec_UpdateFailMalformedHTLCZ CommitmentUpdate_get_update_fail_malformed_htlcs(const struct LDKCommitmentUpdate *NONNULL_PTR this_ptr);
	public static native long[] CommitmentUpdate_get_update_fail_malformed_htlcs(long this_ptr);
	// void CommitmentUpdate_set_update_fail_malformed_htlcs(struct LDKCommitmentUpdate *NONNULL_PTR this_ptr, struct LDKCVec_UpdateFailMalformedHTLCZ val);
	public static native void CommitmentUpdate_set_update_fail_malformed_htlcs(long this_ptr, long[] val);
	// struct LDKUpdateFee CommitmentUpdate_get_update_fee(const struct LDKCommitmentUpdate *NONNULL_PTR this_ptr);
	public static native long CommitmentUpdate_get_update_fee(long this_ptr);
	// void CommitmentUpdate_set_update_fee(struct LDKCommitmentUpdate *NONNULL_PTR this_ptr, struct LDKUpdateFee val);
	public static native void CommitmentUpdate_set_update_fee(long this_ptr, long val);
	// struct LDKCommitmentSigned CommitmentUpdate_get_commitment_signed(const struct LDKCommitmentUpdate *NONNULL_PTR this_ptr);
	public static native long CommitmentUpdate_get_commitment_signed(long this_ptr);
	// void CommitmentUpdate_set_commitment_signed(struct LDKCommitmentUpdate *NONNULL_PTR this_ptr, struct LDKCommitmentSigned val);
	public static native void CommitmentUpdate_set_commitment_signed(long this_ptr, long val);
	// MUST_USE_RES struct LDKCommitmentUpdate CommitmentUpdate_new(struct LDKCVec_UpdateAddHTLCZ update_add_htlcs_arg, struct LDKCVec_UpdateFulfillHTLCZ update_fulfill_htlcs_arg, struct LDKCVec_UpdateFailHTLCZ update_fail_htlcs_arg, struct LDKCVec_UpdateFailMalformedHTLCZ update_fail_malformed_htlcs_arg, struct LDKUpdateFee update_fee_arg, struct LDKCommitmentSigned commitment_signed_arg);
	public static native long CommitmentUpdate_new(long[] update_add_htlcs_arg, long[] update_fulfill_htlcs_arg, long[] update_fail_htlcs_arg, long[] update_fail_malformed_htlcs_arg, long update_fee_arg, long commitment_signed_arg);
	// uintptr_t CommitmentUpdate_clone_ptr(LDKCommitmentUpdate *NONNULL_PTR arg);
	public static native long CommitmentUpdate_clone_ptr(long arg);
	// struct LDKCommitmentUpdate CommitmentUpdate_clone(const struct LDKCommitmentUpdate *NONNULL_PTR orig);
	public static native long CommitmentUpdate_clone(long orig);
	// void ChannelMessageHandler_free(struct LDKChannelMessageHandler this_ptr);
	public static native void ChannelMessageHandler_free(long this_ptr);
	// void RoutingMessageHandler_free(struct LDKRoutingMessageHandler this_ptr);
	public static native void RoutingMessageHandler_free(long this_ptr);
	// struct LDKCVec_u8Z AcceptChannel_write(const struct LDKAcceptChannel *NONNULL_PTR obj);
	public static native byte[] AcceptChannel_write(long obj);
	// struct LDKCResult_AcceptChannelDecodeErrorZ AcceptChannel_read(struct LDKu8slice ser);
	public static native long AcceptChannel_read(byte[] ser);
	// struct LDKCVec_u8Z AnnouncementSignatures_write(const struct LDKAnnouncementSignatures *NONNULL_PTR obj);
	public static native byte[] AnnouncementSignatures_write(long obj);
	// struct LDKCResult_AnnouncementSignaturesDecodeErrorZ AnnouncementSignatures_read(struct LDKu8slice ser);
	public static native long AnnouncementSignatures_read(byte[] ser);
	// struct LDKCVec_u8Z ChannelReestablish_write(const struct LDKChannelReestablish *NONNULL_PTR obj);
	public static native byte[] ChannelReestablish_write(long obj);
	// struct LDKCResult_ChannelReestablishDecodeErrorZ ChannelReestablish_read(struct LDKu8slice ser);
	public static native long ChannelReestablish_read(byte[] ser);
	// struct LDKCVec_u8Z ClosingSigned_write(const struct LDKClosingSigned *NONNULL_PTR obj);
	public static native byte[] ClosingSigned_write(long obj);
	// struct LDKCResult_ClosingSignedDecodeErrorZ ClosingSigned_read(struct LDKu8slice ser);
	public static native long ClosingSigned_read(byte[] ser);
	// struct LDKCVec_u8Z ClosingSignedFeeRange_write(const struct LDKClosingSignedFeeRange *NONNULL_PTR obj);
	public static native byte[] ClosingSignedFeeRange_write(long obj);
	// struct LDKCResult_ClosingSignedFeeRangeDecodeErrorZ ClosingSignedFeeRange_read(struct LDKu8slice ser);
	public static native long ClosingSignedFeeRange_read(byte[] ser);
	// struct LDKCVec_u8Z CommitmentSigned_write(const struct LDKCommitmentSigned *NONNULL_PTR obj);
	public static native byte[] CommitmentSigned_write(long obj);
	// struct LDKCResult_CommitmentSignedDecodeErrorZ CommitmentSigned_read(struct LDKu8slice ser);
	public static native long CommitmentSigned_read(byte[] ser);
	// struct LDKCVec_u8Z FundingCreated_write(const struct LDKFundingCreated *NONNULL_PTR obj);
	public static native byte[] FundingCreated_write(long obj);
	// struct LDKCResult_FundingCreatedDecodeErrorZ FundingCreated_read(struct LDKu8slice ser);
	public static native long FundingCreated_read(byte[] ser);
	// struct LDKCVec_u8Z FundingSigned_write(const struct LDKFundingSigned *NONNULL_PTR obj);
	public static native byte[] FundingSigned_write(long obj);
	// struct LDKCResult_FundingSignedDecodeErrorZ FundingSigned_read(struct LDKu8slice ser);
	public static native long FundingSigned_read(byte[] ser);
	// struct LDKCVec_u8Z FundingLocked_write(const struct LDKFundingLocked *NONNULL_PTR obj);
	public static native byte[] FundingLocked_write(long obj);
	// struct LDKCResult_FundingLockedDecodeErrorZ FundingLocked_read(struct LDKu8slice ser);
	public static native long FundingLocked_read(byte[] ser);
	// struct LDKCVec_u8Z Init_write(const struct LDKInit *NONNULL_PTR obj);
	public static native byte[] Init_write(long obj);
	// struct LDKCResult_InitDecodeErrorZ Init_read(struct LDKu8slice ser);
	public static native long Init_read(byte[] ser);
	// struct LDKCVec_u8Z OpenChannel_write(const struct LDKOpenChannel *NONNULL_PTR obj);
	public static native byte[] OpenChannel_write(long obj);
	// struct LDKCResult_OpenChannelDecodeErrorZ OpenChannel_read(struct LDKu8slice ser);
	public static native long OpenChannel_read(byte[] ser);
	// struct LDKCVec_u8Z RevokeAndACK_write(const struct LDKRevokeAndACK *NONNULL_PTR obj);
	public static native byte[] RevokeAndACK_write(long obj);
	// struct LDKCResult_RevokeAndACKDecodeErrorZ RevokeAndACK_read(struct LDKu8slice ser);
	public static native long RevokeAndACK_read(byte[] ser);
	// struct LDKCVec_u8Z Shutdown_write(const struct LDKShutdown *NONNULL_PTR obj);
	public static native byte[] Shutdown_write(long obj);
	// struct LDKCResult_ShutdownDecodeErrorZ Shutdown_read(struct LDKu8slice ser);
	public static native long Shutdown_read(byte[] ser);
	// struct LDKCVec_u8Z UpdateFailHTLC_write(const struct LDKUpdateFailHTLC *NONNULL_PTR obj);
	public static native byte[] UpdateFailHTLC_write(long obj);
	// struct LDKCResult_UpdateFailHTLCDecodeErrorZ UpdateFailHTLC_read(struct LDKu8slice ser);
	public static native long UpdateFailHTLC_read(byte[] ser);
	// struct LDKCVec_u8Z UpdateFailMalformedHTLC_write(const struct LDKUpdateFailMalformedHTLC *NONNULL_PTR obj);
	public static native byte[] UpdateFailMalformedHTLC_write(long obj);
	// struct LDKCResult_UpdateFailMalformedHTLCDecodeErrorZ UpdateFailMalformedHTLC_read(struct LDKu8slice ser);
	public static native long UpdateFailMalformedHTLC_read(byte[] ser);
	// struct LDKCVec_u8Z UpdateFee_write(const struct LDKUpdateFee *NONNULL_PTR obj);
	public static native byte[] UpdateFee_write(long obj);
	// struct LDKCResult_UpdateFeeDecodeErrorZ UpdateFee_read(struct LDKu8slice ser);
	public static native long UpdateFee_read(byte[] ser);
	// struct LDKCVec_u8Z UpdateFulfillHTLC_write(const struct LDKUpdateFulfillHTLC *NONNULL_PTR obj);
	public static native byte[] UpdateFulfillHTLC_write(long obj);
	// struct LDKCResult_UpdateFulfillHTLCDecodeErrorZ UpdateFulfillHTLC_read(struct LDKu8slice ser);
	public static native long UpdateFulfillHTLC_read(byte[] ser);
	// struct LDKCVec_u8Z UpdateAddHTLC_write(const struct LDKUpdateAddHTLC *NONNULL_PTR obj);
	public static native byte[] UpdateAddHTLC_write(long obj);
	// struct LDKCResult_UpdateAddHTLCDecodeErrorZ UpdateAddHTLC_read(struct LDKu8slice ser);
	public static native long UpdateAddHTLC_read(byte[] ser);
	// struct LDKCVec_u8Z Ping_write(const struct LDKPing *NONNULL_PTR obj);
	public static native byte[] Ping_write(long obj);
	// struct LDKCResult_PingDecodeErrorZ Ping_read(struct LDKu8slice ser);
	public static native long Ping_read(byte[] ser);
	// struct LDKCVec_u8Z Pong_write(const struct LDKPong *NONNULL_PTR obj);
	public static native byte[] Pong_write(long obj);
	// struct LDKCResult_PongDecodeErrorZ Pong_read(struct LDKu8slice ser);
	public static native long Pong_read(byte[] ser);
	// struct LDKCVec_u8Z UnsignedChannelAnnouncement_write(const struct LDKUnsignedChannelAnnouncement *NONNULL_PTR obj);
	public static native byte[] UnsignedChannelAnnouncement_write(long obj);
	// struct LDKCResult_UnsignedChannelAnnouncementDecodeErrorZ UnsignedChannelAnnouncement_read(struct LDKu8slice ser);
	public static native long UnsignedChannelAnnouncement_read(byte[] ser);
	// struct LDKCVec_u8Z ChannelAnnouncement_write(const struct LDKChannelAnnouncement *NONNULL_PTR obj);
	public static native byte[] ChannelAnnouncement_write(long obj);
	// struct LDKCResult_ChannelAnnouncementDecodeErrorZ ChannelAnnouncement_read(struct LDKu8slice ser);
	public static native long ChannelAnnouncement_read(byte[] ser);
	// struct LDKCVec_u8Z UnsignedChannelUpdate_write(const struct LDKUnsignedChannelUpdate *NONNULL_PTR obj);
	public static native byte[] UnsignedChannelUpdate_write(long obj);
	// struct LDKCResult_UnsignedChannelUpdateDecodeErrorZ UnsignedChannelUpdate_read(struct LDKu8slice ser);
	public static native long UnsignedChannelUpdate_read(byte[] ser);
	// struct LDKCVec_u8Z ChannelUpdate_write(const struct LDKChannelUpdate *NONNULL_PTR obj);
	public static native byte[] ChannelUpdate_write(long obj);
	// struct LDKCResult_ChannelUpdateDecodeErrorZ ChannelUpdate_read(struct LDKu8slice ser);
	public static native long ChannelUpdate_read(byte[] ser);
	// struct LDKCVec_u8Z ErrorMessage_write(const struct LDKErrorMessage *NONNULL_PTR obj);
	public static native byte[] ErrorMessage_write(long obj);
	// struct LDKCResult_ErrorMessageDecodeErrorZ ErrorMessage_read(struct LDKu8slice ser);
	public static native long ErrorMessage_read(byte[] ser);
	// struct LDKCVec_u8Z WarningMessage_write(const struct LDKWarningMessage *NONNULL_PTR obj);
	public static native byte[] WarningMessage_write(long obj);
	// struct LDKCResult_WarningMessageDecodeErrorZ WarningMessage_read(struct LDKu8slice ser);
	public static native long WarningMessage_read(byte[] ser);
	// struct LDKCVec_u8Z UnsignedNodeAnnouncement_write(const struct LDKUnsignedNodeAnnouncement *NONNULL_PTR obj);
	public static native byte[] UnsignedNodeAnnouncement_write(long obj);
	// struct LDKCResult_UnsignedNodeAnnouncementDecodeErrorZ UnsignedNodeAnnouncement_read(struct LDKu8slice ser);
	public static native long UnsignedNodeAnnouncement_read(byte[] ser);
	// struct LDKCVec_u8Z NodeAnnouncement_write(const struct LDKNodeAnnouncement *NONNULL_PTR obj);
	public static native byte[] NodeAnnouncement_write(long obj);
	// struct LDKCResult_NodeAnnouncementDecodeErrorZ NodeAnnouncement_read(struct LDKu8slice ser);
	public static native long NodeAnnouncement_read(byte[] ser);
	// struct LDKCResult_QueryShortChannelIdsDecodeErrorZ QueryShortChannelIds_read(struct LDKu8slice ser);
	public static native long QueryShortChannelIds_read(byte[] ser);
	// struct LDKCVec_u8Z QueryShortChannelIds_write(const struct LDKQueryShortChannelIds *NONNULL_PTR obj);
	public static native byte[] QueryShortChannelIds_write(long obj);
	// struct LDKCVec_u8Z ReplyShortChannelIdsEnd_write(const struct LDKReplyShortChannelIdsEnd *NONNULL_PTR obj);
	public static native byte[] ReplyShortChannelIdsEnd_write(long obj);
	// struct LDKCResult_ReplyShortChannelIdsEndDecodeErrorZ ReplyShortChannelIdsEnd_read(struct LDKu8slice ser);
	public static native long ReplyShortChannelIdsEnd_read(byte[] ser);
	// MUST_USE_RES uint32_t QueryChannelRange_end_blocknum(const struct LDKQueryChannelRange *NONNULL_PTR this_arg);
	public static native int QueryChannelRange_end_blocknum(long this_arg);
	// struct LDKCVec_u8Z QueryChannelRange_write(const struct LDKQueryChannelRange *NONNULL_PTR obj);
	public static native byte[] QueryChannelRange_write(long obj);
	// struct LDKCResult_QueryChannelRangeDecodeErrorZ QueryChannelRange_read(struct LDKu8slice ser);
	public static native long QueryChannelRange_read(byte[] ser);
	// struct LDKCResult_ReplyChannelRangeDecodeErrorZ ReplyChannelRange_read(struct LDKu8slice ser);
	public static native long ReplyChannelRange_read(byte[] ser);
	// struct LDKCVec_u8Z ReplyChannelRange_write(const struct LDKReplyChannelRange *NONNULL_PTR obj);
	public static native byte[] ReplyChannelRange_write(long obj);
	// struct LDKCVec_u8Z GossipTimestampFilter_write(const struct LDKGossipTimestampFilter *NONNULL_PTR obj);
	public static native byte[] GossipTimestampFilter_write(long obj);
	// struct LDKCResult_GossipTimestampFilterDecodeErrorZ GossipTimestampFilter_read(struct LDKu8slice ser);
	public static native long GossipTimestampFilter_read(byte[] ser);
	// void CustomMessageHandler_free(struct LDKCustomMessageHandler this_ptr);
	public static native void CustomMessageHandler_free(long this_ptr);
	// void IgnoringMessageHandler_free(struct LDKIgnoringMessageHandler this_obj);
	public static native void IgnoringMessageHandler_free(long this_obj);
	// MUST_USE_RES struct LDKIgnoringMessageHandler IgnoringMessageHandler_new(void);
	public static native long IgnoringMessageHandler_new();
	// struct LDKMessageSendEventsProvider IgnoringMessageHandler_as_MessageSendEventsProvider(const struct LDKIgnoringMessageHandler *NONNULL_PTR this_arg);
	public static native long IgnoringMessageHandler_as_MessageSendEventsProvider(long this_arg);
	// struct LDKRoutingMessageHandler IgnoringMessageHandler_as_RoutingMessageHandler(const struct LDKIgnoringMessageHandler *NONNULL_PTR this_arg);
	public static native long IgnoringMessageHandler_as_RoutingMessageHandler(long this_arg);
	// struct LDKCustomMessageReader IgnoringMessageHandler_as_CustomMessageReader(const struct LDKIgnoringMessageHandler *NONNULL_PTR this_arg);
	public static native long IgnoringMessageHandler_as_CustomMessageReader(long this_arg);
	// struct LDKCustomMessageHandler IgnoringMessageHandler_as_CustomMessageHandler(const struct LDKIgnoringMessageHandler *NONNULL_PTR this_arg);
	public static native long IgnoringMessageHandler_as_CustomMessageHandler(long this_arg);
	// void ErroringMessageHandler_free(struct LDKErroringMessageHandler this_obj);
	public static native void ErroringMessageHandler_free(long this_obj);
	// MUST_USE_RES struct LDKErroringMessageHandler ErroringMessageHandler_new(void);
	public static native long ErroringMessageHandler_new();
	// struct LDKMessageSendEventsProvider ErroringMessageHandler_as_MessageSendEventsProvider(const struct LDKErroringMessageHandler *NONNULL_PTR this_arg);
	public static native long ErroringMessageHandler_as_MessageSendEventsProvider(long this_arg);
	// struct LDKChannelMessageHandler ErroringMessageHandler_as_ChannelMessageHandler(const struct LDKErroringMessageHandler *NONNULL_PTR this_arg);
	public static native long ErroringMessageHandler_as_ChannelMessageHandler(long this_arg);
	// void MessageHandler_free(struct LDKMessageHandler this_obj);
	public static native void MessageHandler_free(long this_obj);
	// const struct LDKChannelMessageHandler *MessageHandler_get_chan_handler(const struct LDKMessageHandler *NONNULL_PTR this_ptr);
	public static native long MessageHandler_get_chan_handler(long this_ptr);
	// void MessageHandler_set_chan_handler(struct LDKMessageHandler *NONNULL_PTR this_ptr, struct LDKChannelMessageHandler val);
	public static native void MessageHandler_set_chan_handler(long this_ptr, long val);
	// const struct LDKRoutingMessageHandler *MessageHandler_get_route_handler(const struct LDKMessageHandler *NONNULL_PTR this_ptr);
	public static native long MessageHandler_get_route_handler(long this_ptr);
	// void MessageHandler_set_route_handler(struct LDKMessageHandler *NONNULL_PTR this_ptr, struct LDKRoutingMessageHandler val);
	public static native void MessageHandler_set_route_handler(long this_ptr, long val);
	// MUST_USE_RES struct LDKMessageHandler MessageHandler_new(struct LDKChannelMessageHandler chan_handler_arg, struct LDKRoutingMessageHandler route_handler_arg);
	public static native long MessageHandler_new(long chan_handler_arg, long route_handler_arg);
	// uintptr_t SocketDescriptor_clone_ptr(LDKSocketDescriptor *NONNULL_PTR arg);
	public static native long SocketDescriptor_clone_ptr(long arg);
	// struct LDKSocketDescriptor SocketDescriptor_clone(const struct LDKSocketDescriptor *NONNULL_PTR orig);
	public static native long SocketDescriptor_clone(long orig);
	// void SocketDescriptor_free(struct LDKSocketDescriptor this_ptr);
	public static native void SocketDescriptor_free(long this_ptr);
	// void PeerHandleError_free(struct LDKPeerHandleError this_obj);
	public static native void PeerHandleError_free(long this_obj);
	// bool PeerHandleError_get_no_connection_possible(const struct LDKPeerHandleError *NONNULL_PTR this_ptr);
	public static native boolean PeerHandleError_get_no_connection_possible(long this_ptr);
	// void PeerHandleError_set_no_connection_possible(struct LDKPeerHandleError *NONNULL_PTR this_ptr, bool val);
	public static native void PeerHandleError_set_no_connection_possible(long this_ptr, boolean val);
	// MUST_USE_RES struct LDKPeerHandleError PeerHandleError_new(bool no_connection_possible_arg);
	public static native long PeerHandleError_new(boolean no_connection_possible_arg);
	// uintptr_t PeerHandleError_clone_ptr(LDKPeerHandleError *NONNULL_PTR arg);
	public static native long PeerHandleError_clone_ptr(long arg);
	// struct LDKPeerHandleError PeerHandleError_clone(const struct LDKPeerHandleError *NONNULL_PTR orig);
	public static native long PeerHandleError_clone(long orig);
	// void PeerManager_free(struct LDKPeerManager this_obj);
	public static native void PeerManager_free(long this_obj);
	// MUST_USE_RES struct LDKPeerManager PeerManager_new(struct LDKMessageHandler message_handler, struct LDKSecretKey our_node_secret, const uint8_t (*ephemeral_random_data)[32], struct LDKLogger logger, struct LDKCustomMessageHandler custom_message_handler);
	public static native long PeerManager_new(long message_handler, byte[] our_node_secret, byte[] ephemeral_random_data, long logger, long custom_message_handler);
	// MUST_USE_RES struct LDKCVec_PublicKeyZ PeerManager_get_peer_node_ids(const struct LDKPeerManager *NONNULL_PTR this_arg);
	public static native byte[][] PeerManager_get_peer_node_ids(long this_arg);
	// MUST_USE_RES struct LDKCResult_CVec_u8ZPeerHandleErrorZ PeerManager_new_outbound_connection(const struct LDKPeerManager *NONNULL_PTR this_arg, struct LDKPublicKey their_node_id, struct LDKSocketDescriptor descriptor, struct LDKCOption_NetAddressZ remote_network_address);
	public static native long PeerManager_new_outbound_connection(long this_arg, byte[] their_node_id, long descriptor, long remote_network_address);
	// MUST_USE_RES struct LDKCResult_NonePeerHandleErrorZ PeerManager_new_inbound_connection(const struct LDKPeerManager *NONNULL_PTR this_arg, struct LDKSocketDescriptor descriptor, struct LDKCOption_NetAddressZ remote_network_address);
	public static native long PeerManager_new_inbound_connection(long this_arg, long descriptor, long remote_network_address);
	// MUST_USE_RES struct LDKCResult_NonePeerHandleErrorZ PeerManager_write_buffer_space_avail(const struct LDKPeerManager *NONNULL_PTR this_arg, struct LDKSocketDescriptor *NONNULL_PTR descriptor);
	public static native long PeerManager_write_buffer_space_avail(long this_arg, long descriptor);
	// MUST_USE_RES struct LDKCResult_boolPeerHandleErrorZ PeerManager_read_event(const struct LDKPeerManager *NONNULL_PTR this_arg, struct LDKSocketDescriptor *NONNULL_PTR peer_descriptor, struct LDKu8slice data);
	public static native long PeerManager_read_event(long this_arg, long peer_descriptor, byte[] data);
	// void PeerManager_process_events(const struct LDKPeerManager *NONNULL_PTR this_arg);
	public static native void PeerManager_process_events(long this_arg);
	// void PeerManager_socket_disconnected(const struct LDKPeerManager *NONNULL_PTR this_arg, const struct LDKSocketDescriptor *NONNULL_PTR descriptor);
	public static native void PeerManager_socket_disconnected(long this_arg, long descriptor);
	// void PeerManager_disconnect_by_node_id(const struct LDKPeerManager *NONNULL_PTR this_arg, struct LDKPublicKey node_id, bool no_connection_possible);
	public static native void PeerManager_disconnect_by_node_id(long this_arg, byte[] node_id, boolean no_connection_possible);
	// void PeerManager_disconnect_all_peers(const struct LDKPeerManager *NONNULL_PTR this_arg);
	public static native void PeerManager_disconnect_all_peers(long this_arg);
	// void PeerManager_timer_tick_occurred(const struct LDKPeerManager *NONNULL_PTR this_arg);
	public static native void PeerManager_timer_tick_occurred(long this_arg);
	// uint64_t htlc_success_tx_weight(bool opt_anchors);
	public static native long htlc_success_tx_weight(boolean opt_anchors);
	// uint64_t htlc_timeout_tx_weight(bool opt_anchors);
	public static native long htlc_timeout_tx_weight(boolean opt_anchors);
	// struct LDKThirtyTwoBytes build_commitment_secret(const uint8_t (*commitment_seed)[32], uint64_t idx);
	public static native byte[] build_commitment_secret(byte[] commitment_seed, long idx);
	// struct LDKTransaction build_closing_transaction(uint64_t to_holder_value_sat, uint64_t to_counterparty_value_sat, struct LDKCVec_u8Z to_holder_script, struct LDKCVec_u8Z to_counterparty_script, struct LDKOutPoint funding_outpoint);
	public static native byte[] build_closing_transaction(long to_holder_value_sat, long to_counterparty_value_sat, byte[] to_holder_script, byte[] to_counterparty_script, long funding_outpoint);
	// void CounterpartyCommitmentSecrets_free(struct LDKCounterpartyCommitmentSecrets this_obj);
	public static native void CounterpartyCommitmentSecrets_free(long this_obj);
	// uintptr_t CounterpartyCommitmentSecrets_clone_ptr(LDKCounterpartyCommitmentSecrets *NONNULL_PTR arg);
	public static native long CounterpartyCommitmentSecrets_clone_ptr(long arg);
	// struct LDKCounterpartyCommitmentSecrets CounterpartyCommitmentSecrets_clone(const struct LDKCounterpartyCommitmentSecrets *NONNULL_PTR orig);
	public static native long CounterpartyCommitmentSecrets_clone(long orig);
	// MUST_USE_RES struct LDKCounterpartyCommitmentSecrets CounterpartyCommitmentSecrets_new(void);
	public static native long CounterpartyCommitmentSecrets_new();
	// MUST_USE_RES uint64_t CounterpartyCommitmentSecrets_get_min_seen_secret(const struct LDKCounterpartyCommitmentSecrets *NONNULL_PTR this_arg);
	public static native long CounterpartyCommitmentSecrets_get_min_seen_secret(long this_arg);
	// MUST_USE_RES struct LDKCResult_NoneNoneZ CounterpartyCommitmentSecrets_provide_secret(struct LDKCounterpartyCommitmentSecrets *NONNULL_PTR this_arg, uint64_t idx, struct LDKThirtyTwoBytes secret);
	public static native long CounterpartyCommitmentSecrets_provide_secret(long this_arg, long idx, byte[] secret);
	// MUST_USE_RES struct LDKThirtyTwoBytes CounterpartyCommitmentSecrets_get_secret(const struct LDKCounterpartyCommitmentSecrets *NONNULL_PTR this_arg, uint64_t idx);
	public static native byte[] CounterpartyCommitmentSecrets_get_secret(long this_arg, long idx);
	// struct LDKCVec_u8Z CounterpartyCommitmentSecrets_write(const struct LDKCounterpartyCommitmentSecrets *NONNULL_PTR obj);
	public static native byte[] CounterpartyCommitmentSecrets_write(long obj);
	// struct LDKCResult_CounterpartyCommitmentSecretsDecodeErrorZ CounterpartyCommitmentSecrets_read(struct LDKu8slice ser);
	public static native long CounterpartyCommitmentSecrets_read(byte[] ser);
	// struct LDKCResult_SecretKeyErrorZ derive_private_key(struct LDKPublicKey per_commitment_point, const uint8_t (*base_secret)[32]);
	public static native long derive_private_key(byte[] per_commitment_point, byte[] base_secret);
	// struct LDKCResult_PublicKeyErrorZ derive_public_key(struct LDKPublicKey per_commitment_point, struct LDKPublicKey base_point);
	public static native long derive_public_key(byte[] per_commitment_point, byte[] base_point);
	// struct LDKCResult_SecretKeyErrorZ derive_private_revocation_key(const uint8_t (*per_commitment_secret)[32], const uint8_t (*countersignatory_revocation_base_secret)[32]);
	public static native long derive_private_revocation_key(byte[] per_commitment_secret, byte[] countersignatory_revocation_base_secret);
	// struct LDKCResult_PublicKeyErrorZ derive_public_revocation_key(struct LDKPublicKey per_commitment_point, struct LDKPublicKey countersignatory_revocation_base_point);
	public static native long derive_public_revocation_key(byte[] per_commitment_point, byte[] countersignatory_revocation_base_point);
	// void TxCreationKeys_free(struct LDKTxCreationKeys this_obj);
	public static native void TxCreationKeys_free(long this_obj);
	// struct LDKPublicKey TxCreationKeys_get_per_commitment_point(const struct LDKTxCreationKeys *NONNULL_PTR this_ptr);
	public static native byte[] TxCreationKeys_get_per_commitment_point(long this_ptr);
	// void TxCreationKeys_set_per_commitment_point(struct LDKTxCreationKeys *NONNULL_PTR this_ptr, struct LDKPublicKey val);
	public static native void TxCreationKeys_set_per_commitment_point(long this_ptr, byte[] val);
	// struct LDKPublicKey TxCreationKeys_get_revocation_key(const struct LDKTxCreationKeys *NONNULL_PTR this_ptr);
	public static native byte[] TxCreationKeys_get_revocation_key(long this_ptr);
	// void TxCreationKeys_set_revocation_key(struct LDKTxCreationKeys *NONNULL_PTR this_ptr, struct LDKPublicKey val);
	public static native void TxCreationKeys_set_revocation_key(long this_ptr, byte[] val);
	// struct LDKPublicKey TxCreationKeys_get_broadcaster_htlc_key(const struct LDKTxCreationKeys *NONNULL_PTR this_ptr);
	public static native byte[] TxCreationKeys_get_broadcaster_htlc_key(long this_ptr);
	// void TxCreationKeys_set_broadcaster_htlc_key(struct LDKTxCreationKeys *NONNULL_PTR this_ptr, struct LDKPublicKey val);
	public static native void TxCreationKeys_set_broadcaster_htlc_key(long this_ptr, byte[] val);
	// struct LDKPublicKey TxCreationKeys_get_countersignatory_htlc_key(const struct LDKTxCreationKeys *NONNULL_PTR this_ptr);
	public static native byte[] TxCreationKeys_get_countersignatory_htlc_key(long this_ptr);
	// void TxCreationKeys_set_countersignatory_htlc_key(struct LDKTxCreationKeys *NONNULL_PTR this_ptr, struct LDKPublicKey val);
	public static native void TxCreationKeys_set_countersignatory_htlc_key(long this_ptr, byte[] val);
	// struct LDKPublicKey TxCreationKeys_get_broadcaster_delayed_payment_key(const struct LDKTxCreationKeys *NONNULL_PTR this_ptr);
	public static native byte[] TxCreationKeys_get_broadcaster_delayed_payment_key(long this_ptr);
	// void TxCreationKeys_set_broadcaster_delayed_payment_key(struct LDKTxCreationKeys *NONNULL_PTR this_ptr, struct LDKPublicKey val);
	public static native void TxCreationKeys_set_broadcaster_delayed_payment_key(long this_ptr, byte[] val);
	// MUST_USE_RES struct LDKTxCreationKeys TxCreationKeys_new(struct LDKPublicKey per_commitment_point_arg, struct LDKPublicKey revocation_key_arg, struct LDKPublicKey broadcaster_htlc_key_arg, struct LDKPublicKey countersignatory_htlc_key_arg, struct LDKPublicKey broadcaster_delayed_payment_key_arg);
	public static native long TxCreationKeys_new(byte[] per_commitment_point_arg, byte[] revocation_key_arg, byte[] broadcaster_htlc_key_arg, byte[] countersignatory_htlc_key_arg, byte[] broadcaster_delayed_payment_key_arg);
	// uintptr_t TxCreationKeys_clone_ptr(LDKTxCreationKeys *NONNULL_PTR arg);
	public static native long TxCreationKeys_clone_ptr(long arg);
	// struct LDKTxCreationKeys TxCreationKeys_clone(const struct LDKTxCreationKeys *NONNULL_PTR orig);
	public static native long TxCreationKeys_clone(long orig);
	// struct LDKCVec_u8Z TxCreationKeys_write(const struct LDKTxCreationKeys *NONNULL_PTR obj);
	public static native byte[] TxCreationKeys_write(long obj);
	// struct LDKCResult_TxCreationKeysDecodeErrorZ TxCreationKeys_read(struct LDKu8slice ser);
	public static native long TxCreationKeys_read(byte[] ser);
	// void ChannelPublicKeys_free(struct LDKChannelPublicKeys this_obj);
	public static native void ChannelPublicKeys_free(long this_obj);
	// struct LDKPublicKey ChannelPublicKeys_get_funding_pubkey(const struct LDKChannelPublicKeys *NONNULL_PTR this_ptr);
	public static native byte[] ChannelPublicKeys_get_funding_pubkey(long this_ptr);
	// void ChannelPublicKeys_set_funding_pubkey(struct LDKChannelPublicKeys *NONNULL_PTR this_ptr, struct LDKPublicKey val);
	public static native void ChannelPublicKeys_set_funding_pubkey(long this_ptr, byte[] val);
	// struct LDKPublicKey ChannelPublicKeys_get_revocation_basepoint(const struct LDKChannelPublicKeys *NONNULL_PTR this_ptr);
	public static native byte[] ChannelPublicKeys_get_revocation_basepoint(long this_ptr);
	// void ChannelPublicKeys_set_revocation_basepoint(struct LDKChannelPublicKeys *NONNULL_PTR this_ptr, struct LDKPublicKey val);
	public static native void ChannelPublicKeys_set_revocation_basepoint(long this_ptr, byte[] val);
	// struct LDKPublicKey ChannelPublicKeys_get_payment_point(const struct LDKChannelPublicKeys *NONNULL_PTR this_ptr);
	public static native byte[] ChannelPublicKeys_get_payment_point(long this_ptr);
	// void ChannelPublicKeys_set_payment_point(struct LDKChannelPublicKeys *NONNULL_PTR this_ptr, struct LDKPublicKey val);
	public static native void ChannelPublicKeys_set_payment_point(long this_ptr, byte[] val);
	// struct LDKPublicKey ChannelPublicKeys_get_delayed_payment_basepoint(const struct LDKChannelPublicKeys *NONNULL_PTR this_ptr);
	public static native byte[] ChannelPublicKeys_get_delayed_payment_basepoint(long this_ptr);
	// void ChannelPublicKeys_set_delayed_payment_basepoint(struct LDKChannelPublicKeys *NONNULL_PTR this_ptr, struct LDKPublicKey val);
	public static native void ChannelPublicKeys_set_delayed_payment_basepoint(long this_ptr, byte[] val);
	// struct LDKPublicKey ChannelPublicKeys_get_htlc_basepoint(const struct LDKChannelPublicKeys *NONNULL_PTR this_ptr);
	public static native byte[] ChannelPublicKeys_get_htlc_basepoint(long this_ptr);
	// void ChannelPublicKeys_set_htlc_basepoint(struct LDKChannelPublicKeys *NONNULL_PTR this_ptr, struct LDKPublicKey val);
	public static native void ChannelPublicKeys_set_htlc_basepoint(long this_ptr, byte[] val);
	// MUST_USE_RES struct LDKChannelPublicKeys ChannelPublicKeys_new(struct LDKPublicKey funding_pubkey_arg, struct LDKPublicKey revocation_basepoint_arg, struct LDKPublicKey payment_point_arg, struct LDKPublicKey delayed_payment_basepoint_arg, struct LDKPublicKey htlc_basepoint_arg);
	public static native long ChannelPublicKeys_new(byte[] funding_pubkey_arg, byte[] revocation_basepoint_arg, byte[] payment_point_arg, byte[] delayed_payment_basepoint_arg, byte[] htlc_basepoint_arg);
	// uintptr_t ChannelPublicKeys_clone_ptr(LDKChannelPublicKeys *NONNULL_PTR arg);
	public static native long ChannelPublicKeys_clone_ptr(long arg);
	// struct LDKChannelPublicKeys ChannelPublicKeys_clone(const struct LDKChannelPublicKeys *NONNULL_PTR orig);
	public static native long ChannelPublicKeys_clone(long orig);
	// struct LDKCVec_u8Z ChannelPublicKeys_write(const struct LDKChannelPublicKeys *NONNULL_PTR obj);
	public static native byte[] ChannelPublicKeys_write(long obj);
	// struct LDKCResult_ChannelPublicKeysDecodeErrorZ ChannelPublicKeys_read(struct LDKu8slice ser);
	public static native long ChannelPublicKeys_read(byte[] ser);
	// MUST_USE_RES struct LDKCResult_TxCreationKeysErrorZ TxCreationKeys_derive_new(struct LDKPublicKey per_commitment_point, struct LDKPublicKey broadcaster_delayed_payment_base, struct LDKPublicKey broadcaster_htlc_base, struct LDKPublicKey countersignatory_revocation_base, struct LDKPublicKey countersignatory_htlc_base);
	public static native long TxCreationKeys_derive_new(byte[] per_commitment_point, byte[] broadcaster_delayed_payment_base, byte[] broadcaster_htlc_base, byte[] countersignatory_revocation_base, byte[] countersignatory_htlc_base);
	// MUST_USE_RES struct LDKCResult_TxCreationKeysErrorZ TxCreationKeys_from_channel_static_keys(struct LDKPublicKey per_commitment_point, const struct LDKChannelPublicKeys *NONNULL_PTR broadcaster_keys, const struct LDKChannelPublicKeys *NONNULL_PTR countersignatory_keys);
	public static native long TxCreationKeys_from_channel_static_keys(byte[] per_commitment_point, long broadcaster_keys, long countersignatory_keys);
	// struct LDKCVec_u8Z get_revokeable_redeemscript(struct LDKPublicKey revocation_key, uint16_t contest_delay, struct LDKPublicKey broadcaster_delayed_payment_key);
	public static native byte[] get_revokeable_redeemscript(byte[] revocation_key, short contest_delay, byte[] broadcaster_delayed_payment_key);
	// void HTLCOutputInCommitment_free(struct LDKHTLCOutputInCommitment this_obj);
	public static native void HTLCOutputInCommitment_free(long this_obj);
	// bool HTLCOutputInCommitment_get_offered(const struct LDKHTLCOutputInCommitment *NONNULL_PTR this_ptr);
	public static native boolean HTLCOutputInCommitment_get_offered(long this_ptr);
	// void HTLCOutputInCommitment_set_offered(struct LDKHTLCOutputInCommitment *NONNULL_PTR this_ptr, bool val);
	public static native void HTLCOutputInCommitment_set_offered(long this_ptr, boolean val);
	// uint64_t HTLCOutputInCommitment_get_amount_msat(const struct LDKHTLCOutputInCommitment *NONNULL_PTR this_ptr);
	public static native long HTLCOutputInCommitment_get_amount_msat(long this_ptr);
	// void HTLCOutputInCommitment_set_amount_msat(struct LDKHTLCOutputInCommitment *NONNULL_PTR this_ptr, uint64_t val);
	public static native void HTLCOutputInCommitment_set_amount_msat(long this_ptr, long val);
	// uint32_t HTLCOutputInCommitment_get_cltv_expiry(const struct LDKHTLCOutputInCommitment *NONNULL_PTR this_ptr);
	public static native int HTLCOutputInCommitment_get_cltv_expiry(long this_ptr);
	// void HTLCOutputInCommitment_set_cltv_expiry(struct LDKHTLCOutputInCommitment *NONNULL_PTR this_ptr, uint32_t val);
	public static native void HTLCOutputInCommitment_set_cltv_expiry(long this_ptr, int val);
	// const uint8_t (*HTLCOutputInCommitment_get_payment_hash(const struct LDKHTLCOutputInCommitment *NONNULL_PTR this_ptr))[32];
	public static native byte[] HTLCOutputInCommitment_get_payment_hash(long this_ptr);
	// void HTLCOutputInCommitment_set_payment_hash(struct LDKHTLCOutputInCommitment *NONNULL_PTR this_ptr, struct LDKThirtyTwoBytes val);
	public static native void HTLCOutputInCommitment_set_payment_hash(long this_ptr, byte[] val);
	// struct LDKCOption_u32Z HTLCOutputInCommitment_get_transaction_output_index(const struct LDKHTLCOutputInCommitment *NONNULL_PTR this_ptr);
	public static native long HTLCOutputInCommitment_get_transaction_output_index(long this_ptr);
	// void HTLCOutputInCommitment_set_transaction_output_index(struct LDKHTLCOutputInCommitment *NONNULL_PTR this_ptr, struct LDKCOption_u32Z val);
	public static native void HTLCOutputInCommitment_set_transaction_output_index(long this_ptr, long val);
	// MUST_USE_RES struct LDKHTLCOutputInCommitment HTLCOutputInCommitment_new(bool offered_arg, uint64_t amount_msat_arg, uint32_t cltv_expiry_arg, struct LDKThirtyTwoBytes payment_hash_arg, struct LDKCOption_u32Z transaction_output_index_arg);
	public static native long HTLCOutputInCommitment_new(boolean offered_arg, long amount_msat_arg, int cltv_expiry_arg, byte[] payment_hash_arg, long transaction_output_index_arg);
	// uintptr_t HTLCOutputInCommitment_clone_ptr(LDKHTLCOutputInCommitment *NONNULL_PTR arg);
	public static native long HTLCOutputInCommitment_clone_ptr(long arg);
	// struct LDKHTLCOutputInCommitment HTLCOutputInCommitment_clone(const struct LDKHTLCOutputInCommitment *NONNULL_PTR orig);
	public static native long HTLCOutputInCommitment_clone(long orig);
	// struct LDKCVec_u8Z HTLCOutputInCommitment_write(const struct LDKHTLCOutputInCommitment *NONNULL_PTR obj);
	public static native byte[] HTLCOutputInCommitment_write(long obj);
	// struct LDKCResult_HTLCOutputInCommitmentDecodeErrorZ HTLCOutputInCommitment_read(struct LDKu8slice ser);
	public static native long HTLCOutputInCommitment_read(byte[] ser);
	// struct LDKCVec_u8Z get_htlc_redeemscript(const struct LDKHTLCOutputInCommitment *NONNULL_PTR htlc, bool opt_anchors, const struct LDKTxCreationKeys *NONNULL_PTR keys);
	public static native byte[] get_htlc_redeemscript(long htlc, boolean opt_anchors, long keys);
	// struct LDKCVec_u8Z make_funding_redeemscript(struct LDKPublicKey broadcaster, struct LDKPublicKey countersignatory);
	public static native byte[] make_funding_redeemscript(byte[] broadcaster, byte[] countersignatory);
	// struct LDKTransaction build_htlc_transaction(const uint8_t (*commitment_txid)[32], uint32_t feerate_per_kw, uint16_t contest_delay, const struct LDKHTLCOutputInCommitment *NONNULL_PTR htlc, bool opt_anchors, struct LDKPublicKey broadcaster_delayed_payment_key, struct LDKPublicKey revocation_key);
	public static native byte[] build_htlc_transaction(byte[] commitment_txid, int feerate_per_kw, short contest_delay, long htlc, boolean opt_anchors, byte[] broadcaster_delayed_payment_key, byte[] revocation_key);
	// struct LDKCVec_u8Z get_anchor_redeemscript(struct LDKPublicKey funding_pubkey);
	public static native byte[] get_anchor_redeemscript(byte[] funding_pubkey);
	// void ChannelTransactionParameters_free(struct LDKChannelTransactionParameters this_obj);
	public static native void ChannelTransactionParameters_free(long this_obj);
	// struct LDKChannelPublicKeys ChannelTransactionParameters_get_holder_pubkeys(const struct LDKChannelTransactionParameters *NONNULL_PTR this_ptr);
	public static native long ChannelTransactionParameters_get_holder_pubkeys(long this_ptr);
	// void ChannelTransactionParameters_set_holder_pubkeys(struct LDKChannelTransactionParameters *NONNULL_PTR this_ptr, struct LDKChannelPublicKeys val);
	public static native void ChannelTransactionParameters_set_holder_pubkeys(long this_ptr, long val);
	// uint16_t ChannelTransactionParameters_get_holder_selected_contest_delay(const struct LDKChannelTransactionParameters *NONNULL_PTR this_ptr);
	public static native short ChannelTransactionParameters_get_holder_selected_contest_delay(long this_ptr);
	// void ChannelTransactionParameters_set_holder_selected_contest_delay(struct LDKChannelTransactionParameters *NONNULL_PTR this_ptr, uint16_t val);
	public static native void ChannelTransactionParameters_set_holder_selected_contest_delay(long this_ptr, short val);
	// bool ChannelTransactionParameters_get_is_outbound_from_holder(const struct LDKChannelTransactionParameters *NONNULL_PTR this_ptr);
	public static native boolean ChannelTransactionParameters_get_is_outbound_from_holder(long this_ptr);
	// void ChannelTransactionParameters_set_is_outbound_from_holder(struct LDKChannelTransactionParameters *NONNULL_PTR this_ptr, bool val);
	public static native void ChannelTransactionParameters_set_is_outbound_from_holder(long this_ptr, boolean val);
	// struct LDKCounterpartyChannelTransactionParameters ChannelTransactionParameters_get_counterparty_parameters(const struct LDKChannelTransactionParameters *NONNULL_PTR this_ptr);
	public static native long ChannelTransactionParameters_get_counterparty_parameters(long this_ptr);
	// void ChannelTransactionParameters_set_counterparty_parameters(struct LDKChannelTransactionParameters *NONNULL_PTR this_ptr, struct LDKCounterpartyChannelTransactionParameters val);
	public static native void ChannelTransactionParameters_set_counterparty_parameters(long this_ptr, long val);
	// struct LDKOutPoint ChannelTransactionParameters_get_funding_outpoint(const struct LDKChannelTransactionParameters *NONNULL_PTR this_ptr);
	public static native long ChannelTransactionParameters_get_funding_outpoint(long this_ptr);
	// void ChannelTransactionParameters_set_funding_outpoint(struct LDKChannelTransactionParameters *NONNULL_PTR this_ptr, struct LDKOutPoint val);
	public static native void ChannelTransactionParameters_set_funding_outpoint(long this_ptr, long val);
	// enum LDKCOption_NoneZ ChannelTransactionParameters_get_opt_anchors(const struct LDKChannelTransactionParameters *NONNULL_PTR this_ptr);
	public static native COption_NoneZ ChannelTransactionParameters_get_opt_anchors(long this_ptr);
	// void ChannelTransactionParameters_set_opt_anchors(struct LDKChannelTransactionParameters *NONNULL_PTR this_ptr, enum LDKCOption_NoneZ val);
	public static native void ChannelTransactionParameters_set_opt_anchors(long this_ptr, COption_NoneZ val);
	// MUST_USE_RES struct LDKChannelTransactionParameters ChannelTransactionParameters_new(struct LDKChannelPublicKeys holder_pubkeys_arg, uint16_t holder_selected_contest_delay_arg, bool is_outbound_from_holder_arg, struct LDKCounterpartyChannelTransactionParameters counterparty_parameters_arg, struct LDKOutPoint funding_outpoint_arg, enum LDKCOption_NoneZ opt_anchors_arg);
	public static native long ChannelTransactionParameters_new(long holder_pubkeys_arg, short holder_selected_contest_delay_arg, boolean is_outbound_from_holder_arg, long counterparty_parameters_arg, long funding_outpoint_arg, COption_NoneZ opt_anchors_arg);
	// uintptr_t ChannelTransactionParameters_clone_ptr(LDKChannelTransactionParameters *NONNULL_PTR arg);
	public static native long ChannelTransactionParameters_clone_ptr(long arg);
	// struct LDKChannelTransactionParameters ChannelTransactionParameters_clone(const struct LDKChannelTransactionParameters *NONNULL_PTR orig);
	public static native long ChannelTransactionParameters_clone(long orig);
	// void CounterpartyChannelTransactionParameters_free(struct LDKCounterpartyChannelTransactionParameters this_obj);
	public static native void CounterpartyChannelTransactionParameters_free(long this_obj);
	// struct LDKChannelPublicKeys CounterpartyChannelTransactionParameters_get_pubkeys(const struct LDKCounterpartyChannelTransactionParameters *NONNULL_PTR this_ptr);
	public static native long CounterpartyChannelTransactionParameters_get_pubkeys(long this_ptr);
	// void CounterpartyChannelTransactionParameters_set_pubkeys(struct LDKCounterpartyChannelTransactionParameters *NONNULL_PTR this_ptr, struct LDKChannelPublicKeys val);
	public static native void CounterpartyChannelTransactionParameters_set_pubkeys(long this_ptr, long val);
	// uint16_t CounterpartyChannelTransactionParameters_get_selected_contest_delay(const struct LDKCounterpartyChannelTransactionParameters *NONNULL_PTR this_ptr);
	public static native short CounterpartyChannelTransactionParameters_get_selected_contest_delay(long this_ptr);
	// void CounterpartyChannelTransactionParameters_set_selected_contest_delay(struct LDKCounterpartyChannelTransactionParameters *NONNULL_PTR this_ptr, uint16_t val);
	public static native void CounterpartyChannelTransactionParameters_set_selected_contest_delay(long this_ptr, short val);
	// MUST_USE_RES struct LDKCounterpartyChannelTransactionParameters CounterpartyChannelTransactionParameters_new(struct LDKChannelPublicKeys pubkeys_arg, uint16_t selected_contest_delay_arg);
	public static native long CounterpartyChannelTransactionParameters_new(long pubkeys_arg, short selected_contest_delay_arg);
	// uintptr_t CounterpartyChannelTransactionParameters_clone_ptr(LDKCounterpartyChannelTransactionParameters *NONNULL_PTR arg);
	public static native long CounterpartyChannelTransactionParameters_clone_ptr(long arg);
	// struct LDKCounterpartyChannelTransactionParameters CounterpartyChannelTransactionParameters_clone(const struct LDKCounterpartyChannelTransactionParameters *NONNULL_PTR orig);
	public static native long CounterpartyChannelTransactionParameters_clone(long orig);
	// MUST_USE_RES bool ChannelTransactionParameters_is_populated(const struct LDKChannelTransactionParameters *NONNULL_PTR this_arg);
	public static native boolean ChannelTransactionParameters_is_populated(long this_arg);
	// MUST_USE_RES struct LDKDirectedChannelTransactionParameters ChannelTransactionParameters_as_holder_broadcastable(const struct LDKChannelTransactionParameters *NONNULL_PTR this_arg);
	public static native long ChannelTransactionParameters_as_holder_broadcastable(long this_arg);
	// MUST_USE_RES struct LDKDirectedChannelTransactionParameters ChannelTransactionParameters_as_counterparty_broadcastable(const struct LDKChannelTransactionParameters *NONNULL_PTR this_arg);
	public static native long ChannelTransactionParameters_as_counterparty_broadcastable(long this_arg);
	// struct LDKCVec_u8Z CounterpartyChannelTransactionParameters_write(const struct LDKCounterpartyChannelTransactionParameters *NONNULL_PTR obj);
	public static native byte[] CounterpartyChannelTransactionParameters_write(long obj);
	// struct LDKCResult_CounterpartyChannelTransactionParametersDecodeErrorZ CounterpartyChannelTransactionParameters_read(struct LDKu8slice ser);
	public static native long CounterpartyChannelTransactionParameters_read(byte[] ser);
	// struct LDKCVec_u8Z ChannelTransactionParameters_write(const struct LDKChannelTransactionParameters *NONNULL_PTR obj);
	public static native byte[] ChannelTransactionParameters_write(long obj);
	// struct LDKCResult_ChannelTransactionParametersDecodeErrorZ ChannelTransactionParameters_read(struct LDKu8slice ser);
	public static native long ChannelTransactionParameters_read(byte[] ser);
	// void DirectedChannelTransactionParameters_free(struct LDKDirectedChannelTransactionParameters this_obj);
	public static native void DirectedChannelTransactionParameters_free(long this_obj);
	// MUST_USE_RES struct LDKChannelPublicKeys DirectedChannelTransactionParameters_broadcaster_pubkeys(const struct LDKDirectedChannelTransactionParameters *NONNULL_PTR this_arg);
	public static native long DirectedChannelTransactionParameters_broadcaster_pubkeys(long this_arg);
	// MUST_USE_RES struct LDKChannelPublicKeys DirectedChannelTransactionParameters_countersignatory_pubkeys(const struct LDKDirectedChannelTransactionParameters *NONNULL_PTR this_arg);
	public static native long DirectedChannelTransactionParameters_countersignatory_pubkeys(long this_arg);
	// MUST_USE_RES uint16_t DirectedChannelTransactionParameters_contest_delay(const struct LDKDirectedChannelTransactionParameters *NONNULL_PTR this_arg);
	public static native short DirectedChannelTransactionParameters_contest_delay(long this_arg);
	// MUST_USE_RES bool DirectedChannelTransactionParameters_is_outbound(const struct LDKDirectedChannelTransactionParameters *NONNULL_PTR this_arg);
	public static native boolean DirectedChannelTransactionParameters_is_outbound(long this_arg);
	// MUST_USE_RES struct LDKOutPoint DirectedChannelTransactionParameters_funding_outpoint(const struct LDKDirectedChannelTransactionParameters *NONNULL_PTR this_arg);
	public static native long DirectedChannelTransactionParameters_funding_outpoint(long this_arg);
	// MUST_USE_RES bool DirectedChannelTransactionParameters_opt_anchors(const struct LDKDirectedChannelTransactionParameters *NONNULL_PTR this_arg);
	public static native boolean DirectedChannelTransactionParameters_opt_anchors(long this_arg);
	// void HolderCommitmentTransaction_free(struct LDKHolderCommitmentTransaction this_obj);
	public static native void HolderCommitmentTransaction_free(long this_obj);
	// struct LDKSignature HolderCommitmentTransaction_get_counterparty_sig(const struct LDKHolderCommitmentTransaction *NONNULL_PTR this_ptr);
	public static native byte[] HolderCommitmentTransaction_get_counterparty_sig(long this_ptr);
	// void HolderCommitmentTransaction_set_counterparty_sig(struct LDKHolderCommitmentTransaction *NONNULL_PTR this_ptr, struct LDKSignature val);
	public static native void HolderCommitmentTransaction_set_counterparty_sig(long this_ptr, byte[] val);
	// void HolderCommitmentTransaction_set_counterparty_htlc_sigs(struct LDKHolderCommitmentTransaction *NONNULL_PTR this_ptr, struct LDKCVec_SignatureZ val);
	public static native void HolderCommitmentTransaction_set_counterparty_htlc_sigs(long this_ptr, byte[][] val);
	// uintptr_t HolderCommitmentTransaction_clone_ptr(LDKHolderCommitmentTransaction *NONNULL_PTR arg);
	public static native long HolderCommitmentTransaction_clone_ptr(long arg);
	// struct LDKHolderCommitmentTransaction HolderCommitmentTransaction_clone(const struct LDKHolderCommitmentTransaction *NONNULL_PTR orig);
	public static native long HolderCommitmentTransaction_clone(long orig);
	// struct LDKCVec_u8Z HolderCommitmentTransaction_write(const struct LDKHolderCommitmentTransaction *NONNULL_PTR obj);
	public static native byte[] HolderCommitmentTransaction_write(long obj);
	// struct LDKCResult_HolderCommitmentTransactionDecodeErrorZ HolderCommitmentTransaction_read(struct LDKu8slice ser);
	public static native long HolderCommitmentTransaction_read(byte[] ser);
	// MUST_USE_RES struct LDKHolderCommitmentTransaction HolderCommitmentTransaction_new(struct LDKCommitmentTransaction commitment_tx, struct LDKSignature counterparty_sig, struct LDKCVec_SignatureZ counterparty_htlc_sigs, struct LDKPublicKey holder_funding_key, struct LDKPublicKey counterparty_funding_key);
	public static native long HolderCommitmentTransaction_new(long commitment_tx, byte[] counterparty_sig, byte[][] counterparty_htlc_sigs, byte[] holder_funding_key, byte[] counterparty_funding_key);
	// void BuiltCommitmentTransaction_free(struct LDKBuiltCommitmentTransaction this_obj);
	public static native void BuiltCommitmentTransaction_free(long this_obj);
	// struct LDKTransaction BuiltCommitmentTransaction_get_transaction(const struct LDKBuiltCommitmentTransaction *NONNULL_PTR this_ptr);
	public static native byte[] BuiltCommitmentTransaction_get_transaction(long this_ptr);
	// void BuiltCommitmentTransaction_set_transaction(struct LDKBuiltCommitmentTransaction *NONNULL_PTR this_ptr, struct LDKTransaction val);
	public static native void BuiltCommitmentTransaction_set_transaction(long this_ptr, byte[] val);
	// const uint8_t (*BuiltCommitmentTransaction_get_txid(const struct LDKBuiltCommitmentTransaction *NONNULL_PTR this_ptr))[32];
	public static native byte[] BuiltCommitmentTransaction_get_txid(long this_ptr);
	// void BuiltCommitmentTransaction_set_txid(struct LDKBuiltCommitmentTransaction *NONNULL_PTR this_ptr, struct LDKThirtyTwoBytes val);
	public static native void BuiltCommitmentTransaction_set_txid(long this_ptr, byte[] val);
	// MUST_USE_RES struct LDKBuiltCommitmentTransaction BuiltCommitmentTransaction_new(struct LDKTransaction transaction_arg, struct LDKThirtyTwoBytes txid_arg);
	public static native long BuiltCommitmentTransaction_new(byte[] transaction_arg, byte[] txid_arg);
	// uintptr_t BuiltCommitmentTransaction_clone_ptr(LDKBuiltCommitmentTransaction *NONNULL_PTR arg);
	public static native long BuiltCommitmentTransaction_clone_ptr(long arg);
	// struct LDKBuiltCommitmentTransaction BuiltCommitmentTransaction_clone(const struct LDKBuiltCommitmentTransaction *NONNULL_PTR orig);
	public static native long BuiltCommitmentTransaction_clone(long orig);
	// struct LDKCVec_u8Z BuiltCommitmentTransaction_write(const struct LDKBuiltCommitmentTransaction *NONNULL_PTR obj);
	public static native byte[] BuiltCommitmentTransaction_write(long obj);
	// struct LDKCResult_BuiltCommitmentTransactionDecodeErrorZ BuiltCommitmentTransaction_read(struct LDKu8slice ser);
	public static native long BuiltCommitmentTransaction_read(byte[] ser);
	// MUST_USE_RES struct LDKThirtyTwoBytes BuiltCommitmentTransaction_get_sighash_all(const struct LDKBuiltCommitmentTransaction *NONNULL_PTR this_arg, struct LDKu8slice funding_redeemscript, uint64_t channel_value_satoshis);
	public static native byte[] BuiltCommitmentTransaction_get_sighash_all(long this_arg, byte[] funding_redeemscript, long channel_value_satoshis);
	// MUST_USE_RES struct LDKSignature BuiltCommitmentTransaction_sign(const struct LDKBuiltCommitmentTransaction *NONNULL_PTR this_arg, const uint8_t (*funding_key)[32], struct LDKu8slice funding_redeemscript, uint64_t channel_value_satoshis);
	public static native byte[] BuiltCommitmentTransaction_sign(long this_arg, byte[] funding_key, byte[] funding_redeemscript, long channel_value_satoshis);
	// void ClosingTransaction_free(struct LDKClosingTransaction this_obj);
	public static native void ClosingTransaction_free(long this_obj);
	// uintptr_t ClosingTransaction_clone_ptr(LDKClosingTransaction *NONNULL_PTR arg);
	public static native long ClosingTransaction_clone_ptr(long arg);
	// struct LDKClosingTransaction ClosingTransaction_clone(const struct LDKClosingTransaction *NONNULL_PTR orig);
	public static native long ClosingTransaction_clone(long orig);
	// uint64_t ClosingTransaction_hash(const struct LDKClosingTransaction *NONNULL_PTR o);
	public static native long ClosingTransaction_hash(long o);
	// MUST_USE_RES struct LDKClosingTransaction ClosingTransaction_new(uint64_t to_holder_value_sat, uint64_t to_counterparty_value_sat, struct LDKCVec_u8Z to_holder_script, struct LDKCVec_u8Z to_counterparty_script, struct LDKOutPoint funding_outpoint);
	public static native long ClosingTransaction_new(long to_holder_value_sat, long to_counterparty_value_sat, byte[] to_holder_script, byte[] to_counterparty_script, long funding_outpoint);
	// MUST_USE_RES struct LDKTrustedClosingTransaction ClosingTransaction_trust(const struct LDKClosingTransaction *NONNULL_PTR this_arg);
	public static native long ClosingTransaction_trust(long this_arg);
	// MUST_USE_RES struct LDKCResult_TrustedClosingTransactionNoneZ ClosingTransaction_verify(const struct LDKClosingTransaction *NONNULL_PTR this_arg, struct LDKOutPoint funding_outpoint);
	public static native long ClosingTransaction_verify(long this_arg, long funding_outpoint);
	// MUST_USE_RES uint64_t ClosingTransaction_to_holder_value_sat(const struct LDKClosingTransaction *NONNULL_PTR this_arg);
	public static native long ClosingTransaction_to_holder_value_sat(long this_arg);
	// MUST_USE_RES uint64_t ClosingTransaction_to_counterparty_value_sat(const struct LDKClosingTransaction *NONNULL_PTR this_arg);
	public static native long ClosingTransaction_to_counterparty_value_sat(long this_arg);
	// MUST_USE_RES struct LDKu8slice ClosingTransaction_to_holder_script(const struct LDKClosingTransaction *NONNULL_PTR this_arg);
	public static native byte[] ClosingTransaction_to_holder_script(long this_arg);
	// MUST_USE_RES struct LDKu8slice ClosingTransaction_to_counterparty_script(const struct LDKClosingTransaction *NONNULL_PTR this_arg);
	public static native byte[] ClosingTransaction_to_counterparty_script(long this_arg);
	// void TrustedClosingTransaction_free(struct LDKTrustedClosingTransaction this_obj);
	public static native void TrustedClosingTransaction_free(long this_obj);
	// MUST_USE_RES struct LDKTransaction TrustedClosingTransaction_built_transaction(const struct LDKTrustedClosingTransaction *NONNULL_PTR this_arg);
	public static native byte[] TrustedClosingTransaction_built_transaction(long this_arg);
	// MUST_USE_RES struct LDKThirtyTwoBytes TrustedClosingTransaction_get_sighash_all(const struct LDKTrustedClosingTransaction *NONNULL_PTR this_arg, struct LDKu8slice funding_redeemscript, uint64_t channel_value_satoshis);
	public static native byte[] TrustedClosingTransaction_get_sighash_all(long this_arg, byte[] funding_redeemscript, long channel_value_satoshis);
	// MUST_USE_RES struct LDKSignature TrustedClosingTransaction_sign(const struct LDKTrustedClosingTransaction *NONNULL_PTR this_arg, const uint8_t (*funding_key)[32], struct LDKu8slice funding_redeemscript, uint64_t channel_value_satoshis);
	public static native byte[] TrustedClosingTransaction_sign(long this_arg, byte[] funding_key, byte[] funding_redeemscript, long channel_value_satoshis);
	// void CommitmentTransaction_free(struct LDKCommitmentTransaction this_obj);
	public static native void CommitmentTransaction_free(long this_obj);
	// uintptr_t CommitmentTransaction_clone_ptr(LDKCommitmentTransaction *NONNULL_PTR arg);
	public static native long CommitmentTransaction_clone_ptr(long arg);
	// struct LDKCommitmentTransaction CommitmentTransaction_clone(const struct LDKCommitmentTransaction *NONNULL_PTR orig);
	public static native long CommitmentTransaction_clone(long orig);
	// struct LDKCVec_u8Z CommitmentTransaction_write(const struct LDKCommitmentTransaction *NONNULL_PTR obj);
	public static native byte[] CommitmentTransaction_write(long obj);
	// struct LDKCResult_CommitmentTransactionDecodeErrorZ CommitmentTransaction_read(struct LDKu8slice ser);
	public static native long CommitmentTransaction_read(byte[] ser);
	// MUST_USE_RES uint64_t CommitmentTransaction_commitment_number(const struct LDKCommitmentTransaction *NONNULL_PTR this_arg);
	public static native long CommitmentTransaction_commitment_number(long this_arg);
	// MUST_USE_RES uint64_t CommitmentTransaction_to_broadcaster_value_sat(const struct LDKCommitmentTransaction *NONNULL_PTR this_arg);
	public static native long CommitmentTransaction_to_broadcaster_value_sat(long this_arg);
	// MUST_USE_RES uint64_t CommitmentTransaction_to_countersignatory_value_sat(const struct LDKCommitmentTransaction *NONNULL_PTR this_arg);
	public static native long CommitmentTransaction_to_countersignatory_value_sat(long this_arg);
	// MUST_USE_RES uint32_t CommitmentTransaction_feerate_per_kw(const struct LDKCommitmentTransaction *NONNULL_PTR this_arg);
	public static native int CommitmentTransaction_feerate_per_kw(long this_arg);
	// MUST_USE_RES struct LDKTrustedCommitmentTransaction CommitmentTransaction_trust(const struct LDKCommitmentTransaction *NONNULL_PTR this_arg);
	public static native long CommitmentTransaction_trust(long this_arg);
	// MUST_USE_RES struct LDKCResult_TrustedCommitmentTransactionNoneZ CommitmentTransaction_verify(const struct LDKCommitmentTransaction *NONNULL_PTR this_arg, const struct LDKDirectedChannelTransactionParameters *NONNULL_PTR channel_parameters, const struct LDKChannelPublicKeys *NONNULL_PTR broadcaster_keys, const struct LDKChannelPublicKeys *NONNULL_PTR countersignatory_keys);
	public static native long CommitmentTransaction_verify(long this_arg, long channel_parameters, long broadcaster_keys, long countersignatory_keys);
	// void TrustedCommitmentTransaction_free(struct LDKTrustedCommitmentTransaction this_obj);
	public static native void TrustedCommitmentTransaction_free(long this_obj);
	// MUST_USE_RES struct LDKThirtyTwoBytes TrustedCommitmentTransaction_txid(const struct LDKTrustedCommitmentTransaction *NONNULL_PTR this_arg);
	public static native byte[] TrustedCommitmentTransaction_txid(long this_arg);
	// MUST_USE_RES struct LDKBuiltCommitmentTransaction TrustedCommitmentTransaction_built_transaction(const struct LDKTrustedCommitmentTransaction *NONNULL_PTR this_arg);
	public static native long TrustedCommitmentTransaction_built_transaction(long this_arg);
	// MUST_USE_RES struct LDKTxCreationKeys TrustedCommitmentTransaction_keys(const struct LDKTrustedCommitmentTransaction *NONNULL_PTR this_arg);
	public static native long TrustedCommitmentTransaction_keys(long this_arg);
	// MUST_USE_RES bool TrustedCommitmentTransaction_opt_anchors(const struct LDKTrustedCommitmentTransaction *NONNULL_PTR this_arg);
	public static native boolean TrustedCommitmentTransaction_opt_anchors(long this_arg);
	// MUST_USE_RES struct LDKCResult_CVec_SignatureZNoneZ TrustedCommitmentTransaction_get_htlc_sigs(const struct LDKTrustedCommitmentTransaction *NONNULL_PTR this_arg, const uint8_t (*htlc_base_key)[32], const struct LDKDirectedChannelTransactionParameters *NONNULL_PTR channel_parameters);
	public static native long TrustedCommitmentTransaction_get_htlc_sigs(long this_arg, byte[] htlc_base_key, long channel_parameters);
	// uint64_t get_commitment_transaction_number_obscure_factor(struct LDKPublicKey broadcaster_payment_basepoint, struct LDKPublicKey countersignatory_payment_basepoint, bool outbound_from_broadcaster);
	public static native long get_commitment_transaction_number_obscure_factor(byte[] broadcaster_payment_basepoint, byte[] countersignatory_payment_basepoint, boolean outbound_from_broadcaster);
	// bool InitFeatures_eq(const struct LDKInitFeatures *NONNULL_PTR a, const struct LDKInitFeatures *NONNULL_PTR b);
	public static native boolean InitFeatures_eq(long a, long b);
	// bool NodeFeatures_eq(const struct LDKNodeFeatures *NONNULL_PTR a, const struct LDKNodeFeatures *NONNULL_PTR b);
	public static native boolean NodeFeatures_eq(long a, long b);
	// bool ChannelFeatures_eq(const struct LDKChannelFeatures *NONNULL_PTR a, const struct LDKChannelFeatures *NONNULL_PTR b);
	public static native boolean ChannelFeatures_eq(long a, long b);
	// bool InvoiceFeatures_eq(const struct LDKInvoiceFeatures *NONNULL_PTR a, const struct LDKInvoiceFeatures *NONNULL_PTR b);
	public static native boolean InvoiceFeatures_eq(long a, long b);
	// bool ChannelTypeFeatures_eq(const struct LDKChannelTypeFeatures *NONNULL_PTR a, const struct LDKChannelTypeFeatures *NONNULL_PTR b);
	public static native boolean ChannelTypeFeatures_eq(long a, long b);
	// uintptr_t InitFeatures_clone_ptr(LDKInitFeatures *NONNULL_PTR arg);
	public static native long InitFeatures_clone_ptr(long arg);
	// struct LDKInitFeatures InitFeatures_clone(const struct LDKInitFeatures *NONNULL_PTR orig);
	public static native long InitFeatures_clone(long orig);
	// uintptr_t NodeFeatures_clone_ptr(LDKNodeFeatures *NONNULL_PTR arg);
	public static native long NodeFeatures_clone_ptr(long arg);
	// struct LDKNodeFeatures NodeFeatures_clone(const struct LDKNodeFeatures *NONNULL_PTR orig);
	public static native long NodeFeatures_clone(long orig);
	// uintptr_t ChannelFeatures_clone_ptr(LDKChannelFeatures *NONNULL_PTR arg);
	public static native long ChannelFeatures_clone_ptr(long arg);
	// struct LDKChannelFeatures ChannelFeatures_clone(const struct LDKChannelFeatures *NONNULL_PTR orig);
	public static native long ChannelFeatures_clone(long orig);
	// uintptr_t InvoiceFeatures_clone_ptr(LDKInvoiceFeatures *NONNULL_PTR arg);
	public static native long InvoiceFeatures_clone_ptr(long arg);
	// struct LDKInvoiceFeatures InvoiceFeatures_clone(const struct LDKInvoiceFeatures *NONNULL_PTR orig);
	public static native long InvoiceFeatures_clone(long orig);
	// uintptr_t ChannelTypeFeatures_clone_ptr(LDKChannelTypeFeatures *NONNULL_PTR arg);
	public static native long ChannelTypeFeatures_clone_ptr(long arg);
	// struct LDKChannelTypeFeatures ChannelTypeFeatures_clone(const struct LDKChannelTypeFeatures *NONNULL_PTR orig);
	public static native long ChannelTypeFeatures_clone(long orig);
	// void InitFeatures_free(struct LDKInitFeatures this_obj);
	public static native void InitFeatures_free(long this_obj);
	// void NodeFeatures_free(struct LDKNodeFeatures this_obj);
	public static native void NodeFeatures_free(long this_obj);
	// void ChannelFeatures_free(struct LDKChannelFeatures this_obj);
	public static native void ChannelFeatures_free(long this_obj);
	// void InvoiceFeatures_free(struct LDKInvoiceFeatures this_obj);
	public static native void InvoiceFeatures_free(long this_obj);
	// void ChannelTypeFeatures_free(struct LDKChannelTypeFeatures this_obj);
	public static native void ChannelTypeFeatures_free(long this_obj);
	// MUST_USE_RES struct LDKInitFeatures InitFeatures_empty(void);
	public static native long InitFeatures_empty();
	// MUST_USE_RES struct LDKInitFeatures InitFeatures_known(void);
	public static native long InitFeatures_known();
	// MUST_USE_RES bool InitFeatures_requires_unknown_bits(const struct LDKInitFeatures *NONNULL_PTR this_arg);
	public static native boolean InitFeatures_requires_unknown_bits(long this_arg);
	// MUST_USE_RES struct LDKNodeFeatures NodeFeatures_empty(void);
	public static native long NodeFeatures_empty();
	// MUST_USE_RES struct LDKNodeFeatures NodeFeatures_known(void);
	public static native long NodeFeatures_known();
	// MUST_USE_RES bool NodeFeatures_requires_unknown_bits(const struct LDKNodeFeatures *NONNULL_PTR this_arg);
	public static native boolean NodeFeatures_requires_unknown_bits(long this_arg);
	// MUST_USE_RES struct LDKChannelFeatures ChannelFeatures_empty(void);
	public static native long ChannelFeatures_empty();
	// MUST_USE_RES struct LDKChannelFeatures ChannelFeatures_known(void);
	public static native long ChannelFeatures_known();
	// MUST_USE_RES bool ChannelFeatures_requires_unknown_bits(const struct LDKChannelFeatures *NONNULL_PTR this_arg);
	public static native boolean ChannelFeatures_requires_unknown_bits(long this_arg);
	// MUST_USE_RES struct LDKInvoiceFeatures InvoiceFeatures_empty(void);
	public static native long InvoiceFeatures_empty();
	// MUST_USE_RES struct LDKInvoiceFeatures InvoiceFeatures_known(void);
	public static native long InvoiceFeatures_known();
	// MUST_USE_RES bool InvoiceFeatures_requires_unknown_bits(const struct LDKInvoiceFeatures *NONNULL_PTR this_arg);
	public static native boolean InvoiceFeatures_requires_unknown_bits(long this_arg);
	// MUST_USE_RES struct LDKChannelTypeFeatures ChannelTypeFeatures_empty(void);
	public static native long ChannelTypeFeatures_empty();
	// MUST_USE_RES struct LDKChannelTypeFeatures ChannelTypeFeatures_known(void);
	public static native long ChannelTypeFeatures_known();
	// MUST_USE_RES bool ChannelTypeFeatures_requires_unknown_bits(const struct LDKChannelTypeFeatures *NONNULL_PTR this_arg);
	public static native boolean ChannelTypeFeatures_requires_unknown_bits(long this_arg);
	// struct LDKCVec_u8Z InitFeatures_write(const struct LDKInitFeatures *NONNULL_PTR obj);
	public static native byte[] InitFeatures_write(long obj);
	// struct LDKCResult_InitFeaturesDecodeErrorZ InitFeatures_read(struct LDKu8slice ser);
	public static native long InitFeatures_read(byte[] ser);
	// struct LDKCVec_u8Z ChannelFeatures_write(const struct LDKChannelFeatures *NONNULL_PTR obj);
	public static native byte[] ChannelFeatures_write(long obj);
	// struct LDKCResult_ChannelFeaturesDecodeErrorZ ChannelFeatures_read(struct LDKu8slice ser);
	public static native long ChannelFeatures_read(byte[] ser);
	// struct LDKCVec_u8Z NodeFeatures_write(const struct LDKNodeFeatures *NONNULL_PTR obj);
	public static native byte[] NodeFeatures_write(long obj);
	// struct LDKCResult_NodeFeaturesDecodeErrorZ NodeFeatures_read(struct LDKu8slice ser);
	public static native long NodeFeatures_read(byte[] ser);
	// struct LDKCVec_u8Z InvoiceFeatures_write(const struct LDKInvoiceFeatures *NONNULL_PTR obj);
	public static native byte[] InvoiceFeatures_write(long obj);
	// struct LDKCResult_InvoiceFeaturesDecodeErrorZ InvoiceFeatures_read(struct LDKu8slice ser);
	public static native long InvoiceFeatures_read(byte[] ser);
	// struct LDKCVec_u8Z ChannelTypeFeatures_write(const struct LDKChannelTypeFeatures *NONNULL_PTR obj);
	public static native byte[] ChannelTypeFeatures_write(long obj);
	// struct LDKCResult_ChannelTypeFeaturesDecodeErrorZ ChannelTypeFeatures_read(struct LDKu8slice ser);
	public static native long ChannelTypeFeatures_read(byte[] ser);
	// void ShutdownScript_free(struct LDKShutdownScript this_obj);
	public static native void ShutdownScript_free(long this_obj);
	// uintptr_t ShutdownScript_clone_ptr(LDKShutdownScript *NONNULL_PTR arg);
	public static native long ShutdownScript_clone_ptr(long arg);
	// struct LDKShutdownScript ShutdownScript_clone(const struct LDKShutdownScript *NONNULL_PTR orig);
	public static native long ShutdownScript_clone(long orig);
	// void InvalidShutdownScript_free(struct LDKInvalidShutdownScript this_obj);
	public static native void InvalidShutdownScript_free(long this_obj);
	// struct LDKu8slice InvalidShutdownScript_get_script(const struct LDKInvalidShutdownScript *NONNULL_PTR this_ptr);
	public static native byte[] InvalidShutdownScript_get_script(long this_ptr);
	// void InvalidShutdownScript_set_script(struct LDKInvalidShutdownScript *NONNULL_PTR this_ptr, struct LDKCVec_u8Z val);
	public static native void InvalidShutdownScript_set_script(long this_ptr, byte[] val);
	// MUST_USE_RES struct LDKInvalidShutdownScript InvalidShutdownScript_new(struct LDKCVec_u8Z script_arg);
	public static native long InvalidShutdownScript_new(byte[] script_arg);
	// uintptr_t InvalidShutdownScript_clone_ptr(LDKInvalidShutdownScript *NONNULL_PTR arg);
	public static native long InvalidShutdownScript_clone_ptr(long arg);
	// struct LDKInvalidShutdownScript InvalidShutdownScript_clone(const struct LDKInvalidShutdownScript *NONNULL_PTR orig);
	public static native long InvalidShutdownScript_clone(long orig);
	// struct LDKCVec_u8Z ShutdownScript_write(const struct LDKShutdownScript *NONNULL_PTR obj);
	public static native byte[] ShutdownScript_write(long obj);
	// struct LDKCResult_ShutdownScriptDecodeErrorZ ShutdownScript_read(struct LDKu8slice ser);
	public static native long ShutdownScript_read(byte[] ser);
	// MUST_USE_RES struct LDKShutdownScript ShutdownScript_new_p2wpkh(const uint8_t (*pubkey_hash)[20]);
	public static native long ShutdownScript_new_p2wpkh(byte[] pubkey_hash);
	// MUST_USE_RES struct LDKShutdownScript ShutdownScript_new_p2wsh(const uint8_t (*script_hash)[32]);
	public static native long ShutdownScript_new_p2wsh(byte[] script_hash);
	// MUST_USE_RES struct LDKCResult_ShutdownScriptInvalidShutdownScriptZ ShutdownScript_new_witness_program(uint8_t version, struct LDKu8slice program);
	public static native long ShutdownScript_new_witness_program(byte version, byte[] program);
	// MUST_USE_RES struct LDKCVec_u8Z ShutdownScript_into_inner(struct LDKShutdownScript this_arg);
	public static native byte[] ShutdownScript_into_inner(long this_arg);
	// MUST_USE_RES struct LDKPublicKey ShutdownScript_as_legacy_pubkey(const struct LDKShutdownScript *NONNULL_PTR this_arg);
	public static native byte[] ShutdownScript_as_legacy_pubkey(long this_arg);
	// MUST_USE_RES bool ShutdownScript_is_compatible(const struct LDKShutdownScript *NONNULL_PTR this_arg, const struct LDKInitFeatures *NONNULL_PTR features);
	public static native boolean ShutdownScript_is_compatible(long this_arg, long features);
	// void CustomMessageReader_free(struct LDKCustomMessageReader this_ptr);
	public static native void CustomMessageReader_free(long this_ptr);
	// uintptr_t Type_clone_ptr(LDKType *NONNULL_PTR arg);
	public static native long Type_clone_ptr(long arg);
	// struct LDKType Type_clone(const struct LDKType *NONNULL_PTR orig);
	public static native long Type_clone(long orig);
	// void Type_free(struct LDKType this_ptr);
	public static native void Type_free(long this_ptr);
	// void NodeId_free(struct LDKNodeId this_obj);
	public static native void NodeId_free(long this_obj);
	// uintptr_t NodeId_clone_ptr(LDKNodeId *NONNULL_PTR arg);
	public static native long NodeId_clone_ptr(long arg);
	// struct LDKNodeId NodeId_clone(const struct LDKNodeId *NONNULL_PTR orig);
	public static native long NodeId_clone(long orig);
	// MUST_USE_RES struct LDKNodeId NodeId_from_pubkey(struct LDKPublicKey pubkey);
	public static native long NodeId_from_pubkey(byte[] pubkey);
	// MUST_USE_RES struct LDKu8slice NodeId_as_slice(const struct LDKNodeId *NONNULL_PTR this_arg);
	public static native byte[] NodeId_as_slice(long this_arg);
	// uint64_t NodeId_hash(const struct LDKNodeId *NONNULL_PTR o);
	public static native long NodeId_hash(long o);
	// struct LDKCVec_u8Z NodeId_write(const struct LDKNodeId *NONNULL_PTR obj);
	public static native byte[] NodeId_write(long obj);
	// struct LDKCResult_NodeIdDecodeErrorZ NodeId_read(struct LDKu8slice ser);
	public static native long NodeId_read(byte[] ser);
	// void NetworkGraph_free(struct LDKNetworkGraph this_obj);
	public static native void NetworkGraph_free(long this_obj);
	// uintptr_t NetworkGraph_clone_ptr(LDKNetworkGraph *NONNULL_PTR arg);
	public static native long NetworkGraph_clone_ptr(long arg);
	// struct LDKNetworkGraph NetworkGraph_clone(const struct LDKNetworkGraph *NONNULL_PTR orig);
	public static native long NetworkGraph_clone(long orig);
	// void ReadOnlyNetworkGraph_free(struct LDKReadOnlyNetworkGraph this_obj);
	public static native void ReadOnlyNetworkGraph_free(long this_obj);
	// void NetworkUpdate_free(struct LDKNetworkUpdate this_ptr);
	public static native void NetworkUpdate_free(long this_ptr);
	// uintptr_t NetworkUpdate_clone_ptr(LDKNetworkUpdate *NONNULL_PTR arg);
	public static native long NetworkUpdate_clone_ptr(long arg);
	// struct LDKNetworkUpdate NetworkUpdate_clone(const struct LDKNetworkUpdate *NONNULL_PTR orig);
	public static native long NetworkUpdate_clone(long orig);
	// struct LDKNetworkUpdate NetworkUpdate_channel_update_message(struct LDKChannelUpdate msg);
	public static native long NetworkUpdate_channel_update_message(long msg);
	// struct LDKNetworkUpdate NetworkUpdate_channel_closed(uint64_t short_channel_id, bool is_permanent);
	public static native long NetworkUpdate_channel_closed(long short_channel_id, boolean is_permanent);
	// struct LDKNetworkUpdate NetworkUpdate_node_failure(struct LDKPublicKey node_id, bool is_permanent);
	public static native long NetworkUpdate_node_failure(byte[] node_id, boolean is_permanent);
	// struct LDKCVec_u8Z NetworkUpdate_write(const struct LDKNetworkUpdate *NONNULL_PTR obj);
	public static native byte[] NetworkUpdate_write(long obj);
	// struct LDKCResult_COption_NetworkUpdateZDecodeErrorZ NetworkUpdate_read(struct LDKu8slice ser);
	public static native long NetworkUpdate_read(byte[] ser);
	// struct LDKEventHandler NetGraphMsgHandler_as_EventHandler(const struct LDKNetGraphMsgHandler *NONNULL_PTR this_arg);
	public static native long NetGraphMsgHandler_as_EventHandler(long this_arg);
	// void NetGraphMsgHandler_free(struct LDKNetGraphMsgHandler this_obj);
	public static native void NetGraphMsgHandler_free(long this_obj);
	// MUST_USE_RES struct LDKNetGraphMsgHandler NetGraphMsgHandler_new(const struct LDKNetworkGraph *NONNULL_PTR network_graph, struct LDKCOption_AccessZ chain_access, struct LDKLogger logger);
	public static native long NetGraphMsgHandler_new(long network_graph, long chain_access, long logger);
	// void NetGraphMsgHandler_add_chain_access(struct LDKNetGraphMsgHandler *NONNULL_PTR this_arg, struct LDKCOption_AccessZ chain_access);
	public static native void NetGraphMsgHandler_add_chain_access(long this_arg, long chain_access);
	// struct LDKRoutingMessageHandler NetGraphMsgHandler_as_RoutingMessageHandler(const struct LDKNetGraphMsgHandler *NONNULL_PTR this_arg);
	public static native long NetGraphMsgHandler_as_RoutingMessageHandler(long this_arg);
	// struct LDKMessageSendEventsProvider NetGraphMsgHandler_as_MessageSendEventsProvider(const struct LDKNetGraphMsgHandler *NONNULL_PTR this_arg);
	public static native long NetGraphMsgHandler_as_MessageSendEventsProvider(long this_arg);
	// void ChannelUpdateInfo_free(struct LDKChannelUpdateInfo this_obj);
	public static native void ChannelUpdateInfo_free(long this_obj);
	// uint32_t ChannelUpdateInfo_get_last_update(const struct LDKChannelUpdateInfo *NONNULL_PTR this_ptr);
	public static native int ChannelUpdateInfo_get_last_update(long this_ptr);
	// void ChannelUpdateInfo_set_last_update(struct LDKChannelUpdateInfo *NONNULL_PTR this_ptr, uint32_t val);
	public static native void ChannelUpdateInfo_set_last_update(long this_ptr, int val);
	// bool ChannelUpdateInfo_get_enabled(const struct LDKChannelUpdateInfo *NONNULL_PTR this_ptr);
	public static native boolean ChannelUpdateInfo_get_enabled(long this_ptr);
	// void ChannelUpdateInfo_set_enabled(struct LDKChannelUpdateInfo *NONNULL_PTR this_ptr, bool val);
	public static native void ChannelUpdateInfo_set_enabled(long this_ptr, boolean val);
	// uint16_t ChannelUpdateInfo_get_cltv_expiry_delta(const struct LDKChannelUpdateInfo *NONNULL_PTR this_ptr);
	public static native short ChannelUpdateInfo_get_cltv_expiry_delta(long this_ptr);
	// void ChannelUpdateInfo_set_cltv_expiry_delta(struct LDKChannelUpdateInfo *NONNULL_PTR this_ptr, uint16_t val);
	public static native void ChannelUpdateInfo_set_cltv_expiry_delta(long this_ptr, short val);
	// uint64_t ChannelUpdateInfo_get_htlc_minimum_msat(const struct LDKChannelUpdateInfo *NONNULL_PTR this_ptr);
	public static native long ChannelUpdateInfo_get_htlc_minimum_msat(long this_ptr);
	// void ChannelUpdateInfo_set_htlc_minimum_msat(struct LDKChannelUpdateInfo *NONNULL_PTR this_ptr, uint64_t val);
	public static native void ChannelUpdateInfo_set_htlc_minimum_msat(long this_ptr, long val);
	// struct LDKCOption_u64Z ChannelUpdateInfo_get_htlc_maximum_msat(const struct LDKChannelUpdateInfo *NONNULL_PTR this_ptr);
	public static native long ChannelUpdateInfo_get_htlc_maximum_msat(long this_ptr);
	// void ChannelUpdateInfo_set_htlc_maximum_msat(struct LDKChannelUpdateInfo *NONNULL_PTR this_ptr, struct LDKCOption_u64Z val);
	public static native void ChannelUpdateInfo_set_htlc_maximum_msat(long this_ptr, long val);
	// struct LDKRoutingFees ChannelUpdateInfo_get_fees(const struct LDKChannelUpdateInfo *NONNULL_PTR this_ptr);
	public static native long ChannelUpdateInfo_get_fees(long this_ptr);
	// void ChannelUpdateInfo_set_fees(struct LDKChannelUpdateInfo *NONNULL_PTR this_ptr, struct LDKRoutingFees val);
	public static native void ChannelUpdateInfo_set_fees(long this_ptr, long val);
	// struct LDKChannelUpdate ChannelUpdateInfo_get_last_update_message(const struct LDKChannelUpdateInfo *NONNULL_PTR this_ptr);
	public static native long ChannelUpdateInfo_get_last_update_message(long this_ptr);
	// void ChannelUpdateInfo_set_last_update_message(struct LDKChannelUpdateInfo *NONNULL_PTR this_ptr, struct LDKChannelUpdate val);
	public static native void ChannelUpdateInfo_set_last_update_message(long this_ptr, long val);
	// MUST_USE_RES struct LDKChannelUpdateInfo ChannelUpdateInfo_new(uint32_t last_update_arg, bool enabled_arg, uint16_t cltv_expiry_delta_arg, uint64_t htlc_minimum_msat_arg, struct LDKCOption_u64Z htlc_maximum_msat_arg, struct LDKRoutingFees fees_arg, struct LDKChannelUpdate last_update_message_arg);
	public static native long ChannelUpdateInfo_new(int last_update_arg, boolean enabled_arg, short cltv_expiry_delta_arg, long htlc_minimum_msat_arg, long htlc_maximum_msat_arg, long fees_arg, long last_update_message_arg);
	// uintptr_t ChannelUpdateInfo_clone_ptr(LDKChannelUpdateInfo *NONNULL_PTR arg);
	public static native long ChannelUpdateInfo_clone_ptr(long arg);
	// struct LDKChannelUpdateInfo ChannelUpdateInfo_clone(const struct LDKChannelUpdateInfo *NONNULL_PTR orig);
	public static native long ChannelUpdateInfo_clone(long orig);
	// struct LDKCVec_u8Z ChannelUpdateInfo_write(const struct LDKChannelUpdateInfo *NONNULL_PTR obj);
	public static native byte[] ChannelUpdateInfo_write(long obj);
	// struct LDKCResult_ChannelUpdateInfoDecodeErrorZ ChannelUpdateInfo_read(struct LDKu8slice ser);
	public static native long ChannelUpdateInfo_read(byte[] ser);
	// void ChannelInfo_free(struct LDKChannelInfo this_obj);
	public static native void ChannelInfo_free(long this_obj);
	// struct LDKChannelFeatures ChannelInfo_get_features(const struct LDKChannelInfo *NONNULL_PTR this_ptr);
	public static native long ChannelInfo_get_features(long this_ptr);
	// void ChannelInfo_set_features(struct LDKChannelInfo *NONNULL_PTR this_ptr, struct LDKChannelFeatures val);
	public static native void ChannelInfo_set_features(long this_ptr, long val);
	// struct LDKNodeId ChannelInfo_get_node_one(const struct LDKChannelInfo *NONNULL_PTR this_ptr);
	public static native long ChannelInfo_get_node_one(long this_ptr);
	// void ChannelInfo_set_node_one(struct LDKChannelInfo *NONNULL_PTR this_ptr, struct LDKNodeId val);
	public static native void ChannelInfo_set_node_one(long this_ptr, long val);
	// struct LDKChannelUpdateInfo ChannelInfo_get_one_to_two(const struct LDKChannelInfo *NONNULL_PTR this_ptr);
	public static native long ChannelInfo_get_one_to_two(long this_ptr);
	// void ChannelInfo_set_one_to_two(struct LDKChannelInfo *NONNULL_PTR this_ptr, struct LDKChannelUpdateInfo val);
	public static native void ChannelInfo_set_one_to_two(long this_ptr, long val);
	// struct LDKNodeId ChannelInfo_get_node_two(const struct LDKChannelInfo *NONNULL_PTR this_ptr);
	public static native long ChannelInfo_get_node_two(long this_ptr);
	// void ChannelInfo_set_node_two(struct LDKChannelInfo *NONNULL_PTR this_ptr, struct LDKNodeId val);
	public static native void ChannelInfo_set_node_two(long this_ptr, long val);
	// struct LDKChannelUpdateInfo ChannelInfo_get_two_to_one(const struct LDKChannelInfo *NONNULL_PTR this_ptr);
	public static native long ChannelInfo_get_two_to_one(long this_ptr);
	// void ChannelInfo_set_two_to_one(struct LDKChannelInfo *NONNULL_PTR this_ptr, struct LDKChannelUpdateInfo val);
	public static native void ChannelInfo_set_two_to_one(long this_ptr, long val);
	// struct LDKCOption_u64Z ChannelInfo_get_capacity_sats(const struct LDKChannelInfo *NONNULL_PTR this_ptr);
	public static native long ChannelInfo_get_capacity_sats(long this_ptr);
	// void ChannelInfo_set_capacity_sats(struct LDKChannelInfo *NONNULL_PTR this_ptr, struct LDKCOption_u64Z val);
	public static native void ChannelInfo_set_capacity_sats(long this_ptr, long val);
	// struct LDKChannelAnnouncement ChannelInfo_get_announcement_message(const struct LDKChannelInfo *NONNULL_PTR this_ptr);
	public static native long ChannelInfo_get_announcement_message(long this_ptr);
	// void ChannelInfo_set_announcement_message(struct LDKChannelInfo *NONNULL_PTR this_ptr, struct LDKChannelAnnouncement val);
	public static native void ChannelInfo_set_announcement_message(long this_ptr, long val);
	// uintptr_t ChannelInfo_clone_ptr(LDKChannelInfo *NONNULL_PTR arg);
	public static native long ChannelInfo_clone_ptr(long arg);
	// struct LDKChannelInfo ChannelInfo_clone(const struct LDKChannelInfo *NONNULL_PTR orig);
	public static native long ChannelInfo_clone(long orig);
	// struct LDKCVec_u8Z ChannelInfo_write(const struct LDKChannelInfo *NONNULL_PTR obj);
	public static native byte[] ChannelInfo_write(long obj);
	// struct LDKCResult_ChannelInfoDecodeErrorZ ChannelInfo_read(struct LDKu8slice ser);
	public static native long ChannelInfo_read(byte[] ser);
	// void DirectedChannelInfo_free(struct LDKDirectedChannelInfo this_obj);
	public static native void DirectedChannelInfo_free(long this_obj);
	// uintptr_t DirectedChannelInfo_clone_ptr(LDKDirectedChannelInfo *NONNULL_PTR arg);
	public static native long DirectedChannelInfo_clone_ptr(long arg);
	// struct LDKDirectedChannelInfo DirectedChannelInfo_clone(const struct LDKDirectedChannelInfo *NONNULL_PTR orig);
	public static native long DirectedChannelInfo_clone(long orig);
	// MUST_USE_RES struct LDKChannelInfo DirectedChannelInfo_channel(const struct LDKDirectedChannelInfo *NONNULL_PTR this_arg);
	public static native long DirectedChannelInfo_channel(long this_arg);
	// MUST_USE_RES struct LDKChannelUpdateInfo DirectedChannelInfo_direction(const struct LDKDirectedChannelInfo *NONNULL_PTR this_arg);
	public static native long DirectedChannelInfo_direction(long this_arg);
	// MUST_USE_RES struct LDKEffectiveCapacity DirectedChannelInfo_effective_capacity(const struct LDKDirectedChannelInfo *NONNULL_PTR this_arg);
	public static native long DirectedChannelInfo_effective_capacity(long this_arg);
	// void EffectiveCapacity_free(struct LDKEffectiveCapacity this_ptr);
	public static native void EffectiveCapacity_free(long this_ptr);
	// uintptr_t EffectiveCapacity_clone_ptr(LDKEffectiveCapacity *NONNULL_PTR arg);
	public static native long EffectiveCapacity_clone_ptr(long arg);
	// struct LDKEffectiveCapacity EffectiveCapacity_clone(const struct LDKEffectiveCapacity *NONNULL_PTR orig);
	public static native long EffectiveCapacity_clone(long orig);
	// struct LDKEffectiveCapacity EffectiveCapacity_exact_liquidity(uint64_t liquidity_msat);
	public static native long EffectiveCapacity_exact_liquidity(long liquidity_msat);
	// struct LDKEffectiveCapacity EffectiveCapacity_maximum_htlc(uint64_t amount_msat);
	public static native long EffectiveCapacity_maximum_htlc(long amount_msat);
	// struct LDKEffectiveCapacity EffectiveCapacity_total(uint64_t capacity_msat);
	public static native long EffectiveCapacity_total(long capacity_msat);
	// struct LDKEffectiveCapacity EffectiveCapacity_infinite(void);
	public static native long EffectiveCapacity_infinite();
	// struct LDKEffectiveCapacity EffectiveCapacity_unknown(void);
	public static native long EffectiveCapacity_unknown();
	// MUST_USE_RES uint64_t EffectiveCapacity_as_msat(const struct LDKEffectiveCapacity *NONNULL_PTR this_arg);
	public static native long EffectiveCapacity_as_msat(long this_arg);
	// void RoutingFees_free(struct LDKRoutingFees this_obj);
	public static native void RoutingFees_free(long this_obj);
	// uint32_t RoutingFees_get_base_msat(const struct LDKRoutingFees *NONNULL_PTR this_ptr);
	public static native int RoutingFees_get_base_msat(long this_ptr);
	// void RoutingFees_set_base_msat(struct LDKRoutingFees *NONNULL_PTR this_ptr, uint32_t val);
	public static native void RoutingFees_set_base_msat(long this_ptr, int val);
	// uint32_t RoutingFees_get_proportional_millionths(const struct LDKRoutingFees *NONNULL_PTR this_ptr);
	public static native int RoutingFees_get_proportional_millionths(long this_ptr);
	// void RoutingFees_set_proportional_millionths(struct LDKRoutingFees *NONNULL_PTR this_ptr, uint32_t val);
	public static native void RoutingFees_set_proportional_millionths(long this_ptr, int val);
	// MUST_USE_RES struct LDKRoutingFees RoutingFees_new(uint32_t base_msat_arg, uint32_t proportional_millionths_arg);
	public static native long RoutingFees_new(int base_msat_arg, int proportional_millionths_arg);
	// bool RoutingFees_eq(const struct LDKRoutingFees *NONNULL_PTR a, const struct LDKRoutingFees *NONNULL_PTR b);
	public static native boolean RoutingFees_eq(long a, long b);
	// uintptr_t RoutingFees_clone_ptr(LDKRoutingFees *NONNULL_PTR arg);
	public static native long RoutingFees_clone_ptr(long arg);
	// struct LDKRoutingFees RoutingFees_clone(const struct LDKRoutingFees *NONNULL_PTR orig);
	public static native long RoutingFees_clone(long orig);
	// uint64_t RoutingFees_hash(const struct LDKRoutingFees *NONNULL_PTR o);
	public static native long RoutingFees_hash(long o);
	// struct LDKCVec_u8Z RoutingFees_write(const struct LDKRoutingFees *NONNULL_PTR obj);
	public static native byte[] RoutingFees_write(long obj);
	// struct LDKCResult_RoutingFeesDecodeErrorZ RoutingFees_read(struct LDKu8slice ser);
	public static native long RoutingFees_read(byte[] ser);
	// void NodeAnnouncementInfo_free(struct LDKNodeAnnouncementInfo this_obj);
	public static native void NodeAnnouncementInfo_free(long this_obj);
	// struct LDKNodeFeatures NodeAnnouncementInfo_get_features(const struct LDKNodeAnnouncementInfo *NONNULL_PTR this_ptr);
	public static native long NodeAnnouncementInfo_get_features(long this_ptr);
	// void NodeAnnouncementInfo_set_features(struct LDKNodeAnnouncementInfo *NONNULL_PTR this_ptr, struct LDKNodeFeatures val);
	public static native void NodeAnnouncementInfo_set_features(long this_ptr, long val);
	// uint32_t NodeAnnouncementInfo_get_last_update(const struct LDKNodeAnnouncementInfo *NONNULL_PTR this_ptr);
	public static native int NodeAnnouncementInfo_get_last_update(long this_ptr);
	// void NodeAnnouncementInfo_set_last_update(struct LDKNodeAnnouncementInfo *NONNULL_PTR this_ptr, uint32_t val);
	public static native void NodeAnnouncementInfo_set_last_update(long this_ptr, int val);
	// const uint8_t (*NodeAnnouncementInfo_get_rgb(const struct LDKNodeAnnouncementInfo *NONNULL_PTR this_ptr))[3];
	public static native byte[] NodeAnnouncementInfo_get_rgb(long this_ptr);
	// void NodeAnnouncementInfo_set_rgb(struct LDKNodeAnnouncementInfo *NONNULL_PTR this_ptr, struct LDKThreeBytes val);
	public static native void NodeAnnouncementInfo_set_rgb(long this_ptr, byte[] val);
	// const uint8_t (*NodeAnnouncementInfo_get_alias(const struct LDKNodeAnnouncementInfo *NONNULL_PTR this_ptr))[32];
	public static native byte[] NodeAnnouncementInfo_get_alias(long this_ptr);
	// void NodeAnnouncementInfo_set_alias(struct LDKNodeAnnouncementInfo *NONNULL_PTR this_ptr, struct LDKThirtyTwoBytes val);
	public static native void NodeAnnouncementInfo_set_alias(long this_ptr, byte[] val);
	// void NodeAnnouncementInfo_set_addresses(struct LDKNodeAnnouncementInfo *NONNULL_PTR this_ptr, struct LDKCVec_NetAddressZ val);
	public static native void NodeAnnouncementInfo_set_addresses(long this_ptr, long[] val);
	// struct LDKNodeAnnouncement NodeAnnouncementInfo_get_announcement_message(const struct LDKNodeAnnouncementInfo *NONNULL_PTR this_ptr);
	public static native long NodeAnnouncementInfo_get_announcement_message(long this_ptr);
	// void NodeAnnouncementInfo_set_announcement_message(struct LDKNodeAnnouncementInfo *NONNULL_PTR this_ptr, struct LDKNodeAnnouncement val);
	public static native void NodeAnnouncementInfo_set_announcement_message(long this_ptr, long val);
	// MUST_USE_RES struct LDKNodeAnnouncementInfo NodeAnnouncementInfo_new(struct LDKNodeFeatures features_arg, uint32_t last_update_arg, struct LDKThreeBytes rgb_arg, struct LDKThirtyTwoBytes alias_arg, struct LDKCVec_NetAddressZ addresses_arg, struct LDKNodeAnnouncement announcement_message_arg);
	public static native long NodeAnnouncementInfo_new(long features_arg, int last_update_arg, byte[] rgb_arg, byte[] alias_arg, long[] addresses_arg, long announcement_message_arg);
	// uintptr_t NodeAnnouncementInfo_clone_ptr(LDKNodeAnnouncementInfo *NONNULL_PTR arg);
	public static native long NodeAnnouncementInfo_clone_ptr(long arg);
	// struct LDKNodeAnnouncementInfo NodeAnnouncementInfo_clone(const struct LDKNodeAnnouncementInfo *NONNULL_PTR orig);
	public static native long NodeAnnouncementInfo_clone(long orig);
	// struct LDKCVec_u8Z NodeAnnouncementInfo_write(const struct LDKNodeAnnouncementInfo *NONNULL_PTR obj);
	public static native byte[] NodeAnnouncementInfo_write(long obj);
	// struct LDKCResult_NodeAnnouncementInfoDecodeErrorZ NodeAnnouncementInfo_read(struct LDKu8slice ser);
	public static native long NodeAnnouncementInfo_read(byte[] ser);
	// void NodeInfo_free(struct LDKNodeInfo this_obj);
	public static native void NodeInfo_free(long this_obj);
	// void NodeInfo_set_channels(struct LDKNodeInfo *NONNULL_PTR this_ptr, struct LDKCVec_u64Z val);
	public static native void NodeInfo_set_channels(long this_ptr, long[] val);
	// struct LDKRoutingFees NodeInfo_get_lowest_inbound_channel_fees(const struct LDKNodeInfo *NONNULL_PTR this_ptr);
	public static native long NodeInfo_get_lowest_inbound_channel_fees(long this_ptr);
	// void NodeInfo_set_lowest_inbound_channel_fees(struct LDKNodeInfo *NONNULL_PTR this_ptr, struct LDKRoutingFees val);
	public static native void NodeInfo_set_lowest_inbound_channel_fees(long this_ptr, long val);
	// struct LDKNodeAnnouncementInfo NodeInfo_get_announcement_info(const struct LDKNodeInfo *NONNULL_PTR this_ptr);
	public static native long NodeInfo_get_announcement_info(long this_ptr);
	// void NodeInfo_set_announcement_info(struct LDKNodeInfo *NONNULL_PTR this_ptr, struct LDKNodeAnnouncementInfo val);
	public static native void NodeInfo_set_announcement_info(long this_ptr, long val);
	// MUST_USE_RES struct LDKNodeInfo NodeInfo_new(struct LDKCVec_u64Z channels_arg, struct LDKRoutingFees lowest_inbound_channel_fees_arg, struct LDKNodeAnnouncementInfo announcement_info_arg);
	public static native long NodeInfo_new(long[] channels_arg, long lowest_inbound_channel_fees_arg, long announcement_info_arg);
	// uintptr_t NodeInfo_clone_ptr(LDKNodeInfo *NONNULL_PTR arg);
	public static native long NodeInfo_clone_ptr(long arg);
	// struct LDKNodeInfo NodeInfo_clone(const struct LDKNodeInfo *NONNULL_PTR orig);
	public static native long NodeInfo_clone(long orig);
	// struct LDKCVec_u8Z NodeInfo_write(const struct LDKNodeInfo *NONNULL_PTR obj);
	public static native byte[] NodeInfo_write(long obj);
	// struct LDKCResult_NodeInfoDecodeErrorZ NodeInfo_read(struct LDKu8slice ser);
	public static native long NodeInfo_read(byte[] ser);
	// struct LDKCVec_u8Z NetworkGraph_write(const struct LDKNetworkGraph *NONNULL_PTR obj);
	public static native byte[] NetworkGraph_write(long obj);
	// struct LDKCResult_NetworkGraphDecodeErrorZ NetworkGraph_read(struct LDKu8slice ser);
	public static native long NetworkGraph_read(byte[] ser);
	// MUST_USE_RES struct LDKNetworkGraph NetworkGraph_new(struct LDKThirtyTwoBytes genesis_hash);
	public static native long NetworkGraph_new(byte[] genesis_hash);
	// MUST_USE_RES struct LDKReadOnlyNetworkGraph NetworkGraph_read_only(const struct LDKNetworkGraph *NONNULL_PTR this_arg);
	public static native long NetworkGraph_read_only(long this_arg);
	// MUST_USE_RES struct LDKCResult_NoneLightningErrorZ NetworkGraph_update_node_from_announcement(const struct LDKNetworkGraph *NONNULL_PTR this_arg, const struct LDKNodeAnnouncement *NONNULL_PTR msg);
	public static native long NetworkGraph_update_node_from_announcement(long this_arg, long msg);
	// MUST_USE_RES struct LDKCResult_NoneLightningErrorZ NetworkGraph_update_node_from_unsigned_announcement(const struct LDKNetworkGraph *NONNULL_PTR this_arg, const struct LDKUnsignedNodeAnnouncement *NONNULL_PTR msg);
	public static native long NetworkGraph_update_node_from_unsigned_announcement(long this_arg, long msg);
	// MUST_USE_RES struct LDKCResult_NoneLightningErrorZ NetworkGraph_update_channel_from_announcement(const struct LDKNetworkGraph *NONNULL_PTR this_arg, const struct LDKChannelAnnouncement *NONNULL_PTR msg, struct LDKCOption_AccessZ chain_access);
	public static native long NetworkGraph_update_channel_from_announcement(long this_arg, long msg, long chain_access);
	// MUST_USE_RES struct LDKCResult_NoneLightningErrorZ NetworkGraph_update_channel_from_unsigned_announcement(const struct LDKNetworkGraph *NONNULL_PTR this_arg, const struct LDKUnsignedChannelAnnouncement *NONNULL_PTR msg, struct LDKCOption_AccessZ chain_access);
	public static native long NetworkGraph_update_channel_from_unsigned_announcement(long this_arg, long msg, long chain_access);
	// void NetworkGraph_close_channel_from_update(const struct LDKNetworkGraph *NONNULL_PTR this_arg, uint64_t short_channel_id, bool is_permanent);
	public static native void NetworkGraph_close_channel_from_update(long this_arg, long short_channel_id, boolean is_permanent);
	// void NetworkGraph_fail_node(const struct LDKNetworkGraph *NONNULL_PTR this_arg, struct LDKPublicKey _node_id, bool is_permanent);
	public static native void NetworkGraph_fail_node(long this_arg, byte[] _node_id, boolean is_permanent);
	// void NetworkGraph_remove_stale_channels(const struct LDKNetworkGraph *NONNULL_PTR this_arg);
	public static native void NetworkGraph_remove_stale_channels(long this_arg);
	// void NetworkGraph_remove_stale_channels_with_time(const struct LDKNetworkGraph *NONNULL_PTR this_arg, uint64_t current_time_unix);
	public static native void NetworkGraph_remove_stale_channels_with_time(long this_arg, long current_time_unix);
	// MUST_USE_RES struct LDKCResult_NoneLightningErrorZ NetworkGraph_update_channel(const struct LDKNetworkGraph *NONNULL_PTR this_arg, const struct LDKChannelUpdate *NONNULL_PTR msg);
	public static native long NetworkGraph_update_channel(long this_arg, long msg);
	// MUST_USE_RES struct LDKCResult_NoneLightningErrorZ NetworkGraph_update_channel_unsigned(const struct LDKNetworkGraph *NONNULL_PTR this_arg, const struct LDKUnsignedChannelUpdate *NONNULL_PTR msg);
	public static native long NetworkGraph_update_channel_unsigned(long this_arg, long msg);
	// MUST_USE_RES struct LDKCOption_CVec_NetAddressZZ ReadOnlyNetworkGraph_get_addresses(const struct LDKReadOnlyNetworkGraph *NONNULL_PTR this_arg, struct LDKPublicKey pubkey);
	public static native long ReadOnlyNetworkGraph_get_addresses(long this_arg, byte[] pubkey);
	// void RouteHop_free(struct LDKRouteHop this_obj);
	public static native void RouteHop_free(long this_obj);
	// struct LDKPublicKey RouteHop_get_pubkey(const struct LDKRouteHop *NONNULL_PTR this_ptr);
	public static native byte[] RouteHop_get_pubkey(long this_ptr);
	// void RouteHop_set_pubkey(struct LDKRouteHop *NONNULL_PTR this_ptr, struct LDKPublicKey val);
	public static native void RouteHop_set_pubkey(long this_ptr, byte[] val);
	// struct LDKNodeFeatures RouteHop_get_node_features(const struct LDKRouteHop *NONNULL_PTR this_ptr);
	public static native long RouteHop_get_node_features(long this_ptr);
	// void RouteHop_set_node_features(struct LDKRouteHop *NONNULL_PTR this_ptr, struct LDKNodeFeatures val);
	public static native void RouteHop_set_node_features(long this_ptr, long val);
	// uint64_t RouteHop_get_short_channel_id(const struct LDKRouteHop *NONNULL_PTR this_ptr);
	public static native long RouteHop_get_short_channel_id(long this_ptr);
	// void RouteHop_set_short_channel_id(struct LDKRouteHop *NONNULL_PTR this_ptr, uint64_t val);
	public static native void RouteHop_set_short_channel_id(long this_ptr, long val);
	// struct LDKChannelFeatures RouteHop_get_channel_features(const struct LDKRouteHop *NONNULL_PTR this_ptr);
	public static native long RouteHop_get_channel_features(long this_ptr);
	// void RouteHop_set_channel_features(struct LDKRouteHop *NONNULL_PTR this_ptr, struct LDKChannelFeatures val);
	public static native void RouteHop_set_channel_features(long this_ptr, long val);
	// uint64_t RouteHop_get_fee_msat(const struct LDKRouteHop *NONNULL_PTR this_ptr);
	public static native long RouteHop_get_fee_msat(long this_ptr);
	// void RouteHop_set_fee_msat(struct LDKRouteHop *NONNULL_PTR this_ptr, uint64_t val);
	public static native void RouteHop_set_fee_msat(long this_ptr, long val);
	// uint32_t RouteHop_get_cltv_expiry_delta(const struct LDKRouteHop *NONNULL_PTR this_ptr);
	public static native int RouteHop_get_cltv_expiry_delta(long this_ptr);
	// void RouteHop_set_cltv_expiry_delta(struct LDKRouteHop *NONNULL_PTR this_ptr, uint32_t val);
	public static native void RouteHop_set_cltv_expiry_delta(long this_ptr, int val);
	// MUST_USE_RES struct LDKRouteHop RouteHop_new(struct LDKPublicKey pubkey_arg, struct LDKNodeFeatures node_features_arg, uint64_t short_channel_id_arg, struct LDKChannelFeatures channel_features_arg, uint64_t fee_msat_arg, uint32_t cltv_expiry_delta_arg);
	public static native long RouteHop_new(byte[] pubkey_arg, long node_features_arg, long short_channel_id_arg, long channel_features_arg, long fee_msat_arg, int cltv_expiry_delta_arg);
	// uintptr_t RouteHop_clone_ptr(LDKRouteHop *NONNULL_PTR arg);
	public static native long RouteHop_clone_ptr(long arg);
	// struct LDKRouteHop RouteHop_clone(const struct LDKRouteHop *NONNULL_PTR orig);
	public static native long RouteHop_clone(long orig);
	// uint64_t RouteHop_hash(const struct LDKRouteHop *NONNULL_PTR o);
	public static native long RouteHop_hash(long o);
	// bool RouteHop_eq(const struct LDKRouteHop *NONNULL_PTR a, const struct LDKRouteHop *NONNULL_PTR b);
	public static native boolean RouteHop_eq(long a, long b);
	// struct LDKCVec_u8Z RouteHop_write(const struct LDKRouteHop *NONNULL_PTR obj);
	public static native byte[] RouteHop_write(long obj);
	// struct LDKCResult_RouteHopDecodeErrorZ RouteHop_read(struct LDKu8slice ser);
	public static native long RouteHop_read(byte[] ser);
	// void Route_free(struct LDKRoute this_obj);
	public static native void Route_free(long this_obj);
	// struct LDKCVec_CVec_RouteHopZZ Route_get_paths(const struct LDKRoute *NONNULL_PTR this_ptr);
	public static native long[][] Route_get_paths(long this_ptr);
	// void Route_set_paths(struct LDKRoute *NONNULL_PTR this_ptr, struct LDKCVec_CVec_RouteHopZZ val);
	public static native void Route_set_paths(long this_ptr, long[][] val);
	// struct LDKPaymentParameters Route_get_payment_params(const struct LDKRoute *NONNULL_PTR this_ptr);
	public static native long Route_get_payment_params(long this_ptr);
	// void Route_set_payment_params(struct LDKRoute *NONNULL_PTR this_ptr, struct LDKPaymentParameters val);
	public static native void Route_set_payment_params(long this_ptr, long val);
	// MUST_USE_RES struct LDKRoute Route_new(struct LDKCVec_CVec_RouteHopZZ paths_arg, struct LDKPaymentParameters payment_params_arg);
	public static native long Route_new(long[][] paths_arg, long payment_params_arg);
	// uintptr_t Route_clone_ptr(LDKRoute *NONNULL_PTR arg);
	public static native long Route_clone_ptr(long arg);
	// struct LDKRoute Route_clone(const struct LDKRoute *NONNULL_PTR orig);
	public static native long Route_clone(long orig);
	// uint64_t Route_hash(const struct LDKRoute *NONNULL_PTR o);
	public static native long Route_hash(long o);
	// bool Route_eq(const struct LDKRoute *NONNULL_PTR a, const struct LDKRoute *NONNULL_PTR b);
	public static native boolean Route_eq(long a, long b);
	// MUST_USE_RES uint64_t Route_get_total_fees(const struct LDKRoute *NONNULL_PTR this_arg);
	public static native long Route_get_total_fees(long this_arg);
	// MUST_USE_RES uint64_t Route_get_total_amount(const struct LDKRoute *NONNULL_PTR this_arg);
	public static native long Route_get_total_amount(long this_arg);
	// struct LDKCVec_u8Z Route_write(const struct LDKRoute *NONNULL_PTR obj);
	public static native byte[] Route_write(long obj);
	// struct LDKCResult_RouteDecodeErrorZ Route_read(struct LDKu8slice ser);
	public static native long Route_read(byte[] ser);
	// void RouteParameters_free(struct LDKRouteParameters this_obj);
	public static native void RouteParameters_free(long this_obj);
	// struct LDKPaymentParameters RouteParameters_get_payment_params(const struct LDKRouteParameters *NONNULL_PTR this_ptr);
	public static native long RouteParameters_get_payment_params(long this_ptr);
	// void RouteParameters_set_payment_params(struct LDKRouteParameters *NONNULL_PTR this_ptr, struct LDKPaymentParameters val);
	public static native void RouteParameters_set_payment_params(long this_ptr, long val);
	// uint64_t RouteParameters_get_final_value_msat(const struct LDKRouteParameters *NONNULL_PTR this_ptr);
	public static native long RouteParameters_get_final_value_msat(long this_ptr);
	// void RouteParameters_set_final_value_msat(struct LDKRouteParameters *NONNULL_PTR this_ptr, uint64_t val);
	public static native void RouteParameters_set_final_value_msat(long this_ptr, long val);
	// uint32_t RouteParameters_get_final_cltv_expiry_delta(const struct LDKRouteParameters *NONNULL_PTR this_ptr);
	public static native int RouteParameters_get_final_cltv_expiry_delta(long this_ptr);
	// void RouteParameters_set_final_cltv_expiry_delta(struct LDKRouteParameters *NONNULL_PTR this_ptr, uint32_t val);
	public static native void RouteParameters_set_final_cltv_expiry_delta(long this_ptr, int val);
	// MUST_USE_RES struct LDKRouteParameters RouteParameters_new(struct LDKPaymentParameters payment_params_arg, uint64_t final_value_msat_arg, uint32_t final_cltv_expiry_delta_arg);
	public static native long RouteParameters_new(long payment_params_arg, long final_value_msat_arg, int final_cltv_expiry_delta_arg);
	// uintptr_t RouteParameters_clone_ptr(LDKRouteParameters *NONNULL_PTR arg);
	public static native long RouteParameters_clone_ptr(long arg);
	// struct LDKRouteParameters RouteParameters_clone(const struct LDKRouteParameters *NONNULL_PTR orig);
	public static native long RouteParameters_clone(long orig);
	// struct LDKCVec_u8Z RouteParameters_write(const struct LDKRouteParameters *NONNULL_PTR obj);
	public static native byte[] RouteParameters_write(long obj);
	// struct LDKCResult_RouteParametersDecodeErrorZ RouteParameters_read(struct LDKu8slice ser);
	public static native long RouteParameters_read(byte[] ser);
	// void PaymentParameters_free(struct LDKPaymentParameters this_obj);
	public static native void PaymentParameters_free(long this_obj);
	// struct LDKPublicKey PaymentParameters_get_payee_pubkey(const struct LDKPaymentParameters *NONNULL_PTR this_ptr);
	public static native byte[] PaymentParameters_get_payee_pubkey(long this_ptr);
	// void PaymentParameters_set_payee_pubkey(struct LDKPaymentParameters *NONNULL_PTR this_ptr, struct LDKPublicKey val);
	public static native void PaymentParameters_set_payee_pubkey(long this_ptr, byte[] val);
	// struct LDKInvoiceFeatures PaymentParameters_get_features(const struct LDKPaymentParameters *NONNULL_PTR this_ptr);
	public static native long PaymentParameters_get_features(long this_ptr);
	// void PaymentParameters_set_features(struct LDKPaymentParameters *NONNULL_PTR this_ptr, struct LDKInvoiceFeatures val);
	public static native void PaymentParameters_set_features(long this_ptr, long val);
	// struct LDKCVec_RouteHintZ PaymentParameters_get_route_hints(const struct LDKPaymentParameters *NONNULL_PTR this_ptr);
	public static native long[] PaymentParameters_get_route_hints(long this_ptr);
	// void PaymentParameters_set_route_hints(struct LDKPaymentParameters *NONNULL_PTR this_ptr, struct LDKCVec_RouteHintZ val);
	public static native void PaymentParameters_set_route_hints(long this_ptr, long[] val);
	// struct LDKCOption_u64Z PaymentParameters_get_expiry_time(const struct LDKPaymentParameters *NONNULL_PTR this_ptr);
	public static native long PaymentParameters_get_expiry_time(long this_ptr);
	// void PaymentParameters_set_expiry_time(struct LDKPaymentParameters *NONNULL_PTR this_ptr, struct LDKCOption_u64Z val);
	public static native void PaymentParameters_set_expiry_time(long this_ptr, long val);
	// uint32_t PaymentParameters_get_max_total_cltv_expiry_delta(const struct LDKPaymentParameters *NONNULL_PTR this_ptr);
	public static native int PaymentParameters_get_max_total_cltv_expiry_delta(long this_ptr);
	// void PaymentParameters_set_max_total_cltv_expiry_delta(struct LDKPaymentParameters *NONNULL_PTR this_ptr, uint32_t val);
	public static native void PaymentParameters_set_max_total_cltv_expiry_delta(long this_ptr, int val);
	// MUST_USE_RES struct LDKPaymentParameters PaymentParameters_new(struct LDKPublicKey payee_pubkey_arg, struct LDKInvoiceFeatures features_arg, struct LDKCVec_RouteHintZ route_hints_arg, struct LDKCOption_u64Z expiry_time_arg, uint32_t max_total_cltv_expiry_delta_arg);
	public static native long PaymentParameters_new(byte[] payee_pubkey_arg, long features_arg, long[] route_hints_arg, long expiry_time_arg, int max_total_cltv_expiry_delta_arg);
	// uintptr_t PaymentParameters_clone_ptr(LDKPaymentParameters *NONNULL_PTR arg);
	public static native long PaymentParameters_clone_ptr(long arg);
	// struct LDKPaymentParameters PaymentParameters_clone(const struct LDKPaymentParameters *NONNULL_PTR orig);
	public static native long PaymentParameters_clone(long orig);
	// uint64_t PaymentParameters_hash(const struct LDKPaymentParameters *NONNULL_PTR o);
	public static native long PaymentParameters_hash(long o);
	// bool PaymentParameters_eq(const struct LDKPaymentParameters *NONNULL_PTR a, const struct LDKPaymentParameters *NONNULL_PTR b);
	public static native boolean PaymentParameters_eq(long a, long b);
	// struct LDKCVec_u8Z PaymentParameters_write(const struct LDKPaymentParameters *NONNULL_PTR obj);
	public static native byte[] PaymentParameters_write(long obj);
	// struct LDKCResult_PaymentParametersDecodeErrorZ PaymentParameters_read(struct LDKu8slice ser);
	public static native long PaymentParameters_read(byte[] ser);
	// MUST_USE_RES struct LDKPaymentParameters PaymentParameters_from_node_id(struct LDKPublicKey payee_pubkey);
	public static native long PaymentParameters_from_node_id(byte[] payee_pubkey);
	// MUST_USE_RES struct LDKPaymentParameters PaymentParameters_for_keysend(struct LDKPublicKey payee_pubkey);
	public static native long PaymentParameters_for_keysend(byte[] payee_pubkey);
	// void RouteHint_free(struct LDKRouteHint this_obj);
	public static native void RouteHint_free(long this_obj);
	// struct LDKCVec_RouteHintHopZ RouteHint_get_a(const struct LDKRouteHint *NONNULL_PTR this_ptr);
	public static native long[] RouteHint_get_a(long this_ptr);
	// void RouteHint_set_a(struct LDKRouteHint *NONNULL_PTR this_ptr, struct LDKCVec_RouteHintHopZ val);
	public static native void RouteHint_set_a(long this_ptr, long[] val);
	// MUST_USE_RES struct LDKRouteHint RouteHint_new(struct LDKCVec_RouteHintHopZ a_arg);
	public static native long RouteHint_new(long[] a_arg);
	// uintptr_t RouteHint_clone_ptr(LDKRouteHint *NONNULL_PTR arg);
	public static native long RouteHint_clone_ptr(long arg);
	// struct LDKRouteHint RouteHint_clone(const struct LDKRouteHint *NONNULL_PTR orig);
	public static native long RouteHint_clone(long orig);
	// uint64_t RouteHint_hash(const struct LDKRouteHint *NONNULL_PTR o);
	public static native long RouteHint_hash(long o);
	// bool RouteHint_eq(const struct LDKRouteHint *NONNULL_PTR a, const struct LDKRouteHint *NONNULL_PTR b);
	public static native boolean RouteHint_eq(long a, long b);
	// struct LDKCVec_u8Z RouteHint_write(const struct LDKRouteHint *NONNULL_PTR obj);
	public static native byte[] RouteHint_write(long obj);
	// struct LDKCResult_RouteHintDecodeErrorZ RouteHint_read(struct LDKu8slice ser);
	public static native long RouteHint_read(byte[] ser);
	// void RouteHintHop_free(struct LDKRouteHintHop this_obj);
	public static native void RouteHintHop_free(long this_obj);
	// struct LDKPublicKey RouteHintHop_get_src_node_id(const struct LDKRouteHintHop *NONNULL_PTR this_ptr);
	public static native byte[] RouteHintHop_get_src_node_id(long this_ptr);
	// void RouteHintHop_set_src_node_id(struct LDKRouteHintHop *NONNULL_PTR this_ptr, struct LDKPublicKey val);
	public static native void RouteHintHop_set_src_node_id(long this_ptr, byte[] val);
	// uint64_t RouteHintHop_get_short_channel_id(const struct LDKRouteHintHop *NONNULL_PTR this_ptr);
	public static native long RouteHintHop_get_short_channel_id(long this_ptr);
	// void RouteHintHop_set_short_channel_id(struct LDKRouteHintHop *NONNULL_PTR this_ptr, uint64_t val);
	public static native void RouteHintHop_set_short_channel_id(long this_ptr, long val);
	// struct LDKRoutingFees RouteHintHop_get_fees(const struct LDKRouteHintHop *NONNULL_PTR this_ptr);
	public static native long RouteHintHop_get_fees(long this_ptr);
	// void RouteHintHop_set_fees(struct LDKRouteHintHop *NONNULL_PTR this_ptr, struct LDKRoutingFees val);
	public static native void RouteHintHop_set_fees(long this_ptr, long val);
	// uint16_t RouteHintHop_get_cltv_expiry_delta(const struct LDKRouteHintHop *NONNULL_PTR this_ptr);
	public static native short RouteHintHop_get_cltv_expiry_delta(long this_ptr);
	// void RouteHintHop_set_cltv_expiry_delta(struct LDKRouteHintHop *NONNULL_PTR this_ptr, uint16_t val);
	public static native void RouteHintHop_set_cltv_expiry_delta(long this_ptr, short val);
	// struct LDKCOption_u64Z RouteHintHop_get_htlc_minimum_msat(const struct LDKRouteHintHop *NONNULL_PTR this_ptr);
	public static native long RouteHintHop_get_htlc_minimum_msat(long this_ptr);
	// void RouteHintHop_set_htlc_minimum_msat(struct LDKRouteHintHop *NONNULL_PTR this_ptr, struct LDKCOption_u64Z val);
	public static native void RouteHintHop_set_htlc_minimum_msat(long this_ptr, long val);
	// struct LDKCOption_u64Z RouteHintHop_get_htlc_maximum_msat(const struct LDKRouteHintHop *NONNULL_PTR this_ptr);
	public static native long RouteHintHop_get_htlc_maximum_msat(long this_ptr);
	// void RouteHintHop_set_htlc_maximum_msat(struct LDKRouteHintHop *NONNULL_PTR this_ptr, struct LDKCOption_u64Z val);
	public static native void RouteHintHop_set_htlc_maximum_msat(long this_ptr, long val);
	// MUST_USE_RES struct LDKRouteHintHop RouteHintHop_new(struct LDKPublicKey src_node_id_arg, uint64_t short_channel_id_arg, struct LDKRoutingFees fees_arg, uint16_t cltv_expiry_delta_arg, struct LDKCOption_u64Z htlc_minimum_msat_arg, struct LDKCOption_u64Z htlc_maximum_msat_arg);
	public static native long RouteHintHop_new(byte[] src_node_id_arg, long short_channel_id_arg, long fees_arg, short cltv_expiry_delta_arg, long htlc_minimum_msat_arg, long htlc_maximum_msat_arg);
	// uintptr_t RouteHintHop_clone_ptr(LDKRouteHintHop *NONNULL_PTR arg);
	public static native long RouteHintHop_clone_ptr(long arg);
	// struct LDKRouteHintHop RouteHintHop_clone(const struct LDKRouteHintHop *NONNULL_PTR orig);
	public static native long RouteHintHop_clone(long orig);
	// uint64_t RouteHintHop_hash(const struct LDKRouteHintHop *NONNULL_PTR o);
	public static native long RouteHintHop_hash(long o);
	// bool RouteHintHop_eq(const struct LDKRouteHintHop *NONNULL_PTR a, const struct LDKRouteHintHop *NONNULL_PTR b);
	public static native boolean RouteHintHop_eq(long a, long b);
	// struct LDKCVec_u8Z RouteHintHop_write(const struct LDKRouteHintHop *NONNULL_PTR obj);
	public static native byte[] RouteHintHop_write(long obj);
	// struct LDKCResult_RouteHintHopDecodeErrorZ RouteHintHop_read(struct LDKu8slice ser);
	public static native long RouteHintHop_read(byte[] ser);
	// struct LDKCResult_RouteLightningErrorZ find_route(struct LDKPublicKey our_node_pubkey, const struct LDKRouteParameters *NONNULL_PTR route_params, const struct LDKNetworkGraph *NONNULL_PTR network, struct LDKCVec_ChannelDetailsZ *first_hops, struct LDKLogger logger, const struct LDKScore *NONNULL_PTR scorer, const uint8_t (*random_seed_bytes)[32]);
	public static native long find_route(byte[] our_node_pubkey, long route_params, long network, long[] first_hops, long logger, long scorer, byte[] random_seed_bytes);
	// void Score_free(struct LDKScore this_ptr);
	public static native void Score_free(long this_ptr);
	// void LockableScore_free(struct LDKLockableScore this_ptr);
	public static native void LockableScore_free(long this_ptr);
	// void MultiThreadedLockableScore_free(struct LDKMultiThreadedLockableScore this_obj);
	public static native void MultiThreadedLockableScore_free(long this_obj);
	// MUST_USE_RES struct LDKMultiThreadedLockableScore MultiThreadedLockableScore_new(struct LDKScore score);
	public static native long MultiThreadedLockableScore_new(long score);
	// void FixedPenaltyScorer_free(struct LDKFixedPenaltyScorer this_obj);
	public static native void FixedPenaltyScorer_free(long this_obj);
	// uintptr_t FixedPenaltyScorer_clone_ptr(LDKFixedPenaltyScorer *NONNULL_PTR arg);
	public static native long FixedPenaltyScorer_clone_ptr(long arg);
	// struct LDKFixedPenaltyScorer FixedPenaltyScorer_clone(const struct LDKFixedPenaltyScorer *NONNULL_PTR orig);
	public static native long FixedPenaltyScorer_clone(long orig);
	// MUST_USE_RES struct LDKFixedPenaltyScorer FixedPenaltyScorer_with_penalty(uint64_t penalty_msat);
	public static native long FixedPenaltyScorer_with_penalty(long penalty_msat);
	// struct LDKScore FixedPenaltyScorer_as_Score(const struct LDKFixedPenaltyScorer *NONNULL_PTR this_arg);
	public static native long FixedPenaltyScorer_as_Score(long this_arg);
	// struct LDKCVec_u8Z FixedPenaltyScorer_write(const struct LDKFixedPenaltyScorer *NONNULL_PTR obj);
	public static native byte[] FixedPenaltyScorer_write(long obj);
	// struct LDKCResult_FixedPenaltyScorerDecodeErrorZ FixedPenaltyScorer_read(struct LDKu8slice ser, uint64_t arg);
	public static native long FixedPenaltyScorer_read(byte[] ser, long arg);
	// void Scorer_free(struct LDKScorer this_obj);
	public static native void Scorer_free(long this_obj);
	// void ScoringParameters_free(struct LDKScoringParameters this_obj);
	public static native void ScoringParameters_free(long this_obj);
	// uint64_t ScoringParameters_get_base_penalty_msat(const struct LDKScoringParameters *NONNULL_PTR this_ptr);
	public static native long ScoringParameters_get_base_penalty_msat(long this_ptr);
	// void ScoringParameters_set_base_penalty_msat(struct LDKScoringParameters *NONNULL_PTR this_ptr, uint64_t val);
	public static native void ScoringParameters_set_base_penalty_msat(long this_ptr, long val);
	// uint64_t ScoringParameters_get_failure_penalty_msat(const struct LDKScoringParameters *NONNULL_PTR this_ptr);
	public static native long ScoringParameters_get_failure_penalty_msat(long this_ptr);
	// void ScoringParameters_set_failure_penalty_msat(struct LDKScoringParameters *NONNULL_PTR this_ptr, uint64_t val);
	public static native void ScoringParameters_set_failure_penalty_msat(long this_ptr, long val);
	// uint16_t ScoringParameters_get_overuse_penalty_start_1024th(const struct LDKScoringParameters *NONNULL_PTR this_ptr);
	public static native short ScoringParameters_get_overuse_penalty_start_1024th(long this_ptr);
	// void ScoringParameters_set_overuse_penalty_start_1024th(struct LDKScoringParameters *NONNULL_PTR this_ptr, uint16_t val);
	public static native void ScoringParameters_set_overuse_penalty_start_1024th(long this_ptr, short val);
	// uint64_t ScoringParameters_get_overuse_penalty_msat_per_1024th(const struct LDKScoringParameters *NONNULL_PTR this_ptr);
	public static native long ScoringParameters_get_overuse_penalty_msat_per_1024th(long this_ptr);
	// void ScoringParameters_set_overuse_penalty_msat_per_1024th(struct LDKScoringParameters *NONNULL_PTR this_ptr, uint64_t val);
	public static native void ScoringParameters_set_overuse_penalty_msat_per_1024th(long this_ptr, long val);
	// uint64_t ScoringParameters_get_failure_penalty_half_life(const struct LDKScoringParameters *NONNULL_PTR this_ptr);
	public static native long ScoringParameters_get_failure_penalty_half_life(long this_ptr);
	// void ScoringParameters_set_failure_penalty_half_life(struct LDKScoringParameters *NONNULL_PTR this_ptr, uint64_t val);
	public static native void ScoringParameters_set_failure_penalty_half_life(long this_ptr, long val);
	// MUST_USE_RES struct LDKScoringParameters ScoringParameters_new(uint64_t base_penalty_msat_arg, uint64_t failure_penalty_msat_arg, uint16_t overuse_penalty_start_1024th_arg, uint64_t overuse_penalty_msat_per_1024th_arg, uint64_t failure_penalty_half_life_arg);
	public static native long ScoringParameters_new(long base_penalty_msat_arg, long failure_penalty_msat_arg, short overuse_penalty_start_1024th_arg, long overuse_penalty_msat_per_1024th_arg, long failure_penalty_half_life_arg);
	// uintptr_t ScoringParameters_clone_ptr(LDKScoringParameters *NONNULL_PTR arg);
	public static native long ScoringParameters_clone_ptr(long arg);
	// struct LDKScoringParameters ScoringParameters_clone(const struct LDKScoringParameters *NONNULL_PTR orig);
	public static native long ScoringParameters_clone(long orig);
	// struct LDKCVec_u8Z ScoringParameters_write(const struct LDKScoringParameters *NONNULL_PTR obj);
	public static native byte[] ScoringParameters_write(long obj);
	// struct LDKCResult_ScoringParametersDecodeErrorZ ScoringParameters_read(struct LDKu8slice ser);
	public static native long ScoringParameters_read(byte[] ser);
	// MUST_USE_RES struct LDKScorer Scorer_new(struct LDKScoringParameters params);
	public static native long Scorer_new(long params);
	// MUST_USE_RES struct LDKScorer Scorer_default(void);
	public static native long Scorer_default();
	// MUST_USE_RES struct LDKScoringParameters ScoringParameters_default(void);
	public static native long ScoringParameters_default();
	// struct LDKScore Scorer_as_Score(const struct LDKScorer *NONNULL_PTR this_arg);
	public static native long Scorer_as_Score(long this_arg);
	// struct LDKCVec_u8Z Scorer_write(const struct LDKScorer *NONNULL_PTR obj);
	public static native byte[] Scorer_write(long obj);
	// struct LDKCResult_ScorerDecodeErrorZ Scorer_read(struct LDKu8slice ser);
	public static native long Scorer_read(byte[] ser);
	// void ProbabilisticScorer_free(struct LDKProbabilisticScorer this_obj);
	public static native void ProbabilisticScorer_free(long this_obj);
	// void ProbabilisticScoringParameters_free(struct LDKProbabilisticScoringParameters this_obj);
	public static native void ProbabilisticScoringParameters_free(long this_obj);
	// uint64_t ProbabilisticScoringParameters_get_base_penalty_msat(const struct LDKProbabilisticScoringParameters *NONNULL_PTR this_ptr);
	public static native long ProbabilisticScoringParameters_get_base_penalty_msat(long this_ptr);
	// void ProbabilisticScoringParameters_set_base_penalty_msat(struct LDKProbabilisticScoringParameters *NONNULL_PTR this_ptr, uint64_t val);
	public static native void ProbabilisticScoringParameters_set_base_penalty_msat(long this_ptr, long val);
	// uint64_t ProbabilisticScoringParameters_get_liquidity_penalty_multiplier_msat(const struct LDKProbabilisticScoringParameters *NONNULL_PTR this_ptr);
	public static native long ProbabilisticScoringParameters_get_liquidity_penalty_multiplier_msat(long this_ptr);
	// void ProbabilisticScoringParameters_set_liquidity_penalty_multiplier_msat(struct LDKProbabilisticScoringParameters *NONNULL_PTR this_ptr, uint64_t val);
	public static native void ProbabilisticScoringParameters_set_liquidity_penalty_multiplier_msat(long this_ptr, long val);
	// uint64_t ProbabilisticScoringParameters_get_liquidity_offset_half_life(const struct LDKProbabilisticScoringParameters *NONNULL_PTR this_ptr);
	public static native long ProbabilisticScoringParameters_get_liquidity_offset_half_life(long this_ptr);
	// void ProbabilisticScoringParameters_set_liquidity_offset_half_life(struct LDKProbabilisticScoringParameters *NONNULL_PTR this_ptr, uint64_t val);
	public static native void ProbabilisticScoringParameters_set_liquidity_offset_half_life(long this_ptr, long val);
	// uint64_t ProbabilisticScoringParameters_get_amount_penalty_multiplier_msat(const struct LDKProbabilisticScoringParameters *NONNULL_PTR this_ptr);
	public static native long ProbabilisticScoringParameters_get_amount_penalty_multiplier_msat(long this_ptr);
	// void ProbabilisticScoringParameters_set_amount_penalty_multiplier_msat(struct LDKProbabilisticScoringParameters *NONNULL_PTR this_ptr, uint64_t val);
	public static native void ProbabilisticScoringParameters_set_amount_penalty_multiplier_msat(long this_ptr, long val);
	// MUST_USE_RES struct LDKProbabilisticScoringParameters ProbabilisticScoringParameters_new(uint64_t base_penalty_msat_arg, uint64_t liquidity_penalty_multiplier_msat_arg, uint64_t liquidity_offset_half_life_arg, uint64_t amount_penalty_multiplier_msat_arg);
	public static native long ProbabilisticScoringParameters_new(long base_penalty_msat_arg, long liquidity_penalty_multiplier_msat_arg, long liquidity_offset_half_life_arg, long amount_penalty_multiplier_msat_arg);
	// uintptr_t ProbabilisticScoringParameters_clone_ptr(LDKProbabilisticScoringParameters *NONNULL_PTR arg);
	public static native long ProbabilisticScoringParameters_clone_ptr(long arg);
	// struct LDKProbabilisticScoringParameters ProbabilisticScoringParameters_clone(const struct LDKProbabilisticScoringParameters *NONNULL_PTR orig);
	public static native long ProbabilisticScoringParameters_clone(long orig);
	// MUST_USE_RES struct LDKProbabilisticScorer ProbabilisticScorer_new(struct LDKProbabilisticScoringParameters params, const struct LDKNetworkGraph *NONNULL_PTR network_graph);
	public static native long ProbabilisticScorer_new(long params, long network_graph);
	// MUST_USE_RES struct LDKProbabilisticScoringParameters ProbabilisticScoringParameters_default(void);
	public static native long ProbabilisticScoringParameters_default();
	// struct LDKScore ProbabilisticScorer_as_Score(const struct LDKProbabilisticScorer *NONNULL_PTR this_arg);
	public static native long ProbabilisticScorer_as_Score(long this_arg);
	// struct LDKCVec_u8Z ProbabilisticScorer_write(const struct LDKProbabilisticScorer *NONNULL_PTR obj);
	public static native byte[] ProbabilisticScorer_write(long obj);
	// struct LDKCResult_ProbabilisticScorerDecodeErrorZ ProbabilisticScorer_read(struct LDKu8slice ser, struct LDKProbabilisticScoringParameters arg_a, const struct LDKNetworkGraph *NONNULL_PTR arg_b);
	public static native long ProbabilisticScorer_read(byte[] ser, long arg_a, long arg_b);
	// void FilesystemPersister_free(struct LDKFilesystemPersister this_obj);
	public static native void FilesystemPersister_free(long this_obj);
	// MUST_USE_RES struct LDKFilesystemPersister FilesystemPersister_new(struct LDKStr path_to_channel_data);
	public static native long FilesystemPersister_new(String path_to_channel_data);
	// MUST_USE_RES struct LDKStr FilesystemPersister_get_data_dir(const struct LDKFilesystemPersister *NONNULL_PTR this_arg);
	public static native String FilesystemPersister_get_data_dir(long this_arg);
	// MUST_USE_RES struct LDKCResult_NoneErrorZ FilesystemPersister_persist_manager(struct LDKStr data_dir, const struct LDKChannelManager *NONNULL_PTR manager);
	public static native long FilesystemPersister_persist_manager(String data_dir, long manager);
	// MUST_USE_RES struct LDKCResult_NoneErrorZ FilesystemPersister_persist_network_graph(struct LDKStr data_dir, const struct LDKNetworkGraph *NONNULL_PTR network_graph);
	public static native long FilesystemPersister_persist_network_graph(String data_dir, long network_graph);
	// MUST_USE_RES struct LDKCResult_CVec_C2Tuple_BlockHashChannelMonitorZZErrorZ FilesystemPersister_read_channelmonitors(const struct LDKFilesystemPersister *NONNULL_PTR this_arg, struct LDKKeysInterface keys_manager);
	public static native long FilesystemPersister_read_channelmonitors(long this_arg, long keys_manager);
	// struct LDKPersist FilesystemPersister_as_Persist(const struct LDKFilesystemPersister *NONNULL_PTR this_arg);
	public static native long FilesystemPersister_as_Persist(long this_arg);
	// void BackgroundProcessor_free(struct LDKBackgroundProcessor this_obj);
	public static native void BackgroundProcessor_free(long this_obj);
	// void Persister_free(struct LDKPersister this_ptr);
	public static native void Persister_free(long this_ptr);
	// MUST_USE_RES struct LDKBackgroundProcessor BackgroundProcessor_start(struct LDKPersister persister, struct LDKEventHandler event_handler, const struct LDKChainMonitor *NONNULL_PTR chain_monitor, const struct LDKChannelManager *NONNULL_PTR channel_manager, struct LDKNetGraphMsgHandler net_graph_msg_handler, const struct LDKPeerManager *NONNULL_PTR peer_manager, struct LDKLogger logger);
	public static native long BackgroundProcessor_start(long persister, long event_handler, long chain_monitor, long channel_manager, long net_graph_msg_handler, long peer_manager, long logger);
	// MUST_USE_RES struct LDKCResult_NoneErrorZ BackgroundProcessor_join(struct LDKBackgroundProcessor this_arg);
	public static native long BackgroundProcessor_join(long this_arg);
	// MUST_USE_RES struct LDKCResult_NoneErrorZ BackgroundProcessor_stop(struct LDKBackgroundProcessor this_arg);
	public static native long BackgroundProcessor_stop(long this_arg);
	// void ParseError_free(struct LDKParseError this_ptr);
	public static native void ParseError_free(long this_ptr);
	// uintptr_t ParseError_clone_ptr(LDKParseError *NONNULL_PTR arg);
	public static native long ParseError_clone_ptr(long arg);
	// struct LDKParseError ParseError_clone(const struct LDKParseError *NONNULL_PTR orig);
	public static native long ParseError_clone(long orig);
	// struct LDKParseError ParseError_bech32_error(struct LDKBech32Error a);
	public static native long ParseError_bech32_error(long a);
	// struct LDKParseError ParseError_parse_amount_error(struct LDKError a);
	public static native long ParseError_parse_amount_error(int a);
	// struct LDKParseError ParseError_malformed_signature(enum LDKSecp256k1Error a);
	public static native long ParseError_malformed_signature(Secp256k1Error a);
	// struct LDKParseError ParseError_bad_prefix(void);
	public static native long ParseError_bad_prefix();
	// struct LDKParseError ParseError_unknown_currency(void);
	public static native long ParseError_unknown_currency();
	// struct LDKParseError ParseError_unknown_si_prefix(void);
	public static native long ParseError_unknown_si_prefix();
	// struct LDKParseError ParseError_malformed_hrp(void);
	public static native long ParseError_malformed_hrp();
	// struct LDKParseError ParseError_too_short_data_part(void);
	public static native long ParseError_too_short_data_part();
	// struct LDKParseError ParseError_unexpected_end_of_tagged_fields(void);
	public static native long ParseError_unexpected_end_of_tagged_fields();
	// struct LDKParseError ParseError_description_decode_error(struct LDKError a);
	public static native long ParseError_description_decode_error(int a);
	// struct LDKParseError ParseError_padding_error(void);
	public static native long ParseError_padding_error();
	// struct LDKParseError ParseError_integer_overflow_error(void);
	public static native long ParseError_integer_overflow_error();
	// struct LDKParseError ParseError_invalid_seg_wit_program_length(void);
	public static native long ParseError_invalid_seg_wit_program_length();
	// struct LDKParseError ParseError_invalid_pub_key_hash_length(void);
	public static native long ParseError_invalid_pub_key_hash_length();
	// struct LDKParseError ParseError_invalid_script_hash_length(void);
	public static native long ParseError_invalid_script_hash_length();
	// struct LDKParseError ParseError_invalid_recovery_id(void);
	public static native long ParseError_invalid_recovery_id();
	// struct LDKParseError ParseError_invalid_slice_length(struct LDKStr a);
	public static native long ParseError_invalid_slice_length(String a);
	// struct LDKParseError ParseError_skip(void);
	public static native long ParseError_skip();
	// void ParseOrSemanticError_free(struct LDKParseOrSemanticError this_ptr);
	public static native void ParseOrSemanticError_free(long this_ptr);
	// uintptr_t ParseOrSemanticError_clone_ptr(LDKParseOrSemanticError *NONNULL_PTR arg);
	public static native long ParseOrSemanticError_clone_ptr(long arg);
	// struct LDKParseOrSemanticError ParseOrSemanticError_clone(const struct LDKParseOrSemanticError *NONNULL_PTR orig);
	public static native long ParseOrSemanticError_clone(long orig);
	// struct LDKParseOrSemanticError ParseOrSemanticError_parse_error(struct LDKParseError a);
	public static native long ParseOrSemanticError_parse_error(long a);
	// struct LDKParseOrSemanticError ParseOrSemanticError_semantic_error(enum LDKSemanticError a);
	public static native long ParseOrSemanticError_semantic_error(SemanticError a);
	// void Invoice_free(struct LDKInvoice this_obj);
	public static native void Invoice_free(long this_obj);
	// bool Invoice_eq(const struct LDKInvoice *NONNULL_PTR a, const struct LDKInvoice *NONNULL_PTR b);
	public static native boolean Invoice_eq(long a, long b);
	// uintptr_t Invoice_clone_ptr(LDKInvoice *NONNULL_PTR arg);
	public static native long Invoice_clone_ptr(long arg);
	// struct LDKInvoice Invoice_clone(const struct LDKInvoice *NONNULL_PTR orig);
	public static native long Invoice_clone(long orig);
	// void SignedRawInvoice_free(struct LDKSignedRawInvoice this_obj);
	public static native void SignedRawInvoice_free(long this_obj);
	// bool SignedRawInvoice_eq(const struct LDKSignedRawInvoice *NONNULL_PTR a, const struct LDKSignedRawInvoice *NONNULL_PTR b);
	public static native boolean SignedRawInvoice_eq(long a, long b);
	// uintptr_t SignedRawInvoice_clone_ptr(LDKSignedRawInvoice *NONNULL_PTR arg);
	public static native long SignedRawInvoice_clone_ptr(long arg);
	// struct LDKSignedRawInvoice SignedRawInvoice_clone(const struct LDKSignedRawInvoice *NONNULL_PTR orig);
	public static native long SignedRawInvoice_clone(long orig);
	// void RawInvoice_free(struct LDKRawInvoice this_obj);
	public static native void RawInvoice_free(long this_obj);
	// struct LDKRawDataPart RawInvoice_get_data(const struct LDKRawInvoice *NONNULL_PTR this_ptr);
	public static native long RawInvoice_get_data(long this_ptr);
	// void RawInvoice_set_data(struct LDKRawInvoice *NONNULL_PTR this_ptr, struct LDKRawDataPart val);
	public static native void RawInvoice_set_data(long this_ptr, long val);
	// bool RawInvoice_eq(const struct LDKRawInvoice *NONNULL_PTR a, const struct LDKRawInvoice *NONNULL_PTR b);
	public static native boolean RawInvoice_eq(long a, long b);
	// uintptr_t RawInvoice_clone_ptr(LDKRawInvoice *NONNULL_PTR arg);
	public static native long RawInvoice_clone_ptr(long arg);
	// struct LDKRawInvoice RawInvoice_clone(const struct LDKRawInvoice *NONNULL_PTR orig);
	public static native long RawInvoice_clone(long orig);
	// void RawDataPart_free(struct LDKRawDataPart this_obj);
	public static native void RawDataPart_free(long this_obj);
	// struct LDKPositiveTimestamp RawDataPart_get_timestamp(const struct LDKRawDataPart *NONNULL_PTR this_ptr);
	public static native long RawDataPart_get_timestamp(long this_ptr);
	// void RawDataPart_set_timestamp(struct LDKRawDataPart *NONNULL_PTR this_ptr, struct LDKPositiveTimestamp val);
	public static native void RawDataPart_set_timestamp(long this_ptr, long val);
	// bool RawDataPart_eq(const struct LDKRawDataPart *NONNULL_PTR a, const struct LDKRawDataPart *NONNULL_PTR b);
	public static native boolean RawDataPart_eq(long a, long b);
	// uintptr_t RawDataPart_clone_ptr(LDKRawDataPart *NONNULL_PTR arg);
	public static native long RawDataPart_clone_ptr(long arg);
	// struct LDKRawDataPart RawDataPart_clone(const struct LDKRawDataPart *NONNULL_PTR orig);
	public static native long RawDataPart_clone(long orig);
	// void PositiveTimestamp_free(struct LDKPositiveTimestamp this_obj);
	public static native void PositiveTimestamp_free(long this_obj);
	// bool PositiveTimestamp_eq(const struct LDKPositiveTimestamp *NONNULL_PTR a, const struct LDKPositiveTimestamp *NONNULL_PTR b);
	public static native boolean PositiveTimestamp_eq(long a, long b);
	// uintptr_t PositiveTimestamp_clone_ptr(LDKPositiveTimestamp *NONNULL_PTR arg);
	public static native long PositiveTimestamp_clone_ptr(long arg);
	// struct LDKPositiveTimestamp PositiveTimestamp_clone(const struct LDKPositiveTimestamp *NONNULL_PTR orig);
	public static native long PositiveTimestamp_clone(long orig);
	// enum LDKSiPrefix SiPrefix_clone(const enum LDKSiPrefix *NONNULL_PTR orig);
	public static native SiPrefix SiPrefix_clone(long orig);
	// enum LDKSiPrefix SiPrefix_milli(void);
	public static native SiPrefix SiPrefix_milli();
	// enum LDKSiPrefix SiPrefix_micro(void);
	public static native SiPrefix SiPrefix_micro();
	// enum LDKSiPrefix SiPrefix_nano(void);
	public static native SiPrefix SiPrefix_nano();
	// enum LDKSiPrefix SiPrefix_pico(void);
	public static native SiPrefix SiPrefix_pico();
	// bool SiPrefix_eq(const enum LDKSiPrefix *NONNULL_PTR a, const enum LDKSiPrefix *NONNULL_PTR b);
	public static native boolean SiPrefix_eq(long a, long b);
	// MUST_USE_RES uint64_t SiPrefix_multiplier(const enum LDKSiPrefix *NONNULL_PTR this_arg);
	public static native long SiPrefix_multiplier(long this_arg);
	// enum LDKCurrency Currency_clone(const enum LDKCurrency *NONNULL_PTR orig);
	public static native Currency Currency_clone(long orig);
	// enum LDKCurrency Currency_bitcoin(void);
	public static native Currency Currency_bitcoin();
	// enum LDKCurrency Currency_bitcoin_testnet(void);
	public static native Currency Currency_bitcoin_testnet();
	// enum LDKCurrency Currency_regtest(void);
	public static native Currency Currency_regtest();
	// enum LDKCurrency Currency_simnet(void);
	public static native Currency Currency_simnet();
	// enum LDKCurrency Currency_signet(void);
	public static native Currency Currency_signet();
	// uint64_t Currency_hash(const enum LDKCurrency *NONNULL_PTR o);
	public static native long Currency_hash(long o);
	// bool Currency_eq(const enum LDKCurrency *NONNULL_PTR a, const enum LDKCurrency *NONNULL_PTR b);
	public static native boolean Currency_eq(long a, long b);
	// void Sha256_free(struct LDKSha256 this_obj);
	public static native void Sha256_free(long this_obj);
	// uintptr_t Sha256_clone_ptr(LDKSha256 *NONNULL_PTR arg);
	public static native long Sha256_clone_ptr(long arg);
	// struct LDKSha256 Sha256_clone(const struct LDKSha256 *NONNULL_PTR orig);
	public static native long Sha256_clone(long orig);
	// uint64_t Sha256_hash(const struct LDKSha256 *NONNULL_PTR o);
	public static native long Sha256_hash(long o);
	// bool Sha256_eq(const struct LDKSha256 *NONNULL_PTR a, const struct LDKSha256 *NONNULL_PTR b);
	public static native boolean Sha256_eq(long a, long b);
	// void Description_free(struct LDKDescription this_obj);
	public static native void Description_free(long this_obj);
	// uintptr_t Description_clone_ptr(LDKDescription *NONNULL_PTR arg);
	public static native long Description_clone_ptr(long arg);
	// struct LDKDescription Description_clone(const struct LDKDescription *NONNULL_PTR orig);
	public static native long Description_clone(long orig);
	// uint64_t Description_hash(const struct LDKDescription *NONNULL_PTR o);
	public static native long Description_hash(long o);
	// bool Description_eq(const struct LDKDescription *NONNULL_PTR a, const struct LDKDescription *NONNULL_PTR b);
	public static native boolean Description_eq(long a, long b);
	// void PayeePubKey_free(struct LDKPayeePubKey this_obj);
	public static native void PayeePubKey_free(long this_obj);
	// struct LDKPublicKey PayeePubKey_get_a(const struct LDKPayeePubKey *NONNULL_PTR this_ptr);
	public static native byte[] PayeePubKey_get_a(long this_ptr);
	// void PayeePubKey_set_a(struct LDKPayeePubKey *NONNULL_PTR this_ptr, struct LDKPublicKey val);
	public static native void PayeePubKey_set_a(long this_ptr, byte[] val);
	// MUST_USE_RES struct LDKPayeePubKey PayeePubKey_new(struct LDKPublicKey a_arg);
	public static native long PayeePubKey_new(byte[] a_arg);
	// uintptr_t PayeePubKey_clone_ptr(LDKPayeePubKey *NONNULL_PTR arg);
	public static native long PayeePubKey_clone_ptr(long arg);
	// struct LDKPayeePubKey PayeePubKey_clone(const struct LDKPayeePubKey *NONNULL_PTR orig);
	public static native long PayeePubKey_clone(long orig);
	// uint64_t PayeePubKey_hash(const struct LDKPayeePubKey *NONNULL_PTR o);
	public static native long PayeePubKey_hash(long o);
	// bool PayeePubKey_eq(const struct LDKPayeePubKey *NONNULL_PTR a, const struct LDKPayeePubKey *NONNULL_PTR b);
	public static native boolean PayeePubKey_eq(long a, long b);
	// void ExpiryTime_free(struct LDKExpiryTime this_obj);
	public static native void ExpiryTime_free(long this_obj);
	// uintptr_t ExpiryTime_clone_ptr(LDKExpiryTime *NONNULL_PTR arg);
	public static native long ExpiryTime_clone_ptr(long arg);
	// struct LDKExpiryTime ExpiryTime_clone(const struct LDKExpiryTime *NONNULL_PTR orig);
	public static native long ExpiryTime_clone(long orig);
	// uint64_t ExpiryTime_hash(const struct LDKExpiryTime *NONNULL_PTR o);
	public static native long ExpiryTime_hash(long o);
	// bool ExpiryTime_eq(const struct LDKExpiryTime *NONNULL_PTR a, const struct LDKExpiryTime *NONNULL_PTR b);
	public static native boolean ExpiryTime_eq(long a, long b);
	// void MinFinalCltvExpiry_free(struct LDKMinFinalCltvExpiry this_obj);
	public static native void MinFinalCltvExpiry_free(long this_obj);
	// uint64_t MinFinalCltvExpiry_get_a(const struct LDKMinFinalCltvExpiry *NONNULL_PTR this_ptr);
	public static native long MinFinalCltvExpiry_get_a(long this_ptr);
	// void MinFinalCltvExpiry_set_a(struct LDKMinFinalCltvExpiry *NONNULL_PTR this_ptr, uint64_t val);
	public static native void MinFinalCltvExpiry_set_a(long this_ptr, long val);
	// MUST_USE_RES struct LDKMinFinalCltvExpiry MinFinalCltvExpiry_new(uint64_t a_arg);
	public static native long MinFinalCltvExpiry_new(long a_arg);
	// uintptr_t MinFinalCltvExpiry_clone_ptr(LDKMinFinalCltvExpiry *NONNULL_PTR arg);
	public static native long MinFinalCltvExpiry_clone_ptr(long arg);
	// struct LDKMinFinalCltvExpiry MinFinalCltvExpiry_clone(const struct LDKMinFinalCltvExpiry *NONNULL_PTR orig);
	public static native long MinFinalCltvExpiry_clone(long orig);
	// uint64_t MinFinalCltvExpiry_hash(const struct LDKMinFinalCltvExpiry *NONNULL_PTR o);
	public static native long MinFinalCltvExpiry_hash(long o);
	// bool MinFinalCltvExpiry_eq(const struct LDKMinFinalCltvExpiry *NONNULL_PTR a, const struct LDKMinFinalCltvExpiry *NONNULL_PTR b);
	public static native boolean MinFinalCltvExpiry_eq(long a, long b);
	// void Fallback_free(struct LDKFallback this_ptr);
	public static native void Fallback_free(long this_ptr);
	// uintptr_t Fallback_clone_ptr(LDKFallback *NONNULL_PTR arg);
	public static native long Fallback_clone_ptr(long arg);
	// struct LDKFallback Fallback_clone(const struct LDKFallback *NONNULL_PTR orig);
	public static native long Fallback_clone(long orig);
	// struct LDKFallback Fallback_seg_wit_program(struct LDKu5 version, struct LDKCVec_u8Z program);
	public static native long Fallback_seg_wit_program(byte version, byte[] program);
	// struct LDKFallback Fallback_pub_key_hash(struct LDKTwentyBytes a);
	public static native long Fallback_pub_key_hash(byte[] a);
	// struct LDKFallback Fallback_script_hash(struct LDKTwentyBytes a);
	public static native long Fallback_script_hash(byte[] a);
	// uint64_t Fallback_hash(const struct LDKFallback *NONNULL_PTR o);
	public static native long Fallback_hash(long o);
	// bool Fallback_eq(const struct LDKFallback *NONNULL_PTR a, const struct LDKFallback *NONNULL_PTR b);
	public static native boolean Fallback_eq(long a, long b);
	// void InvoiceSignature_free(struct LDKInvoiceSignature this_obj);
	public static native void InvoiceSignature_free(long this_obj);
	// uintptr_t InvoiceSignature_clone_ptr(LDKInvoiceSignature *NONNULL_PTR arg);
	public static native long InvoiceSignature_clone_ptr(long arg);
	// struct LDKInvoiceSignature InvoiceSignature_clone(const struct LDKInvoiceSignature *NONNULL_PTR orig);
	public static native long InvoiceSignature_clone(long orig);
	// bool InvoiceSignature_eq(const struct LDKInvoiceSignature *NONNULL_PTR a, const struct LDKInvoiceSignature *NONNULL_PTR b);
	public static native boolean InvoiceSignature_eq(long a, long b);
	// void PrivateRoute_free(struct LDKPrivateRoute this_obj);
	public static native void PrivateRoute_free(long this_obj);
	// uintptr_t PrivateRoute_clone_ptr(LDKPrivateRoute *NONNULL_PTR arg);
	public static native long PrivateRoute_clone_ptr(long arg);
	// struct LDKPrivateRoute PrivateRoute_clone(const struct LDKPrivateRoute *NONNULL_PTR orig);
	public static native long PrivateRoute_clone(long orig);
	// uint64_t PrivateRoute_hash(const struct LDKPrivateRoute *NONNULL_PTR o);
	public static native long PrivateRoute_hash(long o);
	// bool PrivateRoute_eq(const struct LDKPrivateRoute *NONNULL_PTR a, const struct LDKPrivateRoute *NONNULL_PTR b);
	public static native boolean PrivateRoute_eq(long a, long b);
	// MUST_USE_RES struct LDKC3Tuple_RawInvoice_u832InvoiceSignatureZ SignedRawInvoice_into_parts(struct LDKSignedRawInvoice this_arg);
	public static native long SignedRawInvoice_into_parts(long this_arg);
	// MUST_USE_RES struct LDKRawInvoice SignedRawInvoice_raw_invoice(const struct LDKSignedRawInvoice *NONNULL_PTR this_arg);
	public static native long SignedRawInvoice_raw_invoice(long this_arg);
	// MUST_USE_RES const uint8_t (*SignedRawInvoice_hash(const struct LDKSignedRawInvoice *NONNULL_PTR this_arg))[32];
	public static native byte[] SignedRawInvoice_hash(long this_arg);
	// MUST_USE_RES struct LDKInvoiceSignature SignedRawInvoice_signature(const struct LDKSignedRawInvoice *NONNULL_PTR this_arg);
	public static native long SignedRawInvoice_signature(long this_arg);
	// MUST_USE_RES struct LDKCResult_PayeePubKeyErrorZ SignedRawInvoice_recover_payee_pub_key(const struct LDKSignedRawInvoice *NONNULL_PTR this_arg);
	public static native long SignedRawInvoice_recover_payee_pub_key(long this_arg);
	// MUST_USE_RES bool SignedRawInvoice_check_signature(const struct LDKSignedRawInvoice *NONNULL_PTR this_arg);
	public static native boolean SignedRawInvoice_check_signature(long this_arg);
	// MUST_USE_RES struct LDKThirtyTwoBytes RawInvoice_hash(const struct LDKRawInvoice *NONNULL_PTR this_arg);
	public static native byte[] RawInvoice_hash(long this_arg);
	// MUST_USE_RES struct LDKSha256 RawInvoice_payment_hash(const struct LDKRawInvoice *NONNULL_PTR this_arg);
	public static native long RawInvoice_payment_hash(long this_arg);
	// MUST_USE_RES struct LDKDescription RawInvoice_description(const struct LDKRawInvoice *NONNULL_PTR this_arg);
	public static native long RawInvoice_description(long this_arg);
	// MUST_USE_RES struct LDKPayeePubKey RawInvoice_payee_pub_key(const struct LDKRawInvoice *NONNULL_PTR this_arg);
	public static native long RawInvoice_payee_pub_key(long this_arg);
	// MUST_USE_RES struct LDKSha256 RawInvoice_description_hash(const struct LDKRawInvoice *NONNULL_PTR this_arg);
	public static native long RawInvoice_description_hash(long this_arg);
	// MUST_USE_RES struct LDKExpiryTime RawInvoice_expiry_time(const struct LDKRawInvoice *NONNULL_PTR this_arg);
	public static native long RawInvoice_expiry_time(long this_arg);
	// MUST_USE_RES struct LDKMinFinalCltvExpiry RawInvoice_min_final_cltv_expiry(const struct LDKRawInvoice *NONNULL_PTR this_arg);
	public static native long RawInvoice_min_final_cltv_expiry(long this_arg);
	// MUST_USE_RES struct LDKThirtyTwoBytes RawInvoice_payment_secret(const struct LDKRawInvoice *NONNULL_PTR this_arg);
	public static native byte[] RawInvoice_payment_secret(long this_arg);
	// MUST_USE_RES struct LDKInvoiceFeatures RawInvoice_features(const struct LDKRawInvoice *NONNULL_PTR this_arg);
	public static native long RawInvoice_features(long this_arg);
	// MUST_USE_RES struct LDKCVec_PrivateRouteZ RawInvoice_private_routes(const struct LDKRawInvoice *NONNULL_PTR this_arg);
	public static native long[] RawInvoice_private_routes(long this_arg);
	// MUST_USE_RES struct LDKCOption_u64Z RawInvoice_amount_pico_btc(const struct LDKRawInvoice *NONNULL_PTR this_arg);
	public static native long RawInvoice_amount_pico_btc(long this_arg);
	// MUST_USE_RES enum LDKCurrency RawInvoice_currency(const struct LDKRawInvoice *NONNULL_PTR this_arg);
	public static native Currency RawInvoice_currency(long this_arg);
	// MUST_USE_RES struct LDKCResult_PositiveTimestampCreationErrorZ PositiveTimestamp_from_unix_timestamp(uint64_t unix_seconds);
	public static native long PositiveTimestamp_from_unix_timestamp(long unix_seconds);
	// MUST_USE_RES struct LDKCResult_PositiveTimestampCreationErrorZ PositiveTimestamp_from_system_time(uint64_t time);
	public static native long PositiveTimestamp_from_system_time(long time);
	// MUST_USE_RES struct LDKCResult_PositiveTimestampCreationErrorZ PositiveTimestamp_from_duration_since_epoch(uint64_t duration);
	public static native long PositiveTimestamp_from_duration_since_epoch(long duration);
	// MUST_USE_RES uint64_t PositiveTimestamp_as_unix_timestamp(const struct LDKPositiveTimestamp *NONNULL_PTR this_arg);
	public static native long PositiveTimestamp_as_unix_timestamp(long this_arg);
	// MUST_USE_RES uint64_t PositiveTimestamp_as_duration_since_epoch(const struct LDKPositiveTimestamp *NONNULL_PTR this_arg);
	public static native long PositiveTimestamp_as_duration_since_epoch(long this_arg);
	// MUST_USE_RES uint64_t PositiveTimestamp_as_time(const struct LDKPositiveTimestamp *NONNULL_PTR this_arg);
	public static native long PositiveTimestamp_as_time(long this_arg);
	// MUST_USE_RES struct LDKSignedRawInvoice Invoice_into_signed_raw(struct LDKInvoice this_arg);
	public static native long Invoice_into_signed_raw(long this_arg);
	// MUST_USE_RES struct LDKCResult_NoneSemanticErrorZ Invoice_check_signature(const struct LDKInvoice *NONNULL_PTR this_arg);
	public static native long Invoice_check_signature(long this_arg);
	// MUST_USE_RES struct LDKCResult_InvoiceSemanticErrorZ Invoice_from_signed(struct LDKSignedRawInvoice signed_invoice);
	public static native long Invoice_from_signed(long signed_invoice);
	// MUST_USE_RES uint64_t Invoice_timestamp(const struct LDKInvoice *NONNULL_PTR this_arg);
	public static native long Invoice_timestamp(long this_arg);
	// MUST_USE_RES uint64_t Invoice_duration_since_epoch(const struct LDKInvoice *NONNULL_PTR this_arg);
	public static native long Invoice_duration_since_epoch(long this_arg);
	// MUST_USE_RES const uint8_t (*Invoice_payment_hash(const struct LDKInvoice *NONNULL_PTR this_arg))[32];
	public static native byte[] Invoice_payment_hash(long this_arg);
	// MUST_USE_RES struct LDKPublicKey Invoice_payee_pub_key(const struct LDKInvoice *NONNULL_PTR this_arg);
	public static native byte[] Invoice_payee_pub_key(long this_arg);
	// MUST_USE_RES const uint8_t (*Invoice_payment_secret(const struct LDKInvoice *NONNULL_PTR this_arg))[32];
	public static native byte[] Invoice_payment_secret(long this_arg);
	// MUST_USE_RES struct LDKInvoiceFeatures Invoice_features(const struct LDKInvoice *NONNULL_PTR this_arg);
	public static native long Invoice_features(long this_arg);
	// MUST_USE_RES struct LDKPublicKey Invoice_recover_payee_pub_key(const struct LDKInvoice *NONNULL_PTR this_arg);
	public static native byte[] Invoice_recover_payee_pub_key(long this_arg);
	// MUST_USE_RES uint64_t Invoice_expiry_time(const struct LDKInvoice *NONNULL_PTR this_arg);
	public static native long Invoice_expiry_time(long this_arg);
	// MUST_USE_RES bool Invoice_is_expired(const struct LDKInvoice *NONNULL_PTR this_arg);
	public static native boolean Invoice_is_expired(long this_arg);
	// MUST_USE_RES bool Invoice_would_expire(const struct LDKInvoice *NONNULL_PTR this_arg, uint64_t at_time);
	public static native boolean Invoice_would_expire(long this_arg, long at_time);
	// MUST_USE_RES uint64_t Invoice_min_final_cltv_expiry(const struct LDKInvoice *NONNULL_PTR this_arg);
	public static native long Invoice_min_final_cltv_expiry(long this_arg);
	// MUST_USE_RES struct LDKCVec_PrivateRouteZ Invoice_private_routes(const struct LDKInvoice *NONNULL_PTR this_arg);
	public static native long[] Invoice_private_routes(long this_arg);
	// MUST_USE_RES struct LDKCVec_RouteHintZ Invoice_route_hints(const struct LDKInvoice *NONNULL_PTR this_arg);
	public static native long[] Invoice_route_hints(long this_arg);
	// MUST_USE_RES enum LDKCurrency Invoice_currency(const struct LDKInvoice *NONNULL_PTR this_arg);
	public static native Currency Invoice_currency(long this_arg);
	// MUST_USE_RES struct LDKCOption_u64Z Invoice_amount_milli_satoshis(const struct LDKInvoice *NONNULL_PTR this_arg);
	public static native long Invoice_amount_milli_satoshis(long this_arg);
	// MUST_USE_RES struct LDKCResult_DescriptionCreationErrorZ Description_new(struct LDKStr description);
	public static native long Description_new(String description);
	// MUST_USE_RES struct LDKStr Description_into_inner(struct LDKDescription this_arg);
	public static native String Description_into_inner(long this_arg);
	// MUST_USE_RES struct LDKExpiryTime ExpiryTime_from_seconds(uint64_t seconds);
	public static native long ExpiryTime_from_seconds(long seconds);
	// MUST_USE_RES struct LDKExpiryTime ExpiryTime_from_duration(uint64_t duration);
	public static native long ExpiryTime_from_duration(long duration);
	// MUST_USE_RES uint64_t ExpiryTime_as_seconds(const struct LDKExpiryTime *NONNULL_PTR this_arg);
	public static native long ExpiryTime_as_seconds(long this_arg);
	// MUST_USE_RES uint64_t ExpiryTime_as_duration(const struct LDKExpiryTime *NONNULL_PTR this_arg);
	public static native long ExpiryTime_as_duration(long this_arg);
	// MUST_USE_RES struct LDKCResult_PrivateRouteCreationErrorZ PrivateRoute_new(struct LDKRouteHint hops);
	public static native long PrivateRoute_new(long hops);
	// MUST_USE_RES struct LDKRouteHint PrivateRoute_into_inner(struct LDKPrivateRoute this_arg);
	public static native long PrivateRoute_into_inner(long this_arg);
	// enum LDKCreationError CreationError_clone(const enum LDKCreationError *NONNULL_PTR orig);
	public static native CreationError CreationError_clone(long orig);
	// enum LDKCreationError CreationError_description_too_long(void);
	public static native CreationError CreationError_description_too_long();
	// enum LDKCreationError CreationError_route_too_long(void);
	public static native CreationError CreationError_route_too_long();
	// enum LDKCreationError CreationError_timestamp_out_of_bounds(void);
	public static native CreationError CreationError_timestamp_out_of_bounds();
	// enum LDKCreationError CreationError_invalid_amount(void);
	public static native CreationError CreationError_invalid_amount();
	// enum LDKCreationError CreationError_missing_route_hints(void);
	public static native CreationError CreationError_missing_route_hints();
	// bool CreationError_eq(const enum LDKCreationError *NONNULL_PTR a, const enum LDKCreationError *NONNULL_PTR b);
	public static native boolean CreationError_eq(long a, long b);
	// struct LDKStr CreationError_to_str(const enum LDKCreationError *NONNULL_PTR o);
	public static native String CreationError_to_str(long o);
	// enum LDKSemanticError SemanticError_clone(const enum LDKSemanticError *NONNULL_PTR orig);
	public static native SemanticError SemanticError_clone(long orig);
	// enum LDKSemanticError SemanticError_no_payment_hash(void);
	public static native SemanticError SemanticError_no_payment_hash();
	// enum LDKSemanticError SemanticError_multiple_payment_hashes(void);
	public static native SemanticError SemanticError_multiple_payment_hashes();
	// enum LDKSemanticError SemanticError_no_description(void);
	public static native SemanticError SemanticError_no_description();
	// enum LDKSemanticError SemanticError_multiple_descriptions(void);
	public static native SemanticError SemanticError_multiple_descriptions();
	// enum LDKSemanticError SemanticError_no_payment_secret(void);
	public static native SemanticError SemanticError_no_payment_secret();
	// enum LDKSemanticError SemanticError_multiple_payment_secrets(void);
	public static native SemanticError SemanticError_multiple_payment_secrets();
	// enum LDKSemanticError SemanticError_invalid_features(void);
	public static native SemanticError SemanticError_invalid_features();
	// enum LDKSemanticError SemanticError_invalid_recovery_id(void);
	public static native SemanticError SemanticError_invalid_recovery_id();
	// enum LDKSemanticError SemanticError_invalid_signature(void);
	public static native SemanticError SemanticError_invalid_signature();
	// enum LDKSemanticError SemanticError_imprecise_amount(void);
	public static native SemanticError SemanticError_imprecise_amount();
	// bool SemanticError_eq(const enum LDKSemanticError *NONNULL_PTR a, const enum LDKSemanticError *NONNULL_PTR b);
	public static native boolean SemanticError_eq(long a, long b);
	// struct LDKStr SemanticError_to_str(const enum LDKSemanticError *NONNULL_PTR o);
	public static native String SemanticError_to_str(long o);
	// void SignOrCreationError_free(struct LDKSignOrCreationError this_ptr);
	public static native void SignOrCreationError_free(long this_ptr);
	// uintptr_t SignOrCreationError_clone_ptr(LDKSignOrCreationError *NONNULL_PTR arg);
	public static native long SignOrCreationError_clone_ptr(long arg);
	// struct LDKSignOrCreationError SignOrCreationError_clone(const struct LDKSignOrCreationError *NONNULL_PTR orig);
	public static native long SignOrCreationError_clone(long orig);
	// struct LDKSignOrCreationError SignOrCreationError_sign_error(void);
	public static native long SignOrCreationError_sign_error();
	// struct LDKSignOrCreationError SignOrCreationError_creation_error(enum LDKCreationError a);
	public static native long SignOrCreationError_creation_error(CreationError a);
	// bool SignOrCreationError_eq(const struct LDKSignOrCreationError *NONNULL_PTR a, const struct LDKSignOrCreationError *NONNULL_PTR b);
	public static native boolean SignOrCreationError_eq(long a, long b);
	// struct LDKStr SignOrCreationError_to_str(const struct LDKSignOrCreationError *NONNULL_PTR o);
	public static native String SignOrCreationError_to_str(long o);
	// void InvoicePayer_free(struct LDKInvoicePayer this_obj);
	public static native void InvoicePayer_free(long this_obj);
	// void Payer_free(struct LDKPayer this_ptr);
	public static native void Payer_free(long this_ptr);
	// void Router_free(struct LDKRouter this_ptr);
	public static native void Router_free(long this_ptr);
	// void RetryAttempts_free(struct LDKRetryAttempts this_obj);
	public static native void RetryAttempts_free(long this_obj);
	// uintptr_t RetryAttempts_get_a(const struct LDKRetryAttempts *NONNULL_PTR this_ptr);
	public static native long RetryAttempts_get_a(long this_ptr);
	// void RetryAttempts_set_a(struct LDKRetryAttempts *NONNULL_PTR this_ptr, uintptr_t val);
	public static native void RetryAttempts_set_a(long this_ptr, long val);
	// MUST_USE_RES struct LDKRetryAttempts RetryAttempts_new(uintptr_t a_arg);
	public static native long RetryAttempts_new(long a_arg);
	// uintptr_t RetryAttempts_clone_ptr(LDKRetryAttempts *NONNULL_PTR arg);
	public static native long RetryAttempts_clone_ptr(long arg);
	// struct LDKRetryAttempts RetryAttempts_clone(const struct LDKRetryAttempts *NONNULL_PTR orig);
	public static native long RetryAttempts_clone(long orig);
	// bool RetryAttempts_eq(const struct LDKRetryAttempts *NONNULL_PTR a, const struct LDKRetryAttempts *NONNULL_PTR b);
	public static native boolean RetryAttempts_eq(long a, long b);
	// uint64_t RetryAttempts_hash(const struct LDKRetryAttempts *NONNULL_PTR o);
	public static native long RetryAttempts_hash(long o);
	// void PaymentError_free(struct LDKPaymentError this_ptr);
	public static native void PaymentError_free(long this_ptr);
	// uintptr_t PaymentError_clone_ptr(LDKPaymentError *NONNULL_PTR arg);
	public static native long PaymentError_clone_ptr(long arg);
	// struct LDKPaymentError PaymentError_clone(const struct LDKPaymentError *NONNULL_PTR orig);
	public static native long PaymentError_clone(long orig);
	// struct LDKPaymentError PaymentError_invoice(struct LDKStr a);
	public static native long PaymentError_invoice(String a);
	// struct LDKPaymentError PaymentError_routing(struct LDKLightningError a);
	public static native long PaymentError_routing(long a);
	// struct LDKPaymentError PaymentError_sending(struct LDKPaymentSendFailure a);
	public static native long PaymentError_sending(long a);
	// MUST_USE_RES struct LDKInvoicePayer InvoicePayer_new(struct LDKPayer payer, struct LDKRouter router, const struct LDKMultiThreadedLockableScore *NONNULL_PTR scorer, struct LDKLogger logger, struct LDKEventHandler event_handler, struct LDKRetryAttempts retry_attempts);
	public static native long InvoicePayer_new(long payer, long router, long scorer, long logger, long event_handler, long retry_attempts);
	// MUST_USE_RES struct LDKCResult_PaymentIdPaymentErrorZ InvoicePayer_pay_invoice(const struct LDKInvoicePayer *NONNULL_PTR this_arg, const struct LDKInvoice *NONNULL_PTR invoice);
	public static native long InvoicePayer_pay_invoice(long this_arg, long invoice);
	// MUST_USE_RES struct LDKCResult_PaymentIdPaymentErrorZ InvoicePayer_pay_zero_value_invoice(const struct LDKInvoicePayer *NONNULL_PTR this_arg, const struct LDKInvoice *NONNULL_PTR invoice, uint64_t amount_msats);
	public static native long InvoicePayer_pay_zero_value_invoice(long this_arg, long invoice, long amount_msats);
	// MUST_USE_RES struct LDKCResult_PaymentIdPaymentErrorZ InvoicePayer_pay_pubkey(const struct LDKInvoicePayer *NONNULL_PTR this_arg, struct LDKPublicKey pubkey, struct LDKThirtyTwoBytes payment_preimage, uint64_t amount_msats, uint32_t final_cltv_expiry_delta);
	public static native long InvoicePayer_pay_pubkey(long this_arg, byte[] pubkey, byte[] payment_preimage, long amount_msats, int final_cltv_expiry_delta);
	// void InvoicePayer_remove_cached_payment(const struct LDKInvoicePayer *NONNULL_PTR this_arg, const uint8_t (*payment_hash)[32]);
	public static native void InvoicePayer_remove_cached_payment(long this_arg, byte[] payment_hash);
	// struct LDKEventHandler InvoicePayer_as_EventHandler(const struct LDKInvoicePayer *NONNULL_PTR this_arg);
	public static native long InvoicePayer_as_EventHandler(long this_arg);
	// struct LDKCResult_InvoiceSignOrCreationErrorZ create_phantom_invoice(struct LDKCOption_u64Z amt_msat, struct LDKStr description, struct LDKThirtyTwoBytes payment_hash, struct LDKThirtyTwoBytes payment_secret, struct LDKCVec_PhantomRouteHintsZ phantom_route_hints, struct LDKKeysInterface keys_manager, enum LDKCurrency network);
	public static native long create_phantom_invoice(long amt_msat, String description, byte[] payment_hash, byte[] payment_secret, long[] phantom_route_hints, long keys_manager, Currency network);
	// struct LDKCResult_InvoiceSignOrCreationErrorZ create_phantom_invoice_with_description_hash(struct LDKCOption_u64Z amt_msat, struct LDKSha256 description_hash, struct LDKThirtyTwoBytes payment_hash, struct LDKThirtyTwoBytes payment_secret, struct LDKCVec_PhantomRouteHintsZ phantom_route_hints, struct LDKKeysInterface keys_manager, enum LDKCurrency network);
	public static native long create_phantom_invoice_with_description_hash(long amt_msat, long description_hash, byte[] payment_hash, byte[] payment_secret, long[] phantom_route_hints, long keys_manager, Currency network);
	// struct LDKCResult_InvoiceSignOrCreationErrorZ create_invoice_from_channelmanager(const struct LDKChannelManager *NONNULL_PTR channelmanager, struct LDKKeysInterface keys_manager, enum LDKCurrency network, struct LDKCOption_u64Z amt_msat, struct LDKStr description);
	public static native long create_invoice_from_channelmanager(long channelmanager, long keys_manager, Currency network, long amt_msat, String description);
	// struct LDKCResult_InvoiceSignOrCreationErrorZ create_invoice_from_channelmanager_with_description_hash(const struct LDKChannelManager *NONNULL_PTR channelmanager, struct LDKKeysInterface keys_manager, enum LDKCurrency network, struct LDKCOption_u64Z amt_msat, struct LDKSha256 description_hash);
	public static native long create_invoice_from_channelmanager_with_description_hash(long channelmanager, long keys_manager, Currency network, long amt_msat, long description_hash);
	// struct LDKCResult_InvoiceSignOrCreationErrorZ create_invoice_from_channelmanager_with_description_hash_and_duration_since_epoch(const struct LDKChannelManager *NONNULL_PTR channelmanager, struct LDKKeysInterface keys_manager, enum LDKCurrency network, struct LDKCOption_u64Z amt_msat, struct LDKSha256 description_hash, uint64_t duration_since_epoch);
	public static native long create_invoice_from_channelmanager_with_description_hash_and_duration_since_epoch(long channelmanager, long keys_manager, Currency network, long amt_msat, long description_hash, long duration_since_epoch);
	// struct LDKCResult_InvoiceSignOrCreationErrorZ create_invoice_from_channelmanager_and_duration_since_epoch(const struct LDKChannelManager *NONNULL_PTR channelmanager, struct LDKKeysInterface keys_manager, enum LDKCurrency network, struct LDKCOption_u64Z amt_msat, struct LDKStr description, uint64_t duration_since_epoch);
	public static native long create_invoice_from_channelmanager_and_duration_since_epoch(long channelmanager, long keys_manager, Currency network, long amt_msat, String description, long duration_since_epoch);
	// void DefaultRouter_free(struct LDKDefaultRouter this_obj);
	public static native void DefaultRouter_free(long this_obj);
	// MUST_USE_RES struct LDKDefaultRouter DefaultRouter_new(const struct LDKNetworkGraph *NONNULL_PTR network_graph, struct LDKLogger logger, struct LDKThirtyTwoBytes random_seed_bytes);
	public static native long DefaultRouter_new(long network_graph, long logger, byte[] random_seed_bytes);
	// struct LDKRouter DefaultRouter_as_Router(const struct LDKDefaultRouter *NONNULL_PTR this_arg);
	public static native long DefaultRouter_as_Router(long this_arg);
	// struct LDKPayer ChannelManager_as_Payer(const struct LDKChannelManager *NONNULL_PTR this_arg);
	public static native long ChannelManager_as_Payer(long this_arg);
	// struct LDKCResult_SiPrefixParseErrorZ SiPrefix_from_str(struct LDKStr s);
	public static native long SiPrefix_from_str(String s);
	// struct LDKCResult_InvoiceParseOrSemanticErrorZ Invoice_from_str(struct LDKStr s);
	public static native long Invoice_from_str(String s);
	// struct LDKCResult_SignedRawInvoiceParseErrorZ SignedRawInvoice_from_str(struct LDKStr s);
	public static native long SignedRawInvoice_from_str(String s);
	// struct LDKStr ParseError_to_str(const struct LDKParseError *NONNULL_PTR o);
	public static native String ParseError_to_str(long o);
	// struct LDKStr ParseOrSemanticError_to_str(const struct LDKParseOrSemanticError *NONNULL_PTR o);
	public static native String ParseOrSemanticError_to_str(long o);
	// struct LDKStr Invoice_to_str(const struct LDKInvoice *NONNULL_PTR o);
	public static native String Invoice_to_str(long o);
	// struct LDKStr SignedRawInvoice_to_str(const struct LDKSignedRawInvoice *NONNULL_PTR o);
	public static native String SignedRawInvoice_to_str(long o);
	// struct LDKStr Currency_to_str(const enum LDKCurrency *NONNULL_PTR o);
	public static native String Currency_to_str(long o);
	// struct LDKStr SiPrefix_to_str(const enum LDKSiPrefix *NONNULL_PTR o);
	public static native String SiPrefix_to_str(long o);
}
