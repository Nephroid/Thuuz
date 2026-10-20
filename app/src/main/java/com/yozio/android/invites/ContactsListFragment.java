package com.yozio.android.invites;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.CursorAdapter;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.TextAppearanceSpan;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AlphabetIndexer;
import android.widget.CheckBox;
import android.widget.FilterQueryProvider;
import android.widget.ImageView;
import android.widget.SectionIndexer;
import android.widget.TextView;
import com.yozio.android.Constants;
import com.yozio.android.R;
import com.yozio.android.YozioService;
import com.yozio.android.helpers.ImageLoader;
import com.yozio.android.helpers.Utils;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;

/* loaded from: classes.dex */
public class ContactsListFragment extends ListFragment implements LoaderManager.LoaderCallbacks<Cursor>, AdapterView.OnItemClickListener {
    private static int totalCount = -1;
    private ContactsAdapter _contactsAdapter;
    private ImageLoader _imageLoader;
    private OnContactsInteractionListener _onContactSelectedListener;
    private String _searchTerm;
    private HashMap<String, HashMap<String, String>> _selectedPhones = new HashMap<>();
    private HashMap<String, HashMap<String, String>> _selectedEmails = new HashMap<>();

    /* loaded from: classes.dex */
    public interface OnContactsInteractionListener {
        void onContactSelected(int i);
    }

