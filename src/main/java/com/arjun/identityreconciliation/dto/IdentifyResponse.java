package com.arjun.identityreconciliation.dto;

import java.util.List;
import java.util.Set;

public class IdentifyResponse {
    private Long primaryContactId;
    private Set<String> emails;
    private Set<String> phoneNumbers;
    private List<Long> secondaryContactIds;

    public IdentifyResponse() {
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof IdentifyResponse)) return false;
        final IdentifyResponse other = (IdentifyResponse) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$primaryContactId = this.getPrimaryContactId();
        final Object other$primaryContactId = other.getPrimaryContactId();
        if (this$primaryContactId == null ? other$primaryContactId != null : !this$primaryContactId.equals(other$primaryContactId))
            return false;
        final Object this$emails = this.getEmails();
        final Object other$emails = other.getEmails();
        if (this$emails == null ? other$emails != null : !this$emails.equals(other$emails)) return false;
        final Object this$phoneNumbers = this.getPhoneNumbers();
        final Object other$phoneNumbers = other.getPhoneNumbers();
        if (this$phoneNumbers == null ? other$phoneNumbers != null : !this$phoneNumbers.equals(other$phoneNumbers))
            return false;
        final Object this$secondaryContactIds = this.getSecondaryContactIds();
        final Object other$secondaryContactIds = other.getSecondaryContactIds();
        if (this$secondaryContactIds == null ? other$secondaryContactIds != null : !this$secondaryContactIds.equals(other$secondaryContactIds))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof IdentifyResponse;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $primaryContactId = this.getPrimaryContactId();
        result = result * PRIME + ($primaryContactId == null ? 43 : $primaryContactId.hashCode());
        final Object $emails = this.getEmails();
        result = result * PRIME + ($emails == null ? 43 : $emails.hashCode());
        final Object $phoneNumbers = this.getPhoneNumbers();
        result = result * PRIME + ($phoneNumbers == null ? 43 : $phoneNumbers.hashCode());
        final Object $secondaryContactIds = this.getSecondaryContactIds();
        result = result * PRIME + ($secondaryContactIds == null ? 43 : $secondaryContactIds.hashCode());
        return result;
    }

    public String toString() {
        return "IdentifyResponse(primaryContactId=" + this.getPrimaryContactId() + ", emails=" + this.getEmails() + ", phoneNumbers=" + this.getPhoneNumbers() + ", secondaryContactIds=" + this.getSecondaryContactIds() + ")";
    }

    public Long getPrimaryContactId() {
        return this.primaryContactId;
    }

    public Set<String> getEmails() {
        return this.emails;
    }

    public Set<String> getPhoneNumbers() {
        return this.phoneNumbers;
    }

    public List<Long> getSecondaryContactIds() {
        return this.secondaryContactIds;
    }

    public void setPrimaryContactId(Long primaryContactId) {
        this.primaryContactId = primaryContactId;
    }

    public void setEmails(Set<String> emails) {
        this.emails = emails;
    }

    public void setPhoneNumbers(Set<String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public void setSecondaryContactIds(List<Long> secondaryContactIds) {
        this.secondaryContactIds = secondaryContactIds;
    }
}