    @Override // android.support.v4.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this._contactsAdapter = new ContactsAdapter(getActivity());
        if (bundle != null) {
            this._searchTerm = bundle.getString("query");
            this._selectedPhones = (HashMap) bundle.getSerializable("_selectedPhones");
            this._selectedEmails = (HashMap) bundle.getSerializable("_selectedEmails");
        }
        this._imageLoader = new ImageLoader(getActivity(), getListPreferredItemHeight()) { // from class: com.yozio.android.invites.ContactsListFragment.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.yozio.android.helpers.ImageLoader
            public Bitmap processBitmap(Object obj) {
                return ContactsListFragment.this.loadContactPhotoThumbnail((String) obj, getImageSize());
            }
        };
        this._imageLoader.setLoadingImage(R.drawable.yozio_contact_picture_holo_light);
        this._imageLoader.addImageCache(getActivity().getSupportFragmentManager(), 0.1f);
        this._contactsAdapter.setFilterQueryProvider(new FilterQueryProvider() { // from class: com.yozio.android.invites.ContactsListFragment.2
            @Override // android.widget.FilterQueryProvider
            public Cursor runQuery(CharSequence charSequence) {
                return ContactsListFragment.this.doSearch(charSequence.toString());
            }
        });
    }

    public Cursor doSearch(CharSequence charSequence) {
        this._searchTerm = charSequence.toString();
        YozioService.getInstance().trackEvent(Constants.YOZIO_EVENT_NAME_USER_CONTACTS_LIST_SEARCH_TERM, this._searchTerm, null, false);
        getLoaderManager().restartLoader(1, null, this);
        return this._contactsAdapter.getCursor();
    }

    @Override // android.support.v4.app.ListFragment, android.support.v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.yozio_contacts_list_fragment, viewGroup, false);
    }

    @Override // android.support.v4.app.Fragment
    @TargetApi(11)
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        setListAdapter(this._contactsAdapter);
        getListView().setFastScrollEnabled(true);
        if (Utils.hasHoneycomb()) {
            getListView().setVerticalScrollbarPosition(2);
            getListView().setFastScrollAlwaysVisible(true);
        }
        getListView().setOnItemClickListener(this);
        getListView().setOnScrollListener(new AbsListView.OnScrollListener() { // from class: com.yozio.android.invites.ContactsListFragment.3
            @Override // android.widget.AbsListView.OnScrollListener
            public void onScrollStateChanged(AbsListView absListView, int i) {
                if (i == 2) {
                    ContactsListFragment.this._imageLoader.setPauseWork(true);
                } else {
                    ContactsListFragment.this._imageLoader.setPauseWork(false);
                }
                YozioService.getInstance().trackEvent(Constants.YOZIO_EVENT_NAME_USER_CONTACTS_LIST_SCROLL, null, null, false);
            }

            @Override // android.widget.AbsListView.OnScrollListener
            public void onScroll(AbsListView absListView, int i, int i2, int i3) {
            }
        });
        getLoaderManager().initLoader(1, null, this);
    }

    @Override // android.support.v4.app.Fragment
    public void onPause() {
        super.onPause();
        this._imageLoader.setPauseWork(false);
    }

    @Override // android.support.v4.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (!TextUtils.isEmpty(this._searchTerm)) {
            bundle.putString("query", this._searchTerm);
        }
        bundle.putSerializable("_selectedPhones", this._selectedPhones);
        bundle.putSerializable("_selectedEmails", this._selectedEmails);
    }

    @Override // android.support.v4.app.LoaderManager.LoaderCallbacks
    @TargetApi(11)
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        Uri uri;
        String str;
        if (i == 1) {
            if (this._searchTerm == null) {
                uri = ContactsQuery.CONTENT_URI;
                str = ContactsQuery.SELECTION;
            } else {
                uri = ContactsQuery.CONTENT_URI;
                str = String.valueOf(ContactsQuery.SELECTION) + " AND " + (Utils.hasHoneycomb() ? "display_name" : "display_name") + " like '%" + this._searchTerm + "%'";
            }
            return new CursorLoader(getActivity(), uri, ContactsQuery.PROJECTION, str, null, ContactsQuery.SORT_ORDER);
        }
        YozioService.log(Constants.LOG_LEVEL.ERROR, "ContactsListFragment - onCreateLoader - incorrect ID provided (" + i + ")");
        return null;
    }

    @Override // android.support.v4.app.LoaderManager.LoaderCallbacks
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        if (loader.getId() == 1) {
            this._contactsAdapter.swapCursor(cursor);
            if (totalCount == -1) {
                totalCount = cursor.getCount();
            }
            ((TextView) getListView().getEmptyView()).setText(R.string.yozio_no_contacts);
        }
    }

    @Override // android.support.v4.app.LoaderManager.LoaderCallbacks
    public void onLoaderReset(Loader<Cursor> loader) {
        if (loader.getId() == 1) {
            this._contactsAdapter.swapCursor(null);
        }
    }

    public void setSearchQuery(String str) {
        this._searchTerm = str;
        this._contactsAdapter.getFilter().filter(this._searchTerm);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.support.v4.app.Fragment
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            this._onContactSelectedListener = (OnContactsInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(String.valueOf(activity.toString()) + " must implement OnContactsInteractionListener.");
        }
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        Cursor cursor = this._contactsAdapter.getCursor();
        cursor.moveToPosition(i);
        HashMap<String, String> contact = ContactsHelper.getContact(cursor);
        String str = contact.get("type");
        String str2 = contact.get("value");
        CheckBox checkBox = (CheckBox) view.findViewById(R.id.yozio_contact_checkbox);
        if (checkBox.isChecked()) {
            checkBox.setChecked(false);
            if (str.equalsIgnoreCase("email")) {
                this._selectedEmails.remove(str2);
                YozioService.getInstance().trackEvent(Constants.YOZIO_EVENT_NAME_USER_CONTACTS_LIST_UNSELECT_INDIVIDUAL_EMAIL, null, null, false);
            } else {
                this._selectedPhones.remove(str2);
                YozioService.getInstance().trackEvent(Constants.YOZIO_EVENT_NAME_USER_CONTACTS_LIST_UNSELECT_INDIVIDUAL_PHONE, null, null, false);
            }
        } else {
            checkBox.setChecked(true);
            if (str.equalsIgnoreCase("email")) {
                this._selectedEmails.put(str2, contact);
                YozioService.getInstance().trackEvent(Constants.YOZIO_EVENT_NAME_USER_CONTACTS_LIST_SELECT_INDIVIDUAL_EMAIL, null, null, false);
            } else {
                this._selectedPhones.put(str2, contact);
                YozioService.getInstance().trackEvent(Constants.YOZIO_EVENT_NAME_USER_CONTACTS_LIST_SELECT_INDIVIDUAL_PHONE, null, null, false);
            }
        }
        this._contactsAdapter.notifyDataSetChanged();
        this._onContactSelectedListener.onContactSelected(getSelectedCount());
    }

    public void selectAllEntries() {
        Cursor cursor = this._contactsAdapter.getCursor();
        if (cursor.getCount() != 0) {
            int position = cursor.getPosition();
            cursor.moveToFirst();
            do {
                HashMap<String, String> contact = ContactsHelper.getContact(cursor);
                String str = contact.get("type");
                String str2 = contact.get("value");
                if (str.equalsIgnoreCase("email")) {
                    this._selectedEmails.put(str2, contact);
                } else {
                    this._selectedPhones.put(str2, contact);
                }
            } while (cursor.moveToNext());
            cursor.moveToPosition(position);
            this._contactsAdapter.notifyDataSetChanged();
        }
    }

    public void deSelectAllEntries() {
        getListView().clearChoices();
        this._selectedEmails.clear();
        this._selectedPhones.clear();
        this._contactsAdapter.notifyDataSetChanged();
    }

    private int getListPreferredItemHeight() {
        TypedValue typedValue = new TypedValue();
        getActivity().getTheme().resolveAttribute(android.R.attr.listPreferredItemHeight, typedValue, true);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return (int) typedValue.getDimension(displayMetrics);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Bitmap loadContactPhotoThumbnail(String str, int i) {
        AssetFileDescriptor assetFileDescriptor;
        Bitmap bitmap = null;
        if (isAdded()) {
            try {
                if (getActivity() != null) {
                    try {
                        assetFileDescriptor = getActivity().getContentResolver().openAssetFileDescriptor(Utils.hasHoneycomb() ? Uri.parse(str) : Uri.withAppendedPath(Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_URI, str), "photo"), "r");
                        try {
                            FileDescriptor fileDescriptor = assetFileDescriptor.getFileDescriptor();
                            if (fileDescriptor != null) {
                                bitmap = ImageLoader.decodeSampledBitmapFromDescriptor(fileDescriptor, i, i);
                                if (assetFileDescriptor != null) {
                                    try {
                                        assetFileDescriptor.close();
                                    } catch (IOException e) {
                                    }
                                }
                            } else if (assetFileDescriptor != null) {
                                try {
                                    assetFileDescriptor.close();
                                } catch (IOException e2) {
                                }
                            }
                        } catch (FileNotFoundException e3) {
                            e = e3;
                            YozioService.log(Constants.LOG_LEVEL.DEBUG, "ContactsListFragment - Contact photo thumbnail not found for contact " + str + ": " + e.toString());
                            if (assetFileDescriptor != null) {
                                try {
                                    assetFileDescriptor.close();
                                } catch (IOException e4) {
                                }
                            }
                            return bitmap;
                        }
                    } catch (FileNotFoundException e5) {
                        e = e5;
                        assetFileDescriptor = null;
                    } catch (Throwable th) {
                        assetFileDescriptor = null;
                        th = th;
                        if (assetFileDescriptor != null) {
                            try {
                                assetFileDescriptor.close();
                            } catch (IOException e6) {
                            }
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }
        return bitmap;
    }

    /* loaded from: classes.dex */
    public class ContactsAdapter extends CursorAdapter implements SectionIndexer {
        private final AlphabetIndexer _alphabetIndexer;
        private final TextAppearanceSpan _highlightTextSpan;
        private final LayoutInflater _inflater;

        public ContactsAdapter(Context context) {
            super(context, (Cursor) null, 0);
            this._inflater = LayoutInflater.from(context);
            this._alphabetIndexer = new AlphabetIndexer(null, 11, context.getString(R.string.yozio_alphabet));
            this._highlightTextSpan = new TextAppearanceSpan(ContactsListFragment.this.getActivity(), R.style.yozioSearchTextHiglight);
        }

        private int indexOfSearchQuery(String str) {
            if (TextUtils.isEmpty(ContactsListFragment.this._searchTerm)) {
                return -1;
            }
            return str.toLowerCase(Locale.getDefault()).indexOf(ContactsListFragment.this._searchTerm.toLowerCase(Locale.getDefault()));
        }

        @Override // android.support.v4.widget.CursorAdapter
        public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
            View inflate = this._inflater.inflate(R.layout.yozio_contacts_list_item, viewGroup, false);
            ViewHolder viewHolder = new ViewHolder(this, null);
            viewHolder.text1 = (TextView) inflate.findViewById(android.R.id.text1);
            viewHolder.text2 = (TextView) inflate.findViewById(android.R.id.text2);
            viewHolder.icon = (ImageView) inflate.findViewById(android.R.id.icon);
            viewHolder.contactLabel = (TextView) inflate.findViewById(R.id.yozio_contact_label);
            viewHolder.contactValue = (TextView) inflate.findViewById(R.id.yozio_contact_value);
            viewHolder.checkbox = (CheckBox) inflate.findViewById(R.id.yozio_contact_checkbox);
            inflate.setTag(viewHolder);
            return inflate;
        }

        @Override // android.support.v4.widget.CursorAdapter
        public void bindView(View view, Context context, Cursor cursor) {
            ViewHolder viewHolder = (ViewHolder) view.getTag();
            String string = cursor.getString(3);
            String string2 = cursor.getString(2);
            int indexOfSearchQuery = indexOfSearchQuery(string2);
            if (indexOfSearchQuery == -1) {
                viewHolder.text1.setText(string2);
                if (TextUtils.isEmpty(ContactsListFragment.this._searchTerm)) {
                    viewHolder.text2.setVisibility(8);
                } else {
                    viewHolder.text2.setVisibility(0);
                }
            } else {
                SpannableString spannableString = new SpannableString(string2);
                spannableString.setSpan(this._highlightTextSpan, indexOfSearchQuery, ContactsListFragment.this._searchTerm.length() + indexOfSearchQuery, 0);
                viewHolder.text1.setText(spannableString);
                viewHolder.text2.setVisibility(8);
            }
            String string3 = cursor.getString(4);
            String string4 = cursor.getString(5);
            String string5 = cursor.getString(6);
            String string6 = cursor.getString(7);
            String string7 = cursor.getString(8);
            String string8 = cursor.getString(9);
            String string9 = cursor.getString(10);
            if (string3.equalsIgnoreCase("vnd.android.cursor.item/email_v2")) {
                viewHolder.contactLabel.setText(ContactsHelper.getEmailType(string5, string6));
                viewHolder.contactValue.setText(string4);
                viewHolder.checkbox.setChecked(ContactsListFragment.this._selectedEmails.containsKey(string4));
            } else {
                viewHolder.contactLabel.setText(ContactsHelper.getPhoneType(string8, string9));
                viewHolder.contactValue.setText(string7);
                viewHolder.checkbox.setChecked(ContactsListFragment.this._selectedPhones.containsKey(string7));
            }
            ContactsListFragment.this._imageLoader.loadImage(string, viewHolder.icon);
        }

        @Override // android.support.v4.widget.CursorAdapter
        public Cursor swapCursor(Cursor cursor) {
            this._alphabetIndexer.setCursor(cursor);
            return super.swapCursor(cursor);
        }

        @Override // android.support.v4.widget.CursorAdapter, android.widget.Adapter
        public int getCount() {
            if (getCursor() == null) {
                return 0;
            }
            return super.getCount();
        }

        @Override // android.widget.SectionIndexer
        public Object[] getSections() {
            return this._alphabetIndexer.getSections();
        }

        @Override // android.widget.SectionIndexer
        public int getPositionForSection(int i) {
            if (getCursor() == null) {
                return 0;
            }
            return this._alphabetIndexer.getPositionForSection(i);
        }

        @Override // android.widget.SectionIndexer
        public int getSectionForPosition(int i) {
            if (getCursor() == null) {
                return 0;
            }
            return this._alphabetIndexer.getSectionForPosition(i);
        }

        /* loaded from: classes.dex */
        class ViewHolder {
            CheckBox checkbox;
            TextView contactLabel;
            TextView contactValue;
            ImageView icon;
            TextView text1;
            TextView text2;

            private ViewHolder() {
            }

            /* synthetic */ ViewHolder(ContactsAdapter contactsAdapter, ViewHolder viewHolder) {
                this();
            }
        }
    }

    public ContactsAdapter getContactsAdapter() {
        return this._contactsAdapter;
    }

    public String getSearchQuery() {
        return this._searchTerm;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public int getCurrentSelectableCount() {
        Cursor cursor = this._contactsAdapter.getCursor();
        if (cursor.getCount() == 0) {
            return 0;
        }
        HashMap hashMap = new HashMap();
        int position = cursor.getPosition();
        cursor.moveToFirst();
        do {
            HashMap<String, String> contact = ContactsHelper.getContact(cursor);
            hashMap.put(contact.get("value"), contact);
        } while (cursor.moveToNext());
        cursor.moveToPosition(position);
        return hashMap.keySet().size();
    }

    public int getSelectedCount() {
        return this._selectedEmails.size() + this._selectedPhones.size();
    }

    public HashMap<String, HashMap<String, String>> getSelectedEmails() {
        return this._selectedEmails;
    }

    public HashMap<String, HashMap<String, String>> getSelectedPhones() {
        return this._selectedPhones;
    }
}
